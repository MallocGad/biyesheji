package com.ht.university.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: ht
 * @Date: Create in 11:09 2020/3/11
 * @Describe: 设备服务层
 * @Last_change:
 */
@Service
public class DeviceService {
    @Autowired
    private DeviceRepository repository;
    public List<BaseDevice> getBaseDevices(){
        List<BaseDevice> devices = repository.findAll();
        return devices;
    }
}
