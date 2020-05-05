package com.ht.university.user.controller;

import com.ht.university.common.result.ResultBody;
import com.ht.university.user.UserVo;
import com.ht.university.user.entity.User;
import com.ht.university.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

/**
 * @Author: ht
 * @Date: Create in 20:56 2020/3/27
 * @Describe:
 * @Last_change:
 */
@RestController
@RequestMapping("api-user/")
public class UserController {
    @Autowired
    private UserService service;

    /**
     * 获取所有普通y用户
     * @return
     */
    @RequestMapping("get-normal-users")
    public ResultBody getNormalUsers(){
        List<User> normalUsers = service.getNormalUsers();
        return ResultBody.success(normalUsers);
    }

    @RequestMapping("delete-user")
    public ResultBody deleteUser(@RequestBody UserVo param){
        service.deleteUserById(param.getUserId());
        return ResultBody.success();
    }

    @RequestMapping("add-user")
    public ResultBody addUser(@RequestBody UserVo param){
        User user=new User();
        user.setUserName(param.getUserName());
        user.setPassword(param.getPassword());
        user.setEmail(param.getEmail());
        user.setCreateDate(new Date(System.currentTimeMillis()));
        user.setRole("user");
        service.addUser(user);
        return ResultBody.success();
    }
}
