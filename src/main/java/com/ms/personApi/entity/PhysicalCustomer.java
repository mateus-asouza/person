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
@Table(name = "PHYSICAL_CUSTOMER")
public class PhysicalCustomer {

    @Id
    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false, name = "PHYSICAL_CUSTOMER_NAME")
    private String name;


    @Column(nullable = false)
    private String birthDay;

    @JsonIgnore
    @OneToOne(mappedBy = "physicalCustomer")
    private Customer customer;
}
