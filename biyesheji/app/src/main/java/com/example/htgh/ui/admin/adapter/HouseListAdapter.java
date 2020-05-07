package com.example.htgh.ui.admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.htgh.R;
import com.example.htgh.common.StringUtils;

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
    public void onBindViewHolder(@NonNull HouseListViewHolder holder, int position) {
        JSONObject item=null;
        try {
            item = list.getJSONObject(position);
            holder.houseName.setText(item.getString("houseName"));
            holder.isDistributed.setText(
                            item.getString("houseOwnerId").equals("null")?"未分配":"已分配");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
