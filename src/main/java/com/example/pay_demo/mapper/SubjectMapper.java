package com.example.pay_demo.mapper;

import com.example.pay_demo.entity.Docter;
import com.example.pay_demo.entity.DocterSubjectDTO;
import com.example.pay_demo.entity.Subject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SubjectMapper {

    @Select("<script> " +
            "SELECT ds.id id, subject_id subjectId, docter_name docterName,phone  " +
            "FROM docter_subject ds left join docter d on d.id=ds.doctor_id  " +
            " <where>  1=1" +
            " <if test=\"id != null\"> and ds.subject_id = #{id}</if> " +
            " </where> " +
            " </script> ")
    public List<DocterSubjectDTO> findDocterBySubject(Subject subject);

    @Select("<script> " +
            "SELECT  * FROM subject " +
            " <where>  1=1" +
            " <if test=\"id != null\"> and id = #{id}</if> " +
            " <if test=\"subjectName != null\"> and subject_name = #{subjectName}</if> " +
            " </where> " +
            " </script> ")
    public List<Subject> findSubject(Subject subject);
}
