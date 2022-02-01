package com.ms.personApi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDto {

    private Long id;

    @NotEmpty
    @Size(min = 13, max = 14)
    private String phoneNumber;

}
