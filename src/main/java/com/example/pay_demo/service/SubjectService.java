package com.example.pay_demo.service;

import com.example.pay_demo.entity.DocterSubjectDTO;
import com.example.pay_demo.entity.Subject;

import java.util.List;

public interface SubjectService {
    public List<Subject> findSubject(Subject subject);

    public List<DocterSubjectDTO> findDocterBySubject(Subject subject);
}
