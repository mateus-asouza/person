package com.ms.personApi.dto.request;


import com.ms.personApi.entity.Address;
import com.ms.personApi.entity.LegalCustomer;
import com.ms.personApi.entity.Phone;
import com.ms.personApi.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



import javax.validation.constraints.NotEmpty;


import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private Long id;

    private PhoneDto phones;


    private AddressDto addresses;

    private String dataCriacao;

    private String dataAlteracao;

    private PhysicalCustomerDto physicalCustomer;


    private LegalCustomerDto legalCustomer;


    @NotEmpty
    private Long userId;


}
