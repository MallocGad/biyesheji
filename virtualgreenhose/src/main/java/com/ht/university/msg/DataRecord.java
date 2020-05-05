package com.ht.university.msg;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: ht
 * @Date: Create in 17:30 2020/3/11
 * @Describe:数据统计的实体
 * @Last_change:
 */
@Entity
@Table(name = "data_recored")
public class DataRecord {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long dataId;
    private Long deviceId;
    private Float num;
    private Long houseId;
    @DateTimeFormat(pattern = "yyyy.MM.dd HH:mm")
    private Date time;
    @Override
    public String toString() {
        return "DataRecord{" +
                "dataId=" + dataId +
                ", deviceId=" + deviceId +
                ", num=" + num +
                ", houseId=" + houseId +
                ", time=" + time +
                '}';
    }

    public DataRecord() {
    }

    public DataRecord(Long dataId, Long deviceId, Float num, Long houseId, Date time) {
        this.dataId = dataId;
        this.deviceId = deviceId;
        this.num = num;
        this.houseId = houseId;
        this.time = time;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Float getNum() {
        return num;
    }

    public void setNum(Float num) {
        this.num = num;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
