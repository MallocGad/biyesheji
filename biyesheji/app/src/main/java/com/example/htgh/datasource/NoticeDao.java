package com.example.htgh.datasource;

import android.content.Intent;

import com.example.htgh.common.ApiService;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 信息获取层
 */
public class NoticeDao {
    public void getNotices(Intent intent){
        ApiService.sendRequest("/api-notice/get-notices",null,intent);
    }
    public void addNotice(Intent intent,String title,String content){
        JSONObject param=new JSONObject();
        try {
            param.put("title",title);
            param.put("content",content);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ApiService.sendRequest("api-notice/add-notice",param,intent);
    }
}
