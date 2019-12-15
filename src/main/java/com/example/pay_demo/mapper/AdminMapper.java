package com.example.pay_demo.mapper;

import com.example.pay_demo.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {

    @Select("select * from admin where acount=#{acount}")
    Admin findUserByAcount(String acount);

}
