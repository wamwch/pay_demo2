package com.example.pay_demo.mapper;

import com.example.pay_demo.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("insert into comment(comment,docter_id,docter_subject_id,registration_id) values(#{comment} " +
            ",#{docterId},#{docterSubjectId},#{registrationId} )")
    boolean insertComment(Comment comment);

    @Select("select id,comment,docter_id docterId, docter_subject_id docterSubjectId,registration_id registrationId from comment")
    public List<Comment> findComment(Comment comment);

}
