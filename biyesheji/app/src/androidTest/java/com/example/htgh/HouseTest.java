package com.example.htgh;

import android.content.Intent;

import com.example.htgh.common.ApiService;
import com.example.htgh.common.MyApplication;
import com.example.htgh.datasource.House.HouseDao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HouseTest {
    HouseDao dao = new HouseDao();

    @Test
    public void testHouseDetail() {
        Intent intent = new Intent();
        dao.getHouseDetail(1, intent);
        while (true) {
            int status = intent.getIntExtra("requestStatus", -1);
            System.out.println("状态码：" + status);
            if (status != ApiService.LODING) {
                String response = intent.getStringExtra("response");
                System.out.println(response);
                break;
            }
        }
    }

    @Test
    public void testGetHouses() {
        Intent intent = new Intent();
        HouseDao houseDao = new HouseDao();

        houseDao = new HouseDao();
        houseDao.getHouses(3, intent);
        JSONArray data = null;
        List<JSONObject> list = new ArrayList<>();

        while (true) {
            int status = intent.getIntExtra("requestStatus", -1);
            if (ApiService.SUCCESS == status) {
                String response = intent.getStringExtra("response");
                JSONObject object = null;
                try {
                    object = new JSONObject(response);
                    data = object.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject jsonObject = data.getJSONObject(i);
                        list.add(jsonObject);
                        System.out.println(jsonObject.getString("houseId"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        System.out.println("结果"+ list);
    }
    @Test
    public void testGetDatas() {
        HouseDao dao = new HouseDao();
        Intent intent = new Intent();
        Date date = new Date();
//            System.out.println(sj.format(date));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -2);
        dao.getDatas(1, 1, calendar.getTime(),date , intent);
        while (true) {
            int status = intent.getIntExtra("requestStatus", -1);
//            if (status != ApiService.LODING) {
                if (status == ApiService.SUCCESS) {
                    try {
                        JSONObject response = new JSONObject(intent.getStringExtra("response"));
                        JSONArray data = response.getJSONArray("data");
                        JSONObject json = data.getJSONObject(0);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sssZZZ");
                        try {
                            Date time = sdf.parse(json.getString("time"));
                            System.out.println(time);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
//                        System.out.println(response);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                }

//            }
        }
    }

    @Test
    public void testGetAllDatas(){
        HouseDao dao = new HouseDao();
        Intent intent = new Intent();
        dao.getDatas(1, 1, intent);
        while (true) {
            int status = intent.getIntExtra("requestStatus", -1);
            if (status == ApiService.SUCCESS) {
                try {
                    JSONObject response = new JSONObject(intent.getStringExtra("response"));
//                    JSONArray data = response.getJSONArray("data");
                    System.out.println(response);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            }
        }

    }
}
