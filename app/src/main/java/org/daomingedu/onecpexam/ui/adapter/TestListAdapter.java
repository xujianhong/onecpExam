package org.daomingedu.onecpexam.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;


import org.daomingedu.onecpexam.R;
import org.daomingedu.onecpexam.base.CommonAdapter;
import org.daomingedu.onecpexam.base.ViewHolder;
import org.daomingedu.onecpexam.bean.TestRoom;

import java.util.List;

/**
 * Created by qin.
 */

public class TestListAdapter extends CommonAdapter<TestRoom> {
    public TestListAdapter(Context context, List<TestRoom> list) {
        super(context, list, R.layout.item_test);
    }

    @Override
    public void getView(ViewHolder holder, TestRoom data, int pos) {
        holder.setText(R.id.tv_test_time,data.getClassesName());
        holder.setText(R.id.tv_student_count,"考生人数：" + data.getCountTestSign() +" 人");
        TextView unComplete = holder.getView(R.id.tv_student_uncomplete);
        holder.setText(R.id.tv_student_complete,"已评定人数："+data.getAttendTestSign()+" 人");
        int iUnComplete = data.getCountTestSign()-data.getAttendTestSign();
        unComplete.setText("未评定人数："+iUnComplete+" 人");
        if(iUnComplete<=0){
            unComplete.setTextColor(Color.GRAY);
        }
        else{
            unComplete.setTextColor(Color.RED);

        }

    }
}
