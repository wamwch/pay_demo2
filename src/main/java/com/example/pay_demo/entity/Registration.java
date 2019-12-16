package com.example.pay_demo.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class Registration {
    private String id ;
    private String openid;
    private String name;
    private String sex;
    private String age;
    private String docterId;
    private String docterName;
    private String docterSubjectId;
    private String isComment;
    private String status;
    private String description;
    private String phone;
    private Date time;
    private String orderId;

}
