package com.travelmaker.yeoga.controller;

import com.travelmaker.yeoga.model.ACCOUNT;
import com.travelmaker.yeoga.repository.ACCOUNTRepository;
import com.travelmaker.yeoga.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Users")

public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ACCOUNTRepository ACCOUNTRepository;
    @Autowired
    private ACCOUNTRepository repository;

    @GetMapping
    public List<ACCOUNT> getAllUsers() {
        return ACCOUNTRepository.findAll();
    }

    @PostMapping
    public ACCOUNT createUser(@RequestBody ACCOUNT user) {
        return ACCOUNTRepository.save(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ACCOUNT> updateUser(@PathVariable Long id, @RequestBody ACCOUNT userDetails) {
        try {
        Optional<ACCOUNT> userOptional = ACCOUNTRepository.findById(id);
            if (userOptional.isPresent()) {
                ACCOUNT user = userOptional.get();
                user.setUuid(userDetails.getUuid());
                user.setPASSWORD(userDetails.getPASSWORD());
                user.setPHONE(userDetails.getPHONE());
                user.setSEX(userDetails.getSEX());
                user.setNICKNAME(userDetails.getNICKNAME());
                ACCOUNT updatedUser = ACCOUNTRepository.save(user);
                return ResponseEntity.ok(updatedUser);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {return ResponseEntity.status(500).body(null);}
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        ACCOUNTRepository.deleteById(id);
    }

    @GetMapping("/current-time")
    public ResponseEntity<String> getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        String formattedDate = now.atZone(ZoneId.systemDefault()).format(formatter);
        return ResponseEntity.ok(formattedDate);
    }
}
