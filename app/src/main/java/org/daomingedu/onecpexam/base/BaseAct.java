package org.daomingedu.onecpexam.base;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import android.view.KeyEvent;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;

import androidx.appcompat.app.AppCompatActivity;


/**
 * Created by Administrator on 2016/6/22.
 */
public class BaseAct extends AppCompatActivity {
    public Buddy bd;
    public String TAG = "DDmusic";
    private ExitAppReceiver exitReceiver = new ExitAppReceiver();
    //自定义退出应用Action,实际应用中应该放到整个应用的Constant类中.
    public static final String EXIT_APP_ACTION = "ExitApp";
    // 退出时间
    private long currentBackPressedTime = 0;
    // 退出间隔
    private static final int BACK_PRESSED_INTERVAL = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerExitReceiver();
        bd = new Buddy(this);

    }

    private void registerExitReceiver() {

        IntentFilter exitFilter = new IntentFilter();
        exitFilter.addAction(EXIT_APP_ACTION);
//        MyApp.getMsgManager().registerReceiver(exitReceiver,exitFilter);
        registerReceiver(exitReceiver, exitFilter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        bd.back();
    }

    /**
     * 判断是否登录
     *
     * @param phone
     * @param psw
     * @return
     */
    public boolean isLogin(String phone, String psw) {
        return !("1".equals(phone) || "1".equals(psw));
    }

    /**
     * 判断屏幕 竖屏还是横屏
     *
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        int mCurrentOrientation = getResources().getConfiguration().orientation;
        if (mCurrentOrientation == Configuration.ORIENTATION_PORTRAIT) {//竖屏的时候

//            findViewById(R.id.toobar).setVisibility(View.VISIBLE);

        } else if (mCurrentOrientation == Configuration.ORIENTATION_LANDSCAPE) {//横屏的时候

//            findViewById(R.id.toobar).setVisibility(View.GONE);

        }
        super.onConfigurationChanged(newConfig);
    }

    /**
     * 退出应用时弹出提示对话框
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        exitApp(keyCode);
        return false;
    }

    public void exitApp(int keyCode) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            重写onBackPressed()方法,继承自退出的方法
//             判断时间间隔

                if (System.currentTimeMillis()- currentBackPressedTime > BACK_PRESSED_INTERVAL) {

                    currentBackPressedTime = System.currentTimeMillis();

                    Toast.makeText(this, "再按一次返回键退出程序", Toast.LENGTH_SHORT).show();

                } else {
                    // 退出
            Intent intent = new Intent();
            intent.setAction(EXIT_APP_ACTION);
//                    MyApp.sendMsg(intent);
            sendBroadcast(intent);
                }

//            new AlertDialog.Builder(this)
//                    .setMessage("确定要退出吗？")
//                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            Intent intent = new Intent();
//                            intent.setAction(EXIT_APP_ACTION);
//                            sendBroadcast(intent);
//                        }
//                    })
//                    .setNegativeButton("取消", null)
//                    .create()
//                    .show();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        MyApp.getMsgManager().unregisterReceiver(exitReceiver);
        unregisterReceiver(exitReceiver);
        OkHttpUtils.getInstance().cancelTag(this);

    }

    /**
     * 为了保证字体不变
     * @return
     */
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config=new Configuration();
        //config.setToDefaults();
        config.fontScale=1.0f;
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

}
