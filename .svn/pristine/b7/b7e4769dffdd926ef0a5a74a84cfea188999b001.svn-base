package org.daomingedu.onecpexam.ui.adapter;

import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.daomingedu.onecpexam.R;
import org.daomingedu.onecpexam.bean.ExamItem;
import org.daomingedu.onecpexam.bean.ExamItemComment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

/**
 * Created by jianhongxu on 3/26/21
 * Description 分类评分
 */
public class MarkAdapter extends BaseQuickAdapter<ExamItem, BaseViewHolder> {


    public MarkAdapter(int layoutResId, @Nullable List<ExamItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ExamItem item) {
        helper.setText(R.id.tvMarkName, item.getName());
//        List<String> list = new ArrayList<>();
//        for (ExamItemComment it : item.getExamsItemComment()) {
//            list.add(it.getContent());
//        }
//        ((AppCompatSpinner) helper.getView(R.id.asSelect))
//                .setAdapter(new ArrayAdapter(mContext, R.layout.item_text, R.id.tvSelect, list));
    }
}
