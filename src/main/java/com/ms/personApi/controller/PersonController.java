package com.ms.personApi.controller;

import com.ms.personApi.exception.PersonNotFoundException;
import lombok.AllArgsConstructor;
import com.ms.personApi.dto.request.PersonDto;
import com.ms.personApi.dto.response.messageResponseDTO;
import com.ms.personApi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private PersonService personService;//injeção de dependência interface PersonService.

    /*
       Metodo que recebe requisições post, mapea o jason enviado na requisição em um objeto tipo PersonDto
       e retorna a chamada do metodo creatPerson da interface personService passando o objeto personDto
       como parametro e caso sucesso, retorna o status CREATED do enum HttpsStatus.
    */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public messageResponseDTO createPerson(@RequestBody @Valid PersonDto personDto) {
        return personService.createPerson(personDto);
    }

    //Retorna uma lista com todos os registros.
    @GetMapping
    public List<PersonDto> getPeople() {
        return personService.listAll();
    }

    //Pesquisa um registro pelo id e retorna o registro com o id informado.
    @GetMapping("/{id}")
    public PersonDto getPerson(@PathVariable Long id) throws PersonNotFoundException {
        return personService.getPersonById(id);
    }

    //Atualiza um registro correspondente ao id informado.
    @PutMapping("/{id}")
    public messageResponseDTO updatePersonById(@PathVariable Long id, @RequestBody @Valid PersonDto personDto) throws PersonNotFoundException {
        return personService.updatePersonById(id, personDto);
    }

    //Deleta um registro correspondente ao id informado.
    @DeleteMapping("/{id}")
    public messageResponseDTO deletePerson(@PathVariable Long id) throws PersonNotFoundException {
        return personService.deletePerson(id);
    }

}
