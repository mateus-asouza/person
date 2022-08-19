package com.ms.personApi.controller;

import com.ms.personApi.dto.request.CustomerDto;
import com.ms.personApi.entity.Customer;
import com.ms.personApi.exception.PersonNotFoundException;
import com.ms.personApi.service.UserService;
import lombok.AllArgsConstructor;
import com.ms.personApi.dto.response.messageResponseDTO;
import com.ms.personApi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/customer")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerController {

    private CustomerService customerService;//injeção de dependência interface PersonService.
    private UserService userService;

    /*
       Metodo que recebe requisições post, mapea o jason enviado na requisição em um objeto tipo PersonDto
       e retorna a chamada do metodo creatPerson da interface personService passando o objeto personDto
       como parametro e caso sucesso, retorna o status CREATED do enum HttpsStatus.
    */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createPerson(@RequestBody CustomerDto customerDto) {

        return customerService.createPerson(customerDto);
    }

    @GetMapping(value = "/search")
    public Page<Customer> search(@RequestParam(required = false) String dataCriacao,
                                 @RequestParam(required = false) String nomeOrRazaoSocial,
                                 @RequestParam(required = false) String cpfOrCnpj,
                                 @RequestParam boolean isCnpj,
                                 @RequestParam(value = "page",
                                         required = false,
                                         defaultValue = "0") int page,
                                 @RequestParam(value = "size",
                                         required = false,
                                         defaultValue = "10") int size) {
        if(isCnpj){
         return customerService.serachLegalCustomer(dataCriacao,nomeOrRazaoSocial,cpfOrCnpj,page,size);
        }
        return customerService.serachPhysicalCustomer(dataCriacao,nomeOrRazaoSocial,cpfOrCnpj,page,size);

    }

    //Retorna uma lista com todos os registros.
    @GetMapping
    public List<CustomerDto> getPeople() {
        return customerService.listAll();
    }

    //Pesquisa um registro pelo id e retorna o registro com o id informado.
    @GetMapping("/{id}")
    public CustomerDto getPerson(@PathVariable Long id) throws PersonNotFoundException {
        return customerService.getPersonById(id);
    }

    //Atualiza um registro correspondente ao id informado.
    @PutMapping("/{id}")
    public messageResponseDTO updatePersonById(@PathVariable Long id, @RequestBody @Valid CustomerDto customerDto) throws PersonNotFoundException {
        return customerService.updatePersonById(id, customerDto);
    }

    //Deleta um registro correspondente ao id informado.
    @DeleteMapping("/{id}")
    public messageResponseDTO deletePerson(@PathVariable Long id) throws PersonNotFoundException {
        return customerService.deletePerson(id);
    }

}
