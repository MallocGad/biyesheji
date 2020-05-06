package com.ht.university.house.service.impl;

import com.ht.university.device.BaseDevice;
import com.ht.university.msg.*;
import com.ht.university.device.DeviceRepository;
import com.ht.university.house.entity.House;
import com.ht.university.house.entity.HouseDetail;
import com.ht.university.house.mapper.HouseMapper;
import com.ht.university.house.repository.HouseRepository;
import com.ht.university.house.service.HouseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: ht
 * @Date: Create in 15:09 2020/3/5
 * @Describe:
 * @Last_change:
 */
@Service
public class HouseServiceImpl implements HouseService {
    private Logger log=Logger.getLogger(HouseServiceImpl.class);

    //控制设备被开启或关闭写入信息
    @Autowired
    private ControllDeviceMsgService controllDeviceMsgService;
    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private HouseMapper mapper;
    @Autowired
    private DeviceRepository repository;
    //传感器数据录入
    @Autowired
    private DataRecordService dataRecordService;
    @Override
    public List<House> getHousesByUserId(Long userId) {
        House house = new House();
        house.setHouseOwnerId(userId);
        Example<House> example = Example.of(house);
        List<House> houseDetailList = houseRepository.findAll(example);
        return houseDetailList;
    }

    @Override
    public HouseDetail getHouseDetail(Long houseId) {
        HouseDetail houseDetail = mapper.selectHouseDetailById(houseId);
        return houseDetail;
    }

    @Override

