package com.example.htgh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.htgh.common.ApiService;
import com.example.htgh.common.MyApplication;
import com.example.htgh.common.Variables;
import com.example.htgh.datasource.House.HouseDao;
import com.example.htgh.datasource.NoticeDao;
import com.example.htgh.ui.mianactivity.MsgListAdapter;
import com.example.htgh.ui.mianactivity.SelectAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个文件比较长，后期可考虑分文件
 */
public class MainActivity extends AppCompatActivity {

    //用于保存信息
    private Intent intent;

    private int houseId;

    //下拉框
    private Spinner spinner;

    //信息列表展示view
    private RecyclerView msgList;

    //content视图，三个之间相互切换
    private LinearLayout contentMsg;
    private LinearLayout contentPlant;
    private LinearLayout contentPerson;

    //底部切换的view，点击不同的view展示不同的content content2 content3
    private ImageView isChooseMsg;
    private ImageView isChoosePlant;
    private ImageView isChoosePerson;

    //温室数据获取源
    private HouseDao houseDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = new Intent(this, ActivtyChart.class);
        setContentView(R.layout.activity_main);
        //切换content的view
        isChooseMsg = findViewById(R.id.msg);
        isChoosePlant = findViewById(R.id.plant);
        isChoosePerson = findViewById(R.id.person);
        //需要切换的content
        contentMsg = findViewById(R.id.content);
        contentPlant = findViewById(R.id.content2);
        contentPerson = findViewById(R.id.content3);

        //实时监控的
//         Button toVideo= findViewById(R.id.toVideo);
//        toVideo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this,VideoActivity.class);
//                startActivity(intent);
//            }
//        });
        //列表信息展示
        displayMsg();

        //changeContent点击按钮时切换不同的界面
        changeContent();

