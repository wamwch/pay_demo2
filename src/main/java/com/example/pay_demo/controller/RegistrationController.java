package com.example.pay_demo.controller;

import com.example.pay_demo.entity.Docter;
import com.example.pay_demo.entity.DocterSubjectDTO;
import com.example.pay_demo.entity.Registration;
import com.example.pay_demo.service.DocterService;
import com.example.pay_demo.service.RegistrationService;
import com.example.pay_demo.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("registration")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @Autowired
    SubjectService subjectService;

    @RequestMapping("insertRegistration")
    @ResponseBody
    public int insertRegistration(Registration registration){
        registration.setStatus("0");
        registration.setIsComment("0");
        System.out.println(registration.toString());
        if (registration.getDocterId()==null){
            DocterSubjectDTO docterSubjectDTO=new DocterSubjectDTO();
            docterSubjectDTO.setId(registration.getDocterSubjectId());
            List<DocterSubjectDTO> docterBySubjectDocter=subjectService.findDocterBySubjectDocter(docterSubjectDTO);
            if (!docterBySubjectDocter.isEmpty()){
                registration.setDocterId(docterBySubjectDocter.get(0).getDocterId());
            }
        }
        return registrationService.insertRegistration(registration);
    }

    public boolean updateRegistration(Registration registration){
        return registrationService.updateRegistration(registration);
    }

    @RequestMapping("findRegistration")
    public String findRegistration(Registration registration, Model model){
        model.addAttribute("registrations",registrationService.findRegistration(registration));
        return "showRegistration";
    }
}
