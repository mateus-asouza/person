package com.ms.personApi.service;

import com.ms.personApi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CredentialsService {

    private UserRepository userRepository;

    public String encrypt(String senha){
        return DigestUtils.sha1Hex(senha);
    }

}
