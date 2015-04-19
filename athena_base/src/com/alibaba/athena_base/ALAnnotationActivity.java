
package com.alibaba.athena_base;

import java.util.concurrent.TimeUnit;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.LongClick;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.ColorRes;
import org.androidannotations.annotations.res.StringRes;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.athena_base.base.BaseActivity;

/**
 * 注解测试
 * 
 * @author shuai.qi
 */
@EActivity(R.layout.activity_annotation)
public class ALAnnotationActivity extends BaseActivity {

    @ViewById(R.id.an_input_et)
    EditText mInputEd;
    @ViewById(R.id.an_input_tv)
    TextView mInputTv;
    @ViewById(R.id.an_input_btn)
    Button mInputBtn;

    @StringRes(R.string.hello_format)
    String mHelloFormat;

    @ColorRes
    int androidColor;

    @SystemService
    WindowManager windowManager;

    @SystemService
    NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        windowManager.getDefaultDisplay();
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
    }

    @Click(R.id.an_input_btn)
    void anInputBtnClicked() {
        String inputName = mInputEd.getText().toString();
        setProgressBarIndeterminateVisibility(true);

        someBackgroundWork(inputName, 3);
    }

    @LongClick(R.id.an_long_click_btn)
    void anLongClicked() {
        Toast.makeText(this, "long clicked or use @Extra()...", Toast.LENGTH_LONG).show();
    }
    
    @Click(R.id.an_start_act_btn)
    void anStartActClicked(){
        startActivity(new Intent(this,ALAnnotationListActivity_.class));
    }

    @Background
    void someBackgroundWork(String name, long timeToDoSomeLongComputation) {
        try {
            TimeUnit.SECONDS.sleep(timeToDoSomeLongComputation);
        } catch (InterruptedException e) {
        }

        String message = String.format(mHelloFormat, name);

        updateUi(message, androidColor);

        // 发送通知
        showNotificationsDelayed();
    }

    @UiThread(delay = 2000)
    void showNotificationsDelayed() {
        Notification notification = new Notification(R.drawable.load1, "Hello !", 0);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(), 0);
        notification.setLatestEventInfo(getApplicationContext(), "My notification", "Hello World!", contentIntent);
        notificationManager.notify(1, notification);
    }

    @UiThread
    void updateUi(String message, int color) {
        setProgressBarIndeterminateVisibility(false);
        mInputTv.setText(message);
        mInputTv.setTextColor(color);
    }

}
