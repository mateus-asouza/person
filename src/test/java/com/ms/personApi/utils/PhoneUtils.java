package com.ms.personApi.utils;

import com.ms.personApi.dto.request.PhoneDto;
import com.ms.personApi.entity.Phone;


public class PhoneUtils {

    private static final String PHONE_NUMBER = "(62)982367922";
    private static final long PHONE_ID = 1L;

    public static PhoneDto createFakeDTO() {
        return PhoneDto.builder()
                .id(PHONE_ID)
                .phoneNumber(PHONE_NUMBER)
                .build();
    }

    public static Phone createFakeEntity() {
        return Phone.builder()
                .id(PHONE_ID)
                .phoneNumber(PHONE_NUMBER)
                .build();
    }
}
