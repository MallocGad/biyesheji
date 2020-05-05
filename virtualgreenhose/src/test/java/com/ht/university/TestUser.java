package com.ht.university;

import com.ht.university.UniversityApplication;
import com.ht.university.user.entity.User;
import com.ht.university.user.mapper.UserMapper;
import com.ht.university.user.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author: ht
 * @Date: Create in 16:55 2020/1/14
 * @Describe:测试关于user的所有方法
 * @Last_change:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUser {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper mapper;
    @Test
    public void test1(){
        List<User> all = userRepository.findAll();
        System.out.println(all);
    }
    @Test
    public void test2(){
        List<User> users = mapper.selectNomalUsers();
        System.out.println(users);
    }
}
