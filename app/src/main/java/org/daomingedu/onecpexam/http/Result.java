package org.daomingedu.onecpexam.http;

import java.io.Serializable;


/**
 * 用户封装接口返回数据
 *
 * @author yushifei
 *         <p>
 *         2015-9-8
 */
public class Result implements Serializable {

    private int code;
    private String msg;
    private String data;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


}
