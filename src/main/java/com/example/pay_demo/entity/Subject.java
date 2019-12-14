package com.example.pay_demo.entity;

import lombok.Data;

import java.util.List;

@Data
public class Subject {
    private int id;

    private String subjectName;

    List<Docter> docters;
}
