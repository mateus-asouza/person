package com.ms.personApi.service;

import com.ms.personApi.dto.request.UserDto;
import com.ms.personApi.entity.User;
import com.ms.personApi.mapper.UserMapper;
import com.ms.personApi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private UserRepository userRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    public ResponseEntity createUser (UserDto userDto){
        User userToSave = userMapper.toModel(userDto);
        try {
        userRepository.save(userToSave);
        }catch (Exception e){
            ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("User saved successfully.");
    }

    public UserDto findById(Long id){
        Optional<User> user = userRepository.findById(id);
        return userMapper.toDTO(user.orElseGet(User::new));
    }

    public List<UserDto> getAllUser(){
        return userRepository.findAll().stream().map(userMapper::toDTO).collect(Collectors.toList());
    }
}
