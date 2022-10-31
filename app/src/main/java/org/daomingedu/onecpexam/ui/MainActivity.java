package org.daomingedu.onecpexam.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.daomingedu.onecpexam.R;
import org.daomingedu.onecpexam.base.BaseBackAct;
import org.daomingedu.onecpexam.base.MyApp;
import org.daomingedu.onecpexam.bean.Teacher;

import androidx.appcompat.app.AlertDialog;

/**
 * Created by jianhongxu on 3/22/21
 * Description
 */
public class MainActivity extends BaseBackAct implements View.OnClickListener {


    private View ll_my_student;
    private View ll_logout;

    private Teacher teacher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        TextView tv_phone = (TextView) findViewById(R.id.tv_phone);
        TextView tv_name = (TextView) findViewById(R.id.tv_name);

        ll_my_student = findViewById(R.id.ll_my_student);
        ll_logout = findViewById(R.id.ll_logout);

        ll_my_student.setOnClickListener(this);
        ll_logout.setOnClickListener(this);

        findViewById(R.id.ib_back).setOnClickListener(this);
        teacher = MyApp.getTeacher();

        tv_name.setText(teacher.getTeacherName());
        tv_phone.setText("电话：" + teacher.getMobilePhone());



//        resize();
    }

//    private void resize() {
//        int windowWidth = getWindowWidth();
//        int layoutWidth = (windowWidth - DensityUtil.dip2px(this, 56)) / 3;
//        for (View view : layoutList) {
//            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
//            layoutParams.height = layoutWidth;
//            layoutParams.width = layoutWidth;
//            view.setLayoutParams(layoutParams);
//        }
//    }
//
//    private int getWindowWidth() {
//        WindowManager windowManager = getWindowManager();
//        return windowManager.getDefaultDisplay().getWidth();
//    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_my_student:
                bd.jump(TeacherTestSchoolListActivity.class, getIntent().getExtras());
                break;
            case R.id.ll_logout:
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("退出提示")
                        .setMessage("是否退出当前账号？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                bd.jump(LoginActivity.class);
                                finish();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();

                break;
            case R.id.ib_back:
                bd.back();
                break;
        }
    }
}
