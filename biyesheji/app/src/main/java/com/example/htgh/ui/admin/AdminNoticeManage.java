package com.example.htgh.ui.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.htgh.R;
import com.example.htgh.common.ApiService;
import com.example.htgh.datasource.NoticeDao;
import com.example.htgh.ui.admin.adapter.NoticeListAdapter;
import com.example.htgh.ui.admin.adapter.UserListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdminNoticeManage extends AppCompatActivity {

    NoticeDao noticeDao;
    Intent intent;
    RecyclerView noticeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notice_manage);
        noticeDao=new NoticeDao();
        intent=new Intent();
        noticeList=findViewById(R.id.notice_list);
        displayNotices();
        ImageView add=findViewById(R.id.addButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });
    }

    public void displayNotices(){
        JSONArray notices = getNotices();
        noticeList.setLayoutManager(new LinearLayoutManager(AdminNoticeManage.this));
        noticeList.setAdapter(new NoticeListAdapter(this,notices));
        noticeList.setItemAnimator(new DefaultItemAnimator());
        noticeList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));

    }

    public JSONArray getNotices(){
        JSONArray notices=null;
        noticeDao.getNotices(intent);
        while (true) {
            int status = intent.getIntExtra("requestStatus", -1);
            System.out.println("状态码：" + status);
            if (status == ApiService.SUCCESS) {
                String response = intent.getStringExtra("response");
                try {
                    JSONObject obj=new JSONObject(response);
                    notices=obj.getJSONArray("data");
                } catch (JSONException e) {

                }
//                System.out.println(response);
                break;
            }
        }
        return notices;
    }
}
