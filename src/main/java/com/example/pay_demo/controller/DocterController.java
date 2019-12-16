package com.example.pay_demo.controller;

import com.example.pay_demo.entity.Docter;
import com.example.pay_demo.entity.DocterDTO;
import com.example.pay_demo.entity.DocterSubjectDTO;
import com.example.pay_demo.service.DocterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("docter")
public class DocterController {

    @Autowired
    DocterService docterService;

    @RequestMapping("toDocter")
    public String toDocter(DocterSubjectDTO docterSubjectDTO, Model model){
//        List<Docter> docters=docterService.findDocter(docter.getDocterName());
        List<DocterSubjectDTO> docters=docterService.findDocterAndSubject(docterSubjectDTO);
        if (docterSubjectDTO!=null ||!docterSubjectDTO.getDocterName().equals(""))
        {
            model.addAttribute("docterName",docterSubjectDTO.getDocterName());
        }
        model.addAttribute("docters",docters);
        return "showDocter";
    }
    @RequestMapping("excellentDoctor")
    public String excellentDoctor(Docter docter, Model model){
        List<DocterDTO> docters=docterService.findDocterGroupBySubject();
        model.addAttribute("docters",docters);
        return "showExcellentDocter";
    }
}
