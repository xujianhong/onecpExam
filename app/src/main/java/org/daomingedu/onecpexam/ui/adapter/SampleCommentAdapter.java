package org.daomingedu.onecpexam.ui.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


import org.daomingedu.onecpexam.R;
import org.daomingedu.onecpexam.bean.SampleComment;

import java.util.List;

/**
 * Created by Administrator on 2017/1/12.
 */

public class SampleCommentAdapter extends BaseQuickAdapter<SampleComment, BaseViewHolder> {

    public SampleCommentAdapter(int layoutId, List<SampleComment> datas) {
        super(layoutId,datas);

    }


//    private String getOrder(int position) {
//        return "示例" + (position +1) +"：";
//    }

    @Override
    protected void convert(BaseViewHolder helper, SampleComment item) {
        int position = helper.getAdapterPosition();
//        helper.setText(R.id.tv_order,getOrder(position));
        TextView tv_sample_comment = helper.getView(R.id.tv_sample_comment);
        tv_sample_comment.setText(item.getContent());
//        if ((position + 1) % 3 == 1) {
//            tv_sample_comment.setBackgroundResource(R.drawable.sample_back_blue);
//        }else if ((position + 1)  % 3 == 2) {
//            tv_sample_comment.setBackgroundResource(R.drawable.sample_back_green);
//        }else tv_sample_comment.setBackgroundResource(R.drawable.sample_back_yellow);
    }
}
