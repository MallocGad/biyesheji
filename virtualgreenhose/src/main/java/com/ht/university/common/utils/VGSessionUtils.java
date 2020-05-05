package com.ht.university.common.utils;

import com.ht.university.user.entity.User;
import org.apache.catalina.manager.util.SessionUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author: ht
 * @Date: Create in 18:50 2020/1/12
 * @Describe: 获取sessions
 * @Last_change:
 */
public class VGSessionUtils {
    public static HttpSession getCurrentSession(){
        return getRequest().getSession();
    }
    public static HttpServletRequest getRequest(){
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        return request;
    }
    public static Long getCurrentUserId(){
        User user=(User)getCurrentSession().getAttribute(User.class.getName());
        return user.getUserId();
    }
}
