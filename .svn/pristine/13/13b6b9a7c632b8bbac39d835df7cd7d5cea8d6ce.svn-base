package org.daomingedu.onecpexam.base;


import android.app.Application;
import android.content.Context;
import android.content.Intent;
;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;



import com.zhy.http.okhttp.OkHttpUtils;


import org.daomingedu.onecpexam.bean.CommentType;
import org.daomingedu.onecpexam.bean.MyDevice;
import org.daomingedu.onecpexam.bean.Teacher;
import org.daomingedu.onecpexam.http.LogInterceptor;
import org.daomingedu.onecpexam.http.URLS;
import org.daomingedu.onecpexam.utils.ConfigUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.multidex.MultiDex;
import okhttp3.OkHttpClient;


public class MyApp extends Application {


    public static int UI_WIDTH = 1080;
    public static int UI_HEIGHT = 1920;
    public static int UI_DENSITY = 2;
    static Context context;
    private List<CommentType> sampleCommentList;

    private static Teacher teacher;

    public static Teacher getTeacher() {
        return teacher;
    }

    public static void setTeacher(Teacher teacher) {
        MyApp.teacher = teacher;
    }

    public List<CommentType> getSampleCommentList() {
        return sampleCommentList;
    }

    public void setSampleCommentList(List<CommentType> sampleCommentList) {
        this.sampleCommentList = sampleCommentList;
    }

    @Override
    public void onCreate() {
        super.onCreate();

//        bugly
//        Bugly.init(getApplicationContext(), BuildConfig.Bugly_APPID, BuildConfig.DEBUG);

        ConfigUtils.init(this);
        displayMetrics(this);
        if (!TextUtils.isEmpty(ConfigUtils.getString(Constant.BASE_URL))) {
            URLS.setBASEURL(ConfigUtils.getString(Constant.BASE_URL));
        }

        MultiDex.install(this);



        context = this;

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30000L, TimeUnit.MILLISECONDS)
                .readTimeout(30000L, TimeUnit.MILLISECONDS)
                .addInterceptor(new LogInterceptor())
                .build();
        OkHttpUtils.initClient(okHttpClient);

//		ViewTarget.setTagId(R.id.glide_tag);

    }
    /**
     * 登陆成功以后会返回一个随机、有时效的字符串作为本次登录的sessionId
     * 保存sessionId
     *
     * @param sessionId
     */
    public void setSessionId(String sessionId) {

        ConfigUtils.saveString("sessionId", sessionId);
    }

    /**
     * 取sessionId
     *
     * @return
     */
    public String getSessionId() {
        return ConfigUtils.getString("sessionId");
//		if(TextUtils.isEmpty(sessionId)){
//			return "123123123";
//		}
//		return sessionId;
    }

    /**
     * 获取手机屏幕的宽高
     *
     * @param ctx
     */
    public static void displayMetrics(Context ctx) {
        WindowManager manager = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        int w = dm.widthPixels;
        int h = dm.heightPixels;
        MyDevice.sDensity = dm.density;
        MyDevice.sScaledDensity = dm.scaledDensity;
        MyDevice.sWidth = w;
        MyDevice.sHeight = h;
        if (w > h) {
            MyDevice.sWidth = h;
            MyDevice.sHeight = w;
        }
    }


    /**
     * 发送广播
     *
     * @param var0
     */
    public static void sendMsg(Intent var0) {
        getMsgManager().sendBroadcast(var0);
    }

    /**
     * 得到广播
     *
     * @return
     */
    public static LocalBroadcastManager getMsgManager() {
        return LocalBroadcastManager.getInstance(context);
    }

}
