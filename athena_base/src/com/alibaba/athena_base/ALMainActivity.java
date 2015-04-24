package com.alibaba.athena_base;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.athena_base.base.BaseActivity;

@EActivity(R.layout.activity_main)
public class ALMainActivity extends BaseActivity{
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("QS", "annotation onCreate()...");
    }
    
    @Click(R.id.main_annotation_btn)
    public void annotationClicked(){
        Log.i("QS", "annotation clicked...");
        Intent intent = new Intent(this, ALAnnotationActivity_.class);
        startActivity(intent);
    }
    
    @Click(R.id.main_http_btn)
    public void httpClicked(){
        Intent intent = new Intent(this, ALHttpActivity_.class);
        startActivity(intent);
    }
    
    @Click(R.id.main_http_jni)
    public void jniClicked(){
        Intent intent = new Intent(this, ALJniActivity_.class);
        startActivity(intent);
    }
    
    @Click(R.id.main_service)
    public void serviceClicked(){
        Intent intent = new Intent(this, ALServiceActivity_.class);
        startActivity(intent);
    }
}
