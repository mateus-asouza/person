package com.ms.personApi.controller;


import com.ms.personApi.dto.request.UserDto;
import com.ms.personApi.dto.response.messageResponseDTO;
import com.ms.personApi.exception.PersonNotFoundException;
import com.ms.personApi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/user")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private UserService userService;
    @PostMapping
    public ResponseEntity createUser(@RequestBody @Valid UserDto userDto) {
        System.out.println(userDto.toString());
        userDto.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        return userService.createUser(userDto);
    }

    @PutMapping("/{id}")
    public messageResponseDTO updateUser(@PathVariable Long id , @RequestBody @Valid UserDto userDto) throws PersonNotFoundException {

        userDto.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        return userService.updateUserById(id,userDto);
    }

    @GetMapping
    public List<UserDto> getAllUser(){
        return userService.getAllUser();
    }
}