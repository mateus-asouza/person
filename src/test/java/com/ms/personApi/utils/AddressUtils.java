package com.ms.personApi.utils;

import com.ms.personApi.dto.request.AddressDto;
import com.ms.personApi.entity.Address;

public class AddressUtils {
    private static final Long ADDRESS_ID = 1L;
    private static final String CEP_NUMBER = "74375500";
    private static final String STREET_NAME = "Rua das Camélias";
    private static final int ADDRESS_NUMBER = 475;
    private static final String DISTRICT_NAME = "Parque Oeste";
    private static final String CITY_NAME = "Goiânia";
    private static final String STATE_NAME = "GO";

    public static AddressDto createFakeAddressDto() {
        return AddressDto.builder()
                .id(ADDRESS_ID)
                .cep(CEP_NUMBER)
                .street(STREET_NAME)
                .number(ADDRESS_NUMBER)
                .district(DISTRICT_NAME)
                .city(CITY_NAME)
                .state(STATE_NAME)
                .build();
    }

    public static Address createFakeAddress() {
        return Address.builder()
                .id(ADDRESS_ID)
                .cep(CEP_NUMBER)
                .street(STREET_NAME)
                .number(ADDRESS_NUMBER)
                .district(DISTRICT_NAME)
                .city(CITY_NAME)
                .state(STATE_NAME)
                .build();

    }


}
