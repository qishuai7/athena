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
import com.alibaba.athena_base.network.HttpConnectionManager;

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
        
        HttpConnectionManager.getInstance().sendRequest("http://www.baidu.com", "get");
        
    }
    
    @Click(R.id.http_async)
    void httpAsyncClicked(){
        startActivity(new Intent(this,ALHttpAsyncActivity_.class));
    }
  
  
}
