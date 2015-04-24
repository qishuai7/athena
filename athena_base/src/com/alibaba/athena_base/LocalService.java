
package com.alibaba.athena_base;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class LocalService extends Service {
    private static final String TAG = "LocalService";
    private IBinder binder = new LocalService.LocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        
        return binder;
    }
    
    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate");
        // do something
        
        super.onCreate();
    }
    
    @Override
    public void onStart(Intent intent, int startId) {
        Log.i(TAG, "onStart");
        
        super.onStart(intent, startId);
    }
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");
        
        return super.onStartCommand(intent, flags, startId);
    }
    
    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        
        super.onDestroy();
    }
    
    public class LocalBinder extends Binder {
        LocalService getService() {
            return LocalService.this;
        }
    }

}
