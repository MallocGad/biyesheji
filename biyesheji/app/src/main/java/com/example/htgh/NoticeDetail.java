package com.example.htgh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.htgh.common.TimeUtils;

public class NoticeDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);
        TextView title,time,content;
        time=findViewById(R.id.time);
        title=findViewById(R.id.title);
        content=findViewById(R.id.content);
        Intent intent = getIntent();
        time.setText(TimeUtils.dateToString(TimeUtils.jsonDateToDate(intent.getStringExtra("createTime")).getTime()));
        title.setText(intent.getStringExtra("title"));
        content.setText(intent.getStringExtra("content"));
    }
}
