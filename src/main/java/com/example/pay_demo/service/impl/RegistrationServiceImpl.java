package com.example.pay_demo.service.impl;

import com.example.pay_demo.entity.Registration;
import com.example.pay_demo.mapper.RegistrationMapper;
import com.example.pay_demo.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    RegistrationMapper registrationMapper;
    @Override
    public int insertRegistration(Registration registration) {
        return registrationMapper.insertRegistration(registration);
    }

    @Override
    public boolean updateRegistration(Registration registration) {
        return registrationMapper.updateRegistration(registration);
    }

    @Override
    public List<Registration> findRegistration(Registration registration) {
        return registrationMapper.findRegistration(registration);
    }

    @Override
    public List<Registration> findAllRegistration() {
        return registrationMapper.findAllRegistration();
    }

    @Override
    public List<Registration> findAllByStatus(String status) {
        return registrationMapper.findAllByStatus(status);
    }
}
