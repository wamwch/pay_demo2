package com.example.pay_demo.controller;

import com.example.pay_demo.entity.Docter;
import com.example.pay_demo.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    SubjectService subjectService;
    @RequestMapping("toUserIndex")
    public String toIndex(){
        return "userIndex";
    }

    @RequestMapping("toRegistration")
    public String toRegistration(Model model){
        model.addAttribute("subjects",subjectService.findSubject(null));
        model.addAttribute("docters",new ArrayList<Docter>());
        return "registration";
    }


}
