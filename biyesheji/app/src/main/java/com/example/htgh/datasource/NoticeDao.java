package com.example.htgh.datasource;

import android.content.Intent;

import com.example.htgh.common.ApiService;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 信息获取层
 */
public class NoticeDao {
    /**
     * 查询通知
     * @param intent
     */
    public void getNotices(Intent intent){
        ApiService.sendRequest("/api-notice/get-notices",null,intent);
    }

    /**
     * 新增通知
     * @param intent
     * @param title
     * @param content
     */
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

    /**
     * 删除
     * @param intent
     * @param id
     */
    public void deleteNotice(Intent intent,Long id){
        JSONObject param=new JSONObject();
        try {
            param.put("plantId",id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ApiService.sendRequest("api-notice/delete-notice",param,intent);
    }
}
