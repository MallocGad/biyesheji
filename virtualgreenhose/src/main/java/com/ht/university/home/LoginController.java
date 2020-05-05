package com.ht.university.home;

import com.ht.university.common.result.ResultBody;
import com.ht.university.common.utils.VGSessionUtils;
import com.ht.university.user.entity.User;
import com.ht.university.user.service.UserService;
import org.apache.catalina.manager.util.SessionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @Author: ht
 * @Date: Create in 18:51 2020/1/14
 * @Describe:
 * @Last_change:
 */
@Controller
@RequestMapping(value = "/")
public class LoginController {
    @Autowired
    private UserService userService;
    private Logger log=Logger.getLogger(LoginController.class);
    @RequestMapping(value = "/login")
    @ResponseBody
    public ResultBody<User> login(@RequestBody User user){
        //这里为什么有时候能获取到cookie有时候不能？
        HttpSession session = VGSessionUtils.getCurrentSession();
        User getUser = userService.getUserByName(user.getUserName());
        log.info(user.getUserName()+"登录！");
        if(null==getUser){
            return ResultBody.fail("用户名不存在");
        }else if(!getUser.getRole().equals(user.getRole())){
            return ResultBody.fail("错误角色登录"+getUser.getRole());
        }else if(!getUser.getPassword().equals(user.getPassword())){
            return ResultBody.fail("密码错误！");
        }
        log.info(user.getUserName()+"登录成功！");
        //将用户信息保存到当前会话
        session.setAttribute(User.class.getName(),getUser);
        return ResultBody.success(getUser);
    }
    //注销登录
    @RequestMapping(value = "/cancellation")
    @ResponseBody
    public ResultBody<User> cancellation(){
        return null;
    }
}
