package com.example.pay_demo.mapper;

import com.example.pay_demo.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("insert into comment(comment,docter_id,docter_subject_id,registration_id) values(#{comment} " +
            ",#{docterId},#{docterSubjectId},#{registrationId} )")
    boolean insertComment(Comment comment);

    @Select(
            "<script>"+
            "select id,comment,docter_id docterId, docter_subject_id " +
            " docterSubjectId,registration_id registrationId from comment"+
            " <where>  1=1" +
            " <if test=\"id != null\"> and id = #{id}</if> " +
            " <if test=\"registrationId != null\"> and registration_id = #{registrationId}</if> " +
            " <if test=\"docterId != null\"> and docter_id = #{docterId}</if> " +
            " <if test=\"docterSubjectId != null\"> and docter_subject_id = #{docterSubjectId}</if> " +
            " </where> " +
            " </script> "
    )
    public List<Comment> findComment(Comment comment);

    @Update("<script>"+
            "update comment set comment=#{comment} where registration_id=#{registrationId}"+
            " </script> ")
    public boolean updateComment(Comment comment);

}
