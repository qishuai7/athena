package com.alibaba.athena_base;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import com.alibaba.athena_base.vo.PersionInfo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

@EActivity(R.layout.activity_jni)
public class ALJniActivity extends Activity{
    private PersionInfo mPersionInfo = null;
 
    @ViewById(R.id.jni_btn_result)
    TextView mJinResultTv;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }
    
    @AfterViews
    void init(){
        String jniTestString = stringTestNdk();
        String jniDecString = decompressAndUnzip("test");
        Log.d("JNI", "log = " +  jniTestString + "; decompressAndUnzip = "+ jniDecString);
        mJinResultTv.setText(jniTestString);
    }
    
    public native String  stringTestNdk ();  
    public native String  decompressAndUnzip(String content);
    //public native int intTestNdk(int number);
   // public native PersionInfo objTestNdk();
    
    static {  
        System.loadLibrary("core-1.0.0");
    }  
}
