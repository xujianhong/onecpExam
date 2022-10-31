package org.daomingedu.onecpexam.ui;

import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;


import org.daomingedu.onecpexam.R;
import org.daomingedu.onecpexam.base.BaseBackAct;
import org.daomingedu.onecpexam.base.Constant;
import org.daomingedu.onecpexam.base.MyApp;
import org.daomingedu.onecpexam.bean.Teacher;
import org.daomingedu.onecpexam.bean.TestRoom;
import org.daomingedu.onecpexam.http.Api;
import org.daomingedu.onecpexam.http.ListCallback;
import org.daomingedu.onecpexam.http.URLS;
import org.daomingedu.onecpexam.ui.adapter.TestListAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * Created by qin.
 */

public class TestListActivity extends BaseBackAct implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {

    private ListView listView;
    private BaseAdapter adapter;
    private Teacher teacher;
    private SwipeRefreshLayout pull_refresh;
    private List<TestRoom> data = new ArrayList<>();
    private String testId;
    private String schoolId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_base_refresh);
        addNavBtn();
        setTitle("我的班级");
        teacher = MyApp.getTeacher();
        testId = getIntent().getStringExtra(Constant.TEST_ID_EXTRA);
        schoolId = getIntent().getStringExtra(Constant.SCHOOL_ID_EXTRA);
        initViews();
        onRefresh();

    }


    private void initViews() {
        listView = (ListView) findViewById(R.id.list_view);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter = new TestListAdapter(TestListActivity.this, data));
        pull_refresh = (SwipeRefreshLayout) findViewById(R.id.pull_refresh);
        pull_refresh.setOnRefreshListener(this);

    }

    @Override
    public void onRefresh() {
        new Api(this, URLS.generateUrl(URLS.TEACHER_TEST_CLASS_LIST))
                .add("sessionId", teacher.getSessionId())
                .add("testId", testId)
                .add("schoolId",schoolId)
                .post(new ListCallback<TestRoom>() {
                    @Override
                    public void onSuccess(List<TestRoom> list) {
                        if (list == null) return;
                        data.clear();
                        data.addAll(list);
                        pull_refresh.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable ex) {
                        super.onError(ex);
                        pull_refresh.setRefreshing(false);
                    }
                });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = getIntent().getExtras();
        bundle.putSerializable(Constant.ROOM_ID_EXTRA, data.get(position));
        bd.jump(StudentListActivity.class, bundle);
    }
}
