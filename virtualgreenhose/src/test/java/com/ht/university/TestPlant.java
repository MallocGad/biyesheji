package com.ht.university;

import com.ht.university.plant.Plant;
import com.ht.university.plant.PlantService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.AccessType;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author: ht
 * @Date: Create in 18:55 2020/4/6
 * @Describe:
 * @Last_change:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPlant {
    @Autowired
    PlantService service;
    @Test
    public void test(){
        List<Plant> plants = service.selectPlant();
        for (int i = 0; i < plants.size(); i++) {
            System.out.println(plants.get(i));
        }
    }
}
