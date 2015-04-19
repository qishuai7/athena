package com.alibaba.athena_base;

import com.alibaba.athena_base.vo.PersionInfo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class ALJniActivity extends Activity{
    private PersionInfo mPersionInfo = null;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afinal);
        
        String jniTestString = stringTestNdk();
        String jniDecString = decompressAndUnzip("test");
        Log.d("JNI", "log = " +  jniDecString);
        
        Toast.makeText(this, jniDecString, Toast.LENGTH_LONG).show();
    }
    
    public native String  stringTestNdk ();  
    public native String  decompressAndUnzip(String content);
    //public native int intTestNdk(int number);
   // public native PersionInfo objTestNdk();
    
    static {  
        System.loadLibrary("core-1.0.0");
    }  
}
