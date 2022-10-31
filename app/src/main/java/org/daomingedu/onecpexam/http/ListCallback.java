package org.daomingedu.onecpexam.http;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class ListCallback<T> extends MyCallBack<T> {
    public Class<T> clazz;

    public ListCallback() {
        //拿到范型
        ParameterizedType genType = (ParameterizedType) getClass().getGenericSuperclass();
        //得到范型数组
        Type[] params = genType.getActualTypeArguments();
        //返回范型对应的实体类
        clazz = (Class) params[0];
    }
    public ListCallback(Class<T> clazz) {
        this.clazz = clazz;
    }

    public abstract void onSuccess(List<T> list);



    public Class<T> getEntityClass() {
        return clazz;
    }
}