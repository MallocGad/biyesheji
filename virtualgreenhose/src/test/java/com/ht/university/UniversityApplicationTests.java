package com.ht.university;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class UniversityApplicationTests {

    @Autowired
    ApplicationContext ioc;
    @Test
    void contextLoads() {
        boolean b=ioc.containsBean("helloService");
        String[] names = ioc.getBeanDefinitionNames();
        System.out.println(b);
        for (String e:names)
            System.out.println(e);
    }

}
