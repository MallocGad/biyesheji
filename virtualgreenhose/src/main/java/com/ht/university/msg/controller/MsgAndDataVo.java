package com.ht.university.msg.controller;

/**
 * @Author: ht
 * @Long: Create in 10:25 2020/3/14
 * @Describe:msg和data的请求入参封装
 * @Last_change:
 */
public class MsgAndDataVo {
    private Long start;
    private Long end;
    private Long houseId;
    private Long deviceId;
    private int deviceType;

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }
}
