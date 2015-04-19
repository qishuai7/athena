
package com.alibaba.athena_base;

import java.util.Date;
import java.util.List;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalDb;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.bitmap.core.BitmapDisplayConfig;
import net.tsz.afinal.bitmap.display.Displayer;
import net.tsz.afinal.http.AjaxCallBack;

import com.alibaba.athena_base.R;
import com.alibaba.athena_base.db.orm.UserInfo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ALAfinalActivity extends FinalActivity {

    @ViewInject(id = R.id.main_btn_inject, click = "btnInjectClick")
    Button mBtnInject;
    @ViewInject(id = R.id.main_btn_db, click = "btnDbClick")
    Button mBtnDb;
    @ViewInject(id = R.id.main_btn_http, click = "btnHttpClick")
    Button mBtnHttp;
    @ViewInject(id = R.id.main_btn_bitmap, click = "btnBitmapClick")
    Button mBtnBitmap;
    @ViewInject(id = R.id.main_tv_content)
    TextView mTvContent;
    @ViewInject(id = R.id.main_iv_pic)
    ImageView mIvPic;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afinal);
    }

    public void btnInjectClick(View v)
    {
        mTvContent.setText("Afinal开源框架中 \nFinalActivity的使用！");
    }

    public void btnDbClick(View v)
    {
        FinalDb db = FinalDb.create(this);

        UserInfo user = new UserInfo();
        user.setEmail("afinal@tsz.net");
        user.setName("探索者");
        user.setRegisterDate(new Date());

        db.save(user);

        List<UserInfo> userList = db.findAll(UserInfo.class);// 查询所有的用户

        mTvContent.setText("Afinal开源框架中 \n FinalDB的使用！ size = " + userList.size());
    }

    public void btnHttpClick(View v) {
        FinalHttp fh = new FinalHttp();
        fh.get("http://www.baidu.com", new AjaxCallBack<String>() {

            @Override
            public void onLoading(long count, long current) { // 每1秒钟自动被回调一次
                mTvContent.setText(current + "/" + count);
            }

            @Override
            public void onSuccess(String t) {
                mTvContent.setText(t == null ? "null" : t);
            }

            @Override
            public void onStart() {
                // 开始http请求的时候回调
            }

            @Override
            public void onFailure(Throwable t, String strMsg) {
                // 加载失败的时候回调
            }
        });
    }
    
    // 图片管理
    private FinalBitmap mFb = null; 
    public void btnBitmapClick(View v){
        if(mFb == null){
            // 初始化FinalBitmap模块
            mFb = FinalBitmap.create(this);
        }
        
        // 设置加载图片
        mFb.configLoadingImage(null);
        mFb.configDisplayer(new Displayer() {
            
            @Override
            public void loadFailDisplay(ImageView arg0, Bitmap arg1) {
                
                
            }
            
            @Override
            public void loadCompletedisplay(ImageView arg0, Bitmap arg1, BitmapDisplayConfig arg2) {
               
                
            }
        });
        mFb.display(mIvPic, "http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf4ce383e84f1d3572c10dfcf83.jpg");
        
      //设置图片的uri地址
//        String uri = "http://avatar.csdn.net/C/6/8/1_bz419927089.jpg";
//        //初始化加载中时显示的图片
//        Bitmap loadingBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
//        //初始化加载失败显示的图片
//        Bitmap failBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
//        //第一种方式：image为要显示图片的控件
//        mFb.display(mIvPic, uri);
//        //第二种方式：loadingBitmap为正在加载时显示的图片
//        mFb.display(mIvPic, uri, loadingBitmap);
//        //第三种方式：config为FinalBitmap的配置对象，可以配置显示的动画，大小，加载失败图片等等
//        BitmapDisplayConfig config = new BitmapDisplayConfig();
//        config.setLoadingBitmap(loadingBitmap);
//        mFb.display(mIvPic, uri, config);
//        //第四种方式：loadingBitmap为加载中图片，failBitmap为加载失败图片
//        mFb.display(mIvPic, uri, loadingBitmap, failBitmap);
//        //第五种方式：我们也可以设置加载图片的大小
//        mFb.display(mIvPic, uri, 100, 100);
//        //第六种方式：设置加载图片的大小以及加载中和加载失败的图片
//        mFb.display(mIvPic, uri, 100, 100, loadingBitmap, failBitmap);
        
        
        Toast.makeText(this, "bitmapclick = " + mFb, Toast.LENGTH_SHORT).show();
    }
}
