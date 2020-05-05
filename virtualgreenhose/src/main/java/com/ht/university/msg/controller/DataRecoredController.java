package com.ht.university.msg.controller;

import com.ht.university.common.result.ResultBody;
import com.ht.university.common.utils.VGSessionUtils;
import com.ht.university.msg.DataRecord;
import com.ht.university.msg.DataRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @Author: ht
 * @Date: Create in 10:13 2020/3/14
 * @Describe:
 * @Last_change:
 */
@RestController
@RequestMapping(value = "api-data")
public class DataRecoredController {
    @Autowired
    private DataRecordService service;

    @RequestMapping("/get-data-by-time")
    public ResultBody<List> getDatasByTime(@RequestBody MsgAndDataVo param){
        HttpSession session = VGSessionUtils.getCurrentSession();
        System.out.println("输出！！！！"+session.getId());
//        System.out.println("输出userid"+VGSessionUtils.getCurrentUserId());
        System.out.println(new Date(param.getStart()));
        List<DataRecord> datas = service.getDataByTime(new Date(param.getStart()), new Date(param.getEnd()), param.getHouseId(), param.getDeviceId());
        return ResultBody.success(datas);
    }

    @RequestMapping("/get-datas")
    public ResultBody<List> getDatas(@RequestBody MsgAndDataVo param){
        List<DataRecord> datas = service.getDataByHouseIdAndDeviceId(param.getHouseId(), param.getDeviceId());
        return ResultBody.success(datas);
    }
    @RequestMapping("/get-today")
    public ResultBody<List> getToDayDatas(@RequestBody MsgAndDataVo param){
        Date end = new Date();

//        service.getDataByTime();
         return null;
    }
}