        //温室页面的展示
        displayHouse();
        //dataRecored图表展示
        displayDataChart();
        //模拟控制设备
        ControllDevice();
        //个人中心
        showSelf();

    }

    /**
     * 检测点击事件切换展示的content
     */
    public void changeContent() {
        //默认展示msgcontent
        contentMsg.setVisibility(View.VISIBLE);
        isChooseMsg.setColorFilter(Color.parseColor(Variables.checkedColor));
        OnClick onClick = new OnClick();
        isChooseMsg.setOnClickListener(onClick);
        isChoosePlant.setOnClickListener(onClick);
        isChoosePerson.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //点击事件发生前设置为均不可见
            isChooseMsg.setColorFilter(Color.parseColor(Variables.noCheckedColor));
            isChoosePerson.setColorFilter(Color.parseColor(Variables.noCheckedColor));
            isChoosePlant.setColorFilter(Color.parseColor(Variables.noCheckedColor));
            contentMsg.setVisibility(View.INVISIBLE);
            contentPerson.setVisibility(View.INVISIBLE);
            contentPlant.setVisibility(View.INVISIBLE);
            switch (v.getId()) {
                //msg被点击
                case R.id.msg: {
                    //选中和未选中颜色区别
                    isChooseMsg.setColorFilter(Color.parseColor(Variables.checkedColor));
                    //设置可见的内容
                    contentMsg.setVisibility(View.VISIBLE);
                    break;
                }
                case R.id.plant: {
                    isChoosePlant.setColorFilter(Color.parseColor(Variables.checkedColor));
                    contentPlant.setVisibility(View.VISIBLE);
                    break;
                }
                case R.id.person: {
                    isChoosePerson.setColorFilter(Color.parseColor(Variables.checkedColor));
                    contentPerson.setVisibility(View.VISIBLE);
                    break;
                }
            }

        }
    }


    /**
     * 主页信息获取并展示
     */
    public void displayMsg() {
        //列表展示
        msgList = findViewById(R.id.msgList);
        NoticeDao noticeDao = new NoticeDao();
        noticeDao.getNotices(intent);
        JSONArray notices=null;
        while (true) {
            int status = intent.getIntExtra("requestStatus", -1);
            if (ApiService.SUCCESS == status) {
                String response = intent.getStringExtra("response");
                JSONObject object = null;
                try {
                    object = new JSONObject(response);
                    System.out.println("objct:======" + object);
                    notices = object.getJSONArray("data");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        //设置为线性
        msgList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        //数据装在用adapter
        msgList.setAdapter(new MsgListAdapter(MainActivity.this,notices));
        //设置分割线
        msgList.addItemDecoration(new MyItemDecration());
        //设置增加删除条目的动画
        msgList.setItemAnimator(new DefaultItemAnimator());
    }

    class MyItemDecration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0, 0, 0, getResources().getDimensionPixelOffset(R.dimen.dividerHeight));
        }
    }

    /**
     * 温室控制页的展示
     */
    public void displayHouse() {
        spinner = findViewById(R.id.house_spinner);
        houseDao = new HouseDao();
        houseDao.getHouses(MyApplication.getCurrentUserId(), intent);
        JSONArray data = null;
        final List<JSONObject> list = new ArrayList<>();
        while (true) {
            int status = intent.getIntExtra("requestStatus", -1);
            if (ApiService.SUCCESS == status) {
                String response = intent.getStringExtra("response");
                JSONObject object = null;
                try {
                    object = new JSONObject(response);
//                    System.out.println("objct:======" + object);
                    data = object.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        list.add(data.getJSONObject(i));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        //下拉框
        SelectAdapter arrayAdapter = new SelectAdapter(this, R.layout.spinner_checked_item, list);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                JSONObject item = (JSONObject) parent.getItemAtPosition(position);
                try {
                    //更新温室的信息
                    houseDao.getHouseDetail(item.getInt("houseId"), intent);
                    intent.putExtra("houseId", item.getInt("houseId"));
                    houseId = item.getInt("houseId");
                    changeControll();
                    while (true) {
                        if (intent.getIntExtra("requestStatus", -1) == ApiService.SUCCESS) {
                            JSONObject houseDetail = new JSONObject(intent.getStringExtra("response"));
                            JSONObject data = houseDetail.getJSONObject("data");
                            TextView name = findViewById(R.id.plant_name);
                            TextView time = findViewById(R.id.plant_time);
                            name.setText(data.getString("plantName"));
                            time.setText(data.getString("plantTime"));
                            //将当前温室信息存入intent以便于后面chart列表获取
                            intent.putExtra("houseMsg", data.toString());
                            break;
                        }
                    }


                    System.out.println(item.getString("houseName"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void changeControll(){

        Switch device_tf, device_ss, device_bw, device_ny, device_tc;
        device_tf = findViewById(R.id.device_tfxt);
        device_ss = findViewById(R.id.device_ssq);
        device_bw = findViewById(R.id.device_bwxt);
        device_ny = findViewById(R.id.device_nyps);
        device_tc = findViewById(R.id.device_qtsb);
        if (intent.getIntExtra("houseId",-1)==-1){
            device_bw.setEnabled(false);
            device_tf.setEnabled(false);
            device_ss.setEnabled(false);
            device_ny.setEnabled(false);
            device_tc.setEnabled(false);
        }
    }

    /**
     * 图表展示
     */

    public void displayDataChart() {
        Button dataGztj;
        Button dataWdtj;
        Button dataEyht;
        Button dataPhtj;
        Button dataSdtj;
        //光照统计
        dataGztj = findViewById(R.id.data_gztj);
        //温度
        dataWdtj = findViewById(R.id.data_wdtj);
        //二氧化碳
        dataEyht = findViewById(R.id.data_eyht);
        //PH
        dataPhtj = findViewById(R.id.data_phtj);
        //湿度
        dataSdtj = findViewById(R.id.data_sdtj);
        class ButtonLister implements View.OnClickListener {

            @Override
            public void onClick(View v) {
                int deviceId = 0;
                int id = v.getId();
                switch (id) {
                    //光照
                    case R.id.data_gztj:
                        deviceId = 1;
                        break;
                    //温度
                    case R.id.data_wdtj:
                        deviceId = 2;
                        break;
                    //湿度
                    case R.id.data_sdtj:
                        deviceId = 3;
                        break;
                    //PH
                    case R.id.data_phtj:
                        deviceId = 4;
                        break;
                    //co2
                    case R.id.data_eyht:
                        deviceId = 5;
                        break;
                }
                int houseId = intent.getIntExtra("houseId", -1);
                if(-1!=houseId) {
                intent.putExtra("deviceId", deviceId);
                startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this,"未选择温室",Toast.LENGTH_SHORT).show();
                }

            }
        }
        ButtonLister buttonLister = new ButtonLister();
        dataGztj.setOnClickListener(buttonLister);
        dataWdtj.setOnClickListener(buttonLister);
        dataEyht.setOnClickListener(buttonLister);
        dataPhtj.setOnClickListener(buttonLister);
        dataSdtj.setOnClickListener(buttonLister);
    }

    public void ControllDevice() {
        class DeviceLister implements CompoundButton.OnCheckedChangeListener {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int deviceId = 0;
                String msg = "";
                switch (buttonView.getId()) {
                    case R.id.device_tfxt: {
                        deviceId = 1;
                        msg = "通风设备";
                    }
                    break;
                    case R.id.device_ssq: {
                        deviceId = 2;
                        msg = "洒水系统";
                    }
                    break;
                    case R.id.device_bwxt: {
                        deviceId = 3;
                        msg = "保温系统";
                    }
                    break;
                    case R.id.device_nyps: {
                        deviceId = 4;
                        msg = "农药喷洒";
                    }
                    break;
                    case R.id.device_qtsb: {
                        deviceId = 5;
                        msg = "温室天窗";
                    }
                    break;
                }
                String notice = "开启";
                if (!isChecked) {
                    notice = "关闭";
                    houseDao.closeControllDevice(deviceId, houseId, intent);
                } else {
                    houseDao.openControllDevice(deviceId, houseId, intent);
                }
                Toast.makeText(MainActivity.this, msg + "已经" + notice, Toast.LENGTH_SHORT).show();
            }
        }

        DeviceLister deviceLister = new DeviceLister();
        Switch device_tf, device_ss, device_bw, device_ny, device_tc;
        device_tf = findViewById(R.id.device_tfxt);
        device_ss = findViewById(R.id.device_ssq);
        device_bw = findViewById(R.id.device_bwxt);
        device_ny = findViewById(R.id.device_nyps);
        device_tc = findViewById(R.id.device_qtsb);

        device_tf.setOnCheckedChangeListener(deviceLister);
        device_ss.setOnCheckedChangeListener(deviceLister);
        device_bw.setOnCheckedChangeListener(deviceLister);
        device_ny.setOnCheckedChangeListener(deviceLister);
        device_tc.setOnCheckedChangeListener(deviceLister);    }
    public void showSelf(){
//        TextView
        TextView username,email,createTime,role;
        username=findViewById(R.id.username);
        email=findViewById(R.id.email);
        createTime=findViewById(R.id.create_time);
        role=findViewById(R.id.role);
        Button exit=findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog=new AlertDialog.Builder(MainActivity.this)
                        .setTitle("退出程序")
                        .setMessage("确定退出程序吗?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                MainActivity.this.finish();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        }).create();
                alertDialog.show();

            }
        });

        JSONObject userInfo = (JSONObject)MyApplication.getAttribute("userInfo");
        try {
            username.setText(userInfo.getString("userName"));
            email.setText(userInfo.getString("email"));
            createTime.setText(userInfo.getString("createDate"));
            role.setText(userInfo.getString("role"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
