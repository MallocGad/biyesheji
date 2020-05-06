package com.ht.university.tasks;

import com.ht.university.house.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: ht
 * @Date: Create in 9:15 2020/5/6
 * @Describe:数据自动产生任务
 * @Last_change:
 */
@Component
public class DataGenerate {
    @Autowired
    HouseService service;
    //    每一小时运行一次
//    @Scheduled(cron = "0 0 0/1 * * ?")
//    每3小时触发一次
    @Scheduled(cron = "0 0 0/3 * * ?")
    //每五秒运行一次
//    @Scheduled(cron = "0/5 * * * * ?")
    public void taskGenarate(){
        service.devicePutRecored();
    }
}
