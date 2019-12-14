package com.example.pay_demo.controller;

import com.example.pay_demo.entity.Registration;
import com.example.pay_demo.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("registration")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @RequestMapping("insertRegistration")
    @ResponseBody
    public int insertRegistration(Registration registration){
        System.out.println(registration.toString());
        return registrationService.insertRegistration(registration);
    }

    public boolean updateRegistration(Registration registration){
        return registrationService.updateRegistration(registration);
    }

    public List<Registration> findRegistration(Registration registration){
        return registrationService.findRegistration(registration);
    }
}
