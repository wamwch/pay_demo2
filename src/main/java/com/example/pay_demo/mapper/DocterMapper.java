package com.example.pay_demo.mapper;

import com.example.pay_demo.entity.Docter;
import com.example.pay_demo.entity.DocterSubjectDTO;
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

    @Select("<script> " +
            "SELECT d.id docterId, d.phone phone , d.docter_name docterName , " +
            "ds.id id, ds.subject_id  subjectId,s.subject_name subjectName " +
            "FROM docter d " +
            "left join docter_subject ds on ds.docter_id=d.id " +
            "left join subject s on ds.subject_id=s.id " +
            " <where>  1=1" +
            " <if test=\"docterName != null\"> and docter_name = #{docterName}</if> " +
            " <if test=\"subjectId != null\"> and ds.subject_id = #{subjectId}</if> " +
            " <if test=\"docterId != null\"> and ds.docter_id = #{docterId}</if> " +
            " </where> " +
            " </script> ")
    List<DocterSubjectDTO> findDocterAndSubject(DocterSubjectDTO docterSubjectDTO);

    @Select("select a.id id,a.docter_name name,a.phone phone,a.iswork iswork,c.subject_name subject from  docter a ,docter_subject b,subject c where a.id=b.docter_id and b.id= c.id")
    List<DoctorInfoVO> findAlldoctors();
}
