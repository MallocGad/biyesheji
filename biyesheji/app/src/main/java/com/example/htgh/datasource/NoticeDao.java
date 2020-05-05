package com.example.htgh.datasource;

import android.content.Intent;

import com.example.htgh.common.ApiService;

/**
 * 信息获取层
 */
public class NoticeDao {
    public void getNotices(Intent intent){
        ApiService.sendRequest("/api-notice/get-notices",null,intent);
    }
}
