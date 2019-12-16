package com.example.pay_demo.entity;

import lombok.Data;

@Data
public class Comment {
    private String id;

    private String comment;

    private String docterId;

    private String docterSubjectId;

    private String registrationId;
}
