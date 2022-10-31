package org.daomingedu.onecpexam.ui;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.tabs.TabLayout;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;
import com.kyanogen.signatureview.SignatureView;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;

import org.daomingedu.onecpexam.R;
import org.daomingedu.onecpexam.base.BaseBackAct;
import org.daomingedu.onecpexam.base.Constant;
import org.daomingedu.onecpexam.base.MyApp;
import org.daomingedu.onecpexam.bean.CommentType;
import org.daomingedu.onecpexam.bean.ExamItem;
import org.daomingedu.onecpexam.bean.ExamItemComment;
import org.daomingedu.onecpexam.bean.QueryStudentInfo;
import org.daomingedu.onecpexam.bean.SampleComment;
import org.daomingedu.onecpexam.http.Api;
import org.daomingedu.onecpexam.http.ListCallback;
import org.daomingedu.onecpexam.http.ObjCallback;
import org.daomingedu.onecpexam.http.URLS;
import org.daomingedu.onecpexam.ui.adapter.MarkAdapter;
import org.daomingedu.onecpexam.ui.adapter.SampleCommentAdapter;
import org.daomingedu.onecpexam.utils.ImageUtils;
import org.daomingedu.onecpexam.utils.JsonParser;
import org.daomingedu.onecpexam.utils.Log;
import org.daomingedu.onecpexam.view.SampleCoverVideo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by jianhongxu on 3/23/21
 * Description
 */
public class SubTestActivity extends BaseBackAct implements View.OnClickListener, BaseQuickAdapter.OnItemClickListener {

    private QueryStudentInfo studentInfo;

    private List<CommentType> commentTypes = new ArrayList<>();
    private List<SampleComment> sampleComments = new ArrayList<>();
    private SampleCommentAdapter adapter;

    private ImageView iv_sound;
    private Dialog dialog;
    private EditText et_attend_comment;

    private SpeechRecognizer mIat;

    TabLayout tb_title;
    private RecyclerView lv_sample;
    private LinearLayout llMark;

    List<ExamItem> examItems = new ArrayList<>();

    private boolean isModifySignature = false; // 是否修改过签名，防止过多压缩bitmap


    private SampleCoverVideo sampleCoverVideo;


    private MediaPlayer mediaPlayer = new MediaPlayer();
    // 用HashMap存储听写结果
    private RecognizerListener mRecognizerListener = new RecognizerListener() {
        @Override
        public void onVolumeChanged(int i, byte[] bytes) {
            if (i <= 5) {
                iv_sound.setImageResource(R.mipmap.sound0);
            } else if (i <= 10) {
                iv_sound.setImageResource(R.mipmap.sound1);
            } else if (i <= 15) {
                iv_sound.setImageResource(R.mipmap.sound2);
            } else if (i <= 20) {
                iv_sound.setImageResource(R.mipmap.sound3);
            } else if (i <= 25) {
                iv_sound.setImageResource(R.mipmap.sound4);
            } else iv_sound.setImageResource(R.mipmap.sound5);
        }

        @Override
        public void onBeginOfSpeech() {

        }

        @Override
        public void onEndOfSpeech() {
            dialog.dismiss();
        }

        @Override
        public void onResult(RecognizerResult recognizerResult, boolean b) {
            printResult(recognizerResult);
            if (b) {
                dialog.dismiss();
            }
        }

        @Override
        public void onError(SpeechError speechError) {

        }

        @Override
        public void onEvent(int i, int i1, int i2, Bundle bundle) {

        }
    };

    /**
     * 打印结果
     *
     * @param results
     */
    private void printResult(RecognizerResult results) {
        String text = JsonParser.parseIatResult(results.getResultString());

        et_attend_comment.append(text);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sub_test);

        setTitle("评测");
        addNavBtn();

        initDialog();

        studentInfo = (QueryStudentInfo) getIntent().getSerializableExtra(Constant.TEST_STUDENT_INFO_EXTRA);



        sampleCoverVideo = (SampleCoverVideo) findViewById(R.id.scv_player_sub_test);

        ((TextView)findViewById(R.id.tvCatalog)).setText("曲目名称："+studentInfo.getCatalogName());

        GSYVideoOptionBuilder gsyVideoOptionBuilder = new GSYVideoOptionBuilder();
        GSYVideoManager.onPause();
        gsyVideoOptionBuilder
                .setIsTouchWiget(false)
                .setUrl(studentInfo.getPath())
                .setCacheWithPlay(false)
                .setRotateViewAuto(true)
                .setLockLand(true)
                .setPlayTag(TAG)
                .setShowFullAnimation(true)
                .setNeedLockFull(true)
                //.setPlayPosition(position)
                .setVideoAllCallBack(new GSYSampleCallBack() {
                    @Override
                    public void onPrepared(String url, Object... objects) {
                        super.onPrepared(url, objects);
                    }

                    @Override
                    public void onQuitFullscreen(String url, Object... objects) {
                        super.onQuitFullscreen(url, objects);
                    }

                    @Override
                    public void onPlayError(String url, Object... objects) {
                        super.onPlayError(url, objects);
                        GSYVideoManager.onPause();
                        gsyVideoOptionBuilder
                                .setUrl(studentInfo.getTransPath())
                                .setIsTouchWiget(false)
                                .setCacheWithPlay(false)
                                .setRotateViewAuto(true)
                                .setLockLand(true)
                                .setPlayTag(TAG)
                                .setShowFullAnimation(true)
                                .setNeedLockFull(true)
                                .build(sampleCoverVideo);
                        sampleCoverVideo.play();
                    }

                    @Override
                    public void onEnterFullscreen(String url, Object... objects) {
                        super.onEnterFullscreen(url, objects);
                        //SampleCoverVideo scvPlayer = helper.getView(R.id.scv_player);
                        //scvPlayer.getCurrentPlayer().getTitleTextView().setText((String)objects[0]);

                    }
                }).build(sampleCoverVideo);


