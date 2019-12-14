package com.example.pay_demo.entity;

import lombok.Data;

@Data
public class Comment {
    private int id;

    private String comment;

    private int docterId;

    private int docterSubjectId;

    private int registrationId;
}
