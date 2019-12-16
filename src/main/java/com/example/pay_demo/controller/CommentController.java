package com.example.pay_demo.controller;

import com.example.pay_demo.entity.Comment;
import com.example.pay_demo.entity.Registration;
import com.example.pay_demo.service.CommentService;
import com.example.pay_demo.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    RegistrationService registrationService;

    @RequestMapping("addComment")
    public String addComment(Comment comment,Model model){
        System.out.println(comment.toString());
        List<Comment> comments=commentService.findComment(comment);
        if (comments.isEmpty()){
            boolean b=commentService.insertComment(comment);
            Registration registration=new Registration();
            registration.setIsComment("1");
            registration.setId(comment.getRegistrationId());
            registrationService.updateRegistration(registration);
        }else {
            commentService.updateComment(comment);
        }
        model.addAttribute("comment",comment);
        return "showComment";
    }

    @RequestMapping("toComment")
    public String toComment(Comment comment, Model model){
        System.out.println(comment.toString());
        List<Comment> comments=commentService.findComment(comment);
        if (!comments.isEmpty()){
            Comment comment1=comments.get(0);
            model.addAttribute("comment",comment1);
        }
        model.addAttribute("registrationId",comment.getRegistrationId());
        return "showComment";
    }
}
