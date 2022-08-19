package com.ms.personApi.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LegalCustomerDto {

    @NotNull
    private String cnpj;

    @NotNull
    private String corporateName;

    @NotNull
    private String fantasyName;
}
