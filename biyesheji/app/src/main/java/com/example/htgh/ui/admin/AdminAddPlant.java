package com.example.htgh.ui.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.htgh.R;
import com.example.htgh.common.ApiService;
import com.example.htgh.datasource.plant.PlantDao;

public class AdminAddPlant extends AppCompatActivity {

    private EditText plantName;
    private EditText comment,oCMin,oCMax,lxMin,lxMax,ppmMin,ppmMax,PHMin,PHMax,RHMin,RHMax;
    private String commentS, plantNameS, oCMinS,oCMaxS,lxMinS,lxMaxS,ppmMinS,ppmMaxS,PHMinS,PHMaxS,RHMinS,RHMaxS;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_plant);
        oCMax=findViewById(R.id.ocMax);
        oCMin=findViewById(R.id.ocMin);
        ppmMin=findViewById(R.id.PPMMin);
        ppmMax=findViewById(R.id.PPMMax);
        RHMax=findViewById(R.id.RHMax);
        RHMin=findViewById(R.id.RHMin);
        PHMin=findViewById(R.id.PHMin);
        PHMax=findViewById(R.id.PHMin);
        lxMax=findViewById(R.id.lxMax);
        lxMin=findViewById(R.id.lxMin);
        plantName=findViewById(R.id.plantName);
        comment=findViewById(R.id.plant_comment);
        save=findViewById(R.id.save);
        init();
    }

    private void init(){
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oCMaxS=oCMax.getText().toString();
                oCMinS=oCMin.getText().toString();
                lxMaxS=lxMax.getText().toString();
                lxMinS=lxMin.getText().toString();
                ppmMaxS=ppmMax.getText().toString();
                ppmMinS=ppmMin.getText().toString();
                RHMaxS=RHMax.getText().toString();
                RHMinS=RHMin.getText().toString();
                PHMaxS=PHMax.getText().toString();
                PHMinS=PHMin.getText().toString();
                commentS=comment.getText().toString();
                boolean flag=true;
                if(!jugeMaxMin(oCMax,oCMin))
                    flag=false;
                if(!jugeMaxMin(RHMax,RHMin))
                    flag=false;
                if(!jugeMaxMin(PHMax,PHMin))
                    flag=false;
                if(!jugeMaxMin(lxMax,lxMin)){
                    flag=false;
                }
                if(!jugeMaxMin(ppmMax,ppmMin))
                    flag=false;
                if(flag) {
                    Intent intent = new Intent(AdminAddPlant.this, AdminPlantManage.class);
                    new PlantDao().addPlants(intent,plantNameS,oCMaxS,oCMinS,commentS,PHMinS,PHMaxS,
                            RHMaxS,RHMinS,lxMaxS,lxMinS,ppmMaxS,ppmMinS);
                    while(true) {
                        int status = intent.getIntExtra("requestStatus", -1);
                        System.out.println("状态码：" + status);
                        if (status != ApiService.LODING) {
                            String response = intent.getStringExtra("response");
                            //处理用户名重复的问题看是后台自动生成还是咋的
                            System.out.println(response);
                            break;
                        }

                    }
                    //添加成功
                    startActivity(intent);
                    AdminAddPlant.this.finish();
                }
            }
        });
    }

    private boolean jugeMaxMin(EditText max,EditText min){
        boolean flag=true;
        if(max.getText().toString()==null||max.getText().toString()!=""){
            max.setError("字段不能为空");
            flag=false;
        }
        if(min.getText().toString()==null||min.getText().toString()!=""){
            min.setError("字段不能为空");
            flag=false;
        }

        if(flag==true) {
            int maxI=Integer.valueOf(max.getText().toString());
            int minI=Integer.valueOf(min.getText().toString());
            if (maxI > minI) {
                return true;
            } else {
                max.setError("最小值必须小于最大值");
                flag=false;
            }
        }
        return flag;
    }
}
