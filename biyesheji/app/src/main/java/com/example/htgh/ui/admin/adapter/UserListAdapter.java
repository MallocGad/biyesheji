package com.example.htgh.ui.admin.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.htgh.R;
import com.example.htgh.datasource.user.UserDao;
import com.example.htgh.ui.admin.AdminMainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 管理员界面中用户列表展示
 */
public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserListViewHolder>{
    private Context mContext;

    //list列表数据
    private JSONArray userList;

    public UserListAdapter(Context context, JSONArray userList){
        this.mContext=context;
        this.userList=userList;
    }

    @NonNull
    @Override
    public UserListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserListViewHolder(LayoutInflater.from(mContext).inflate(R.layout.user_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserListAdapter.UserListViewHolder holder, final int position) {
        JSONObject item=null;
        try {
            item=userList.getJSONObject(position);
            holder.username.setText(item.getString("userName"));
            holder.email.setText(item.getString("email"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //删除
        final JSONObject finalItem = item;
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog=new AlertDialog.Builder(mContext)
                        .setTitle("删除记录")
                        .setMessage("确定删除此用户吗?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int id=0;
                                try {
                                    id=finalItem.getInt("userId");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Intent intent=new Intent();
                                new UserDao().deleteUser(intent,id);
                                userList.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(0,userList.length()-1);

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
        //编辑
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.length();
    }

    class UserListViewHolder extends RecyclerView.ViewHolder {
        private TextView username,email;
        private ImageView delete,edit;
        //方便点击列表元素是触发事件
        private View item;
        public UserListViewHolder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.user_name);
            email=itemView.findViewById(R.id.user_email);
            delete=itemView.findViewById(R.id.delete_button);
            edit=itemView.findViewById(R.id.edit_button);
            item=itemView;
        }
    }
}
