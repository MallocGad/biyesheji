package com.example.htgh.common;

import android.content.Context;
import android.content.Intent;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 接口调用公共服务层，全局的处理异常
 */
public class ApiService{
    //cookie丢失
    public static int COOKIELOSE=3;
    //网络请求出错
    public static int NETWORKERRO=1;
    //请求中
    public static int LODING=2;
    //成功
    public static int SUCCESS=0;
    public static String BaseUrl="http://"+ Variables.ip+":"+Variables.port+"/vg";
    private String url;
    private JSONObject parameter;
    private Context context;
    //返回的数据
    private JSONObject data;
    public ApiService(String url,JSONObject parameter,Context context){

    }
    public ApiService(){

    }

    /**
     * @param url 相对于:8888/vg的请求链接
     * @param parameter 请求参数 String.valueOf(json)转换的字符串
     * @return 返回构造好的call
     */
    public static Call builCall(String url, JSONObject parameter){
        OkHttpClient client=
                new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(20,TimeUnit.SECONDS).build();
        final JSONObject jsonObject=new JSONObject();
        MediaType mediaType = MediaType.parse("application/json; charset=utf8");
        JSONObject data=null;
        final RequestBody requestBody=RequestBody.create(String.valueOf(parameter),mediaType);
        Request request = new Request.Builder().url(BaseUrl + "/" + url).post(requestBody).build();
        Call call = client.newCall(request);
        return call;
    }

    /**
     * @param url 相对于:8888/vg的请求链接
     * @param parameter 请求参数 String.valueOf(json)转换的字符串
     * @param  cookies 可以携带cookie 如sessionid
     * @return 返回构造好的call
     */
    public static Call builCall(String url, JSONObject parameter,String cookies){
        OkHttpClient client=
                new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(20,TimeUnit.SECONDS).build();
        final JSONObject jsonObject=new JSONObject();
        MediaType mediaType = MediaType.parse("application/json; charset=utf8");
        JSONObject data=null;
        final RequestBody requestBody=RequestBody.create(String.valueOf(parameter),mediaType);
        Request request = new Request.Builder().url(BaseUrl + "/" + url).post(requestBody).addHeader("cookie",cookies).build();
        Call call = client.newCall(request);
        return call;
    }

    /**
     *请求封装
     * @param url
     * @param parameter
     * @param intent
     */
    public static void sendRequest(String url, JSONObject parameter,final Intent intent){
        MyApplication application = MyApplication.getApplication();
        Call call =null;
        //持久会话
        String sessionId =null;
        if(null!=application)
            sessionId=(String)application.getAttribute("sessionId");
        if(null !=sessionId&&!("".equals(sessionId))){
            call=builCall(url, parameter,sessionId);
        }else {
            call = builCall(url, parameter);
        }
        //加载中
        intent.putExtra("requestStatus",LODING);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                intent.putExtra("requestStatus",NETWORKERRO);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                intent.putExtra("response", response.body().string());
                intent.putExtra("requestStatus",SUCCESS);
            }
        });
    }

}

