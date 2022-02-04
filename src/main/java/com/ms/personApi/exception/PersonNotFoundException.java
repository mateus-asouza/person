package com.ms.personApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Retorna uma mensagem exception personalizada e o status http NOT_FOUD.
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends Exception {
    public PersonNotFoundException(Long id) {
        super("Person not found with ID " + id);
    }
}
