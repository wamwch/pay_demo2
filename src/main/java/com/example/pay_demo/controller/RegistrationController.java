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

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("registration")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @Autowired
    SubjectService subjectService;

    @RequestMapping("insertRegistration")
    public String insertRegistration(Registration registration){
        registration.setStatus("2");
        registration.setIsComment("0");
        long orderId=System.currentTimeMillis();
        registration.setOrderId(String.valueOf(orderId));
        System.out.println("orderId"+orderId);
        System.out.println(registration.toString());
        if (registration.getDocterId()==null){
            DocterSubjectDTO docterSubjectDTO=new DocterSubjectDTO();
            docterSubjectDTO.setId(registration.getDocterSubjectId());
            List<DocterSubjectDTO> docterBySubjectDocter=subjectService.findDocterBySubjectDocter(docterSubjectDTO);
            if (!docterBySubjectDocter.isEmpty()){
                registration.setDocterId(docterBySubjectDocter.get(0).getDocterId());
            }
        }
        registrationService.insertRegistration(registration);
//        return "redirect:/registration/findRegistration";
        return "redirect:/api/client/pay/unifiedOrder?orderId="+orderId+"&money="+1;
    }

    public boolean updateRegistration(Registration registration){
        return registrationService.updateRegistration(registration);
    }

    @RequestMapping("findRegistration")
    public String findRegistration(Registration registration, Model model, HttpSession session){
        String openid=session.getAttribute("openid").toString();
        System.out.println(openid);

        model.addAttribute("registrations",registrationService.findRegistration(registration));
        return "showRegistration";
    }

    @RequestMapping("findMyRegistration")
    public String findMyRegistration(Registration registration, Model model, HttpSession session){
        String openid=session.getAttribute("openid").toString();
        System.out.println(openid);
        registration.setOpenid(openid);
        model.addAttribute("registrations",registrationService.findRegistration(registration));
        return "showRegistration";
    }
}
