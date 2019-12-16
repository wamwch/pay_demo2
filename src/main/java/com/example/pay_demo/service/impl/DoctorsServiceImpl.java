package com.example.pay_demo.service.impl;

import com.example.pay_demo.entity.VO.DoctorInfoVO;
import com.example.pay_demo.mapper.DocterMapper;
import com.example.pay_demo.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:ljf
 * @date: 2019/12/15
 * @description:
 **/
@Service
public class DoctorsServiceImpl implements DoctorService {

    @Autowired
    private DocterMapper docterMapper;

    @Override
    public List<DoctorInfoVO> findAllDoctors() {
        return  docterMapper.findAlldoctors();
    }
}
