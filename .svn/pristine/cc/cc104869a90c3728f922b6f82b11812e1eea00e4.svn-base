package org.daomingedu.onecpexam.http;

import android.app.Activity;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;


import org.daomingedu.onecpexam.base.Buddy;
import org.daomingedu.onecpexam.ui.LoginActivity;
import org.daomingedu.onecpexam.utils.Log;

import java.io.File;

import okhttp3.Call;
import okhttp3.Request;


public class Api {
    private long timeout=0L;

    private Buddy bd = null;
//	public RequestParams params;

    Activity context;
    PostFormBuilder formBuilder;
    GetBuilder getBuilder;

    public Api(Activity context, String urls) {
        bd = new Buddy(context);
        this.context = context;
        formBuilder = new PostFormBuilder();
        formBuilder.url(urls);

        getBuilder = new GetBuilder();
        getBuilder.url(urls);
    }


    public Api add(String name, String value) {
        if (!TextUtils.isEmpty(value)){
            formBuilder.addParams(name, value);
            getBuilder.addParams(name, value);
        }
        return this;
    }

    public Api addfile(String name, File value) {
        formBuilder.addFile("file", name, value);
        return this;
    }
    public Api addfile(String name, String value) {
        formBuilder.addFile(name,  value,null);
        return this;
    }

    /**设置请求超时*/
    public Api timeout(Long timeout){
        this.timeout = timeout;
        return this;
    }


    public void post(final ListCallback callback, final ResultFailCallback resultFailCallback, final String loadstr) {
        basePost(callback, resultFailCallback, loadstr);
    }

    public void post(final ListCallback callback, final String loadstr) {
        basePost(callback, null, loadstr);

    }

    public void post(final ListCallback callback) {
        basePost(callback, null, null);

    }

    public void post(final ObjCallback callback, final ResultFailCallback resultFailCallback, final String loadstr) {
        basePost(callback, resultFailCallback, loadstr);

    }

    public void post(final ObjCallback callback, final String loadstr) {
        basePost(callback, null, loadstr);
    }

    public void get(final ObjCallback callback, final String loadstr) {
        baseGet(callback, null, loadstr);
    }

