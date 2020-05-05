package com.example.htgh.datasource.plant;

import android.content.Intent;

import com.example.htgh.common.ApiService;

import org.json.JSONArray;

import java.util.List;

public class PlantDao {
    //获取用户和植物列表
    JSONArray users,plants;
    public void getPlants(Intent intent){
        ApiService.sendRequest("api-plant/find-all-plant",null,intent);
    }

}
