package com.ms.personApi.service;

import com.ms.personApi.dto.request.CustomerDto;
import com.ms.personApi.dto.request.UserDto;
import com.ms.personApi.dto.response.messageResponseDTO;
import com.ms.personApi.entity.Customer;
import com.ms.personApi.entity.User;
import com.ms.personApi.exception.PersonNotFoundException;
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

    public messageResponseDTO updateUserById(Long id, UserDto userDto) throws PersonNotFoundException {
        verifyIfExists(id);
        User userToUpdate = userMapper.toModel(userDto);
        User updatedUser = userRepository.save(userToUpdate);
        return messageResponseDTO
                .builder()
                .message("Usuário " + updatedUser.getUsername()+" atualizado com sucesso!")
                .build();
    }

    public UserDto findById(Long id){
        Optional<User> user = userRepository.findById(id);
        return userMapper.toDTO(user.orElseGet(User::new));
    }

    public List<UserDto> getAllUser(){
        return userRepository.findAll().stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    //Pesquisa um registro correspondente ao id passado e retorna uma Person, caso contrario retorna uma exception.
    private User verifyIfExists(Long id) throws PersonNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException((id)));
    }

}
