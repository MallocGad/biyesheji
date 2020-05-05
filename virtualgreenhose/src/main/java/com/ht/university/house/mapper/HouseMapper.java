package com.ht.university.house.mapper;

import com.ht.university.house.entity.House;
import com.ht.university.house.entity.HouseDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.stereotype.Repository;

/**
 * @Author: ht
 * @Date: Create in 17:43 2020/3/5
 * @Describe:
 * @Last_change:
 */
@Mapper
public interface HouseMapper {
    /**
     * 查询温室详细信息
     * @param id
     * @return
     */
    public HouseDetail selectHouseDetailById(Long id);

    /**
     * 编辑
     * @param house
     */
    @Update("update house " +
            "set house_name = #{houseName}," +
            "house_owner_id = #{houseOwnerId}," +
            "plant_id = #{plantId}," +
            "plant_time = #{plantTime}" +
            "where house_id = #{houseId}")
    public void editHouse(House house);

    /**
     * 当删除用户时 更新温室所属者id为null
     * @param userId
     */
    @Update("update house " +
            "set house_owner_id= null " +
            "where house_owner_id=#{userId}")
    public void setNullByUserId(Long userId);

    /**
     *当删除作物时 更新温室种植信息为null
     * @param plantId
     */
    @Update("update house " +
            "set plant_id=null " +
            "where plant_id=#{plantId}")
    public void setNullByPlantId(Long plantId);
}