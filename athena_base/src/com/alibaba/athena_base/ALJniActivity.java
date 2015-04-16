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
        setContentView(R.layout.activity_main);
        
        String jniString = stringTestNdk();
        Log.d("QS", jniString);
        
        mPersionInfo = new PersionInfo(1,"xiaoming","man");
        
        //int number =  intTestNdk(8);
        
        Toast.makeText(this, jniString, Toast.LENGTH_LONG).show();
    }
    
    public native String  stringTestNdk ();  
    //public native int intTestNdk(int number);
   // public native PersionInfo objTestNdk();
    
    static {  
        System.loadLibrary("testNDK");  
    }  
}
