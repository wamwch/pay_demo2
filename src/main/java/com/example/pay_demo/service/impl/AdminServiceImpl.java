package com.example.pay_demo.service.impl;

import com.example.pay_demo.entity.Admin;
import com.example.pay_demo.mapper.AdminMapper;
import com.example.pay_demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author:ljf
 * @date: 2019/12/15
 * @description:
 **/
@Service
public class AdminServiceImpl implements AdminService {
    
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin findUserByAcount(String acount) {
        Admin admin = adminMapper.findUserByAcount(acount);
        if(admin!= null)
            return  admin;
        return null;
    }

    @Override
    public boolean checkUserPassword(String password) {
        return false;
    }
}