    public void devicePutRecored() {
        List<BaseDevice> devices = repository.findAll();
        List<House> houses = houseRepository.findAll();
        //为每个温室的设备生成数据并做好记录,后期可以考虑用socket通知，或者邮箱通知
        Date date = new Date();
        for (House house : houses){
            HouseDetail houseDetail = mapper.selectHouseDetailById(house.getHouseId());
            /****光照*****/
            devices.get(0).setMax(houseDetail.getPlantMaxLx());
            devices.get(0).setMin(houseDetail.getPlantMinLx());
            Float lx = devices.get(0).getVirtulNum();
            log.error(devices.get(0)+"产生光照数据为："+lx);
            //温度超出范围开启或者关闭天窗,并记录设备开启关闭时间
            //温度超出范围值应该形成通知信息
            if(lx>houseDetail.getPlantMaxLx()){
                //设备信息
                controllDeviceMsgService.closeBaoWenDev(houseDetail.getHouseId(),houseDetail.getHouseOwnerId());
                //通知信息

            }else if (lx<houseDetail.getPlantMinLx()){
                controllDeviceMsgService.openBaowenDev(houseDetail.getHouseId(),houseDetail.getHouseOwnerId());
            }
            dataRecordService.addDataRecored(new DataRecord(null,devices.get(0).getId(),lx,house.getHouseId(),date));
            /****温度传感器****/
            devices.get(1).setMax(houseDetail.getPlantMaxOC());
            devices.get(1).setMin(houseDetail.getPlantMinOC());
            Float oC = devices.get(1).getVirtulNum();
            log.info(devices.get(1)+"产生数据为："+oC);
            //光照超出范围开启或者关闭天窗,并记录设备开启关闭时间
            //另外超出范围值应该形成通知信息
            if(oC>houseDetail.getPlantMaxOC()){
                //设备信息
                controllDeviceMsgService.closeWindowDev(houseDetail.getHouseId(),houseDetail.getHouseOwnerId());
                //通知信息

            }else if (oC<houseDetail.getPlantMinOC()){
                controllDeviceMsgService.openWindowDev(houseDetail.getHouseId(),houseDetail.getHouseOwnerId());
            }
            dataRecordService.addDataRecored(new DataRecord(null,devices.get(1).getId(),oC,house.getHouseId(),date));
            /***湿度****/
            devices.get(2).setMax(houseDetail.getPlantMaxRH());
            devices.get(2).setMin(houseDetail.getPlantMinRH());
            Float RH = devices.get(2).getVirtulNum();
            log.info(devices.get(2)+"产生数据为："+RH);
            //RH超出范围开启洒水系统即可,并记录设备开启关闭时间
            //另外超出范围值应该形成通知信息
            if(RH>houseDetail.getPlantMaxRH()){
                //设备信息
                controllDeviceMsgService.closeTongFengDev(houseDetail.getHouseId(),houseDetail.getHouseOwnerId());
                controllDeviceMsgService.closeSaShuiDev(houseDetail.getHouseId(),houseDetail.getHouseOwnerId());
                //通知信息

            }else if (RH<houseDetail.getPlantMinRH()){
                controllDeviceMsgService.openTongFengDev(houseDetail.getHouseId(),houseDetail.getHouseOwnerId());
                controllDeviceMsgService.openSaShuiDev(houseDetail.getHouseId(),houseDetail.getHouseOwnerId());

            }
            dataRecordService.addDataRecored(new DataRecord(null,devices.get(2).getId(),RH,house.getHouseId(),date));
            /****PH****/
            devices.get(3).setMax(houseDetail.getPlantMaxPH());
            devices.get(3).setMin(houseDetail.getPlantMinPH());
            Float PH = devices.get(3).getVirtulNum();
            log.info(devices.get(3)+"产生PH数据为："+PH);
            //湿度超出范围开启或者关闭通风设备,并记录设备开启关闭时间
            //另外超出范围值应该形成通知信息
            if(PH>houseDetail.getPlantMaxPH()){
                //设备信息
                controllDeviceMsgService.openSaShuiDev(houseDetail.getHouseId(),houseDetail.getHouseOwnerId());
                //通知信息

            }else if (PH<houseDetail.getPlantMinPH()){
                controllDeviceMsgService.openSaShuiDev(houseDetail.getHouseId(),houseDetail.getHouseOwnerId());
            }
            dataRecordService.addDataRecored(new DataRecord(null,devices.get(3).getId(),PH,house.getHouseId(),date));
            /*****二氧化碳****/
            devices.get(4).setMax(houseDetail.getPlantMaxPpm());
            devices.get(4).setMin(houseDetail.getPlantMinPpm());
            Float ppm = devices.get(4).getVirtulNum();
            log.info(devices.get(4)+"产生ppm数据为："+ppm);
            //二氧化然超出范围开启或者关闭通风设备,并记录设备开启关闭时间
            //另外超出范围值应该形成通知信息
            if(ppm>houseDetail.getPlantMaxPpm()){
                //设备信息
                controllDeviceMsgService.openTongFengDev(houseDetail.getHouseId(),houseDetail.getHouseOwnerId());
                //通知信息

            }else if (ppm<houseDetail.getPlantMinPH()){
                controllDeviceMsgService.closeTongFengDev(houseDetail.getHouseId(),houseDetail.getHouseOwnerId());
            }
            dataRecordService.addDataRecored(new DataRecord(null,devices.get(4).getId(),ppm,house.getHouseId(),date));

        }
    }

    @Override
    public void addHouse(House house) {
        //当用户没有设置植物id时此时没有种植作物的时间
        if(house.getPlantId()==null||house.getPlantId().equals("")){
            house.setPlantTime(new java.sql.Date(System.currentTimeMillis()));
        }
        houseRepository.save(house);
    }

    @Override
    public void deleteHouse(Long houseId) {
        houseRepository.deleteById(houseId);
    }

    @Override
    public void editHouse(House house) {
        //更新温室信息时自动更新种植的时间
        //后期应该加个日历控件可以修改种植时间
        if(house.getPlantTime()==null||house.getPlantTime().equals(""))
            house.setPlantTime(new java.sql.Date(System.currentTimeMillis()));
        mapper.editHouse(house);
    }

    @Override
    public List<House> getAllHouses() {
        List<House> all = houseRepository.findAll();
        return all;
    }


}

