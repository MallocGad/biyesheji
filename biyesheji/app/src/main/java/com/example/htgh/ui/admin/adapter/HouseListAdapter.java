package com.example.htgh.ui.admin.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.htgh.datasource.House.HouseDao;
import com.example.htgh.datasource.NoticeDao;
import com.example.htgh.ui.admin.AdminEditHouse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 管理员界面中  温室列表展示
 */
public class HouseListAdapter extends RecyclerView.Adapter<HouseListAdapter.HouseListViewHolder> {
    private JSONArray list;
    private Context context;

    public HouseListAdapter(Context context,JSONArray list){
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public HouseListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HouseListViewHolder(LayoutInflater.from(context).inflate(R.layout.house_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HouseListViewHolder holder, final int position) {
        JSONObject item=null;
        try {
            item = list.getJSONObject(position);
            holder.houseName.setText(item.getString("houseName"));
            holder.isDistributed.setText(
                            item.getString("houseOwnerId").equals("null")?"未分配":"已分配");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final JSONObject finalItem = item;
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog=new AlertDialog.Builder(context)
                        .setTitle("删除记录")
                        .setMessage("确定删除此温室吗?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int id=0;
                                try {
                                    id= finalItem.getInt("houseId");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Intent intent=new Intent();
                                new HouseDao().deleteHouse(intent,new Long(id));
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

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject item=null;
                try {
                    item=list.getJSONObject(position);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent intent=new Intent(context, AdminEditHouse.class);
                intent.putExtra("house",item.toString());
                context.startActivity(intent);
            }
        });

        //单击自己时发生
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.length();
    }

    class HouseListViewHolder extends RecyclerView.ViewHolder{
        private ImageView editButton,deleteButton;
        private TextView houseName,isDistributed;
        private View view;
        public HouseListViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
            houseName=itemView.findViewById(R.id.house_name);
            isDistributed=itemView.findViewById(R.id.is_distributed);
            editButton=itemView.findViewById(R.id.edit_button);
            deleteButton=itemView.findViewById(R.id.delete_button);
        }
    }
}
