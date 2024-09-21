package com.example.yeoga.service;

import com.example.yeoga.dto.UserDTO;
import com.example.yeoga.entity.UserEntity;
import com.example.yeoga.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            // 필요한 경우 각 리스트를 변환하여 설정
            return userDto;
        }
        return null; // 또는 예외 처리
    }
}