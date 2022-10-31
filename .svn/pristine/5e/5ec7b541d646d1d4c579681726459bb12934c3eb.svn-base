package org.daomingedu.onecpexam.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.daomingedu.onecpexam.R;
import org.daomingedu.onecpexam.base.BaseBackAct;
import org.daomingedu.onecpexam.base.Constant;
import org.daomingedu.onecpexam.base.MyApp;
import org.daomingedu.onecpexam.bean.QueryStudentInfo;
import org.daomingedu.onecpexam.bean.TestRoomStudent;
import org.daomingedu.onecpexam.http.Api;
import org.daomingedu.onecpexam.http.ObjCallback;
import org.daomingedu.onecpexam.http.URLS;
import org.daomingedu.onecpexam.view.dialog.MyDialog;


/**
 * Created by Administrator on 2017/1/10.
 */

public class StuInfoActivity extends BaseBackAct implements View.OnClickListener {


    private QueryStudentInfo queryStudentInfo;

    private String testSignId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_stu_info);
        setTitle("学生信息");
        addNavBtn().setBackgroundColor(getResources().getColor(android.R.color.transparent));

        testSignId = getIntent().getStringExtra(Constant.TEST_SIGN_EXTRA);

//        getStudentInfo(testSignId);

        findViewById(R.id.bt_begin_assessment).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(testSignId)) {
            getStudentInfo(testSignId);
        }
    }


    void getStudentInfo(String testSignId) {
        new Api(this, URLS.generateUrl(URLS.QUERY_STUDENT_TEST_INFO))
                .add("sessionId", MyApp.getTeacher().getSessionId())
                .add("testSignId", testSignId)
                .post(new ObjCallback<QueryStudentInfo>() {
                    @Override
                    public void onSuccess(QueryStudentInfo info) {
                        if (info == null) return;
                        initView(info);


                    }
                }, "请求学生信息");
    }


    private void initView(QueryStudentInfo info) {

        queryStudentInfo = info;

        ImageView civ_head = findViewById(R.id.iv_head);

        if (!TextUtils.isEmpty(info.getAttendScore()))
            ((TextView) findViewById(R.id.tv_score)).setText(info.getAttendScore());
        if (!TextUtils.isEmpty(info.getAttendComment()))
            ((TextView) findViewById(R.id.tv_commit)).setText(info.getAttendComment());
        ((TextView) findViewById(R.id.tv_name)).setText(info.getStudentName());

        if ("M".equals(info.getStudentInfo().getSex())) {
            ((TextView) findViewById(R.id.tvSex)).setText("性别：男");
        } else if ("F".equals(info.getStudentInfo().getSex())) {
            ((TextView) findViewById(R.id.tvSex)).setText("性别：女");
        }
        ((TextView) findViewById(R.id.tvGrade)).setText("年级："+info.getStudentInfo().getGradeName());
        ((TextView) findViewById(R.id.tvClass)).setText("班级："+info.getStudentInfo().getClassesName());

        Glide.with(StuInfoActivity.this).load(info.getPhotoImg()).into(civ_head);

    }


    @Override
    public void onClick(View v) {

        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.TEST_STUDENT_INFO_EXTRA, queryStudentInfo);
        bd.jump(SubTestActivity.class, bundle);

    }


}
