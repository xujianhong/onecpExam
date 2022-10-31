package org.daomingedu.onecpexam.http;

/**
 * Created by xjh on 2016/9/23.
 */

public enum ResultCodeEnum {
    SUCCESS {public int getVal(){return 0;}public String getName(){return "Success!";}},//
    TIMEOUT {public int getVal(){return 1001;}public String getName(){return "登陆超时!";}},
    BUSINESSRROR {public int getVal(){return 203;}public String getName(){return "业务逻辑错误!";}},
    SERVICEERROR {public int getVal(){return 202;}public String getName(){return "服务器异常!";}},
    PARAMERROR {public int getVal(){return 201;}public String getName(){return "参数错误!";}} ;//
    public abstract int getVal();
    public abstract String getName();
}
