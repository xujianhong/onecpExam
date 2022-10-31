package org.daomingedu.onecpexam.view.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import org.daomingedu.onecpexam.R;


/**
 * Created by xjh on 2016/7/22.
 */
public class PopSelectFilter extends PopupWindow {

    private Button pop_add;

    View v;

    public PopSelectFilter(Activity act, View.OnClickListener listener){
        super(act);

        LayoutInflater inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.pop_select_filter, null);
        v.findViewById(R.id.btn_not_sub).setOnClickListener(listener);
        v.findViewById(R.id.btn_not_test).setOnClickListener(listener);
        v.findViewById(R.id.btn_sub).setOnClickListener(listener);
        v.findViewById(R.id.btn_all).setOnClickListener(listener);
        //设置SelectPicPopupWindow的View
        this.setContentView(v);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点�?
        this.setFocusable(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.setElevation(5);
        }
        //设置SelectPicPopupWindow弹出窗体动画效果
//        this.setAnimationStyle(R.style.AnimBottom);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背�?
        this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在�?�择框外面则�?毁弹出框
        v.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = v.findViewById(R.id.pop_layout).getTop();
                int y=(int) event.getY();
                if(event.getAction()== MotionEvent.ACTION_UP){
                    if(y<height){
                        dismiss();
                    }
                }
                return true;
            }
        });
    }
}
