package org.daomingedu.onecpexam.http;







import org.daomingedu.onecpexam.utils.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by jianhongxu on 2017/8/16.
 */

public class LogInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
//
//                Request request = original.newBuilder()
//                        .addHeader("Accept", "application/json")
//                        .method(original.method(), original.body())
//                        .build();

        long startTime = System.currentTimeMillis();
        Response response = chain.proceed(request);
        long endTime = System.currentTimeMillis();
        long duration=endTime-startTime;
        MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        Log.d("\n");
        Log.d("----------Start----------------");
        Log.d( "| "+request.headers().toString());
        Log.d( "| "+request.toString());
        String method=request.method();
        if("POST".equals(method)){
            StringBuilder sb = new StringBuilder();
            if (request.body() instanceof FormBody) {
                FormBody body = (FormBody) request.body();
                for (int i = 0; i < body.size(); i++) {
                    sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                }
                sb.delete(sb.length() - 1, sb.length());
                Log.d("| RequestParams:{"+sb.toString()+"}");
            }
        }
        Log.d("| Response:" + content);
        Log.d("----------End:"+duration+"毫秒----------");

        return response.newBuilder().body(ResponseBody.create(MediaType.parse("UTF-8"), content)).build();
    }
}
