package com.ht.university.msg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;


/**
 * @Author: ht
 * @Date: Create in 18:01 2020/3/12
 * @Describe:传感器数据录入数据库
 * @Last_change:
 */
@Service
public class DataRecordService {
    @Autowired
    private DataRecordRepository dataRecordRepository;
    @Autowired
    private DataRecordMapper mapper;
    /**
     * 新增数据
     * @param dataRecord
     */
    public void addDataRecored(DataRecord dataRecord){
        dataRecordRepository.save(dataRecord);
    }

    /**
     * 获取数据
     */
    public List<DataRecord> getDataByHouseIdAndDeviceId(Long houseId,Long deviceId){
        DataRecord dataRecord=new DataRecord(null,deviceId,null,houseId,null);
        Example<DataRecord> example=Example.of(dataRecord);
        List<DataRecord> all = dataRecordRepository.findAll(example);
        return all;
    }

    public List<DataRecord> getDataByTime(Date start, Date end, Long houseId,Long deviceId){
        List<DataRecord> dataRecords = mapper.selectDataByDate(start, end, houseId, deviceId);
        return dataRecords;
    }
}
