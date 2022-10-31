package org.daomingedu.onecpexam.ui;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.daomingedu.onecpexam.R;
import org.daomingedu.onecpexam.base.BaseBackAct;
import org.daomingedu.onecpexam.base.Constant;
import org.daomingedu.onecpexam.base.MyApp;
import org.daomingedu.onecpexam.bean.Teacher;
import org.daomingedu.onecpexam.bean.Test;
import org.daomingedu.onecpexam.http.Api;
import org.daomingedu.onecpexam.http.ListCallback;
import org.daomingedu.onecpexam.http.URLS;
import org.daomingedu.onecpexam.ui.adapter.SelectRoomAdapter;

import java.util.List;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by jianhongxu on 3/22/21
 * Description
 */
public class SelectRoomActivity extends BaseBackAct implements BaseQuickAdapter.OnItemClickListener {

    private SelectRoomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_select_room);
        setTitle("选择考试");
        RecyclerView view = (RecyclerView) findViewById(R.id.rv_room);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setItemAnimator(new DefaultItemAnimator());
        view.setHasFixedSize(true);
        view.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new SelectRoomAdapter();
        view.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getTestInfo();
    }

    private void getTestInfo() {
        new Api(this, URLS.generateUrl(URLS.SEARCHTEACHERTESTLIST))
                .add("sessionId", MyApp.getTeacher().getSessionId())
                .post(new ListCallback<Test>() {
                    @Override
                    public void onSuccess(List<Test> list) {
                        if (list == null) return;
                        adapter.setNewData(list);
                    }
                });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //不执行父类点击事件
        return keyCode == KeyEvent.KEYCODE_BACK || super.onKeyDown(keyCode, event);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Test test = (Test) adapter.getItem(position);
        if (test != null) {
            Bundle bundle = new Bundle();
            bundle.putString(Constant.TEST_ID_EXTRA, test.getTestId());
            bd.jump(MainActivity.class, bundle);

        }
    }
}
