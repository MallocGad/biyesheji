package com.ht.university.notice;

import com.ht.university.common.result.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: ht
 * @Date: Create in 12:58 2020/3/19
 * @Describe:通知信息控制层
 * @Last_change:
 */
@RestController
@RequestMapping("api-notice")
public class NoticeController {
    @Autowired
    private NoticeService service;
    @RequestMapping("get-notices")
    public ResultBody getNotices(){
        List<Notice> notices = service.getNotices();
        return ResultBody.success(notices);
    }

    @RequestMapping("add-notice")
    public ResultBody addNotices(@RequestBody NoticeVo param){
        service.addNotice(param.getTitle(),param.getContent());
        return ResultBody.success();
    }
}