    private void baseGet(final MyCallBack callBack, final ResultFailCallback resultFailCallback, final String loadstr) {
        if (!TextUtils.isEmpty(loadstr)) bd.showProgress(loadstr);
//

        getBuilder
                .tag(context)
                .build()
                .connTimeOut(timeout)
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(Request request, int id) {
                        Log.e("onBefore" + request.toString() + "    " + id);
                        super.onBefore(request, id);
                    }

                    @Override
                    public void onAfter(int id) {
                        Log.e("onAfter" + id);
                        super.onAfter(id);
                    }

                    @Override
                    public void onError(Call call, Exception ex, int id) {
                        bd.cancelProgress();
                        Log.e(ex.getMessage());
                        if (ex instanceof java.net.SocketTimeoutException)//链接超时
                        {
                            bd.toastFailed("请求超时");
                        } else {
                            if (!call.isCanceled())
                                bd.toastFailed("网络异常");
                        }
                        callBack.onError(ex);
                    }

                    @Override
                    public void onResponse(String json, int id) {
                        Log.w("返回数据：" + json);

                        bd.cancelProgress();
                        //数据解析
                        Result result = null;
                        try {
                            result = JSON.parseObject(json, Result.class);
                        } catch (Exception e) {
                            bd.toastFailed("无法解析返回数据");
                            return;
                        }
                        if (result.getCode() == ResultCodeEnum.SUCCESS.getVal())//成功
                        {
                            Log.e(result.getCode() == ResultCodeEnum.SUCCESS.getVal() );
                            if (callBack != null && callBack instanceof ListCallback) {
                                ListCallback listCallback = (ListCallback) callBack;
//                        try {
                                listCallback.onSuccess(JSON.parseArray(result.getData(), listCallback.getEntityClass()));
//                        } catch (Exception ex) {
//                            bd.toastFailed("解析" + listCallback.getEntityClass().getSimpleName() + "失败");
//                        }
                            } else if (callBack != null && callBack instanceof ObjCallback) {
                                ObjCallback objCallback = (ObjCallback) callBack;
                                objCallback.onSuccess(JSON.parseObject(result.getData(), objCallback.getEntityClass()));
//                        } catch (Exception e) {
//                            bd.toastFailed("解析" + objCallback.getEntityClass().getSimpleName() + "失败");
//                            return;
//                        }
                            }

                        } else if (result.getCode() == ResultCodeEnum.TIMEOUT.getVal()) {//登陆超时
                            bd.toastFailed("登录超时");
                            bd.back();

                            bd.jump(LoginActivity.class);
                        } else if (resultFailCallback == null) {
                            bd.toastFailed(result.getMsg());
                        } else {
                            resultFailCallback.onResultFail(result.getCode(), result.getMsg());
                        }
                    }
                });
    }

    private void basePost(final MyCallBack callBack, final ResultFailCallback resultFailCallback, final String loadstr) {
        if (!TextUtils.isEmpty(loadstr)) bd.showProgress(loadstr);
//

        formBuilder
                .tag(context)
                .build()
                .connTimeOut(timeout)
                .execute(new StringCallback() {
            @Override
            public void onBefore(Request request, int id) {
                Log.e("onBefore" + request.toString() + "    " + id);
                super.onBefore(request, id);
            }

            @Override
            public void onAfter(int id) {
                Log.e("onAfter" + id);
                super.onAfter(id);
            }

            @Override
            public void onError(Call call, Exception ex, int id) {
                bd.cancelProgress();
                Log.e(ex.getMessage());
                if (ex instanceof java.net.SocketTimeoutException)//链接超时
                {
                    bd.toastFailed("请求超时");
                } else {
                    if (!call.isCanceled())
                        bd.toastFailed("网络异常");
                }
                callBack.onError(ex);
            }

            @Override
            public void onResponse(String json, int id) {
                Log.w("返回数据：" + json);

                bd.cancelProgress();
                //数据解析
                Result result = null;
                try {
                    result = JSON.parseObject(json, Result.class);
                } catch (Exception e) {
                    bd.toastFailed("无法解析返回数据");
                    return;
                }
                if (result.getCode() == ResultCodeEnum.SUCCESS.getVal())//成功
                {
                    if (callBack != null && callBack instanceof ListCallback) {
                        ListCallback listCallback = (ListCallback) callBack;
//                        try {
                            listCallback.onSuccess(JSON.parseArray(result.getData(), listCallback.getEntityClass()));
//                        } catch (Exception ex) {
//                            bd.toastFailed("解析" + listCallback.getEntityClass().getSimpleName() + "失败");
//                        }
                    } else if (callBack != null && callBack instanceof ObjCallback) {
                        ObjCallback objCallback = (ObjCallback) callBack;
//                        try {

                            new Gson().toJson(JSON.parseObject(result.getData()));
                            objCallback.onSuccess(JSON.parseObject(result.getData(), objCallback.getEntityClass()));
//                        } catch (Exception e) {
//                            bd.toastFailed("解析" + objCallback.getEntityClass().getSimpleName() + "失败");
//                            return;
//                        }
                    }

                } else if (result.getCode() == ResultCodeEnum.TIMEOUT.getVal()) {//登陆超时
                    bd.toastFailed("登录超时");
                    bd.back();

                    bd.jump(LoginActivity.class);

                } else if (resultFailCallback == null) {
                    bd.toastFailed(result.getMsg());
                } else {
                    resultFailCallback.onResultFail(result.getCode(), result.getMsg());
                }
            }
        });
    }

    ///-------------------------------------------------------------

    public interface ResultFailCallback {
        void onResultFail(int code, String msg);
    }
}
