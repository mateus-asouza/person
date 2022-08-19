package com.ms.personApi.mapper;

import com.ms.personApi.dto.request.CustomerDto;
import com.ms.personApi.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CustomerMapper {
    //Gera um mapeador para criar um PersonDto a partir de um Person, mapeando todos atributos.
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    //Converte PersonDto para Person
    Customer toModel(CustomerDto customerDTO);

    //Converte Person para PersonDto
    CustomerDto toDTO(Customer customer);


}
