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
import org.daomingedu.onecpexam.ui.adapter.TeacherTestSchoolListAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


public class TeacherTestSchoolListActivity extends BaseBackAct implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {

    private ListView list_view;
    private SwipeRefreshLayout pull_refresh;
    private String testId;
    private Teacher teacher;
    private BaseAdapter adapter;

    private List<TestRoom> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_test_school_list);
        addNavBtn();
        setTitle("我的学校");

        teacher = MyApp.getTeacher();
        testId = getIntent().getStringExtra(Constant.TEST_ID_EXTRA);

        initViews();
        onRefresh();


    }

    private void initViews() {
        list_view = (ListView) findViewById(R.id.list_view);
        list_view.setOnItemClickListener(this);
        list_view.setAdapter(adapter = new TeacherTestSchoolListAdapter(TeacherTestSchoolListActivity.this, data));
        pull_refresh = (SwipeRefreshLayout) findViewById(R.id.pull_refresh);
        pull_refresh.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        new Api(this, URLS.generateUrl(URLS.TEACHER_TEST_SCHOOL_LIST))
                .add("sessionId", teacher.getSessionId())
                .add("testId", testId)
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
        bundle.putString(Constant.SCHOOL_ID_EXTRA,data.get(position).getSchoolId());
        bd.jump(TestListActivity.class, bundle);
    }
}