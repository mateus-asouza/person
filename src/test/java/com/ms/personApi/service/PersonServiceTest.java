package com.ms.personApi.service;

import com.ms.personApi.dto.request.PersonDto;
import com.ms.personApi.dto.response.messageResponseDTO;
import com.ms.personApi.entity.Address;
import com.ms.personApi.exception.PersonNotFoundException;
import com.ms.personApi.mapper.PersonMapper;
import com.ms.personApi.entity.Person;
import com.ms.personApi.repository.PersonRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static com.ms.personApi.utils.PersonUtils.createFakeDto;
import static com.ms.personApi.utils.PersonUtils.createFakeEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @InjectMocks
    private PersonService personService;

    PersonDto personDto = createFakeDto();
    Person person = createFakeEntity();


    //teste Unitário metodo personService.createPerson
    @Test
    void testGivenPersonDtoThenReturnSavedMessage() {


        when(personRepository.save(any(Person.class))).thenReturn(person);

        messageResponseDTO expecteSeccessMessage = createExpectedMessageResponse(person.getId(), "Created person with ID ");
        messageResponseDTO successMessage = personService.createPerson(personDto);

        assertEquals(expecteSeccessMessage, successMessage);
    }

    //teste Unitário metodo personService.listAll();
    @Test
    void testGivenPersonDtoThenReturnListOfPeople() {

        List<Person> people = new ArrayList<>();
        when(personRepository.findAll()).thenReturn(people);

        List<PersonDto> successListPeopleDto = personService.listAll();
        List<PersonDto> expectedListDto = new ArrayList<>();
        for (Person person1 : people) {
            PersonDto dto = personMapper.toDTO(person1);
            expectedListDto.add(dto);
        }


        assertEquals(expectedListDto, successListPeopleDto);
    }

    //teste Unitário metodo personService.getPersonById();
    @Test
    void testGivenPersonIdThenReturnPerson() throws PersonNotFoundException {
        when(personRepository.findById(person.getId())).thenReturn(Optional.of(person));

        PersonDto expectedReturnedPersonDto = personDto;
        PersonDto successRetunedPersonDto = personService.getPersonById(person.getId());


        assertEquals(expectedReturnedPersonDto, successRetunedPersonDto);
    }

    //teste Unitário metodo personService.updatePersonById();
    @Test
    void testGivenPersonIdAndPersonThenUpdatePerson() throws PersonNotFoundException {
        when(personRepository.findById(person.getId())).thenReturn(Optional.of(person));
        when(personRepository.save(any(Person.class))).thenReturn(person);
        messageResponseDTO expecteSeccessMessage = createExpectedMessageResponse(person.getId(), "Updated person with id ");
        messageResponseDTO successMessage = personService.updatePersonById(person.getId(), personDto);

        assertEquals(expecteSeccessMessage, successMessage);
    }

    //teste Unitário metodo personService.deletePerson();
    @Test
    void testGivenPersonIdThenDelete() throws PersonNotFoundException {
        when(personRepository.findById(person.getId())).thenReturn(Optional.of(person));

        messageResponseDTO expectedMessage = createExpectedMessageResponse(person.getId(), "Deleted person with id ");
        messageResponseDTO successMessage = personService.deletePerson(person.getId());

        assertEquals(expectedMessage, successMessage);
    }

    //metodo para criar mensagem de resposta.
    private messageResponseDTO createExpectedMessageResponse(Long id, String message) {
        return messageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }

}
