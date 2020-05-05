package com.ht.university.user.service.impl;

import com.ht.university.house.mapper.HouseMapper;
import com.ht.university.user.entity.User;
import com.ht.university.user.mapper.UserMapper;
import com.ht.university.user.repository.UserRepository;
import com.ht.university.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author: ht
 * @Date: Create in 17:08 2020/1/14
 * @Describe:
 * @Last_change:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper mapper;
    @Autowired
    HouseMapper houseMapper;
    @Override
    public List<User> getAllUser() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User getUserByName(String name) {
        User user = new User();
        user.setUserName(name);
        Example<User> example=Example.of(user);
        Optional<User> optionalUser = userRepository.findOne(example);
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        }
        return null;
    }

    @Override
    public User getUser(String username, String pwd,String role) {
        User user = new User();
        user.setUserName(username);
        user.setPassword(pwd);
        Example<User> example=Example.of(user);
        Optional<User> optionalUser = userRepository.findOne(example);
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        }
        return null;
    }

    @Override
    public List<User> getNormalUsers() {
        List<User> users = mapper.selectNomalUsers();
        return users;
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
        //删除用户后对应的id也应该设置为空
        houseMapper.setNullByUserId(userId);
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

}
