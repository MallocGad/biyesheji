package com.ht.university.msg;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * @Author: ht
 * @Date: Create in 12:50 2020/3/13
 * @Describe:
 * @Last_change:
 */
@Mapper
public interface DataRecordMapper {
    @Select(value = "select data_id dataId,device_Id deviceId,num,house_id houseId,time " +
            "from data_recored where time >= #{start} and time <= #{end} " +
            "and house_id=#{houseId} and device_id=#{deviceId} ")
    public List<DataRecord> selectDataByDate(Date start, Date end, Long houseId, Long deviceId);

}
