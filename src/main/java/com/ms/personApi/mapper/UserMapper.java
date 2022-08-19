package com.ms.personApi.mapper;


import com.ms.personApi.dto.request.UserDto;
import com.ms.personApi.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    //Gera um mapeador para criar um UserDto a partir de um User, mapeando todos atributos.
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    //Converte UserDto para User
    User toModel(UserDto userDTO);

    //Converte User para UserDto
    UserDto toDTO(User user);


}
