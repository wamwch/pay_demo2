package com.example.pay_demo.service.impl;

import com.example.pay_demo.entity.Comment;
import com.example.pay_demo.entity.Registration;
import com.example.pay_demo.mapper.CommentMapper;
import com.example.pay_demo.mapper.RegistrationMapper;
import com.example.pay_demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    RegistrationMapper registrationMapper;

    @Override
    @Transient
    public boolean insertComment(Comment comment) {

        Registration registration=new Registration();
        registration.setId(comment.getRegistrationId());
        registration.setStatus("1");
        registrationMapper.updateRegistration(registration);
        return commentMapper.insertComment(comment);
    }


    public List<Comment> findComment(Comment comment){
        return commentMapper.findComment(comment);
    }
}
