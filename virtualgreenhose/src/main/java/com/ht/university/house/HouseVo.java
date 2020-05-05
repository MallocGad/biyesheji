package com.ht.university.house;

import com.ht.university.house.entity.House;

/**
 * @Author: ht
 * @Date: Create in 14:50 2020/3/5
 * @Describe:请求house的入参实体封装
 * @Last_change:
 */
public class HouseVo extends House{
    private Long houseId;
    private Long userId;

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
