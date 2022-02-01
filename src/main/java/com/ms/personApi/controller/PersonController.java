package com.ms.personApi.controller;

import lombok.AllArgsConstructor;
import com.ms.personApi.dto.request.PersonDto;
import com.ms.personApi.dto.response.MessageResponseDTO;
import com.ms.personApi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController //retorna o objeto e os dados do objeto são gravados diretamente na resposta HTTP como JSON.
@RequestMapping("/api/v1/people")//mapeia um caminho de requisições.
@AllArgsConstructor(onConstructor = @__(@Autowired))//Gera um construtor all-args e injeta a anotação @Autowired no construtor.
public class PersonController {

    private PersonService personService;//injeção de dependência interface PersonService.

    /*
       Metodo que recebe requisições post, mapea o jason enviado na requisição em um objeto tipo PersonDto
       e retorna a chamada do metodo creatPerson da interface personService passando o objeto personDto
       como parametro e caso sucesso, retorna o status CREATED do enum HttpsStatus.
    */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDto personDto) {
        return personService.createPerson(personDto);
    }
}
