package com.alibaba.athena_base.network;

/**
 * 模拟网络环境  
 * @author shuai.qi
 *
 */
public class NetOperator {
    public void operator(){  
        try {  
            //休眠1秒  
            Thread.sleep(1000);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
    }  
}
