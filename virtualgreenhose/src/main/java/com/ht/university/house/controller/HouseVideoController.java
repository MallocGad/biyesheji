package com.ht.university.house.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: ht
 * @Date: Create in 10:45 2020/3/24
 * @Describe:
 * @Last_change:
 */
@Controller
@RequestMapping("api-house")
public class HouseVideoController {
    @RequestMapping(value = "/player",method = RequestMethod.GET)
    public void player(){

    }
}
