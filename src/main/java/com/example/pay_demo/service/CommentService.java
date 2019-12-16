package com.example.pay_demo.service;

import com.example.pay_demo.entity.Comment;

import java.util.List;

public interface CommentService {

    boolean insertComment(Comment comment);

    public List<Comment> findComment(Comment comment);
}
