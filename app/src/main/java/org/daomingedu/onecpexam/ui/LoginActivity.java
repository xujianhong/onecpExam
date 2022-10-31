package org.daomingedu.onecpexam.ui;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.daomingedu.onecpexam.R;
import org.daomingedu.onecpexam.base.BaseAct;
import org.daomingedu.onecpexam.base.Constant;
import org.daomingedu.onecpexam.base.MyApp;
import org.daomingedu.onecpexam.bean.Teacher;
import org.daomingedu.onecpexam.bean.VersionInfo;
import org.daomingedu.onecpexam.http.Api;
import org.daomingedu.onecpexam.http.ObjCallback;
import org.daomingedu.onecpexam.http.URLS;
import org.daomingedu.onecpexam.utils.ConfigUtils;
import org.daomingedu.onecpexam.utils.Log;
import org.daomingedu.onecpexam.utils.StringUtils;
import org.daomingedu.onecpexam.view.MyToast;

import java.io.File;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;

/**
 * Created by jianhongxu on 3/22/21
 * Description
 */
public class LoginActivity extends BaseAct implements View.OnClickListener {

    private EditText et_code;
    private EditText et_phone;
    private Button bt_get_code;
    private CountDownTimer countDownTimer;
    private boolean counting;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        et_code = (EditText) findViewById(R.id.et_code);
        et_phone = (EditText) findViewById(R.id.et_phone);

        Button bt_login = (Button) findViewById(R.id.bt_login);
        bt_login.setOnClickListener(this);
        et_phone.setText(ConfigUtils.getString("phone"));
        resizeImageWelcome();

        new RxPermissions(this)
                .request(Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA)
                .subscribe(aBoolean -> {
                    if (!aBoolean) {
                        new MyToast(this).toast("请开启相关权限");
                        finish();
                    }
                }, Throwable::printStackTrace);

        getVersionInfo();
    }

    /**
     * 获得版本信息
     */
    private void getVersionInfo() {
        new Api(this, URLS.generateUrl(URLS.GET_VERSION_INFO))
                .add("key", Constant.KEY)
                .add("systemType", "3")
                .post(new ObjCallback<VersionInfo>() {
                    @Override
                    public void onSuccess(VersionInfo versionInfo) {
                        if (versionInfo == null) return;
                        try {
                            PackageInfo pi = getPackageManager().getPackageInfo(getPackageName(), 0);
                            int nowVersionCode = pi.versionCode;
                            int versionCode = versionInfo.getVersionCode();
                            Log.d(nowVersionCode);
                            Log.d(versionCode);
                            if (versionCode > nowVersionCode) {
                                int isMust = versionInfo.getIsMust();
                                String remark = versionInfo.getRemark();
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);
                                switch (isMust) {
                                    case 0:
                                        alertDialog.setNegativeButton("暂不更新", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.dismiss();
                                            }
                                        });
                                        break;
                                    case 1:
                                        alertDialog.setCancelable(false);
                                        break;
                                }
                                alertDialog.setTitle("版本更新");
                                alertDialog.setMessage(remark);
                                alertDialog.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        progressDialog = new ProgressDialog(LoginActivity.this);
                                        progressDialog.setCancelable(false);
                                        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                                        progressDialog.setMessage("正在下载更新");
                                        progressDialog.show();
                                        loadNewVersionProgress(versionInfo.getPath());
                                    }
                                });
                                alertDialog.create().show();
                            }
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable ex) {

                    }
                }, null);
    }

    private void loadNewVersionProgress(String url) {
        OkGo.<File>get(url).tag(this).execute(new FileCallback() {
            @Override
            public void onSuccess(Response<File> response) {
                File file = response.body();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    //加入provider
                    Uri apkUri = FileProvider.getUriForFile(LoginActivity.this, "org.daomingedu.onecpexam.fileprovider", file);
                    //授予一个URI的临时权限
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
                } else {
                    intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                }
                startActivity(intent);
            }

            @Override
            public void downloadProgress(Progress progress) {
                super.downloadProgress(progress);
                progressDialog.setMax((int) progress.totalSize);
                progressDialog.setProgress((int) progress.currentSize);
            }
        });
    }

    private void resizeImageWelcome() {
        ImageView iv_welcome = (ImageView) findViewById(R.id.iv_welcome);
        ViewGroup.LayoutParams params = iv_welcome.getLayoutParams();
        int imageWidth = iv_welcome.getDrawable().getIntrinsicWidth();
        int imageHeight = iv_welcome.getDrawable().getIntrinsicHeight();
        params.height = getWindowWidth() * imageHeight / imageWidth;
        iv_welcome.setLayoutParams(params);
    }

    private int getWindowWidth() {
        WindowManager windowManager = getWindowManager();
        return windowManager.getDefaultDisplay().getWidth();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                validate();
                break;

        }
    }

    private void validate() {
        String code = et_code.getText().toString().trim();
        String phone = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            bd.toastFailed(getString(R.string.input_phone));
            et_phone.requestFocus();
        } else if (!StringUtils.isMobileNo(phone) && !"888888".equals(phone)) {
            bd.toastFailed(getString(R.string.illegal_phone));
            et_phone.requestFocus();
        } else if (TextUtils.isEmpty(code)) {
            bd.toastFailed(getString(R.string.input_code));
            et_code.requestFocus();
        } else {
//            if (superUser(code,phone)) return;
            login(code, phone);
        }
    }

    private void login(final String code, final String phone) {
        new Api(this, URLS.generateUrl(URLS.LOGIN))
                .add("loginPwd", code)
                .add("mobilePhone", phone)
                .post(new ObjCallback<Teacher>() {
                    @Override
                    public void onSuccess(Teacher obj) {

                        ConfigUtils.setParam(LoginActivity.this, "phone", phone);

                        MyApp.setTeacher(obj);
                        bd.jump(SelectRoomActivity.class);
                        finish();
                    }


                }, "正在登陆...");


    }


}
