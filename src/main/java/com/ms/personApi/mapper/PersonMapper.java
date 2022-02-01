package com.ms.personApi.mapper;

import com.ms.personApi.dto.request.PersonDto;
import com.ms.personApi.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface PersonMapper {
    //Gera um mapeador para criar um PersonDto a partir de um Person, mapeando todos atributos.
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    Person toModel(PersonDto personDTO);

    PersonDto toDTO(Person person);


}
