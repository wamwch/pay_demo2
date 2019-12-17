package com.example.pay_demo.mapper;

import com.example.pay_demo.entity.DocterDTO;
import com.example.pay_demo.entity.Registration;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface RegistrationMapper {

    @Insert("insert into registration(openid,name,sex,age,docter_id,docter_subject_id," +
            "is_comment,status,description,phone ,time,order_id) values (#{openid},#{name}," +
            "#{sex},#{age},#{docterId},#{docterSubjectId},#{isComment},#{status},#{description},#{phone},#{time},#{orderId})")
    public int insertRegistration(Registration registration);

    @Update("<script> " +
            "update registration"+
            " <set>" +
            " <if test=\"openid != null\">  openid = #{openid}</if> " +
            " <if test=\"name != null\">  name = #{name}</if> " +
            " <if test=\"sex != null\">  sex = #{sex}</if> " +
            " <if test=\"age != null\">  age = #{age}</if> " +
            " <if test=\"docterId != null\">  docter_id = #{docterId}</if> " +
            " <if test=\"docterSubjectId != null\">  docter_subject_id = #{docterSubjectId}</if> " +
            " <if test=\"isComment != null\">  is_comment = #{isComment}</if> " +
            " <if test=\"status != null\">  status = #{status}</if> " +
            " </set> " +
            " <where>  " +
            " <if test=\"id != null\"> and id = #{id}</if> " +
            " <if test=\"openid != null\"> and openid = #{openid}</if> " +
            " <if test=\"docterId != null\"> and docter_id = #{docterId}</if> " +
            " <if test=\"docterSubjectId != null\"> and docter_subject_id = #{docterSubjectId}</if> " +
            " <if test=\"orderId != null\"> and order_id = #{orderId}</if> " +
            " </where> " +

            " </script> ")
    public boolean updateRegistration(Registration registration);

    @Select("<script> " +
            "SELECT r.id id,r.openid openid,r.name name,r.sex,r.age, r.docter_id docterId,d.docter_name docterName " +
            ",r.docter_subject_id docterSubjectId ,r.is_comment isComment ,r.status status, r.description description, r.phone phone "+
            "FROM registration r left join docter d on d.id=r.docter_id" +
            " <where>  1=1" +
            " <if test=\"id != null\"> and id = #{id}</if> " +
            " <if test=\"openid != null\"> and openid = #{openid}</if> " +
            " <if test=\"name != null\"> and name = #{name}</if> " +
            " <if test=\"sex != null\"> and sex = #{sex}</if> " +
            " <if test=\"age != null\"> and age = #{age}</if> " +
            " <if test=\"docterId != null\"> and docter_id = #{docterId}</if> " +
            " <if test=\"docterSubjectId != null\"> and docter_subject_id = #{docterSubjectId}</if> " +
            " <if test=\"isComment != null\"> and is_comment = #{isComment}</if> " +
            " <if test=\"status != null\"> and status = #{status}</if> " +
            " </where> " +
            " </script> ")
    public List<Registration> findRegistration(Registration registration);

    @Select("<script> " +
            "SELECT count(r.id) amount,docter_name docterName ,d.phone  " +
            "FROM registration r left join docter d on " +
            "d.id=r.docter_id GROUP BY r.docter_id ORDER BY amount DESC LIMIT 3;" +
            " </script> ")
    public List<DocterDTO> findDocterGroupBySubject();


    //查看所有预约用户
    @Select("select * from  registration")
    List<Registration> findAllRegistration();

    //查看不同预约情况的病人
    @Select("select * from registration  where status = #{status}")
    List<Registration> findAllByStatus(String status);



}
