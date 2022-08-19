package com.ms.personApi.controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ms.personApi.dto.request.CredentialsDto;
import com.ms.personApi.entity.User;
import com.ms.personApi.exception.InvalidCredentialsException;
import com.ms.personApi.repository.UserRepository;
import com.ms.personApi.service.CredentialsService;
import lombok.AllArgsConstructor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/login")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LoginController {
    private UserRepository userRepository;
    private CredentialsService credentialsService;

    @PostMapping
    void login(@RequestBody CredentialsDto credentials, HttpServletResponse response) throws InvalidCredentialsException {
        Optional<User> maybeUser = userRepository.findByLogin(credentials.getLogin());
        if(maybeUser.isEmpty()){
            throw new InvalidCredentialsException();
        }
        String encryptedPassword = credentialsService.encrypt(credentials.getPassword());
        User user = maybeUser.get();
        if(!user.getPassword().equals(encryptedPassword)){
            throw new InvalidCredentialsException();
        }
        String jwt = JWT.create()
                .withClaim("loggedUserId",user.getId())
                .withClaim("userLogin",user.getLogin())
                .sign(Algorithm.HMAC256("6YYbcd9!@#"));
        Cookie cookie = new Cookie("token",jwt);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60*30);
        response.addCookie(cookie);
    }
}
