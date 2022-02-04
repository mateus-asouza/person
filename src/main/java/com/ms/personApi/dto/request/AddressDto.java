package com.ms.personApi.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AddressDto {
    private Long id;

    @NotEmpty
    @Size(min = 8, max = 10)
    private String cep;

    @NotEmpty
    private String street;

    @NotNull
    private int number;

    @NotEmpty
    private String district;

    @NotEmpty
    private String city;

    @NotEmpty
    private String state;


}