package com.ht.university.house.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

/**
 * @Author: ht
 * @Date: Create in 14:19 2020/3/5
 * @Describe:house的实体类
 * @Last_change:
 */
public class HouseDetail {
    //下面是house实体的属性
    private Long houseId;
    private String houseName;
    private Long houseOwnerId;
    private Long plantId;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date plantTime;

    //下面是联查需要增加的
    private String plantName;
    //作物生长温度
    private Float plantMinOC;
    private Float plantMaxOC;
    private String PlantComment;
    //ph值
    private Float plantMinPH;
    private Float plantMaxPH;
    //湿度
    private Float plantMaxRH;
    private Float plantMinRH;
    //光照强度
    private Float plantMaxLx;
    private Float plantMinLx;
    //二氧化碳浓度
    private Float plantMaxPpm;
    private Float plantMinPpm;

    @Override
    public String toString() {
        return "HouseDetail{" +
                "houseId=" + houseId +
                ", houseName='" + houseName + '\'' +
                ", houseOwnerId=" + houseOwnerId +
                ", plantId=" + plantId +
                ", plantTime=" + plantTime +
                ", plantName='" + plantName + '\'' +
                ", plantMinOC=" + plantMinOC +
                ", plantMaxOC=" + plantMaxOC +
                ", PlantComment='" + PlantComment + '\'' +
                ", plantMinPH=" + plantMinPH +
                ", plantMaxPH=" + plantMaxPH +
                ", plantMaxRH=" + plantMaxRH +
                ", plantMinRH=" + plantMinRH +
                ", plantMaxLx=" + plantMaxLx +
                ", plantMinLx=" + plantMinLx +
                ", plantMaxPpm=" + plantMaxPpm +
                ", plantMinPpm=" + plantMinPpm +
                '}';
    }

    public Date getPlantTime() {
        return plantTime;
    }

    public void setPlantTime(Date plantTime) {
        this.plantTime = plantTime;
    }

    public HouseDetail(Long houseId, String houseName, Long houseOwnerId, Long plantId, Date plantTime, String plantName, Float plantMinOC, Float plantMaxOC, String plantComment, Float plantMinPH, Float plantMaxPH, Float plantMaxRH, Float plantMinRH, Float plantMaxLx, Float plantMinLx, Float plantMaxPpm, Float plantMinPpm) {
        this.houseId = houseId;
        this.houseName = houseName;
        this.houseOwnerId = houseOwnerId;
        this.plantId = plantId;
        this.plantTime = plantTime;
        this.plantName = plantName;
        this.plantMinOC = plantMinOC;
        this.plantMaxOC = plantMaxOC;
        PlantComment = plantComment;
        this.plantMinPH = plantMinPH;
        this.plantMaxPH = plantMaxPH;
        this.plantMaxRH = plantMaxRH;
        this.plantMinRH = plantMinRH;
        this.plantMaxLx = plantMaxLx;
        this.plantMinLx = plantMinLx;
        this.plantMaxPpm = plantMaxPpm;
        this.plantMinPpm = plantMinPpm;
    }

    public Float getPlantMaxPpm() {
        return plantMaxPpm;
    }

    public void setPlantMaxPpm(Float plantMaxPpm) {
        this.plantMaxPpm = plantMaxPpm;
    }

    public Float getPlantMinPpm() {
        return plantMinPpm;
    }

    public void setPlantMinPpm(Float plantMinPpm) {
        this.plantMinPpm = plantMinPpm;
    }

    public HouseDetail(Long houseId, String houseName, Long houseOwnerId, Long plantId, String plantName, Float plantMinOC, Float plantMaxOC, String plantComment, Float plantMinPH, Float plantMaxPH, Float plantMaxRH, Float plantMinRH, Float plantMaxLx, Float plantMinLx, Float plantMaxPpm, Float plantMinPpm) {
        this.houseId = houseId;
        this.houseName = houseName;
        this.houseOwnerId = houseOwnerId;
        this.plantId = plantId;
        this.plantName = plantName;
        this.plantMinOC = plantMinOC;
        this.plantMaxOC = plantMaxOC;
        PlantComment = plantComment;
        this.plantMinPH = plantMinPH;
        this.plantMaxPH = plantMaxPH;
        this.plantMaxRH = plantMaxRH;
        this.plantMinRH = plantMinRH;
        this.plantMaxLx = plantMaxLx;
        this.plantMinLx = plantMinLx;
        this.plantMaxPpm = plantMaxPpm;
        this.plantMinPpm = plantMinPpm;
    }

    public Float getPlantMinPH() {
        return plantMinPH;
    }

    public void setPlantMinPH(Float plantMinPH) {
        this.plantMinPH = plantMinPH;
    }

    public Float getPlantMaxPH() {
        return plantMaxPH;
    }

    public void setPlantMaxPH(Float plantMaxPH) {
        this.plantMaxPH = plantMaxPH;
    }

    public Float getPlantMaxRH() {
        return plantMaxRH;
    }

    public void setPlantMaxRH(Float plantMaxRH) {
        this.plantMaxRH = plantMaxRH;
    }

    public Float getPlantMinRH() {
        return plantMinRH;
    }

    public void setPlantMinRH(Float plantMinRH) {
        this.plantMinRH = plantMinRH;
    }

    public Float getPlantMaxLx() {
        return plantMaxLx;
    }

    public void setPlantMaxLx(Float plantMaxLx) {
        this.plantMaxLx = plantMaxLx;
    }

    public Float getPlantMinLx() {
        return plantMinLx;
    }

    public void setPlantMinLx(Float plantMinLx) {
        this.plantMinLx = plantMinLx;
    }




    public HouseDetail() {
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

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public Float getPlantMinOC() {
        return plantMinOC;
    }

    public void setPlantMinOC(Float plantMinOC) {
        this.plantMinOC = plantMinOC;
    }

    public Float getPlantMaxOC() {
        return plantMaxOC;
    }

    public void setPlantMaxOC(Float plantMaxOC) {
        this.plantMaxOC = plantMaxOC;
    }

    public String getPlantComment() {
        return PlantComment;
    }

    public void setPlantComment(String plantComment) {
        PlantComment = plantComment;
    }
}
