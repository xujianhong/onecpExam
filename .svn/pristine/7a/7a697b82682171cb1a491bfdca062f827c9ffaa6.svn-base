package org.daomingedu.onecpexam.view.pop.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;


import org.daomingedu.onecpexam.R;
import org.daomingedu.onecpexam.bean.CommentType;

import java.util.List;

/**
 * Created by Administrator on 2017/1/12.
 */

public class SampleTypeAdapter extends CommonAdapter<CommentType> {


    private final int height;


    public SampleTypeAdapter(Context context, View button , int layoutId, List<CommentType> datas) {
        super(context, layoutId, datas);
        height = button.getHeight();

    }

    @Override
    protected void convert(ViewHolder viewHolder, CommentType item, int position) {
        TextView tv_sample_type = viewHolder.getView(R.id.tv_sample_type);
        tv_sample_type.setHeight(height);
//        tv_sample_type.setTextSize(TypedValue.COMPLEX_UNIT_PX,);
        tv_sample_type.setText(item.getTypeName());
    }


}
