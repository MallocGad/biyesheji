package com.ht.university;

import com.ht.university.house.entity.House;
import com.ht.university.house.entity.HouseDetail;
import com.ht.university.house.service.HouseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author: ht
 * @Date: Create in 15:19 2020/3/5
 * @Describe:
 * @Last_change:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UniversityApplication.class)
public class HouseDetailTest {
    @Autowired
    HouseService houseService;
    @Test
    public void testGetHousesByUserId(){
        List<House> list = houseService.getHousesByUserId(new Long(3));
        System.out.println(list);
    }
    @Test
    public void testGetHouseDetail(){
        HouseDetail houseDetail = houseService.getHouseDetail(new Long(1));
        System.out.println(houseDetail);
    }

    @Test
    public void testHouseDevice(){
        houseService.devicePutRecored();
    }

    /**
     * 测试自动生成数据信息
     */
    @Test
    public void testAddDataRecored(){
        for (int i = 0; i < 10; i++) {
            houseService.devicePutRecored();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
