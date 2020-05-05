package com.ht.university.house.service;

import com.ht.university.house.entity.House;
import com.ht.university.house.entity.HouseDetail;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public interface HouseService{
    /**
     * 通过用户id获取所属的温室列表
     * @param userId
     * @return
     */
    public List<House> getHousesByUserId(Long userId);

    /**
     * 通过温室houseId获取温室详细信息
     * @param houseId
     * @return
     */
    public HouseDetail getHouseDetail(Long houseId);

    /**
     * 温室的各个设备获取数据并记录，温度超出范围生成通知信息
     */
    public void devicePutRecored();

    /**
     * 新增温室
     * @param house
     */
    public void addHouse(House house);

    /**
     * 删除温室
     * @param houseId
     */
    public void deleteHouse(Long houseId);

    /**
     * 编辑温室
     * @param house
     */
    public void editHouse(House house);

    /**
     * 获取所有温室
     */
    public List<House> getAllHouses();
}