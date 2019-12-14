package com.example.pay_demo.entity;

import lombok.Data;

@Data
public class Registration {
    private String id ;
    private String openid;
    private String name;
    private String sex;
    private String age;
    private String docterId;
    private String docterSubjectId;
    private String isComment;
    private String status;
    private String description;
    private String phone;

}
