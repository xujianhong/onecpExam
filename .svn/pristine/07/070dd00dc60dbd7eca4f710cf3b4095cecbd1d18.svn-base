package org.daomingedu.onecpexam.view.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.daomingedu.onecpexam.R;


/**
 * Created by xjh on 2016/7/13.
 */
public class MyDialog extends BaseDialog {

    Context context;

    public MyDialog(Context context) {
        super(context, R.style.Dialog);
    }

    public MyDialog(Context context, int layout) {
        super(context, layout, R.style.Dialog);
        this.context = context;
    }

    @Override
    public BaseDialog showNoNetWork() {
        return showCancle("没有链接网络");
    }


    @Override
    public BaseDialog showCancle(String msg) {
        return showCancle(msg, null);
    }


    @Override
    public void setCanceledOnTouchOutside(boolean cancel) {
        super.setCanceledOnTouchOutside(cancel);
    }

    @Override
    public void setCancelable(boolean flag) {
        super.setCancelable(flag);
    }

    @Override
    public BaseDialog setOnClickView(View view) {
        return super.setOnClickView(view);
    }

    @Override
    public BaseDialog setOnClickView(View view, ClickListener listener) {
        return super.setOnClickView(view, listener);
    }

    @Override
    public BaseDialog setViewText(int viewId, String text) {
        return super.setViewText(viewId, text);
    }

    @Override
    public BaseDialog showCancle(String title, String msg) {
        if (!isShowing()) {
            try {
                setContentView(R.layout.dialog_msg_one);
                TextView tv_msg = findView(R.id.tv_dialog_msg);
                tv_msg.setText(title);
                Button btn = findView(R.id.btn_mid);
                if(!TextUtils.isEmpty(msg))
                btn.setText(msg);
                setOnClickView(btn);
                show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }


    public BaseDialog showCancleAndSure(String title, String msg, String cancleBtnText, String sureBtnText) {
        if (!isShowing()) {
            try {
                setContentView(R.layout.dialog_msg_two);

                TextView tv_title = findView(R.id.tv_dialog_title);
                tv_title.setText(title);
                TextView tv_msg = findView(R.id.tv_dialog_msg);
                tv_msg.setText(msg);
                Button bt_cancle = findView(R.id.btn_cancle);
                Button bt_sure = findView(R.id.btn_sure);
                bt_cancle.setText(cancleBtnText);
                setOnClickView(bt_cancle);
                bt_sure.setText(sureBtnText);

                show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    @Override
    public BaseDialog showSelectCancle(String var1, String var2, String cancleBtnText) {
        if (!isShowing()) {
            try {
                setContentView(R.layout.dialog_msg_select);

                Button btn_selecttext = findView(R.id.btn_selecttext);
                btn_selecttext.setText(var1);
                Button btn_selectrecodong = findView(R.id.btn_selectrecodong);
                btn_selectrecodong.setText(var2);
                Button btn_mid = findView(R.id.btn_mid);
                btn_mid.setText(cancleBtnText);
                setOnClickView(btn_mid);
                show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }


    TextView tipTextView;

    @Override
    public BaseDialog showLoading(String msg) {
        return showLoading(msg,false);
    }

    @Override
    public BaseDialog showLoading(String msg, boolean isProgress) {
        if (!isShowing()) {
            try {
                setContentView(isProgress?R.layout.dialog_loading_updata:R.layout.dialog_loading);
                tipTextView = findView(R.id.tipTextView);// 提示文字
                if (msg != null) {
                    tipTextView.setText(msg);// 设置加载信息
                }
                if (isProgress) {
                    pb_loading = findView(R.id.pb_loading);
                    pb_loading.setMax(100);
                }

                show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }


}
