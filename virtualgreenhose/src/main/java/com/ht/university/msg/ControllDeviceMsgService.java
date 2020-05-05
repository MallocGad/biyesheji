package com.ht.university.msg;

import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: ht
 * @Date: Create in 16:31 2020/3/11
 * @Describe:
 * @Last_change:
 */
@Service
public class ControllDeviceMsgService {
    @Autowired
    private ControllDeviceMsgRepository repository;

    public void openTongFengDev(Long houseId,Long userId){
        repository.save(new ControllDeviceMsg(null,"开启通风设备",houseId,userId,new Date().toString()));
    }

    public void closeTongFengDev(Long houseId,Long userId){
        repository.save(new ControllDeviceMsg(null,"关闭通风设备",houseId,userId,new Date().toString()));
    }

    public void openSaShuiDev(Long houseId,Long userId){
        repository.save(new ControllDeviceMsg(null,"开启洒水系统",houseId,userId,new Date().toString()));

    }

    public void closeSaShuiDev(Long houseId,Long userId){
        repository.save(new ControllDeviceMsg(null,"关闭洒水系统",houseId,userId,new Date().toString()));

    }

    public void closeBaoWenDev(Long houseId,Long userId){
        repository.save(new ControllDeviceMsg(null,"关闭保温设备",houseId,userId,new Date().toString()));
    }
    public void openBaowenDev(Long houseId,Long userId){
        repository.save(new ControllDeviceMsg(null,"开启保温设备",houseId,userId,new Date().toString()));
    }

    public void openNongYaoDev(Long houseId,Long userId){
        repository.save(new ControllDeviceMsg(null,"开启农药喷洒设备",houseId,userId,new Date().toString()));

    }

    public void closeNongYaoDev(Long houseId,Long userId){
        repository.save(new ControllDeviceMsg(null,"关闭农药喷洒设备",houseId,userId,new Date().toString()));
    }

    public void closeWindowDev(Long houseId,Long userId){
        repository.save(new ControllDeviceMsg(null,"关闭天窗",houseId,userId,new Date().toString()));
    }
    public void openWindowDev(Long houseId,Long userId){
        repository.save(new ControllDeviceMsg(null,"开启天窗",houseId,userId,new Date().toString()));
    }
}
