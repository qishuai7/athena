
package com.alibaba.athena_base;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.alibaba.athena_base.base.BaseActivity;

@EActivity(R.layout.activity_service)
public class ALServiceActivity extends BaseActivity {
    private static final String TAG = "ALServiceActivity";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Click(R.id.service_start_btn)
    void myStartService() {
        // 启动服务
        Intent intent = new Intent(this, LocalService.class);
        startService(intent);
    }

    LocalService localService = null;

    @Click(R.id.service_bind_btn)
    void myStartBindService() {
        // 用bindService方法启动服务
        Intent intent = new Intent(this, LocalService.class);
        bindService(intent, new ServiceConnection() {

            @Override
            public void onServiceConnected(ComponentName componentName, IBinder binder) {
                // 调用bindService方法启动服务时候，如果服务需要与activity交互，
                // 则通过onBind方法返回IBinder并返回当前本地服务
                localService = ((LocalService.LocalBinder) binder).getService();
                // 这里可以提示用户,或者调用服务的某些方法
                Log.i(TAG, "onServiceConnected . ");
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                localService = null;
                // 这里可以提示用户
                Log.i(TAG, "onServiceDisconnected . ");
            }
        }, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, LocalService.class);
        stopService(intent);
        super.onBackPressed();
    }
    
}
