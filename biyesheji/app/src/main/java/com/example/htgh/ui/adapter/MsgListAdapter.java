package com.example.htgh.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.htgh.NoticeDetail;
import com.example.htgh.R;
import com.example.htgh.common.TimeUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * 普通用户中 信息列表展示
 */
public class MsgListAdapter extends RecyclerView.Adapter<MsgListAdapter.MsgListViewHolder> {

    private Context mContext;

    //list列表数据
    private JSONArray msgList;
    //list传入
    public MsgListAdapter(Context context,JSONArray notices){
        this.mContext=context;
        this.msgList=notices;
    }

    @NonNull
    @Override
    public MsgListAdapter.MsgListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //这里传入的view就是每个item的布局
        //这里怎么传入的，分别什么意思？
        return new MsgListViewHolder(LayoutInflater.from(mContext).inflate(R.layout.main_msg_list_item,parent,false));
    }

    //这里为item中的各个组件元素设置内容
    @Override
    public void onBindViewHolder(@NonNull MsgListAdapter.MsgListViewHolder holder, final int position) {
        JSONObject item=null;
        try {
            item = msgList.getJSONObject(position);
            holder.notice_title.setText(item.getString("title"));
            Date time = TimeUtils.jsonDateToDate(item.getString("createTime"));
            holder.notice_time.setText((time.getMonth()+1)+"-"+time.getDate());
            final String content = item.getString("content");
            holder.notice_content.setText(content.substring(0,18)+(content.length()>18?"...":""));
            final JSONObject finalItem = item;
            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(mContext, NoticeDetail.class);
                    try {
                        intent.putExtra("title", finalItem.getString("title"));
                        intent.putExtra("createTime",finalItem.getString("createTime"));
                        intent.putExtra("content",finalItem.getString("content"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    mContext.startActivity(intent);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        holder.textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    //这里返回列表长度
    @Override
    public int getItemCount() {
        return msgList.length();
    }

    //这里获取listitem中的view
    class MsgListViewHolder extends RecyclerView.ViewHolder{

        private TextView notice_title,notice_content,notice_time;
        private View item;
        public MsgListViewHolder(View itemView){
            super(itemView);
            item=itemView;
            notice_title=itemView.findViewById(R.id.notice_title);
            notice_content=itemView.findViewById(R.id.notice_content);
            notice_time=itemView.findViewById(R.id.notice_time);
        }
    }
}
