package com.example.pay_demo.mapper;

import com.example.pay_demo.entity.Docter;
import org.apache.ibatis.annotations.Mapper;
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
}
