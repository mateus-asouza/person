package com.ms.personApi.controller;

import com.ms.personApi.dto.request.LoginForm;
import com.ms.personApi.dto.request.TokenDto;
import com.ms.personApi.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/auth")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationController {

    private AuthenticationManager authManager;
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid LoginForm form){
        UsernamePasswordAuthenticationToken loginData = form.toConvert();
        try {

        Authentication authentication =  authManager.authenticate(loginData);
        String token = tokenService.generateToken(authentication);

        return ResponseEntity.ok(new TokenDto(token,"Bearer"));
        }catch (AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
