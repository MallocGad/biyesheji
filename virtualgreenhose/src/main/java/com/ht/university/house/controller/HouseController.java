package com.ht.university.house.controller;

import com.ht.university.common.result.ResultBody;
import com.ht.university.house.HouseVo;
import com.ht.university.house.entity.House;
import com.ht.university.house.entity.HouseDetail;
import com.ht.university.house.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

/**
 * @Author: ht
 * @Date: Create in 13:47 2020/3/5
 * @Describe:house温室相关的controller层
 * @Last_change:
 */
@RestController
@RequestMapping("api-house")
public class HouseController {

    @Autowired
    /**
     * 获取对应温室的详情信息
     * 入参需要houseId
     * @param param
     * @return
     */
    private HouseService service;
    @RequestMapping("/get-house-detail")
    public ResultBody<HouseDetail> getHouseDetail(@RequestBody HouseVo param){
        HouseDetail houseDetail = service.getHouseDetail(param.getHouseId());
        return ResultBody.success(houseDetail);
    }

    /**
     * 获取用户所属的温室列表
     * @param param
     * @return
     */
    @RequestMapping("/get-houses-list")
    public ResultBody<List> getHouseList(@RequestBody HouseVo param){
        List<House> housesByUserId = service.getHousesByUserId(param.getUserId());
        return ResultBody.success(housesByUserId);
    }

    /**
     * 获取所有的温室
     * @return
     */
    @RequestMapping("/get-all-houses")
    public ResultBody<List> getAllHouse(){
        List<House> houses = service.getAllHouses();
        return ResultBody.success(houses);
    }
    /**
     * 添加温室
     * @param param
     * @return
     */
    @RequestMapping("/add-house")
    public ResultBody addHouse(@RequestBody HouseVo param){
        House house=new House();
        house.setHouseName(param.getHouseName());
        house.setHouseOwnerId(param.getHouseOwnerId());
        house.setPlantId(param.getPlantId());
        //添加温室时这个时间的逻辑待定
        house.setPlantTime(new Date(System.currentTimeMillis()));
        service.addHouse(house);
        return ResultBody.success();
    }

    @RequestMapping("/delete-house")
    public RequestBody deleteHouse(@RequestBody HouseVo param){
        return null;
    }
}
