package com.alibaba.athena_base;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.alibaba.athena_base.base.BaseActivity;

@EActivity(R.layout.activity_main)
public class ALMainActivity extends BaseActivity{
    
    @ViewById(R.id.main_btn_annotation)
    Button mAnnotationBtn;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("QS", "annotation onCreate()...");
    }
    
    @Click(R.id.main_btn_annotation)
    public void annotationClicked(){
        Log.i("QS", "annotation clicked...");
        Intent intent = new Intent(this, ALAnnotationActivity_.class);
        startActivity(intent);
    }
}
