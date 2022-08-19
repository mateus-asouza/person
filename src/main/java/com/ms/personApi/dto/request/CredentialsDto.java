package com.ms.personApi.dto.request;

import com.ms.personApi.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsDto {

    @NotEmpty
    private String login;

    @NotEmpty
    private String password;

}
