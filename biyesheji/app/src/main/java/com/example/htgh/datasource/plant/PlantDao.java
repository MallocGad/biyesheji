package com.example.htgh.datasource.plant;

import android.content.Intent;

import com.example.htgh.common.ApiService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class PlantDao {
    //获取用户和植物列表
    JSONArray users,plants;

    /**
     * 查找
     * @param intent
     */
    public void getPlants(Intent intent){
        ApiService.sendRequest("api-plant/find-all-plant",null,intent);
    }

    /**
     * 删除
     * @param intent
     * @param plantId
     */
    public void deletePlants(Intent intent,Long plantId){
        JSONObject param=new JSONObject();
        try {
            param.put("plantId",plantId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ApiService.sendRequest("api-plant/delete-plant",param,intent);
    }

    public void addPlants(Intent intent, String plantName, String plantMinOC, String plantMaxOC,
                          String plantComment, String plant_minPH, String plant_maxPH,
                          String plant_maxRH, String plant_minRH, String plant_maxLx,
                          String plant_minLx, String plant_maxPpm, String plant_minPpm){
        JSONObject param=new JSONObject();
        try {
            param.put("plantName",plantName);
            param.put("plantMinOC",plantMinOC);
            param.put("plantMaxOC",plantMaxOC);
            param.put("plantComment",plantComment);
            param.put("plant_minPH",plant_minPH);
            param.put("plant_maxPH",plant_maxPH);
            param.put("plant_maxRH",plant_maxRH);
            param.put("plant_minRH",plant_minRH);
            param.put("plant_maxLx",plant_maxLx);
            param.put("plant_minLx",plant_minLx);
            param.put("plant_maxPpm",plant_maxPpm);
            param.put("plant_minPpm",plant_minPpm);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ApiService.sendRequest("api-plant/add-plant",param,intent);
    }

    /**
     * 编辑
     * @param intent
     * @param plantId
     * @param plantName
     * @param plantMinOC
     * @param plantMaxOC
     * @param plantComment
     * @param plant_minPH
     * @param plant_maxPH
     * @param plant_maxRH
     * @param plant_minRH
     * @param plant_maxLx
     * @param plant_minLx
     * @param plant_maxPpm
     * @param plant_minPpm
     */
    public void editPlants(Intent intent,Long plantId,
                           String plantName, Float plantMinOC, Float plantMaxOC,
                           String plantComment, Float plant_minPH, Float plant_maxPH,
                           Float plant_maxRH, Float plant_minRH, Float plant_maxLx,
                           Float plant_minLx, Float plant_maxPpm, Float plant_minPpm){
        JSONObject param=new JSONObject();
        try {
            param.put("plantId",plantId);
            param.put("plantName",plantName);
            param.put("plantMinOC",plantMinOC);
            param.put("plantMaxOC",plantMaxOC);
            param.put("plantComment",plantComment);
            param.put("plant_minPH",plant_minPH);
            param.put("plant_maxPH",plant_maxPH);
            param.put("plant_maxRH",plant_maxRH);
            param.put("plant_minRH",plant_minRH);
            param.put("plant_maxLx",plant_maxLx);
            param.put("plant_minLx",plant_minLx);
            param.put("plant_maxPpm",plant_maxPpm);
            param.put("plant_minPpm",plant_minPpm);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ApiService.sendRequest("api-plant/delete-plant",param,intent);
    }
}
