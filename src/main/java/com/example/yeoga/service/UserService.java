package com.example.yeoga.service;

import com.example.yeoga.dto.UserDTO;
import com.example.yeoga.entity.UserEntity;
import com.example.yeoga.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO getUserByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);

        // 엔티티를 DTO로 변환
        if (userEntity != null) {
            UserDTO userDto = new UserDTO();
            userDto.setId(userEntity.getId());
            userDto.setUsername(userEntity.getUsername());
            userDto.setRole(userEntity.getRole());
            userDto.setEmail(userEntity.getEmail());
            userDto.setBirthday(userEntity.getBirthday());
            userDto.setTel(userEntity.getTel());

            // 필요한 경우 각 리스트를 변환하여 설정
            return userDto;
        }
        return null; // 또는 예외 처리
    }
    @Transactional
    public void deleteUserByUsername(String username) {
        // 사용자 존재 여부 확인
        if (!userRepository.existsByUsername(username)) {
            throw new UsernameNotFoundException("User not found");
        }
        // 사용자 삭제
        userRepository.deleteByUsername(username);
    }
    public UserEntity updateUser(UserEntity user) {
        Optional<UserEntity> existingUser = userRepository.findById(user.getId());
        if (existingUser.isPresent()) {
            UserEntity userToUpdate = existingUser.get();
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setTel(user.getTel());
            userToUpdate.setBirthday(user.getBirthday());
            // 필요한 다른 필드들도 업데이트
            return userRepository.save(userToUpdate);
        } else {
            throw new RuntimeException("User not found");
        }
    }
    public String findUsernameByEmail(String email) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            return userOptional.get().getUsername();
        } else {
            throw new RuntimeException("User not found");
        }
    }

}