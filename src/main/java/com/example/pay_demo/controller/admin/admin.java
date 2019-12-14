package com.example.pay_demo.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author:ljf
 * @date: 2019/12/15
 * @description:
 **/
@Controller(value = "admin")
public class admin {

    @RequestMapping("/login")
    public String login(){
        return "admin/login";
    }
}
