package com.ms.personApi.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhysicalCustomerDto {

    @NotNull
   @CPF
    private String cpf;

    @NotNull
    private String name;



    private String birthDay;
}
