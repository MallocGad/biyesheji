package com.example.htgh.ui.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.htgh.R;
import com.example.htgh.common.ApiService;
import com.example.htgh.datasource.House.HouseDao;
import com.example.htgh.datasource.plant.PlantDao;
import com.example.htgh.datasource.user.UserDao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AdminEditHouse extends AppCompatActivity {

    private JSONArray users,plants;
    private JSONObject house;
    private Spinner userSpinner,plantSpinner;
    private Button save;
    private TextView houseName;
    private TextView plantTime;
    boolean userSpinnerFirst=true;
    boolean plantSpinnerFirst=true;
    int userSpinnerPosition=-1;
    int plantSpinnerPosition=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_house);
        userSpinner=findViewById(R.id.user_spinner);
        houseName=findViewById(R.id.house_name);
        plantSpinner=findViewById(R.id.plant_spinner);
        plantTime=findViewById(R.id.create_date);
        save = findViewById(R.id.save);
        try {
            house=new JSONObject(getIntent().getStringExtra("house"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        init();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                int userId=-1;
                int plantId=-1;
                try {
                    JSONObject checkedUser = users.getJSONObject(getUserSpinnerPosition()-1);
                    userId=checkedUser.getInt("userId");
                    JSONObject plant = plants.getJSONObject(getPlantSpinnerPosition()-1);
                    plantId=plant.getInt("plantId");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    new HouseDao().editHouse(intent,house.getInt("houseId"),houseName.getText().toString(),userId,plantId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                while(true) {
                    int status = intent.getIntExtra("requestStatus", -1);
                    System.out.println("状态码：" + status);
                    if (status != ApiService.LODING) {
                        String response = intent.getStringExtra("response");
//                        System.out.println(response);
                        break;
                    }
                }
                startActivity(new Intent(AdminEditHouse.this, AdminHouseManage.class));
                AdminEditHouse.this.finish();
            }
        });
    }


    public List<String> getUsers(){
        Intent intent = new Intent();
        new UserDao().getNormalUsers(intent);
        while (true) {
            int status = intent.getIntExtra("requestStatus", -1);
            System.out.println("状态码：" + status);
            if (status == ApiService.SUCCESS) {
                String response = intent.getStringExtra("response");
                try {
                    JSONObject obj=new JSONObject(response);
                    users=obj.getJSONArray("data");
                } catch (JSONException e) {

                }
//                System.out.println(response);
                break;
            }
        }
        ArrayList<String> userNames = new ArrayList<>();
        JSONObject item=null;
        //占位
        userNames.add("");
        for (int i=0;i<users.length();i++){
            try {
                item=users.getJSONObject(i);
                userNames.add(item.getString("userName"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return userNames;
    }

    public List<String> getPlants(){
        Intent intent = new Intent();
        new PlantDao().getPlants(intent);
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
        ArrayList<String> plantNames = new ArrayList<>();
        JSONObject item=null;
        //第一个为空作为占位
        plantNames.add("");
        for (int i=0;i<plants.length();i++){
            try {
                item=plants.getJSONObject(i);
                plantNames.add(item.getString("plantName"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return plantNames;
    }

    public int getUserSpinnerPosition() {
        return userSpinnerPosition;
    }

    public void setUserSpinnerPosition(int userSpinnerPosition) {
        this.userSpinnerPosition = userSpinnerPosition;
    }

    public int getPlantSpinnerPosition() {
        return plantSpinnerPosition;
    }

    public void setPlantSpinnerPosition(int plantSpinnerPosition) {
        this.plantSpinnerPosition = plantSpinnerPosition;
    }
    public void init(){
        //用户列表下拉框
        List<String> usersString = getUsers();
        //植物列表下拉框
        List<String> plantsString = getPlants();

        final ArrayAdapter<String> userSpinnerAdapter =new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,usersString);
        ArrayAdapter<String> plantSpinnerAdapter =new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,plantsString);
        userSpinner.setAdapter(userSpinnerAdapter);
        plantSpinner.setAdapter(plantSpinnerAdapter);

        userSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setUserSpinnerPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        plantSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setPlantSpinnerPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        try {
            houseName.setText(house.getString("houseName"));
            plantTime.setText(house.getString("plantTime"));
            int userPosition = 0,plantPosition = 0;
            for (int i=0;i<plants.length();i++){
                if(plants.getJSONObject(i).getInt("plantId")==house.getInt("plantId")){
                    plantPosition=i;
                    break;
                }
            }
            for (int i=0;i<users.length();i++){
                if(users.getJSONObject(i).getInt("userId")==house.getInt("houseOwnerId")){
                    userPosition=i;
                    break;
                }
            }
            plantSpinner.setSelection(plantPosition+1);
            userSpinner.setSelection(userPosition+1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        houseName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()<2||s.length()>12){
                    houseName.setError("字段在2-12个字符");
                    save.setEnabled(false);

                }else
                    save.setEnabled(true);
            }
        });


    }
}
