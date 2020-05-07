package com.example.htgh.ui.admin.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.htgh.R;
import com.example.htgh.common.StringUtils;
import com.example.htgh.common.TimeUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * 管理员界面中通知列表展示
 */
public class NoticeListAdapter extends RecyclerView.Adapter {
    private Context context;
    private JSONArray list;

    public NoticeListAdapter(Context context, JSONArray list){
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoticeViewHolder(LayoutInflater.from(context).inflate(R.layout.notice_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NoticeViewHolder noticeHolder=(NoticeViewHolder)holder;
        JSONObject item=null;
        try {
            item = list.getJSONObject(position);
            noticeHolder.title.setText(item.getString("title"));
            Date createTime = TimeUtils.jsonDateToDate(item.getString("createTime"));
            noticeHolder.time.setText(createTime.getMonth()+1+"/"+createTime.getDate()+" "+createTime.getHours()+":"+createTime.getMinutes());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        noticeHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        noticeHolder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //单击自己时发生
        noticeHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.length();
    }

    class NoticeViewHolder extends RecyclerView.ViewHolder{
        private ImageView editButton,deleteButton;
        private TextView title,time;
        private View view;
        public NoticeViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
            title=itemView.findViewById(R.id.title);
            time=itemView.findViewById(R.id.time);
            editButton=itemView.findViewById(R.id.edit_button);
            deleteButton=itemView.findViewById(R.id.delete_button);
        }
    }
}
