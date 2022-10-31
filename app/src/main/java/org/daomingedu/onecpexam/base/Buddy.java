package org.daomingedu.onecpexam.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.text.TextUtils;

import org.daomingedu.onecpexam.R;
import org.daomingedu.onecpexam.utils.ConfigUtils;
import org.daomingedu.onecpexam.view.MyToast;
import org.daomingedu.onecpexam.view.dialog.BaseDialog;
import org.daomingedu.onecpexam.view.dialog.ClickListener;
import org.daomingedu.onecpexam.view.dialog.MyDialog;

import androidx.annotation.AnimRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class Buddy {
    public Activity context;

    protected MyDialog dialog;//自定义弹窗

    public static MyToast toast;

    public Buddy(Activity context) {
        this.context = context;
        toast = new MyToast(context);
    }




    public void showProgress(String msg) {
        getDialog().showLoading(msg);
    }

    public void showProgress(String msg, boolean isProgress) {
        getDialog().showLoading(msg, isProgress);
    }

    public void cancelProgress() {
        if (getDialog() != null) {
            getDialog().dismiss();
        }

    }


    public void setPhonePsw(String phone, String psw) {
        if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(psw)) {
            ConfigUtils.saveString("phone", phone);
            ConfigUtils.saveString("psw", psw);
        }
    }

    public void setPhone(String phone) {
        if (!TextUtils.isEmpty(phone)) {
            ConfigUtils.saveString("phone", phone);
        }
    }

    public void setPsw(String psw) {
        if (!TextUtils.isEmpty(psw)) {
            ConfigUtils.saveString("psw", psw);
        }
    }

    public void clearPsw() {
        ConfigUtils.remove("psw");
    }

    public String getPhone() {
        return ConfigUtils.getString("phone", "1");
    }

    public String getPsw() {
        return ConfigUtils.getString("psw", "1");
    }


    /**
     * 得到自定义dialog
     *
     * @return
     */
    public BaseDialog getDialog() {
        if (dialog == null) {
            dialog = new MyDialog(context);
            dialog.setCancelable(false);
        }
        return dialog;
    }

    /**
     * show Toast
     *
     * @param str
     */
    public void toast(String str) {
        toast.toast(str);
    }

    public void toastSuccess(String str) {
       toast.toastSuccess(str);
    }

    public void toastFailed(String str) {
       toast.toastFailed(str);
    }

    public void toastInfo(String str) {
        toast.toastInfo(str);
    }

    public void toast(String str, @Nullable Drawable drawable, int time) {
       toast.toast(str,drawable,time);

    }


    public MyDialog showExit() {
        return (MyDialog) getDialog().showCancleAndSure("退出提示", "是否要离开了呢？", "取消", "确定");

    }

    /**
     * 自定义双按钮dialog
     *
     * @param strTitle                标题
     * @param strContent              内容
     * @param strFalse                左边按钮
     * @param strTrue                 右边按钮
     * @param onNegativeClickListener 右边按钮监听器
     */
    public void confirm(String strTitle, String strContent, String strFalse, String strTrue, ClickListener onNegativeClickListener) {
        getDialog().showCancleAndSure(strTitle, strContent, strFalse, strTrue)
                .setOnClickView(R.id.btn_sure, onNegativeClickListener);

    }

    /**
     * 自定义双按钮dialog
     *
     * @param strContent              内容
     * @param strFalse                左边按钮
     * @param strTrue                 右边按钮
     * @param onNegativeClickListener 右边按钮监听器
     */
    public void confirm(String strContent, String strFalse, String strTrue, ClickListener onNegativeClickListener) {
        getDialog().showCancleAndSure("提示", strContent, strFalse, strTrue)
                .setOnClickView(R.id.btn_sure, onNegativeClickListener);

    }

    /**
     * 自定义双按钮dialog
     *
     * @param strContent              内容
     * @param onNegativeClickListener 右边按钮监听器
     */
    public void confirm(String strContent, ClickListener onNegativeClickListener) {
        getDialog().showCancleAndSure("提示", strContent, "取消", "确定")
                .setOnClickView(R.id.btn_sure, onNegativeClickListener);

    }

    /**
     * 自定义双按钮dialog
     *
     * @param strTitle                标题
     * @param strContent              内容
     * @param strFalse                左边按钮
     * @param strTrue                 右边按钮
     * @param onPositiveClickListener 左边按钮
     * @param onNegativeClickListener 右边按钮
     */
    public void confirm(String strTitle, String strContent, String strFalse, String strTrue,
                        ClickListener onPositiveClickListener, ClickListener onNegativeClickListener) {
        getDialog().showCancleAndSure(strTitle, strContent, strFalse, strTrue).setOnClickView(R.id.btn_cancle, onPositiveClickListener)
                .setOnClickView(R.id.btn_sure, onNegativeClickListener);

    }

    /**
     * 自定义双按钮dialog
     *
     * @param strContent              内容
     * @param strFalse                左边按钮
     * @param strTrue                 右边按钮
     * @param onPositiveClickListener 左边按钮
     * @param onNegativeClickListener 右边按钮
     */
    public void confirm(String strContent, String strFalse, String strTrue,
                        ClickListener onPositiveClickListener, ClickListener onNegativeClickListener) {
        getDialog().showCancleAndSure("提示", strContent, strFalse, strTrue).setOnClickView(R.id.btn_cancle, onPositiveClickListener)
                .setOnClickView(R.id.btn_sure, onNegativeClickListener);

    }

    /**
     * 自定义双按钮dialog
     *
     * @param strContent              内容
     * @param onPositiveClickListener 左边按钮
     * @param onNegativeClickListener 右边按钮
     */
    public void confirm(String strContent, ClickListener onPositiveClickListener, ClickListener onNegativeClickListener) {
        getDialog().showCancleAndSure("提示", strContent, "取消", "确定").setOnClickView(R.id.btn_cancle, onPositiveClickListener)
                .setOnClickView(R.id.btn_sure, onNegativeClickListener);

    }

    /**
     * 自定义单按钮dialog
     *
     * @param strTitle        标题
     * @param strMsg          内容
     * @param onClickLisenter 按钮监听器
     */
    public void alert(String strTitle, String strMsg, ClickListener onClickLisenter) {
        getDialog().showCancle(strTitle, strMsg).setOnClickView(R.id.btn_mid, onClickLisenter);
    }

    /**
     * 自定义单按钮dialog
     *
     * @param strMsg          内容
     * @param onClickLisenter 按钮监听器
     */
    public void alert(String strMsg, ClickListener onClickLisenter) {
        getDialog().showCancle("提示", strMsg).setOnClickView(R.id.btn_mid, onClickLisenter);
    }

    /**
     * 自定义单按钮dialog
     *
     * @param strTitle 标题
     * @param strMsg   内容
     */
    public void alert(String strTitle, String strMsg) {
        getDialog().showCancle(strTitle, strMsg);
    }

    /**
     * 自定义单按钮dialog
     *
     * @param strMsg 内容
     */
    public void alert(String strMsg) {
        getDialog().showCancle("提示", strMsg);
    }

    /**
     * 跳转到指定页面
     *
     * @param clazz 跳转页面的class对象
     */
    public void jump(Class clazz) {
        jump(clazz, null);
    }

    /**
     * 跳转到指定页面并携带值
     *
     * @param clazz 指定页面的class对象
     * @param b     携带的值
     */
    public void jump(Class clazz, Bundle b) {
        jump(clazz, b, R.anim.in_from_right, R.anim.out_to_left);
    }

    /**
     * 跳转到指定页面并卸载值，同时设置跳转动画
     *
     * @param clazz   指定页面的class对象
     * @param b       携带的值
     * @param animIn  activity进入动画
     * @param animOut activity退出动画
     */
    public void jump(Class clazz, @Nullable Bundle b, @AnimRes int animIn, @AnimRes int animOut) {
        Intent it = new Intent(context, clazz);
        if (b != null) {
            it.putExtras(b);
        }
        jump(it, animIn, animOut);
    }

    /**
     * 跳转到intent指定的页面
     *
     * @param it
     */
    public void jump(Intent it) {
        jump(it, R.anim.in_from_right, R.anim.out_to_left);
    }

    /**
     * 跳转到intent指定页面不携带值 设置跳转动画
     *
     * @param it      intent对象
     * @param animIn  进入动画
     * @param animOut 退出动画
     */
    public void jump(Intent it, @AnimRes int animIn, @AnimRes int animOut) {
        context.startActivity(it);
        context.overridePendingTransition(animIn, animOut);
    }

    /**
     * 跳转到intent指定页面，并请求返回值
     *
     * @param it          intent对象
     * @param requestCode requestCode
     */
    public void jumpResult(Intent it, int requestCode) {
        context.startActivityForResult(it, requestCode);
        context.overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    /**
     * 跳转到clazz对应页面，并请求返回值
     *
     * @param clazz
     * @param requestCode
     */
    public void jumpResult(Class clazz, int requestCode) {
        jumpResult(null, clazz, requestCode);
    }

    /**
     * 从fragment中跳转到指定页面，请求返回值，由fragment接收处理
     *
     * @param fragment
     * @param clazz
     * @param requestCode
     */
    public void jumpResult(Fragment fragment, Class clazz, int requestCode) {
        jumpResult(fragment, clazz, requestCode, null);
    }

    /**
     * 跳转到指定页面，携带之，并请求返回值
     *
     * @param clazz
     * @param requestCode
     * @param b
     */
    public void jumpResult(Class clazz, int requestCode, Bundle b) {
        jumpResult(null, clazz, requestCode, b);
    }

    /**
     * 从fragment中跳转到指定页面，携带参数，并请求返回值，由fragment接收处理
     *
     * @param fragment
     * @param clazz
     * @param requestCode
     * @param b
     */
    public void jumpResult(Fragment fragment, Class clazz, int requestCode, Bundle b) {
        jumpResult(fragment, clazz, requestCode, b, R.anim.in_from_right, R.anim.out_to_left);
    }

    /**
     * 到指定页面，携带参数，并请求返回值，并设置跳转动画
     *
     * @param clazz
     * @param requestCode
     * @param b
     * @param animIn
     * @param animOut
     */
    public void jumpResult(Class clazz, int requestCode, @Nullable Bundle b, @AnimRes int animIn, @AnimRes int animOut) {
        jumpResult(null, clazz, requestCode, b, animIn, animOut);
    }

    /**
     * 从fragment中跳转到指定页面，携带参数，并请求返回值，由fragment接收处理,并设置跳转动画
     *
     * @param fragment
     * @param clazz
     * @param requestCode
     * @param b
     * @param animIn
     * @param animOut
     */
    public void jumpResult(@Nullable Fragment fragment, @NonNull Class clazz, int requestCode, @Nullable Bundle b, @AnimRes int animIn, @AnimRes int animOut) {
        Intent it = new Intent(context, clazz);
        if (b != null) {
            it.putExtras(b);
        }
        if (fragment == null) {
            context.startActivityForResult(it, requestCode);
        } else {
            fragment.startActivityForResult(it, requestCode);
        }
        context.overridePendingTransition(animIn, animOut);
    }

    /**
     * 离开当前页面使用默认跳转动画
     */
    public void back() {
        back(R.anim.in_from_left, R.anim.out_to_right);
    }

    /**
     * 离开当前页面使用自定义跳转动画
     *
     * @param animIn
     * @param animOut
     */
    public void back(@AnimRes int animIn, @AnimRes int animOut) {
        context.finish();
        context.overridePendingTransition(animIn, animOut);
    }

    public String getSessionId() {
        return ((MyApp) context.getApplication()).getSessionId();
    }

}
