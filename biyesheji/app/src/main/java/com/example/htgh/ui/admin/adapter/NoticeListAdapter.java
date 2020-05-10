package com.example.htgh.ui.admin.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.htgh.R;
import com.example.htgh.common.ApiService;
import com.example.htgh.common.StringUtils;
import com.example.htgh.common.TimeUtils;
import com.example.htgh.datasource.NoticeDao;
import com.example.htgh.datasource.plant.PlantDao;

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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
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
        final JSONObject finalItem = item;
        noticeHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog=new AlertDialog.Builder(context)
                        .setTitle("删除记录")
                        .setMessage("确定删除此通知吗?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int id=0;
                                try {
                                    id= finalItem.getInt("noticeId");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Intent intent=new Intent();
                                new NoticeDao().deleteNotice(intent,new Long(id));
                                while(true) {
                                    int status = intent.getIntExtra("requestStatus", -1);
                                    System.out.println("状态码：" + status);
                                    if (status != ApiService.LODING) {
                                        String response = intent.getStringExtra("response");
                                        System.out.println(response);
                                        break;
                                    }
                                }
                                list.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(0,list.length()-1);

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

        noticeHolder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"功能暂未开放",Toast.LENGTH_SHORT).show();

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