        sampleCoverVideo.play();

        et_attend_comment = (EditText) findViewById(R.id.et_attend_comment);

        if (!TextUtils.isEmpty(studentInfo.getAttendComment()))
            et_attend_comment.append(studentInfo.getAttendComment());

        if (!TextUtils.isEmpty(studentInfo.getAttendScore()))
            ((EditText) findViewById(R.id.edScore)).append(studentInfo.getAttendScore());


        /**
         * 提交成绩点击事件
         */
        findViewById(R.id.bt_confirm).setOnClickListener(this);


        /**
         * 初始化讯飞语音
         */
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=5874c9be");
        mIat = SpeechRecognizer.createRecognizer(SubTestActivity.this, null);
        setParam();

        tb_title = (TabLayout) findViewById(R.id.tb_title);

        /**
         * 初始化listview
         */
        lv_sample = (RecyclerView) findViewById(R.id.lv_sample);
        llMark = (LinearLayout) findViewById(R.id.llMark);

        commentTypes = ((MyApp) getApplication()).getSampleCommentList();
        getSample();


        ImageView iv_record = (ImageView) findViewById(R.id.iv_record);

        iv_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIat.startListening(mRecognizerListener);
                dialog.show();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        GSYVideoManager.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }

    private void getSample() {
        new Api(this, URLS.generateUrl(URLS.GET_COMMENT))
                .post(new ListCallback<CommentType>() {
                    @Override
                    public void onSuccess(List<CommentType> list) {
                        commentTypes = list;
                        initSample();
                        ((MyApp) getApplication()).setSampleCommentList(list);
                    }
                });

        new Api(this, URLS.generateUrl(URLS.QUERY_EXAMS_ITEM))
                .post(new ListCallback<ExamItem>() {

                    @Override
                    public void onSuccess(List<ExamItem> list) {
                        examItems = list;
                        setMark(list);
                    }
                });


    }


    /**
     * 设置分级评论页面
     *
     * @param list
     */
    private void setMark(List<ExamItem> list) {
        JSONArray array = JSON.parseArray(studentInfo.getExamsItemJson());
        List<Map<String, String>> mlist = null;
        if (array != null)
            mlist = JSON.parseObject(array.toString(), List.class);
        Log.e(mlist);
        for (ExamItem item : list) {
            LinearLayout ll = new LinearLayout(this);
            ll.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            ll.setOrientation(LinearLayout.HORIZONTAL);

            ll.setPadding(30, 0, 0, 0);
            TextView tv = new TextView(this);
            tv.setText(item.getName() + ":");
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.gravity=Gravity.CENTER_VERTICAL;
            lp.setMargins(10,10,10,10);
            tv.setLayoutParams(lp);

            tv.setTextColor(ContextCompat.getColor(this, R.color.black_1f));


            ll.addView(tv);


            AppCompatSpinner as = new AppCompatSpinner(this);
            List<String> ls = new ArrayList<>();

            for (int i = 0; i < item.getExamsItemComment().size(); i++) {
                if (i == 0) {
                    ls.add("请选择");
                }
                ls.add(item.getExamsItemComment().get(i).getContent());
            }
            as.setAdapter(new ArrayAdapter(this, R.layout.item_text, R.id.tvSelect, ls));
            LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp2.gravity =Gravity.CENTER_VERTICAL;
            lp2.weight=1;
            as.setLayoutParams(lp2);
            as.setTag(item.getName());
            as.setPadding(0, 10, 10, 10);

            if (mlist != null)
                for (Map<String, String> m : mlist) {
                    if (item.getName().equals(m.get("item"))) {

                        as.setSelection(ls.indexOf(m.get("comment")));
                        break;
                    }
                }

            asList.add(as);
            ll.addView(as);


            LinearLayout llear = new LinearLayout(this);
            llear.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
            llear.setBackgroundColor(ContextCompat.getColor(this, R.color.black_9f));

            llMark.addView(ll);
            llMark.addView(llear);
        }

    }

    List<AppCompatSpinner> asList = new ArrayList<>();


    private void initSample() {
        if (commentTypes == null || commentTypes.size() == 0) return;
        sampleComments.addAll(commentTypes.get(0).getTestCommentList());
        adapter = new SampleCommentAdapter(R.layout.item_sample, sampleComments);
        adapter.setOnItemClickListener(SubTestActivity.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        lv_sample.setLayoutManager(layoutManager);
        lv_sample.setAdapter(adapter);


        for (CommentType c : commentTypes) {
            tb_title.addTab(tb_title.newTab().setText(c.getTypeName()));
        }

        tb_title.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e(tab.getText());
                sampleComments.clear();
                sampleComments.addAll(commentTypes.get(tab.getPosition()).getTestCommentList());
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /**
     * 语音输入参数设置
     */
    public void setParam() {
        // 清空参数
        mIat.setParameter(SpeechConstant.PARAMS, null);

        // 设置听写引擎
        mIat.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        // 设置返回结果格式
        mIat.setParameter(SpeechConstant.RESULT_TYPE, "json");
        // 设置语言
        mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        // 设置语言区域
        mIat.setParameter(SpeechConstant.ACCENT, "mandarin ");

        // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
        mIat.setParameter(SpeechConstant.VAD_BOS, "10000");

        // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
        mIat.setParameter(SpeechConstant.VAD_EOS, "10000");

        // 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
        mIat.setParameter(SpeechConstant.ASR_PTT, "1");
    }


    private void initDialog() {
        dialog = new Dialog(SubTestActivity.this, R.style.Dialog);
        dialog.setContentView(R.layout.dialog_speak);
        dialog.setCancelable(false);
        iv_sound = (ImageView) dialog.findViewById(R.id.iv_sound);
        Button bt_cancel = (Button) dialog.findViewById(R.id.bt_cancel);
        Button bt_finish = (Button) dialog.findViewById(R.id.bt_finish);
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIat.stopListening();
                dialog.dismiss();
            }
        });
        bt_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIat.stopListening();
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {
        List<Map<String, String>> selectlist = new ArrayList<>();
        for (AppCompatSpinner as : asList) {
            if (!as.getSelectedItem().toString().equals("请选择")) {
                Map<String, String> m = new HashMap<>();
                m.put("item", as.getTag().toString());
                m.put("comment", as.getSelectedItem().toString());
                selectlist.add(m);
            }
        }

        if (selectlist.size() < examItems.size()) {
            bd.toastFailed("请选择分项评语");
            return;
        }

        Log.e(selectlist);

        String attendScore = ((EditText) findViewById(R.id.edScore)).getText().toString();
        if (TextUtils.isEmpty(attendScore)) {
            bd.toastFailed("请输入分数");
            return;
        }

        if (0 > Integer.parseInt(attendScore) || Integer.parseInt(attendScore) > 100) {
            bd.toastFailed("请输入0-100区间的分数");
            return;
        }
        String attendComment = et_attend_comment.getText().toString();
        if (TextUtils.isEmpty(attendComment)) {
            bd.toastFailed("请输入评语");
            return;
        }

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_signature);
        SignatureView signatureView = dialog.findViewById(R.id.dialog_signatureview);
        Button btn_clear = dialog.findViewById(R.id.dialog_signature_clear);
        Button btn_commit = dialog.findViewById(R.id.dialog_signature_commit);
        Button btn_cancel = dialog.findViewById(R.id.dialog_signature_cancel);
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!signatureView.isBitmapEmpty()) {
                    isModifySignature = true;
                    signatureView.clearCanvas();
                }
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (signatureView.isBitmapEmpty()) {
                    bd.toastFailed("请先完成签名");
                    dialog.dismiss();
                    return;
                }
                String base64 = studentInfo.getSignature();
                if (isModifySignature || TextUtils.isEmpty(studentInfo.getSignature())) {
                    Bitmap bitmap = signatureView.getSignatureBitmap();
                    base64 = ImageUtils.drawableToByte(ImageUtils.compressBitmap(bitmap, 60));
                    Log.d("" + base64.length());
                }

                dialog.dismiss();

                new Api(SubTestActivity.this, URLS.generateUrl(URLS.SAVE_TEST_RESULT))
                        .add("sessionId", MyApp.getTeacher().getSessionId())
                        .add("testSignResultId", studentInfo.getTestSignResultId())
                        .add("attendScore", attendScore)
                        .add("attendComment", attendComment)
                        .add("signature", base64)
                        .add("examsItemJson", JSON.toJSONString(selectlist))
                        .post(new ObjCallback<Object>() {
                            @Override
                            public void onSuccess(Object obj) {
                                bd.toastSuccess("提交成功");
                                finish();
                            }


                        }, "正在提交评论");
            }
        });
        dialog.show();
        if (!TextUtils.isEmpty(studentInfo.getSignature())) {
            Bitmap bitmap = ImageUtils.base64ToBitmap(studentInfo.getSignature());
            signatureView.setBitmap(bitmap.copy(Bitmap.Config.ARGB_8888, true));
        }

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        et_attend_comment.append(sampleComments.get(position).getContent());
        et_attend_comment.setSelection(et_attend_comment.length());
    }


}
