package com.ht.university.msg;

import javax.persistence.*;

/**
 * @Author: ht
 * @Date: Create in 16:24 2020/3/11
 * @Describe:控制设备产生的信息实体
 * @Last_change:
 */
@Entity
@Table(name = "controll_device_msg")
public class ControllDeviceMsg {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String msg;
    private Long houseId;
    private Long userId;
    private String time;

    public ControllDeviceMsg(Long id, String msg, Long houseId, Long userId, String time) {
        this.id = id;
        this.msg = msg;
        this.houseId = houseId;
        this.userId = userId;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ControllDeviceMsg{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", houseId=" + houseId +
                ", userId=" + userId +
                '}';
    }

    public ControllDeviceMsg(Long id, String msg, Long houseId, Long userId) {
        this.id = id;
        this.msg = msg;
        this.houseId = houseId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
