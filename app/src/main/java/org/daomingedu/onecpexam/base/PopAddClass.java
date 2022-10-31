package org.daomingedu.onecpexam.base;

import android.widget.PopupWindow;

/**
 * Created by xjh on 2016/7/22.
 */
public class PopAddClass extends PopupWindow {

//    private Button pop_add;
//
//    View v;
//
//    public PopAddClass(Activity act, View.OnClickListener listener){
//        super(act);
//
//        LayoutInflater inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        v = inflater.inflate(R.layout.pop_add_class, null);
//        pop_add = (Button)v.findViewById(R.id.pop_add);
//        pop_add.setOnClickListener(listener);
//
//
//        //设置SelectPicPopupWindow的View
//        this.setContentView(v);
//        //设置SelectPicPopupWindow弹出窗体的宽
//        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
//        //设置SelectPicPopupWindow弹出窗体的高
//        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
//        //设置SelectPicPopupWindow弹出窗体可点�?
//        this.setFocusable(true);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            this.setElevation(5);
//        }
//        //设置SelectPicPopupWindow弹出窗体动画效果
////        this.setAnimationStyle(R.style.AnimBottom);
//        //实例化一个ColorDrawable颜色为半透明
//        ColorDrawable dw = new ColorDrawable(0xb0000000);
//        //设置SelectPicPopupWindow弹出窗体的背�?
//        this.setBackgroundDrawable(dw);
//        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在�?�择框外面则�?毁弹出框
//        v.setOnTouchListener(new View.OnTouchListener() {
//
//            public boolean onTouch(View v, MotionEvent event) {
//
//                int height = v.findViewById(R.id.pop_layout).getTop();
//                int y=(int) event.getY();
//                if(event.getAction()== MotionEvent.ACTION_UP){
//                    if(y<height){
//                        dismiss();
//                    }
//                }
//                return true;
//            }
//        });
//    }
}
