package com.ms.personApi.repository;

import com.ms.personApi.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//Interface que extende de JpaRepository que possui métodos para persistencia em banco de dados.
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    /*@Query("SELECT c FROM Customer c INNER JOIN  PhysicalCustomer p WHERE c.dataCriacao LIKE %:dataCriacao% \n" +
            "AND p.name LIKE %:name% AND p.cpf LIKE  %:cpf%")*/
    @Query(value = "SELECT * FROM CUSTOMER C INNER JOIN PHYSICAL_CUSTOMER P ON C.PHYSICAL_CUSTOMER_ID = P.CPF" +
            " WHERE C.DATA_CRIACAO LIKE :dataCriacao AND P.PHYSICAL_CUSTOMER_NAME  LIKE :name AND P.CPF LIKE :cpf", nativeQuery = true)
    Page<Customer> searchPhysicalCustomer(
            @Param("dataCriacao") String dataCriacao,
            @Param("name") String name,
            @Param("cpf") String cpf,
            Pageable pageable);

  @Query(value = "SELECT * FROM CUSTOMER C INNER JOIN LEGAL_CUSTOMER L ON C.LEGAL_CUSTOMER_ID = L.CNPJ" +
            " WHERE C.DATA_CRIACAO LIKE :dataCriacao AND L.CORPORATE_NAME LIKE :corporateName AND L.CNPJ LIKE :cnpj", nativeQuery = true)
    Page<Customer> searchLegalCustomer(
          @Param("dataCriacao") String dataCriacao,
          @Param("corporateName") String corporateName,
          @Param("cnpj") String cnpj,
            Pageable pageable);

}
