package com.alibaba.athena_base;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import com.alibaba.athena_base.task.ProgressBarAsyncTask;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

@EActivity(R.layout.activity_http_async)
public class ALHttpAsyncActivity extends Activity{
    @ViewById(R.id.http_async_tv)
    TextView mHttpAsyncTv;
    @ViewById(R.id.http_async_pbar)
    ProgressBar mHttpAsyncPbar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Click(R.id.http_async_btn)
    void startAsyncBtnClick(){
        ProgressBarAsyncTask task = new ProgressBarAsyncTask(mHttpAsyncTv,mHttpAsyncPbar);
        task.execute(500);
    }
}
