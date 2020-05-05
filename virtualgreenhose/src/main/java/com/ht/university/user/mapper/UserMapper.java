package com.ht.university.user.mapper;

import com.ht.university.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: ht
 * @Date: Create in 20:40 2020/3/27
 * @Describe:
 * @Last_change:
 */

/**
 * 简单查询使用@注解完成
 */
@Mapper
public interface UserMapper {

    /**
     * 查询所有普通用户
     * @return
     */
    @Select("select user_id userId," +
            "email,"+
            "user_name userName" +
            " from user where role= 'user'")
    public List<User> selectNomalUsers();
}
