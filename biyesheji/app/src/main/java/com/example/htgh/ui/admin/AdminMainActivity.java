package com.example.htgh.ui.admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.htgh.R;

public class AdminMainActivity extends AppCompatActivity {

    private LinearLayout userManage;
    private LinearLayout houseManage;
    private LinearLayout plantManage;
    private LinearLayout noticeManage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        userManage=findViewById(R.id.user_manage);
        houseManage=findViewById(R.id.house_manage);
        plantManage=findViewById(R.id.plant_manage);
        noticeManage=findViewById(R.id.notice_manage);

        Button exit=findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog=new AlertDialog.Builder(AdminMainActivity.this)
                        .setTitle("退出程序")
                        .setMessage("确定退出程序吗?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AdminMainActivity.this.finish();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        }).create();
                alertDialog.show();

            }
        });

        class MyLister implements View.OnClickListener{

            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.user_manage:{ startActivity(new Intent(AdminMainActivity.this,AdminUserManage.class)); break;}
                    case R.id.house_manage:{startActivity(new Intent(AdminMainActivity.this,AdminHouseManage.class));break;}
                    case R.id.plant_manage:{startActivity(new Intent(AdminMainActivity.this,AdminPlantManage.class));break;}
                    case R.id.notice_manage:{startActivity(new Intent(AdminMainActivity.this,AdminNoticeManage.class));break;}
                    default:break;
                }

            }
        }
        MyLister lister=new MyLister();
        userManage.setOnClickListener(lister);
        houseManage.setOnClickListener(lister);
        plantManage.setOnClickListener(lister);
        noticeManage.setOnClickListener(lister);
    }
}
