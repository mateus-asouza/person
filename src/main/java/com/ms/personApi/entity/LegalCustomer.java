package com.ms.personApi.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;


import javax.persistence.*;

@Entity
@DynamicUpdate
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "LEGAL_CUSTOMER")
public class LegalCustomer {
    @Id
    @Column(name = "CNPJ")
    private String cnpj;

    @Column(nullable = false,name = "CORPORATE_NAME")
    private String corporateName;

    @Column(nullable = false,name = "FANTASY_NAME")
    private String fantasyName;

    @JsonIgnore
    @OneToOne(mappedBy = "legalCustomer")
    private Customer customer;
}
