package com.ht.university.unittest;

import com.ht.university.device.BaseDevice;
import com.ht.university.house.service.HouseService;
import com.ht.university.msg.DataRecord;
import com.ht.university.user.entity.User;
import org.junit.Test;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.SimpleFormatter;

/**
 * @Author: ht
 * @Date: Create in 13:46 2020/3/6
 * @Describe:
 * @Last_change:
 */
public class MyTest {
    @Test
    public void test1(){
        System.out.println(User.class.getName());
    }
    @Test
    public void testRandom(){
        BaseDevice baseDevice=new BaseDevice();
        baseDevice.setMax(new Float(2999));
        baseDevice.setMin(new Float(0.5f));

        for(int i=0;i<10;i++){
            Float virtulNum = baseDevice.getVirtulNum();
            System.out.println(virtulNum);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void testDate(){
        for (int i = 0; i <5 ; i++) {
            Date date = new Date(new java.util.Date().getTime());
            System.out.println(date.getTime());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testCalendar(){
        SimpleDateFormat sj=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        java.util.Date date=new java.util.Date();
        System.out.println(sj.format(date));
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,-1);
        System.out.println(sj.format(calendar.getTime()));


    }
}
