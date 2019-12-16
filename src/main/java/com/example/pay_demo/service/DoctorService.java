package com.example.pay_demo.service;

import com.example.pay_demo.entity.VO.DoctorInfoVO;

import java.util.List;

/**
 * @author:ljf
 * @date: 2019/12/15
 * @description:
 **/
public interface DoctorService {

    List<DoctorInfoVO> findAllDoctors();
}
