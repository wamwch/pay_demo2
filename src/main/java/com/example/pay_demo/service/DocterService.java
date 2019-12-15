package com.example.pay_demo.service;

import com.example.pay_demo.entity.Docter;
import com.example.pay_demo.entity.DocterDTO;

import java.util.List;

public interface DocterService {
    List<Docter> findDocter(String docterName);

    public List<DocterDTO> findDocterGroupBySubject();
}
