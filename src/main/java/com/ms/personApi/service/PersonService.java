package com.ms.personApi.service;

import com.ms.personApi.dto.request.PersonDto;
import com.ms.personApi.dto.response.MessageResponseDTO;
import com.ms.personApi.entity.Person;
import com.ms.personApi.mapper.PersonMapper;
import com.ms.personApi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    //Metodo que mapea personDto para Person atraves do metodo toModel do PersonMapper e armazena na variável personToSave,
    //persiste o objeto no banco de dados atraves so metodo personRepository.save passando o onjeto convertido como parametro e retorna uma messagem,
    //utilizando o metodo createMessageResponse.
    public MessageResponseDTO createPerson(PersonDto personDto) {
        Person personToSave = personMapper.toModel(personDto);
        Person savedPerson = personRepository.save(personToSave);
        return createMessageResponse(savedPerson.getId(), "Created person with ID ");
    }

    //formata uma mensagem response
    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }

}
