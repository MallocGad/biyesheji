package com.ht.university.msg.controller;

import com.ht.university.common.result.ResultBody;
import com.ht.university.common.utils.VGSessionUtils;
import com.ht.university.msg.ControllDeviceMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ht
 * @Date: Create in 9:47 2020/3/14
 * @Describe:用户手动操作设备记录
 * @Last_change:
 */
@RestController
@RequestMapping(value = "api-msg")
public class MsgController {
    @Autowired
    private ControllDeviceMsgService service;
    @RequestMapping("/open-device")
    public ResultBody openDevice(@RequestBody MsgAndDataVo param){
        Long userId = VGSessionUtils.getCurrentUserId();
        Long houseId = param.getHouseId();
        switch (param.getDeviceType()){
            case 1:service.openTongFengDev(houseId,userId);break;
            case 2:service.openSaShuiDev(houseId,userId);break;
            case 3:service.openBaowenDev(houseId,userId);break;
            case 4:service.openNongYaoDev(houseId,userId);break;
            case 5:service.openWindowDev(houseId,userId);break;
            default:return ResultBody.fail("打开设备失败");
        }
        return ResultBody.success();
    }
    @RequestMapping("/close-device")
    public ResultBody closeDevice(@RequestBody MsgAndDataVo param){
        Long userId = VGSessionUtils.getCurrentUserId();
        Long houseId = param.getHouseId();
        switch (param.getDeviceType()){
            case 1:service.closeTongFengDev(houseId,userId);break;
            case 2:service.closeSaShuiDev(houseId,userId);break;
            case 3:service.closeBaoWenDev(houseId,userId);break;
            case 4:service.closeNongYaoDev(houseId,userId);break;
            case 5:service.closeWindowDev(houseId,userId);break;
            default:return ResultBody.fail("关闭设备失败");
        }
        return ResultBody.success();
    }
}
