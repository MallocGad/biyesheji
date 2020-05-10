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
import com.example.htgh.common.TimeUtils;
import com.example.htgh.datasource.plant.PlantDao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * 管理员界面中 植物列表展示
 */
public class PlantListAdapter  extends RecyclerView.Adapter{
    private Context context;
    private JSONArray list;

    public PlantListAdapter(Context context, JSONArray list){
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlantListAdapter.PlantViewHolder(LayoutInflater.from(context).inflate(R.layout.plant_list_item,parent,false));
    }

    /**
     * 设置种子信息
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        PlantListAdapter.PlantViewHolder plantHolder=(PlantListAdapter.PlantViewHolder)holder;
        JSONObject item=null;
        try {
            item = list.getJSONObject(position);
            plantHolder.name.setText(item.getString("plantName"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final JSONObject finalItem = item;
        plantHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog=new AlertDialog.Builder(context)
                        .setTitle("删除记录")
                        .setMessage("确定删除此记录吗?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int id=0;
                                try {
                                    id= finalItem.getInt("plantId");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Intent intent=new Intent();
                                new PlantDao().deletePlants(intent,new Long(id));
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

        plantHolder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"功能暂未开放",Toast.LENGTH_SHORT).show();

            }
        });

        //单击自己时发生
        plantHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.length();
    }

    class PlantViewHolder extends RecyclerView.ViewHolder{
        private ImageView editButton,deleteButton;
        private TextView name;
        private View view;
        public PlantViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
            name=itemView.findViewById(R.id.plant_name);
            editButton=itemView.findViewById(R.id.edit_button);
            deleteButton=itemView.findViewById(R.id.delete_button);
        }
    }
}
