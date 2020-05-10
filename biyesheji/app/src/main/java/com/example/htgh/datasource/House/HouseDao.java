package com.example.htgh.datasource.House;

import android.content.Intent;

import com.example.htgh.common.ApiService;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 温室相关的网络请求在这里
 */
public class HouseDao {

    public void saveHouse(Intent intent,String houseName,int userId,int plantId){
        JSONObject param=new JSONObject();
        try {
            param.put("houseName",houseName);
            if (userId!=-1)
                param.put("houseOwnerId",userId);
            if(plantId!=-1)
                param.put("plantId",plantId);
            ApiService.sendRequest("api-house/add-house",param,intent);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void editHouse(Intent intent,int houseId,String houseName,int userId,int plantId){
        JSONObject param=new JSONObject();
        try {
            param.put("houseName",houseName);
            param.put("houseId",houseId);
            if (userId!=-1)
                param.put("houseOwnerId",userId);
            if(plantId!=-1)
                param.put("plantId",plantId);
            ApiService.sendRequest("api-house/edit-house",param,intent);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    public void getHouseDetail(int id,Intent intent){
        JSONObject param=new JSONObject();
        try {
            param.put("houseId",id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ApiService.sendRequest("api-house/get-house-detail",param,intent);
    }

    /**
     * 获取所有温室信息
     * @param intent
     */
    public void getAllHouses(Intent intent){
        ApiService.sendRequest("api-house/get-all-houses",null,intent);
    }

    /**
     * 删除温室
     */
    public void deleteHouse(Intent intent,Long houseId){
        JSONObject param=new JSONObject();
        try {
            param.put("houseId",houseId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ApiService.sendRequest("api-house/delete-house",param,intent);
    }

    /**
     * 进入页面获取温室的列表信息
     * @param id
     * @param intent
     */
    public void getHouses(int id,Intent intent){
        JSONObject param=new JSONObject();
        try {
            param.put("userId",id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ApiService.sendRequest("api-house/get-houses-list",param,intent);
    }

    /**
     * 获取对应时间范围类的数据统计信息
     * @param houseId
     * @param deviceId
     * @param start
     * @param end
     * @param intent
     */
    public void getDatas(int houseId, int deviceId, Date start,Date end,Intent intent){
        JSONObject param=new JSONObject();
        try {
            param.put("start",start.getTime());
            param.put("end",end.getTime());
            param.put("deviceId",deviceId);
            param.put("houseId",houseId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ApiService.sendRequest("api-data/get-data-by-time",param,intent);

    }

    /**
     * 获取所有的数据信息
     * @param houseId
     * @param deviceId
     * @param intent
     */
    public void getDatas(int houseId,int deviceId,Intent intent){
        JSONObject param=new JSONObject();
        try {
            param.put("deviceId",deviceId);
            param.put("houseId",houseId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ApiService.sendRequest("api-data/get-datas",param,intent);
    }

    /**
     * 开启设备
     * @param deviceType
     * @param houseId
     * @param intent
     */
    public void openControllDevice(int deviceType,int houseId,Intent intent){
        JSONObject param=new JSONObject();
        try {
            param.put("deviceType",deviceType);
            param.put("houseId",houseId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ApiService.sendRequest("api-msg/open-device",param,intent);
    }

    /**
     * 关闭设备
     * @param deviceType
     * @param houseId
     * @param intent
     */
    public void closeControllDevice(int deviceType,int houseId,Intent intent){
        JSONObject param=new JSONObject();
        try {
            param.put("deviceType",deviceType);
            param.put("houseId",houseId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ApiService.sendRequest("api-msg/close-device",param,intent);
    }


}
