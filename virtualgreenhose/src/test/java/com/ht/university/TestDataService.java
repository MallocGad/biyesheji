package com.ht.university;

import com.ht.university.device.BaseDevice;
import com.ht.university.device.DeviceRepository;
import com.ht.university.house.entity.House;
import com.ht.university.house.entity.HouseDetail;
import com.ht.university.house.mapper.HouseMapper;
import com.ht.university.house.repository.HouseRepository;
import com.ht.university.msg.DataRecord;
import com.ht.university.msg.DataRecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author: ht
 * @Date: Create in 20:47 2020/3/12
 * @Describe:
 * @Last_change:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDataService {
    @Autowired
    private DataRecordService dataRecordService;
    @Autowired
    private HouseMapper mapper;
    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private DeviceRepository repository;
    @Test
    public void testDate(){
        Date date = new Date();

        dataRecordService.addDataRecored(
                new DataRecord(null,new Long(111),111f,new Long(111),date));
        List<DataRecord> dataByHouseIdAndDeviceId = dataRecordService.getDataByHouseIdAndDeviceId(new Long(111), new Long(111));
        System.out.println(dataByHouseIdAndDeviceId);
    }

    @Test
    public void testStartEnd(){
        //测试获取近七天的数据
        Date end=new Date();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(end);
        calendar.add(Calendar.DATE,-2);
        List<DataRecord> dataByTime = dataRecordService.getDataByTime(calendar.getTime(), end, new Long(1), new Long(1));
        System.out.println(dataByTime);
    }

    /**
     * 制造数据 每三小时生成一次数据
     * 制造最近三天的数据
     */
    @Test
    public void addDatas(){
        List<BaseDevice> devices = repository.findAll();
        List<House> houses = houseRepository.findAll();
        //为每个温室的设备生成数据并做好记录,后期可以考虑用socket通知，或者邮箱通知
        Date date1 = new Date();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date1);
        calendar.add(Calendar.DATE,-3);
        long now = calendar.getTimeInMillis();
        for (int i = 0; i <24 ; i++) {
            //三小时前
            now+=3*60*60*1000;
            Date date= new Date(now);


            for (House house : houses) {
                if(house.getPlantId()==null)
                    continue;
                HouseDetail houseDetail = mapper.selectHouseDetailById(house.getHouseId());
                /****光照*****/
                devices.get(0).setMax(houseDetail.getPlantMaxLx());
                devices.get(0).setMin(houseDetail.getPlantMinLx());
                Float lx = devices.get(0).getVirtulNum();
                dataRecordService.addDataRecored(new DataRecord(null, devices.get(0).getId(), lx, house.getHouseId(), date));
                /****温度传感器****/
                devices.get(1).setMax(houseDetail.getPlantMaxOC());
                devices.get(1).setMin(houseDetail.getPlantMinOC());
                Float oC = devices.get(1).getVirtulNum();
                //光照超出范围开启或者关闭天窗,并记录设备开启关闭时间
                dataRecordService.addDataRecored(new DataRecord(null, devices.get(1).getId(), oC, house.getHouseId(), date));
                /***湿度****/
                devices.get(2).setMax(houseDetail.getPlantMaxRH());
                devices.get(2).setMin(houseDetail.getPlantMinRH());
                Float RH = devices.get(2).getVirtulNum();
                dataRecordService.addDataRecored(new DataRecord(null, devices.get(2).getId(), RH, house.getHouseId(), date));
                /****PH****/
                devices.get(3).setMax(houseDetail.getPlantMaxPH());
                devices.get(3).setMin(houseDetail.getPlantMinPH());
                Float PH = devices.get(3).getVirtulNum();
                dataRecordService.addDataRecored(new DataRecord(null, devices.get(3).getId(), PH, house.getHouseId(), date));
                /*****二氧化碳****/
                devices.get(4).setMax(houseDetail.getPlantMaxPpm());
                devices.get(4).setMin(houseDetail.getPlantMinPpm());
                Float ppm = devices.get(4).getVirtulNum();

                dataRecordService.addDataRecored(new DataRecord(null, devices.get(4).getId(), ppm, house.getHouseId(), date));

            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
