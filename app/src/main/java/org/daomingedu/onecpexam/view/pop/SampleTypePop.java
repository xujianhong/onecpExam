package org.daomingedu.onecpexam.view.pop;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;


import org.daomingedu.onecpexam.R;
import org.daomingedu.onecpexam.bean.CommentType;
import org.daomingedu.onecpexam.view.pop.adapter.SampleTypeAdapter;

import java.util.List;

/**
 * Created by xjh on 2016/7/22.
 */
public class SampleTypePop extends PopupWindow {
    ListView lv_sample_type;
    private Activity act;
    private ValueAnimator valueAnimator;

    public SampleTypePop(Activity act, View button, ListView.OnItemClickListener listener, List<CommentType> datas){
        super(act);
        this.act = act;

        LayoutInflater inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v= inflater.inflate(R.layout.pop_sample_list, null);
        lv_sample_type = (ListView) v.findViewById(R.id.lv_sample_type);

        //设置SelectPicPopupWindow的View
        this.setContentView(v);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(button.getWidth());

        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点�?
        this.setFocusable(true);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            this.setElevation(5);
//        }
        //设置SelectPicPopupWindow弹出窗体动画效果
//        this.setAnimationStyle(R.style.AnimBottom);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
////        设置SelectPicPopupWindow弹出窗体的背�?
        this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在�?�择框外面则�?毁弹出框
//        lv_sample_type.setOnTouchListener(new View.OnTouchListener() {
//
//            public boolean onTouch(View v, MotionEvent event) {
//
//                int height = v.findViewById(R.id.lv_sample_type).getTop();
//                int y=(int) event.getY();
//                if(event.getAction()== MotionEvent.ACTION_UP){
//                    if(y<height){
//                        dismiss();
//                    }
//                }
//                return true;
//            }
//        });
        lv_sample_type.setOnItemClickListener(listener);
        lv_sample_type.setAdapter(new SampleTypeAdapter(act,button,R.layout.item_sample_type,datas));
    }

//    private void doAnimation(boolean isShow) {
//        if (isShow) {
//            valueAnimator = ValueAnimator.ofFloat(1, 0.5f);
//        } else valueAnimator = ValueAnimator.ofFloat(0.5f, 1);
//        valueAnimator.setInterpolator(new LinearInterpolator());
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float value = (float) animation.getAnimatedValue();
//                backgroundAlpha(value);
//            }
//        });
//        valueAnimator.setDuration(300);
//        valueAnimator.start();
//    }

//    // 设置屏幕透明度
//    public void backgroundAlpha(float bgAlpha) {
//        WindowManager.LayoutParams lp = act.getWindow().getAttributes();
//        lp.alpha = bgAlpha; // 0.0~1.0
//        act.getWindow().setAttributes(lp);
//    }
//
//
//    @Override
//    public void showAsDropDown(View parent, int gravity, int x, int y) {
//        super.showAsDropDown(parent,gravity,x,y);
//        doAnimation(true);
//    }
//
//    @Override
//    public void dismiss() {
//        super.dismiss();
//        doAnimation(false);
//    }
}
