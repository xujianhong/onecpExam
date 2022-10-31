package org.daomingedu.onecpexam.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import org.daomingedu.onecpexam.R;
import org.daomingedu.onecpexam.base.CommonAdapter;
import org.daomingedu.onecpexam.base.ViewHolder;
import org.daomingedu.onecpexam.bean.TestRoomStudent;

import java.util.List;

/**
 * Created by qin.
 */

public class StudentListAdapter extends CommonAdapter<TestRoomStudent> {
    public StudentListAdapter(Context context, List<TestRoomStudent> list) {
        super(context, list, R.layout.item_test_student);
    }

    @Override
    public void getView(ViewHolder holder, TestRoomStudent data, int pos) {
        holder.setText(R.id.tv_name,data.getStudentName());

        holder.setText(R.id.tv_major_score,"评定结果："+data.getAttendScore());
        TextView tv_state = holder.getView(R.id.tv_state);
        ImageView imageView = holder.getView(R.id.iv_head);
        Glide.with(mContext).load(data.getPhotoImg()).into(imageView);
        tv_state.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
        //状态  0 未考试  1没有打成绩  2已经完成
        switch (data.getStatus()){
            case TestRoomStudent.STATUS_NO_PARTICIPATE:
               tv_state.setTextColor(mContext.getResources().getColor(R.color.black_1f));
                tv_state.setText("未参考");
                break;
            case TestRoomStudent.STATUS_PARTICIPATE:
                tv_state.setTextColor(mContext.getResources().getColor(R.color.black_1f));
                Drawable drawable = mContext.getResources().getDrawable(R.mipmap.warn);
                tv_state.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null);
                tv_state.setText("没有打成绩");
                break;
            case TestRoomStudent.STATUS_COMPLETE:
                tv_state.setTextColor(mContext.getResources().getColor(R.color.gray_a0));
                tv_state.setText("已考完");
                break;

        }
    }
}
