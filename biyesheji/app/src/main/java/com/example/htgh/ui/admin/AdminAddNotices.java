package com.example.htgh.ui.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.htgh.R;
import com.example.htgh.common.ApiService;
import com.example.htgh.datasource.NoticeDao;

public class AdminAddNotices extends AppCompatActivity {

    private  EditText title,content;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_notices);
        title=findViewById(R.id.notice_title);
        content=findViewById(R.id.notice_content);
        submit=findViewById(R.id.submit);
        init();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                new NoticeDao().addNotice(intent,title.getText().toString(),content.getText().toString());
                while(true) {
                    int status = intent.getIntExtra("requestStatus", -1);
                    System.out.println("状态码：" + status);
                    if (status != ApiService.LODING) {
                        String response = intent.getStringExtra("response");
                        //处理用户名重复的问题看是后台自动生成还是咋的
//                        System.out.println(response);
                        break;
                    }
                }
                startActivity(new Intent(AdminAddNotices.this,AdminNoticeManage.class));
                AdminAddNotices.this.finish();
            }
        });
    }

    public void init(){
        submit.setEnabled(false);
        TextWatcher titleWatch=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==0) {
                    submit.setEnabled(true);
                    title.setError("标题不能为空");
                }
            }
        };
        title.addTextChangedListener(titleWatch);
    }
}
