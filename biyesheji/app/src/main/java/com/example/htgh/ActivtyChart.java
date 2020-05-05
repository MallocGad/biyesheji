package com.example.htgh;

import androidx.appcompat.app.AppCompatActivity;

import com.example.htgh.common.AnimationUtils;
import com.example.htgh.common.ApiService;
import com.example.htgh.common.ShowUtils;
import com.example.htgh.datasource.House.HouseDao;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.htgh.common.ChartUtils;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.renderer.YAxisRenderer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ActivtyChart extends AppCompatActivity implements View.OnClickListener {

    //点击字体和图片都能触发pop
    private TextView tvDate;
    private ImageView ivDate;
    private int houseId;
    private TextView unit;
    private String houseName;
    private int deviceId;
    JSONObject houseMsg;

    private String maxNum="0",minNum="0";
    private LineChart chart;
    private static final String[] dates = new String[]{"最近一天", "最近两天", "最近三天"};
    private List<String> dateList = Arrays.asList(dates);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
        Intent intent = getIntent();
        deviceId = intent.getIntExtra("deviceId", 1);
        try {
            houseMsg = new JSONObject(intent.getStringExtra("houseMsg"));
            houseId = houseMsg.getInt("houseId");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //初始化温室信息视图
        initHouseMsg();
        //初始化图表视图
        initView();
    }

    private void initView() {
        tvDate = findViewById(R.id.tv_date);
        ivDate = findViewById(R.id.iv_date);
        chart = findViewById(R.id.chart);
        unit=findViewById(R.id.tv_unit);
        ivDate.setColorFilter(Color.WHITE);
        tvDate.setOnClickListener(this);
        ivDate.setOnClickListener(this);

        unit.setText(getUnit(deviceId));
        ChartUtils.initChart(chart);

        //设置警戒线
        YAxis axis=chart.getAxisLeft();
        float v = Float.parseFloat(maxNum);
        LimitLine maxLine=new LimitLine(v,"max");
        LimitLine minLine=new LimitLine(Float.parseFloat(minNum),"min");
        maxLine.setLineWidth(2f);  //设置线宽
        minLine.setLineWidth(2f);  //设置线宽
        maxLine.setTextSize(10f);   //设置限制线上label字体大小
        minLine.setTextSize(10f);   //设置限制线上label字体大小
        maxLine.setLineColor(Color.RED); //设置线的颜色
        minLine.setLineColor(Color.RED); //设置线的颜色
        minLine.setTextColor(Color.WHITE);  //设置限制线上label字体的颜色
        maxLine.setTextColor(Color.WHITE);  //设置限制线上label字体的颜色
        maxLine.enableDashedLine(5f,3f,0);
        minLine.enableDashedLine(5f,3f,0);
        maxLine.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        minLine.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        axis.addLimitLine(maxLine);
        axis.addLimitLine(minLine);
        axis.setDrawLimitLinesBehindData(true);
        chart.invalidate();
        ChartUtils.notifyDataSetChanged(chart, getData(houseId, deviceId, 0));
    }

    /**
     * 获取数据
     * @param houseId
     * @param deviceId
     * @param position 0:为近一天，1：为近两天，2：为近三天
     * @return
     */
    private JSONArray getData(int houseId, int deviceId, int position) {
        Intent intent = new Intent();
        HouseDao dao = new HouseDao();
        int amount = 0;
        if (0 == position) {
            amount = -1;
        } else if (1 == position) {
            amount = -2;
        } else if (2 == position) {
            amount = -3;
        }
        //            SimpleDateFormat sj=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();
//            System.out.println(sj.format(date));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, amount);
        dao.getDatas(houseId, deviceId,calendar.getTime() , date, intent);
        while (true) {
            int status = intent.getIntExtra("requestStatus", -1);
            if (status == ApiService.SUCCESS) {
                try {
                    JSONObject response = new JSONObject(intent.getStringExtra("response"));
                    JSONArray data = response.getJSONArray("data");
                    return data;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return null;
    }

    /**
     * 切换最近几天的点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_date:
            case R.id.iv_date:
                String data = tvDate.getText().toString();

                if (!ShowUtils.isPopupWindowShowing()) {
                    AnimationUtils.startModeSelectAnimation(ivDate, true);
                    ShowUtils.showPopupWindow(this, tvDate, 100, 200, dateList,
                            new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view,
                                                        int position, long id) {
                                    ShowUtils.updatePopupWindow(position);
                                    AnimationUtils.startModeSelectAnimation(ivDate, false);
                                    ShowUtils.popupWindowDismiss();
                                    tvDate.setText(dateList.get(position));
                                    // 更新图表
                                    ChartUtils.notifyDataSetChanged(chart, getData(houseId, deviceId, position));
                                }
                            });
                } else {
                    AnimationUtils.startModeSelectAnimation(ivDate, false);
                    ShowUtils.popupWindowDismiss();
                }

                if (dateList.get(0).equals(data)) {
                    ShowUtils.updatePopupWindow(0);
                } else if (dateList.get(1).equals(data)) {
                    ShowUtils.updatePopupWindow(1);
                } else if (dateList.get(2).equals(data)) {
                    ShowUtils.updatePopupWindow(2);
                }
                break;

            default:
                break;
        }
    }

    /**
     * 初始化温室的信息
     */
    public void initHouseMsg(){
        TextView houseName=findViewById(R.id.house_name);
        TextView name=findViewById(R.id.plant_name);
        TextView time = findViewById(R.id.plant_time);
        TextView comment=findViewById(R.id.plant_comment);
        TextView titleOfType=findViewById(R.id.plant_data_type);//展示范围的标题
        TextView min=findViewById(R.id.plant_min);
        TextView max=findViewById(R.id.plant_max);
        TextView unitText=findViewById(R.id.plant_data_unit);//这里是单位

        try {
            houseName.setText(houseMsg.getString("houseName"));
            comment.setText(houseMsg.getString("plantComment"));
            name.setText(houseMsg.getString("plantName"));
            time.setText(houseMsg.getString("plantTime"));
            unitText.setText(getMsgUnit(deviceId));
            if(1==deviceId){
                maxNum=houseMsg.getString("plantMaxLx");
                minNum=houseMsg.getString("plantMinLx");
                titleOfType.setText("最适光照范围：");
            }else if(2==deviceId){
                maxNum=houseMsg.getString("plantMaxOC");
                minNum=houseMsg.getString("plantMinOC");
                titleOfType.setText("最适温度范围：");
            }else if(3==deviceId){
                maxNum=houseMsg.getString("plantMaxRH");
                minNum=houseMsg.getString("plantMinRH");

                titleOfType.setText("最适湿度比范围：");
            }else if(4==deviceId){
                maxNum=houseMsg.getString("plantMaxPH");
                minNum=houseMsg.getString("plantMinPH");
                titleOfType.setText("最适PH范围：");
            }else if(5==deviceId){
                maxNum=houseMsg.getString("plantMaxPpm");
                minNum=houseMsg.getString("plantMinPpm");
                titleOfType.setText("最适CO2浓度范围：");
            }else{
            }
            max.setText(maxNum);
            min.setText(minNum);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getMsgUnit(int deviceId){
        if(1==deviceId){
            return " 勒克斯(Lx)";
        }else if(2==deviceId){
            return " 摄氏度(°C)";
        }else if(3==deviceId){
            return " 湿度百分比(%RH)";
        }else if(4==deviceId){
            return "PH值 (PH)";
        }else if(5==deviceId){
            return "二氧化碳浓度比 (%ppm)";
        }else{
            return " ";
        }
    }

    //展示在图表上的单位
    public String getUnit(int deviceId){
        if(1==deviceId){
            return "单位：(Lx)";
        }else if(2==deviceId){
            return "单位：(°C)";
        }else if(3==deviceId){
            return "单位：(%RH)";
        }else if(4==deviceId){
            return "单位：(PH)";
        }else if(5==deviceId){
            return "单位：(%ppm)";
        }else{
            return " ";
        }
    }
}
