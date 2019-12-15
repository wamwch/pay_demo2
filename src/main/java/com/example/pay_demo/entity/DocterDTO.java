package com.example.pay_demo.entity;

import lombok.Data;

@Data
public class DocterDTO {
    private String id;

    private String docterName;

    private String phone;

    private String subjectName;

    /**
     * 排名
     */
    private int ranking;

    /**
     * 看病数量
     */
    private int amount ;
}
