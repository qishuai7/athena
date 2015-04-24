package com.alibaba.athena_base.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.CharArrayBuffer;

import android.util.Log;



public class BaseNetTask implements Runnable{
    private DefaultHttpClient mClient;
    private String mUrl;
    private String mContentType ;
    
    
    
    public BaseNetTask(DefaultHttpClient httpClient, String url, String contentType) {
        this.mClient = httpClient;
        this.mUrl = url;
        this.mContentType = contentType;
    }

    public void run() {
        HttpUriRequest hur = new HttpGet(mUrl);
        try {
            HttpResponse response =  mClient.execute(hur);
            int statusCode = response.getStatusLine().getStatusCode();
            HttpEntity entity = null;
            HttpEntity temp = response.getEntity();
            if (temp != null) {
                entity = new BufferedHttpEntity(temp);
            }
            
            
            String responseStr = toString(entity, null);
            Log.i("QS", "statusCode = " + statusCode);
            Log.i("QS", "responseStr = " + responseStr);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String toString(
            final HttpEntity entity, final String defaultCharset) throws IOException, ParseException {
        if (entity == null) {
            throw new IllegalArgumentException("HTTP entity may not be null");
        }
        InputStream instream = entity.getContent();
        if (instream == null) {
            return "";
        }
        if (entity.getContentLength() > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("HTTP entity too large to be buffered in memory");
        }
        int i = (int) entity.getContentLength();
        if (i < 0) {
            i = 4096;
        }
        String charset = getContentCharSet(entity);
        if (charset == null) {
            charset = defaultCharset;
        }
        if (charset == null) {
            charset = HTTP.DEFAULT_CONTENT_CHARSET;
        }
        Reader reader = new InputStreamReader(instream, charset);
        CharArrayBuffer buffer = new CharArrayBuffer(i);
        try {
            char[] tmp = new char[1024];
            int l;
            while ((l = reader.read(tmp)) != -1) {
                buffer.append(tmp, 0, l);
            }
        } finally {
            reader.close();
        }
        return buffer.toString();
    }
    
    
    public static String getContentCharSet(final HttpEntity entity)
            throws ParseException {

        if (entity == null) {
            throw new IllegalArgumentException("HTTP entity may not be null");
        }
        String charset = null;
        if (entity.getContentType() != null) {
            HeaderElement values[] = entity.getContentType().getElements();
            if (values.length > 0) {
                NameValuePair param = values[0].getParameterByName("charset");
                if (param != null) {
                    charset = param.getValue();
                }
            }
        }
        return charset;
    }
}

