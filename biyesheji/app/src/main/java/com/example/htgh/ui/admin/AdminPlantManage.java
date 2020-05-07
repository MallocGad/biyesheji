package com.example.htgh.ui.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.htgh.R;
import com.example.htgh.common.ApiService;
import com.example.htgh.datasource.plant.PlantDao;
import com.example.htgh.ui.admin.adapter.PlantListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdminPlantManage extends AppCompatActivity {

    private Intent intent;
    private PlantDao plantDao;
    RecyclerView plantList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_plant_manage);
        plantDao=new PlantDao();
        intent=new Intent();
        plantList=findViewById(R.id.plants_list);
        displayPlants();
        ImageView add=findViewById(R.id.addButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void displayPlants(){
        JSONArray plants = getPlants();
        plantList.setLayoutManager(new LinearLayoutManager(AdminPlantManage.this));
        plantList.setAdapter(new PlantListAdapter(this,plants));
        plantList.setItemAnimator(new DefaultItemAnimator());
        plantList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
    }
    public JSONArray getPlants(){
        JSONArray plants=null;
        plantDao.getPlants(intent);
        //同步方式获取
        while (true) {
            int status = intent.getIntExtra("requestStatus", -1);
            System.out.println("状态码：" + status);
            if (status == ApiService.SUCCESS) {
                String response = intent.getStringExtra("response");
                try {
                    JSONObject obj=new JSONObject(response);
                    plants=obj.getJSONArray("data");
                } catch (JSONException e) {
                }
//                System.out.println(response);
                break;
            }
        }
        return plants;
    }
}
