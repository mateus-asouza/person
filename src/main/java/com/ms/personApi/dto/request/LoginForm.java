package com.ms.personApi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {

    private String login;
    private String password;

    public UsernamePasswordAuthenticationToken toConvert() {
        return new UsernamePasswordAuthenticationToken(login,password);
    }
}
