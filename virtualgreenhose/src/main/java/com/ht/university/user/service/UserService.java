package com.ht.university.user.service;

import com.ht.university.user.entity.User;

import java.util.List;

/**
 * @Author: ht
 * @Date: Create in 17:07 2020/1/14
 * @Describe:
 * @Last_change:
 */
public interface UserService {
    /**
     * 获取所有用户
     * @return
     */
    public List<User> getAllUser();

    /**
     * 通过用户名获取用户
     * @param name
     * @return
     */
    public User getUserByName(String name);

    /**
     * 新增
     * @param user
     */
    public void addUser(User user);

    /**
     * 通过条件查询用户
     * @param username
     * @param pwd
     * @param role
     * @return
     */
    public User getUser(String username,String pwd,String role);

    /**
     * 查询所有普通用户
     * @return
     */
    public List<User> getNormalUsers();

    /**
     * 删除用户
     */

    public void deleteUserById(Long userId);
}
