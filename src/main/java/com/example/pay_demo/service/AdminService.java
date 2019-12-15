package com.example.pay_demo.service;

import com.example.pay_demo.entity.Admin;

public interface AdminService {

    Admin findUserByAcount(String acount);

    boolean  checkUserPassword(String password);
}
