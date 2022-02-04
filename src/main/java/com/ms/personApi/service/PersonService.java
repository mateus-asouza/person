package com.ms.personApi.service;

import com.ms.personApi.dto.request.PersonDto;
import com.ms.personApi.dto.response.MessageResponseDTO;
import com.ms.personApi.entity.Person;
import com.ms.personApi.exception.PersonNotFoundException;
import com.ms.personApi.mapper.PersonMapper;
import com.ms.personApi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    //Mapea personDto para Person atraves do metodo toModel do PersonMapper e armazena na variável personToSave,
    //persiste o objeto no banco de dados atraves so metodo personRepository.save passando o onjeto convertido como parametro e retorna uma messagem
    //infrmando que o registro foi criado.
    public MessageResponseDTO createPerson(PersonDto personDto) {
        Person personToSave = personMapper.toModel(personDto);
        Person savedPerson = personRepository.save(personToSave);
        return createMessageResponse(savedPerson.getId(), "Created person with ID ");
    }

    //Cria uma lista do tipo Person através do metodo findAll do Jpa Repository, obtem uma stream da lista de dados usando o método stream(),
    //Em seguida, o resultado será manipulado pelo método map(),que obterá todas as informações que desejamos, que são convertidos para PersonDto
    //pelo personMapper e o método collect() devolve uma lista de PersonDto e retorna.
    public List<PersonDto> listAll() {
        List<Person> personList = personRepository.findAll();
        return personList.stream().
                map(personMapper::toDTO).
                collect(Collectors.toList());
    }

    //Recebe um id, cria um a variável do tipo Person que recebe a chamda do metodo verifyIfExists passando o id,
    //o mesmo pesquisa um registro correspondente ao id passado e atribui o objeto a variavel,
    //apos isso o metodo getPersonById retorna o valor de person convetido para PersonDto.
    public PersonDto getPersonById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);
        return personMapper.toDTO(person);
    }

    //Verifica se existe um registro correspondente ao id passado, o metodo personMapper converto o personDto passado por paramentro em Person
    //e atribui a variavel personToUpadte, o metodo personRepository.save atualiza o registro no BD e armazena o objeto na varivel updatedPerson,
    //por fim o metodo retorna uma mensagem informando que o registro foi atualizado, caso contrario retorna uma exception.
    public MessageResponseDTO updatePersonById(Long id, PersonDto personDto) throws PersonNotFoundException {
        verifyIfExists(id);
        Person personToUpdate = personMapper.toModel(personDto);
        Person updatedPerson = personRepository.save(personToUpdate);
        return createMessageResponse(updatedPerson.getId(), "Updated person with id ");
    }

    //Verifica se existe um registro correspondente ao id passado e atribui o objeto a variavel personToDelete, o metodo
    //personRepository.delete deleta o registro correspondende ao valor da variavel personToDelete e por fim
    //retorna uma mensagem informando que o registro foi deletado, caso contrario retorna uma exception.
    public MessageResponseDTO deletePerson(Long id) throws PersonNotFoundException {
        Person personToDelete = verifyIfExists(id);
        personRepository.delete(personToDelete);
        return createMessageResponse(personToDelete.getId(), "Deleted person with id ");
    }

    //Pesquisa um registro correspondente ao id passado e retorna uma Person, caso contrario retorna uma exception.
    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException((id)));
    }

    //formata uma mensagem response
    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }


}
