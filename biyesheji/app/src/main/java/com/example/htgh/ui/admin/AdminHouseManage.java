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
import com.example.htgh.datasource.House.HouseDao;
import com.example.htgh.ui.admin.adapter.HouseListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdminHouseManage extends AppCompatActivity {

    private ImageView addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_house_manage);
        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHouseManage.this, AdminAddHouse.class);
                startActivity(intent);
            }
        });
        displayHouses();


    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        displayHouses();
    }

    private void displayHouses(){
        RecyclerView houseList = findViewById(R.id.house_list);
        JSONArray houses = getHouses();
        houseList.setLayoutManager(new LinearLayoutManager(AdminHouseManage.this));
        houseList.setAdapter(new HouseListAdapter(this,houses));
        houseList.setItemAnimator(new DefaultItemAnimator());
        houseList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));

    }

    public JSONArray getHouses(){
        Intent intent=new Intent();
        new HouseDao().getAllHouses(intent);
        JSONArray houses=null;
        while (true) {
            int status = intent.getIntExtra("requestStatus", -1);
//            System.out.println("状态码：" + status);
            if (status == ApiService.SUCCESS) {
                String response = intent.getStringExtra("response");
                try {
                    JSONObject obj = new JSONObject(response);
                    houses = obj.getJSONArray("data");
                } catch (JSONException e) {

                }
                break;
            }
        }
        return houses;
    }
}
