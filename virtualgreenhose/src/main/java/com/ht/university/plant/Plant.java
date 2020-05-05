package com.ht.university.plant;

import javax.persistence.*;

/**
 * @Author: ht
 * @Date: Create in 10:16 2020/4/4
 * @Describe:
 * @Last_change:
 */
@Entity
@Table(name = "plant")
public class Plant {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long plantId;
    private String plantName;
    //作物生长温度
    @Column(name = "plant_min_oC")
    private Float plantMinOC;
    @Column(name = "plant_max_oC")
    private Float plantMaxOC;
    private String PlantComment;
    //ph值
    @Column(name = "plant_min_PH")
    private Float plant_minPH;
    @Column(name = "plant_max_PH")
    private Float plant_maxPH;
    //湿度
    @Column(name = "plant_max_RH")
    private Float plant_maxRH;
    @Column(name = "plant_min_RH")
    private Float plant_minRH;
    //光照强度
    @Column(name = "plant_max_lx")
    private Float plant_maxLx;
    @Column(name = "plant_min_lx")
    private Float plant_minLx;
    //二氧化碳浓度
    @Column(name = "plant_max_ppm")
    private Float plant_maxPpm;
    @Column(name = "plant_min_ppm")
    private Float plant_minPpm;

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


    public String getPlantComment() {
        return PlantComment;
    }

    public void setPlantComment(String plantComment) {
        PlantComment = plantComment;
    }

    public Float getPlantMinOC() {
        return plantMinOC;
    }

    public void setPlantMinOC(Float plant_minOC) {
        this.plantMinOC = plant_minOC;
    }

    public Float getPlantMaxOC() {
        return plantMaxOC;
    }

    public void setPlantMaxOC(Float plant_maxOC) {
        this.plantMaxOC = plant_maxOC;
    }

    public Float getPlant_minPH() {
        return plant_minPH;
    }

    public void setPlant_minPH(Float plant_minPH) {
        this.plant_minPH = plant_minPH;
    }

    public Float getPlant_maxPH() {
        return plant_maxPH;
    }

    public void setPlant_maxPH(Float plant_maxPH) {
        this.plant_maxPH = plant_maxPH;
    }

    public Float getPlant_maxRH() {
        return plant_maxRH;
    }

    public void setPlant_maxRH(Float plant_maxRH) {
        this.plant_maxRH = plant_maxRH;
    }

    public Float getPlant_minRH() {
        return plant_minRH;
    }

    public void setPlant_minRH(Float plant_minRH) {
        this.plant_minRH = plant_minRH;
    }

    public Float getPlant_maxLx() {
        return plant_maxLx;
    }

    public void setPlant_maxLx(Float plant_maxLx) {
        this.plant_maxLx = plant_maxLx;
    }

    public Float getPlant_minLx() {
        return plant_minLx;
    }

    public void setPlant_minLx(Float plant_minLx) {
        this.plant_minLx = plant_minLx;
    }

    public Float getPlant_maxPpm() {
        return plant_maxPpm;
    }

    public void setPlant_maxPpm(Float plant_maxPpm) {
        this.plant_maxPpm = plant_maxPpm;
    }

    public Float getPlant_minPpm() {
        return plant_minPpm;
    }

    public void setPlant_minPpm(Float plant_minPpm) {
        this.plant_minPpm = plant_minPpm;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "plantId=" + plantId +
                ", plantName='" + plantName + '\'' +
                ", plantMinOC=" + plantMinOC +
                ", plantMaxOC=" + plantMaxOC +
                ", PlantComment='" + PlantComment + '\'' +
                ", plant_minPH=" + plant_minPH +
                ", plant_maxPH=" + plant_maxPH +
                ", plant_maxRH=" + plant_maxRH +
                ", plant_minRH=" + plant_minRH +
                ", plant_maxLx=" + plant_maxLx +
                ", plant_minLx=" + plant_minLx +
                ", plant_maxPpm=" + plant_maxPpm +
                ", plant_minPpm=" + plant_minPpm +
                '}';
    }
}
