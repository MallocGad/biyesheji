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
import com.example.htgh.adpters.PopupWindowAdapter;
import com.example.htgh.common.ApiService;
import com.example.htgh.datasource.House.HouseDao;
import com.example.htgh.datasource.plant.PlantDao;
import com.example.htgh.datasource.user.UserDao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AdminAddHouse extends AppCompatActivity {

    private JSONArray users,plants;
    private Spinner userSpinner,plantSpinner;
    private Button save;
    private TextView houseName;
    boolean userSpinnerFirst=true;
    boolean plantSpinnerFirst=true;
    int userSpinnerPosition=-1;
    int plantSpinnerPosition=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_house);
        userSpinner=findViewById(R.id.user_spinner);
        houseName=findViewById(R.id.house_name);
        plantSpinner=findViewById(R.id.plant_spinner);
        save = findViewById(R.id.save);
        init();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                int userId=-1;
                int plantId=-1;
                try {
                    JSONObject checkedUser = users.getJSONObject(getUserSpinnerPosition());
                    userId=checkedUser.getInt("userId");
                    JSONObject plant = plants.getJSONObject(getPlantSpinnerPosition());
                    plantId=plant.getInt("plantId");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                new HouseDao().saveHouse(intent,houseName.getText().toString(),userId,plantId);
                while(true) {
                    int status = intent.getIntExtra("requestStatus", -1);
                    System.out.println("状态码：" + status);
                    if (status != ApiService.LODING) {
                        String response = intent.getStringExtra("response");
//                        System.out.println(response);
                        break;
                    }
                }
                startActivity(new Intent(AdminAddHouse.this, AdminHouseManage.class));
                AdminAddHouse.this.finish();
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

        //默认不选中
        userSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //默认不选中
                if (userSpinnerFirst){
                    view.setVisibility(View.INVISIBLE);
                }
                userSpinnerFirst=false;
                setUserSpinnerPosition(position-1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        plantSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //默认不选中
                if (plantSpinnerFirst){
                    view.setVisibility(View.INVISIBLE);
                }
                plantSpinnerFirst=false;
                setPlantSpinnerPosition(position-1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
