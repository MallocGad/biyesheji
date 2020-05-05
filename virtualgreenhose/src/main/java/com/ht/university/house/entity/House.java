package com.ht.university.house.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Date;

/**
 * @Author: ht
 * @Date: Create in 15:53 2020/3/5
 * @Describe: house实体
 * @Last_change:
 */
@Entity
@Table(name = "HOUSE")
public class House {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long houseId;
    private String houseName;
    private Long houseOwnerId;
    private Long plantId;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date plantTime;

    public House() {
    }



    @Override
    public String toString() {
        return "House{" +
                "houseId=" + houseId +
                ", houseName='" + houseName + '\'' +
                ", houseOwnerId=" + houseOwnerId +
                ", plantId=" + plantId +
                ", plantTime=" + plantTime +
                '}';
    }

    public House(Long houseId, String houseName, Long houseOwnerId, Long plantId, Date plantTime) {
        this.houseId = houseId;
        this.houseName = houseName;
        this.houseOwnerId = houseOwnerId;
        this.plantId = plantId;
        this.plantTime = plantTime;
    }

    public Date getPlantTime() {
        return plantTime;
    }

    public void setPlantTime(Date plantTime) {
        this.plantTime = plantTime;
    }

    public House(Long houseId, String houseName, Long houseOwnerId, Long plantId) {
        this.houseId = houseId;
        this.houseName = houseName;
        this.houseOwnerId = houseOwnerId;
        this.plantId = plantId;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public Long getHouseOwnerId() {
        return houseOwnerId;
    }

    public void setHouseOwnerId(Long houseOwnerId) {
        this.houseOwnerId = houseOwnerId;
    }

    public Long getPlantId() {
        return plantId;
    }

    public void setPlantId(Long plantId) {
        this.plantId = plantId;
    }
}
