package com.ht.university.device;

import javax.persistence.*;
import java.util.Date;
import java.util.Random;

/**
 * @Author: ht
 * @Date: Create in 22:22 2020/3/10
 * @Describe:  设备的实体，并封装设备的一些方法
 * @Last_change:
 */
@Entity
@Table(name="device")
public class BaseDevice {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String deviceName;
    //单位
    private String unit;
    //传感器数值范围
    private Float max;
    private Float min;

    /**
     * 默认为0.1的ratio 即0.05的偏离率
     * @return
     */
    public Float getVirtulNum(){
        return getVirtulNum(0.1f);
    }

    /**
     * 模拟传感器获取对应的值
     * @param ratio  ratio*0.5为超出预期范围的比例
     * @return
     */
    public Float getVirtulNum(float ratio){
        //ratio为0超出范围的概率为0 为1超出范围的概率为0.5
        if(ratio<0) ratio=0;
        else if(ratio>1)ratio=1;
        //随机数范围大小
        float range = max - min;
        //随机数偏离范围大小
        float ratioRange = range * ratio;
        Date date = new Date();
        Random random = new Random(date.getTime());
        //生成随机数
        return (range+ratioRange)* random.nextFloat()+(min-ratioRange*0.5f);
    }

    @Override
    public String toString() {
        return "BaseDevice{" +
                "id=" + id +
                ", deviceName='" + deviceName + '\'' +
                ", unit='" + unit + '\'' +
                ", max=" + max +
                ", min=" + min +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Float getMax() {
        return max;
    }

    public void setMax(Float max) {
        this.max = max;
    }

    public Float getMin() {
        return min;
    }

    public void setMin(Float min) {
        this.min = min;
    }
}
