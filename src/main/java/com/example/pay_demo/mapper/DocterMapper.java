package com.example.pay_demo.mapper;

import com.example.pay_demo.entity.Docter;
import com.example.pay_demo.entity.VO.DoctorInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DocterMapper {
    @Select("<script> " +
            "SELECT *" +
            "FROM docter " +
            " <where>  1=1" +
            " <if test=\"docterName != null\"> and docter_name = #{docterName}</if> " +
            " </where> " +
            " </script> ")
    List<Docter> findDocter(String docterName);

    @Select("select a.id id,a.docter_name name,a.phone phone,a.iswork iswork,c.subjectName subject from  docter a ,docter_subject b,subject c where a.id=b.doctor_id and b.id= c.id")
//    @Results({
//            @Result(column = "a.id" ,property = "id"),
//            @Result(column = "a.docter_name",property = "name"),
//            @Result(column = "a.phone",property = "phone"),
//            @Result(column = "a.iswork",property = "iswork"),
//            @Result(column = "c.subjectName",property = "subject")
//    })
    List<DoctorInfoVO> findAlldoctors();
}
