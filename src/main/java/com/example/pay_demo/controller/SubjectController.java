package com.example.pay_demo.controller;

import com.example.pay_demo.entity.DocterSubjectDTO;
import com.example.pay_demo.entity.Subject;
import com.example.pay_demo.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @RequestMapping("findSubject")
    @ResponseBody
    public List<Subject> findSubject(Subject subject, Model model){
        List<Subject> subjects=subjectService.findSubject(subject);
        model.addAttribute("subjects",subjects);
        return null;
    }

    @RequestMapping("findDocterBySubject")
    @ResponseBody
    public List<DocterSubjectDTO> findDocterBySubject(Subject subject){
        System.out.println(subject.toString());
        return subjectService.findDocterBySubject(subject);
    }

}
