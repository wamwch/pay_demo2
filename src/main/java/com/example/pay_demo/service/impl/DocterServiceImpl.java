package com.example.pay_demo.service.impl;

import com.example.pay_demo.entity.Docter;
import com.example.pay_demo.entity.DocterDTO;
import com.example.pay_demo.entity.DocterSubjectDTO;
import com.example.pay_demo.mapper.DocterMapper;
import com.example.pay_demo.mapper.RegistrationMapper;
import com.example.pay_demo.service.DocterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocterServiceImpl implements DocterService {

    @Autowired
    RegistrationMapper registrationMapper;

    @Autowired
    DocterMapper docterMapper;
    @Override
    public List<Docter> findDocter(String docterName) {
        return docterMapper.findDocter(docterName);
    }

    @Override
    public List<DocterDTO> findDocterGroupBySubject() {
        return registrationMapper.findDocterGroupBySubject();
    }

    @Override
    public List<DocterSubjectDTO> findDocterAndSubject(DocterSubjectDTO docterSubjectDTO) {
        return docterMapper.findDocterAndSubject(docterSubjectDTO);
    }
}
