package com.ht.university;

import com.ht.university.house.entity.House;
import com.ht.university.house.service.HouseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: ht
 * @Date: Create in 20:19 2020/3/27
 * @Describe:
 * @Last_change:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class HouseServceTest {
    @Autowired
    private HouseService service;
    @Test
    public void testAdd(){
        service.addHouse(new House(null,"测试添加",null,null));
    }
    @Test
    public void testEdit(){
        service.editHouse(new House(
                3L,"修改测试",3L,2L,null
        ));
    }

}
