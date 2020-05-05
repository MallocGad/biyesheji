package com.example.htgh.common;

import android.graphics.Color;

import com.example.htgh.common.TimeUtils;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 图表工具
 */
public class ChartUtils {

    public static int dayValue = 0;
    public static int weekValue = 1;
    public static int monthValue = 2;

    /**
     * 初始化图表
     *
     * @param chart 原始图表
     * @return 初始化后的图表
     */
    public static LineChart initChart(LineChart chart) {

        chart.getDescription().setEnabled(false);//设置图表描述信息
        chart.setNoDataText("数据还未采集哦~~~");//没有数据时显示的文字
        chart.setNoDataTextColor(Color.BLUE);//没有数据时显示文字的颜色
        chart.setDrawGridBackground(false);//chart 绘图区后面的背景矩形将绘制
        chart.setDrawBorders(false);//禁止绘制图表边框的线


        XAxis xAxis = chart.getXAxis();
        // 不显示x轴
//        xAxis.setDrawAxisLine(false);
        // 设置x轴数据的位置
        xAxis.setEnabled(true);//设置轴启用或禁用 如果禁用以下的设置全部不生效
        xAxis.setDrawAxisLine(true);//是否绘制轴线
        xAxis.setDrawGridLines(true);//设置x轴上每个点对应的线
        xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
        xAxis.setTextColor(Color.WHITE);
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.setAvoidFirstLastClipping(true);//图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘
        xAxis.setLabelRotationAngle(10f);//设置x轴标签的旋转角度
//        xAxis.setGridColor(Color.parseColor("#30FFFFFF"));
        // 设置x轴数据偏移量
//        xAxis.setYOffset(-12);
        YAxis yRightAxis= chart.getAxisRight();
        YAxis yLeftAxis = chart.getAxisLeft();
        yRightAxis.setEnabled(false);
        yLeftAxis.enableGridDashedLine(10f, 10f, 0f);
        // 不显示y轴
//        yAxis.setDrawAxisLine(false);
        // 设置y轴数据的位置
//        yAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        // 不从y轴发出横向直线
//        yAxis.setDrawGridLines(false);
//        yAxis.setTextColor(Color.WHITE);
//        yAxis.setTextSize(12);
        // 设置y轴数据偏移量
//        yAxis.setXOffset(30);
//        yAxis.setYOffset(-3);
//        yAxis.setAxisMinimum(0);

        //Matrix matrix = new Matrix();
        // x轴缩放1.5倍
        //matrix.postScale(1.5f, 1f);
        // 在图表动画显示之前进行缩放
        //chart.getViewPortHandler().refresh(matrix, chart, false);
        // x轴执行动画
        //chart.animateX(2000);
        chart.invalidate();
        return chart;
    }

    /**
     * 设置图表数据
     *
     * @param chart  图表
     * @param values 数据
     */
    public static void setChartData(LineChart chart, List<Entry> values) {
        LineDataSet lineDataSet;

        if (chart.getData() != null && chart.getData().getDataSetCount() > 0) {
            lineDataSet = (LineDataSet) chart.getData().getDataSetByIndex(0);
            lineDataSet.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            lineDataSet = new LineDataSet(values, "数据趋势图");
            // 设置曲线颜色
            lineDataSet.setColor(Color.parseColor("#FFFFFF"));
            lineDataSet.setCircleColor(Color.WHITE);
            lineDataSet.setLineWidth(1f);//设置线宽
            lineDataSet.setCircleRadius(3f);//设置焦点圆心的大小
            lineDataSet.enableDashedHighlightLine(10f, 5f, 0f);//点击后的高亮线的显示样式
            lineDataSet.setHighlightLineWidth(2f);//设置点击交点后显示高亮线宽
            lineDataSet.setHighlightEnabled(true);//是否禁用点击高亮线
            lineDataSet.setHighLightColor(Color.WHITE);//设置点击交点后显示交高亮线的颜色
            lineDataSet.setValueTextSize(9f);//设置显示值的文字大小
            lineDataSet.setDrawFilled(false);//设置禁用范围背景填充
            // 设置平滑曲线
//            lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            // 不显示坐标点的小圆点
//            lineDataSet.setDrawCircles(false);
            // 不显示坐标点的数据
//            lineDataSet.setDrawValues(false);
            // 不显示定位线
//            lineDataSet.setHighlightEnabled(false);
            LineData data = new LineData(lineDataSet);
            chart.setData(data);
            chart.invalidate();
        }
    }

    /**
     * 更新图表
     *
     * @param chart     图表
     * @param values    数据
     */
    public static void notifyDataSetChanged(final LineChart chart, final JSONArray values) {
        if(values.length()==0){
            chart.clear();
            return;
        }
        chart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            //设置y坐标值
            public String getFormattedValue(float value, AxisBase axis) {
                int index=(int)value;
                if(index<0||index>=values.length())
                    return "";
                else
                    //x轴坐标值处理
                    return xValuesProcess(values,chart)[index];
            }
        });

        chart.invalidate();
        setChartData(chart, yValuesProcess(values));
    }

    /**
     * x轴数据处理
     * @return x轴数据
     */
    private static String[] xValuesProcess(JSONArray datas,LineChart chart) {
        String[] xValues=new String[datas.length()];
        for (int i = 0; i <datas.length() ; i++) {
            try {
                String time = datas.getJSONObject(i).getString("time");
                Date date = TimeUtils.jsonDateToDate(time);
                xValues[i]=date.getHours()+"点";
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return xValues;
    }

    /**
     * x轴数据处理
     * @param datas
     * @return
     */
    private static List<Entry> yValuesProcess(JSONArray datas){
        List<Entry> values = new ArrayList<>();
        for (int i = 0; i <datas.length() ; i++) {
            try {
                values.add(new Entry(i,datas.getJSONObject(i).getInt("num")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return values;
    }
}