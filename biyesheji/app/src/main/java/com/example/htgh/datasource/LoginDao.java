package com.example.htgh.datasource;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.htgh.common.ApiService;
import com.example.htgh.common.MyApplication;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Response;

/**
 * 用户登录层，涉及到网络请求
 */
public class LoginDao {
    //传递信息的载体
    private Intent intent;
    //请求是否完成标志 -1：请求中，0：成功 1：网络错误
    private int status;

    /**
     * @param intent 用于传递消息
     */
    public LoginDao(Intent intent) {
        this.intent = intent;
    }

    /**
     * 用户的登录
     *
     * @param username
     * @param pwd
     * @param role     登录类型 user 普通用户 admin 管理员
     * @return 是否成功
     */
    public boolean login(String username, String pwd, String role) {
        JSONObject param = new JSONObject();
        try {
            param.put("userName", username);
            param.put("password", pwd);
            param.put("role", role);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //开始请求
        status = ApiService.LODING;
        Call call = ApiService.builCall("login", param);
        //发起请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                //网络请求失败
                status = ApiService.NETWORKERRO;
                Log.d("网络错误",e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //网络请求成功
//                Log.d("login请求返回:",response.body().string());
                intent.putExtra("response", response.body().string());
                //保持回话
                Headers headers = response.headers();
                List<String> cookies = headers.values("Set-Cookie");
                if(!cookies.isEmpty()){
                    String session = cookies.get(0);
                    String sessionId = session.substring(0, session.indexOf(";"));
                    //会将话存储在application中，生命周期与app一样
                    MyApplication.setSessionId(sessionId);
                    status = ApiService.SUCCESS;
                }
                status=ApiService.COOKIELOSE;
            }
        });
        return true;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
