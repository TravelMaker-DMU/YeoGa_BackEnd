package com.travelmaker.yeoga.user.service;

import com.travelmaker.yeoga.user.dto.SignupDTO;
import com.travelmaker.yeoga.user.model.User;
import com.travelmaker.yeoga.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<?> registerUser(SignupDTO signupDTO) {
        if (userRepository.existsByEmail(signupDTO.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }

        User user = new User(
                signupDTO.getUsername(),
                signupDTO.getEmail(),
                Collections.singletonList(passwordEncoder.encode(signupDTO.getPassword()))
        );

        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
