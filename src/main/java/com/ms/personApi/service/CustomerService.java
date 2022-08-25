package com.ms.personApi.service;

import com.ms.personApi.dto.request.CustomerDto;
import com.ms.personApi.dto.response.messageResponseDTO;
import com.ms.personApi.entity.Customer;
import com.ms.personApi.entity.User;
import com.ms.personApi.exception.PersonNotFoundException;
import com.ms.personApi.mapper.CustomerMapper;
import com.ms.personApi.repository.CustomerRepository;
import com.ms.personApi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerService {

    private CustomerRepository customerRepository;
    private UserRepository userRepository;
    private final CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    //Mapea personDto para Person atraves do metodo toModel do PersonMapper e armazena na variável personToSave,
    //persiste o objeto no banco de dados atraves so metodo personRepository.save passando o onjeto convertido como parametro e retorna uma messagem
    //infrmando que o registro foi criado.
    public ResponseEntity createCustomer(CustomerDto customerDto) {
        Customer customerToSave = customerMapper.toModel(customerDto);
        Optional<User> user = userRepository.findById(customerDto.getUserId());
        if(verifyIfExistsByExample(customerToSave)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("The person already exists.\n");
        }else{
            if(user.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The user not found.\n");
            }
            customerToSave.setUser(user.get());
            customerRepository.save(customerToSave);
            return ResponseEntity.status(HttpStatus.CREATED).body("Person created successfully.\n");
        }
    }

    //Cria uma lista do tipo Person através do metodo findAll do Jpa Repository, obtem uma stream da lista de dados usando o método stream(),
    //Em seguida, o resultado será manipulado pelo método map(),que obterá todas as informações que desejamos, que são convertidos para PersonDto
    //pelo personMapper e o método collect() devolve uma lista de PersonDto e retorna.
    public List<CustomerDto> listAll() {
        List<Customer> customerList = customerRepository.findAll();
        return customerList.stream().
                map(customer -> {
                    CustomerDto customerDto = customerMapper.toDTO(customer);
                    Optional<User> user = Optional.ofNullable(customer.getUser());
                    if(user.isEmpty()){
                        return customerDto;
                    }
                    customerDto.setUserId(customer.getUser().getId());
                    return customerDto;
                }).
                collect(Collectors.toList());

    }

    //Recebe um id, cria um a variável do tipo Person que recebe a chamda do metodo verifyIfExists passando o id,
    //o mesmo pesquisa um registro correspondente ao id passado e atribui o objeto a variavel,
    //apos isso o metodo getPersonById retorna o valor de person convetido para PersonDto.
    public CustomerDto getCustomerById(Long id) throws PersonNotFoundException {
        Customer customer = verifyIfExists(id);
        return customerMapper.toDTO(customer);
    }

    //Verifica se existe um registro correspondente ao id passado, o metodo personMapper converto o personDto passado por paramentro em Person
    //e atribui a variavel personToUpadte, o metodo personRepository.save atualiza o registro no BD e armazena o objeto na varivel updatedPerson,
    //por fim o metodo retorna uma mensagem informando que o registro foi atualizado, caso contrario retorna uma exception.
    public messageResponseDTO updateCustomerById(Long id, CustomerDto customerDto) throws PersonNotFoundException {
        verifyIfExists(id);
        Customer customerToUpdate = customerMapper.toModel(customerDto);
        Customer updatedCustomer = customerRepository.save(customerToUpdate);
        return createMessageResponse(updatedCustomer.getId(), "Updated person with id ");
    }

    //Verifica se existe um registro correspondente ao id passado e atribui o objeto a variavel personToDelete, o metodo
    //personRepository.delete deleta o registro correspondende ao valor da variavel personToDelete e por fim
    //retorna uma mensagem informando que o registro foi deletado, caso contrario retorna uma exception.
    public messageResponseDTO deleteCustomerById(Long id) throws PersonNotFoundException {
        Customer customerToDelete = verifyIfExists(id);
        customerRepository.delete(customerToDelete);
        return createMessageResponse(customerToDelete.getId(), "Deleted person with id ");
    }

    public Page<Customer>serachPhysicalCustomer(String dataCriacao, String name, String cpf, int page, int size){
        PageRequest pageRequest = PageRequest.of(page,size, Sort.Direction.ASC,"CUTOMER_ID");
        dataCriacao = Objects.equals(dataCriacao, "") ? "%":dataCriacao;
        cpf = Objects.equals(cpf, "") ? "%":cpf;
        name = Objects.equals(name, "") ? "%":name;
        return customerRepository.searchPhysicalCustomer(dataCriacao,name,cpf,pageRequest);
    }
    public Page<Customer>serachLegalCustomer(String dataCriacao, String corporateName, String cnpj, int page, int size){
        PageRequest pageRequest = PageRequest.of(page,size, Sort.Direction.ASC,"CUTOMER_ID");
        dataCriacao = Objects.equals(dataCriacao, "") ? "%":dataCriacao;
        cnpj = Objects.equals(cnpj, "") ? "%":cnpj;
        corporateName = Objects.equals(corporateName, "") ? "%":corporateName;

        return customerRepository.searchLegalCustomer(dataCriacao,corporateName,cnpj,pageRequest);
    }

    public Page<Customer> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size,Sort.Direction.ASC,"id");
        return customerRepository.findAll(pageRequest);
    }

    //Pesquisa um registro correspondente ao id passado e retorna uma Person, caso contrario retorna uma exception.
    private Customer verifyIfExists(Long id) throws PersonNotFoundException {
        return customerRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException((id)));
    }

    private Boolean verifyIfExistsByExample(Customer customer){
        Example<Customer> example = Example.of(customer);
        return customerRepository.exists(example);
    }

    //formata uma mensagem response
    private messageResponseDTO createMessageResponse(Long id, String message) {
        return messageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }

    private messageResponseDTO createErrorMessage(String message){
        return messageResponseDTO
                .builder()
                .message(message)
                .build();

    }


}
