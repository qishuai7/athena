
package com.alibaba.athena_base.network;

import java.security.KeyStore;
import java.util.HashMap;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.http.HttpVersion;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.SyncBasicHttpContext;

/**
 * 网络请求管理类，实例化一次
 * 
 * @author shuai.qi
 */
public class HttpConnectionManager {
    private static final int MAX_CONNECTIONS = 5;// 2;
    private static final int MAX_THREAD = 5;// 2;
    private static final int SOCKET_TIMEOUT = 15 * 1000;

    private static int mMaxConnections = MAX_CONNECTIONS;
    private static int mSocketTimeout = SOCKET_TIMEOUT;

    private final DefaultHttpClient mHttpClient;
    private final HttpContext mHttpContext;
    private ThreadPoolExecutor mThreadPool;
    
    public static HttpConnectionManager instance = null;
    public static HttpConnectionManager getInstance(){
        if(instance == null){
            instance = new HttpConnectionManager();
        }
        
        return instance;
    }

    private HttpConnectionManager() {
        SSLSocketFactory sf;
        try { // 解决Android2.2 “javax.net.ssl.SSLException: Not trusted server certificate”的异常
            KeyStore trustStore;
            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);
            sf = new MySSLSocketFactory(trustStore);
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        } catch (Exception e) {
            sf = SSLSocketFactory.getSocketFactory();
            // Log.i("tang", "SSL 设置异常!!!");
            e.printStackTrace();
        }

        BasicHttpParams httpParams = new BasicHttpParams();

        ConnManagerParams.setTimeout(httpParams, mSocketTimeout);
        ConnManagerParams.setMaxConnectionsPerRoute(httpParams, new ConnPerRouteBean(mMaxConnections));
        ConnManagerParams.setMaxTotalConnections(httpParams, MAX_CONNECTIONS);

        HttpConnectionParams.setSoTimeout(httpParams, mSocketTimeout);
        HttpConnectionParams.setConnectionTimeout(httpParams, mSocketTimeout);
        HttpConnectionParams.setTcpNoDelay(httpParams, true);
        HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);

        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", sf, 443));
        ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager(httpParams, schemeRegistry);

        mHttpContext = new SyncBasicHttpContext(new BasicHttpContext());
        mHttpClient = new DefaultHttpClient(cm, httpParams);
        mThreadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(MAX_THREAD);
        // mRequestMap = new WeakHashMap<Integer, List<ReqData>>();
        // mClientHeaderMap = new HashMap<String, String>();

    }
    
    
    public void sendRequest(String url , String contentType){
        Future<?> request = mThreadPool.submit(new BaseNetTask(mHttpClient,url,contentType));
        
    }

}
