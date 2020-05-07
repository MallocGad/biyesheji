package com.example.htgh.datasource.user;

import android.content.Intent;

import com.example.htgh.common.ApiService;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.PasswordAuthentication;

/**
 * 用户接口层請求
 */
public class UserDao {
    /**
     * 获取普通用户
     */
    public void getNormalUsers(Intent intent){
//        JSONObject param=new JSONObject();
        ApiService.sendRequest("/api-user/get-normal-users",null,intent);
    }

    /**
     * 新增
     * @param intent
     * @param username
     * @param pwd
     * @param email
     */
    public void addUser(Intent intent,String username,String pwd,String email){
        JSONObject param=new JSONObject();
        try {
            param.put("userName",username);
            param.put("password",pwd);
            param.put("email",email);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ApiService.sendRequest("/api-user/add-user",param,intent);
    }

    /**
     * 删除
     * @param intent
     * @param id
     */
    public void deleteUser(Intent intent,long id){
        JSONObject param=new JSONObject();
        try {
            param.put("userId",id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ApiService.sendRequest("api-user/delete-user",param,intent);
    }

    /**
     * 编辑用户
     * @param intent
     * @param id
     * @param userName
     * @param password
     * @param role
     * @param email
     */
    public void editUser(Intent intent,Long id,String userName, String password, String role, String email){
        JSONObject param=new JSONObject();
        try {
            param.put("userId",id);
            param.put("userName",userName);
            param.put("password",password);
            param.put("role",role);
            param.put("email",email);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ApiService.sendRequest("api-user/edit-user",param,intent);
    }
}
