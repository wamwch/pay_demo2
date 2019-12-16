package com.example.pay_demo.service.impl;

import com.example.pay_demo.entity.DocterSubjectDTO;
import com.example.pay_demo.entity.Subject;
import com.example.pay_demo.mapper.SubjectMapper;
import com.example.pay_demo.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    SubjectMapper subjectMapper;
    @Override
    public List<Subject> findSubject(Subject subject) {
        return subjectMapper.findSubject(subject);
    }

    @Override
    public List<DocterSubjectDTO> findDocterBySubject(Subject subject) {
        return subjectMapper.findDocterBySubject(subject);
    }

    @Override
    public List<DocterSubjectDTO> findDocterBySubjectDocter(DocterSubjectDTO docterSubjectDTO) {
        return subjectMapper.findDocterBySubjectDocter(docterSubjectDTO);
    }
}
