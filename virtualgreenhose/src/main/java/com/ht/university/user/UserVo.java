package com.ht.university.user;

import com.ht.university.user.entity.User;

import java.sql.Date;

/**
 * @Author: ht
 * @Date: Create in 17:06 2020/3/29
 * @Describe:user相关请求参数封装
 * @Last_change:
 */
public class UserVo {
    private Long userId;
    private String userName;
    private String password;
    private String role;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
