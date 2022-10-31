package org.daomingedu.onecpexam.view;

import android.content.Context;
import android.graphics.drawable.Drawable;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.daomingedu.onecpexam.R;
import org.daomingedu.onecpexam.utils.DensityUtil;

import androidx.annotation.Nullable;


/**
 * Created by qin on 2017/10/9.
 */

public class MyToast {
    private Context context;
    private static Toast toast;
    public MyToast(Context context){
        this.context = context;
    }
    public void toast(String str) {
        toast(str, null, Toast.LENGTH_SHORT);
    }

    public void toastSuccess(String str) {
        Drawable drawable = context.getResources().getDrawable(R.mipmap.snack_success);
        toast(str, drawable, Toast.LENGTH_SHORT);
    }

    public void toastFailed(String str) {
        Drawable drawable = context.getResources().getDrawable(R.mipmap.snack_failed);
        toast(str, drawable, Toast.LENGTH_SHORT);
    }

    public void toastInfo(String str) {
        Drawable drawable = context.getResources().getDrawable(R.mipmap.snack_info);
        toast(str, drawable, Toast.LENGTH_SHORT);
    }

    public void toast(String str, @Nullable Drawable drawable, int time) {
        if (toast == null) {
            toast = Toast.makeText(context, str, time);
        }
        View view = View.inflate(context, R.layout.item_toast, null);
        TextView tv_toast = (TextView) view.findViewById(R.id.tv_toast);
        tv_toast.setText(str);
        if (drawable != null) {
            int drawableWidth = DensityUtil.dip2px(context, 20);
            drawable.setBounds(0, 0, drawableWidth, drawableWidth * drawable.getMinimumHeight() / drawable.getMinimumWidth());
            tv_toast.setCompoundDrawablePadding(DensityUtil.dip2px(context, 10));
            tv_toast.setCompoundDrawables(drawable, null, null, null);
        }
        toast.setView(view);
        toast.setDuration(time);
        toast.show();

    }
}
