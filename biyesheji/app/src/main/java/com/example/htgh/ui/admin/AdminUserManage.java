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
import com.example.htgh.datasource.user.UserDao;
import com.example.htgh.ui.admin.adapter.UserListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdminUserManage extends AppCompatActivity {

    UserDao userDao;
    Intent intent;
    RecyclerView userList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_manage);
        userList=findViewById(R.id.user_list);
        intent=getIntent();
        userDao=new UserDao();
        displayUsers();
        ImageView add=findViewById(R.id.addButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(AdminUserManage.this, AdminAddUser.class);
                startActivity(intent);
            }
        });
    }

    //当activity为单例时 若已存在会调用此方法
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        displayUsers();
    }

    //展示用户
    public void displayUsers(){
        JSONArray users = getUsers();
        userList.setLayoutManager(new LinearLayoutManager(AdminUserManage.this));
        userList.setAdapter(new UserListAdapter(this,users));
        userList.setItemAnimator(new DefaultItemAnimator());
        userList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));

    }
    public JSONArray getUsers(){
        JSONArray users=null;
        userDao.getNormalUsers(intent);
        while (true) {
            int status = intent.getIntExtra("requestStatus", -1);
            System.out.println("状态码：" + status);
            if (status == ApiService.SUCCESS) {
                String response = intent.getStringExtra("response");
                try {
                    JSONObject obj=new JSONObject(response);
                    users=obj.getJSONArray("data");
                } catch (JSONException e) {

                }
//                System.out.println(response);
                break;
            }
        }
        return users;
    }
}
