package com.example.pay_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {
    @RequestMapping("toUserIndex")
    public String toIndex(){
        return "userIndex";
    }

    @RequestMapping("toRegistration")
    public String toRegistration(){
        return "registration";
    }


}
