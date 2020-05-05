package com.example.htgh;

import android.content.Intent;

import com.example.htgh.common.ApiService;
import com.example.htgh.datasource.NoticeDao;

import org.junit.Test;

public class NoticrTest {
    NoticeDao dao;
   @Test
   public void test1(){
       dao=new NoticeDao();
       Intent intent = new Intent();
       dao.getNotices( intent);
       while (true) {
           int status = intent.getIntExtra("requestStatus", -1);
           System.out.println("状态码：" + status);
           if (status != ApiService.LODING) {
               String response = intent.getStringExtra("response");
               System.out.println(response);
               break;
           }
       }
   }
}
