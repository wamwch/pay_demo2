package com.example.pay_demo;

import com.example.pay_demo.entity.PayEntity;
import com.example.pay_demo.entity.Registration;
import com.example.pay_demo.mapper.DocterMapper;
import com.example.pay_demo.mapper.RegistrationMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

@SpringBootTest
class PayDemoApplicationTests {

    @Autowired
    DocterMapper docterMapper;

    @Autowired
    RegistrationMapper registrationMapper;
    @Test
    void contextLoads() throws IOException, URISyntaxException {
        Registration registration=new Registration();
        registration.setName("小丑");
        registration.setAge("18");
        registration.setOpenid("15234");
        registration.setSex("男");
        registration.setDocterId("12345");
        registration.setDocterSubjectId("17893");
        registration.setStatus("0");
        registration.setIsComment("0");
        System.out.println(registration.toString());
        int b=registrationMapper.insertRegistration(registration);
        System.out.println(b);
//        Registration registration1=new Registration();
//        registration1.setStatus(0);
//        registration1.setId(1);
//        List<Registration> registrations=registrationMapper.findRegistration(registration);
//        System.out.println(registrations.toString());


    }



}
