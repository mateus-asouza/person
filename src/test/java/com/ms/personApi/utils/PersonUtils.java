package com.ms.personApi.utils;


import com.ms.personApi.dto.request.PersonDto;
import com.ms.personApi.entity.Person;

import java.util.Collections;

public class PersonUtils {

    private static final Long PERSON_ID = 1L;
    private static final String COMPLETE_NAME = "Mateus Alves";
    private static final String CPF_NUMBER = "643.862.870-00";
    private static final String BIRTHDAY_DATE = "14/04/1995";

    public static PersonDto createFakeDto() {
        return PersonDto.builder()
                .id(PERSON_ID)
                .name(COMPLETE_NAME)
                .cpf(CPF_NUMBER)
                .birthDay(BIRTHDAY_DATE)
                .phones(Collections.singletonList(PhoneUtils.createFakeDTO()))
                .addresses(Collections.singletonList(AddressUtils.createFakeAddressDto()))
                .build();
    }

    public static Person createFakeEntity() {
        return Person.builder()
                .id(PERSON_ID)
                .name(COMPLETE_NAME)
                .cpf(CPF_NUMBER)
                .birthDay(BIRTHDAY_DATE)
                .addresses(Collections.singletonList(AddressUtils.createFakeAddress()))
                .phones(Collections.singletonList(PhoneUtils.createFakeEntity()))
                .build();
    }
}
