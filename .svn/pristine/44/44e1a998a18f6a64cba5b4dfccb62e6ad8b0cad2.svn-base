package org.daomingedu.onecpexam.utils;


import org.daomingedu.onecpexam.BuildConfig;

/**
 * Created by xjh on 2016/6/30.
 */
public class Log {
    public static boolean isPrint = BuildConfig.DEBUG;
    public static String TAG = BuildConfig.APPLICATION_ID;
    public Log() {
    }

    public static void i(Object obj) {
        if(isPrint) {
//            System.out.println(obj);
            android.util.Log.i(TAG,obj+"");
        }

    }

    public static void e(Object obj) {
        if(isPrint) {
//            System.err.println(obj);
            android.util.Log.e(TAG,obj+"");
        }

    }
    public static void d(Object obj) {
        if(isPrint) {
//            System.err.println(obj);
            android.util.Log.d(TAG,obj+"");
        }

    }

    public static void w(Object obj) {
        if(isPrint) {
//            System.err.println(obj);
            android.util.Log.w(TAG,obj+"");
        }

    }

}
