package com.ms.personApi.config.security;

import com.ms.personApi.entity.User;
import com.ms.personApi.repository.UserRepository;
import com.ms.personApi.service.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


public class AuthenticationViaTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserRepository userRepository;
    public AuthenticationViaTokenFilter(TokenService tokenService,UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = toRecoverToken(httpServletRequest);
        boolean valid = tokenService.isTokenValid(token);
        if(valid){
            authenticateClient(token);
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private void authenticateClient(String token) {
        Long idUser = tokenService.getIdUser(token);
        User user = userRepository.findById(idUser).get();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private String toRecoverToken(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")){
            return null;
        }else{
            return token.substring(7,token.length());
        }

    }
}
