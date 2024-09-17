package com.travelmaker.yeoga.service;

import com.travelmaker.yeoga.Exception.ResourceNotFoundException;
import com.travelmaker.yeoga.dto.SignupDTO;
import com.travelmaker.yeoga.model.User;
import com.travelmaker.yeoga.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

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

    public Optional<User> findById(Long userId) {
        return Optional.ofNullable(userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId)));
    }
}
