package com.alibaba.athena_base;

import java.io.IOException;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.athena_base.base.BaseActivity;

@EActivity(R.layout.activity_http)
public class ALHttpActivity extends BaseActivity{
    
    @ViewById(R.id.http_get_btn)
    Button mHttpGetBtn;
    @ViewById(R.id.http_post_btn)
    Button mHttpPostBtn;
    @ViewById(R.id.http_result_tv)
    TextView mResultTv;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }
    
    @Click(R.id.http_get_btn)
    void httpGetBtnClicked(){
        Log.i("QS", "start get http..."); 
        (new Thread(new MyThread())).start();
    }
    
    @Click(R.id.http_async)
    void httpAsyncClicked(){
        startActivity(new Intent(this,ALHttpAsyncActivity_.class));
    }
    
    
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            mResultTv.setText("msg = " + msg.what);
        }
        
    };
    
    class MyThread implements Runnable {   
        public void run() {  
             while (true) {    
                     
                  Message message = new Message();   
                  message.what = 110;   
                    
                  ALHttpActivity.this.mHandler.sendMessage(message);   
                  try {   
                       Thread.sleep(3000);    
                  } catch (InterruptedException e) {   
                       Thread.currentThread().interrupt();   
                  }   
             }   
        }   
   }   

    public void testGetHttp(){
        HttpGet getMethod = new HttpGet("http://www.baidu.com"); 
        HttpClient client = new DefaultHttpClient();
        
        try {
            HttpResponse response = client.execute(getMethod);
            
            Log.i("QS", "resCode = " + response.getStatusLine().getStatusCode()); //获取响应码  
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            Log.i("QS", "result = " + result);//获取服务器响应内容  
            
            mResultTv.setText("Get = " + result);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
