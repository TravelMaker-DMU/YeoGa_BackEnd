package com.travelmaker.yeoga.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.travelmaker.yeoga.model.ACCOUNT;
import com.travelmaker.yeoga.repository.ACCOUNTRepository;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private ACCOUNTRepository repository;

    public List<ACCOUNT> findAll() {
        return repository.findAll();
    }

    public ACCOUNT findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public ACCOUNT save(ACCOUNT member) {
        return repository.save(member);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
