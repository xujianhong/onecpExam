package org.daomingedu.onecpexam.ui.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.daomingedu.onecpexam.R;
import org.daomingedu.onecpexam.bean.Test;

import java.util.ArrayList;


/**
 * Created by jianhongxu on 2018/1/15.
 */

public class SelectRoomAdapter extends BaseQuickAdapter<Test, BaseViewHolder> {
    public SelectRoomAdapter() {
        super(R.layout.item_select_room, new ArrayList<Test>());
    }

    @Override
    protected void convert(BaseViewHolder helper, Test item) {
        helper.setText(R.id.tv_test_name,"考试名称："+item.getTestName());
//        helper.setText(R.id.tv_test_time,"考试时间："+item.getTime());
//        helper.setText(R.id.tv_test_room,"考室：("+item.getRoom_name()+")");

//        TextView textView = helper.getView(R.id.tv_test_status);
//        if(Test.STATUS_DOING==item.getStatus()){
//            textView.setTextColor(mContext.getResources().getColor(R.color.black_1f));
//            textView.setText("考试状态：考试进行中");
//        }
//        else if(Test.STATUS_DONE ==item.getStatus()){
//            textView.setTextColor(mContext.getResources().getColor(R.color.black_9f));
//            textView.setText("考试状态：已考完");
//        }
    }
}
