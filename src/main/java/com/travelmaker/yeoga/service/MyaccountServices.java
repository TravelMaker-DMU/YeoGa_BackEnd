package com.travelmaker.yeoga.service;

import com.travelmaker.yeoga.model.ACCOUNT;
import com.travelmaker.yeoga.repository.ACCOUNTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyaccountServices {

    @Autowired
    private ACCOUNTRepository accountRepository;
}
