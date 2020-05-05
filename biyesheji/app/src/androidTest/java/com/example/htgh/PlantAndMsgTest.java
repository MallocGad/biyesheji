package com.example.htgh;

import android.content.Intent;

import com.example.htgh.common.ApiService;
import com.example.htgh.datasource.plant.PlantDao;

import org.junit.Test;

public class PlantAndMsgTest {
    PlantDao dao=new PlantDao();
    @Test
    public void test1(){
        Intent intent=new Intent();
        dao.getPlants(intent);
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
}
