package org.daomingedu.onecpexam.http;



import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class ObjCallback<T> extends MyCallBack {
    public Class<T> clazz;

    public ObjCallback() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        clazz = (Class) params[0];
    }
    public ObjCallback(Class<T> clazz) {
        this.clazz = clazz;
    }

    public abstract void onSuccess(T obj);




    public Class<T> getEntityClass() {
        return clazz;
    }
}