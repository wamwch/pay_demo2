package com.example.pay_demo.service;


import com.example.pay_demo.entity.Registration;

import java.util.List;

public interface RegistrationService {

    public int insertRegistration(Registration registration);

    public boolean updateRegistration(Registration registration);

    public List<Registration> findRegistration(Registration registration);

}
