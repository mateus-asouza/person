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
    @Query(value = "SELECT * FROM CUSTOMER c INNER JOIN PHYSICAL_CUSTOMER p ON c.PHYSICAL_CUSTOMER_ID = p.CPF", nativeQuery = true)
    Page<Customer> searchPhysicalCustomer(
            @Param("dataCriacao") String dataCriacao,
            @Param("name") String name,
            @Param("cpf") String cpf,
            Pageable pageable);
    @Query("SELECT c FROM Customer c INNER JOIN  c.legalCustomer p  WHERE c.dataCriacao  LIKE %:dataCriacao% \n" +
            "AND p.corporateName    LIKE %:name% AND p.cnpj  LIKE  %:cpf%")
    Page<Customer> searchLegalCustomer(
            @Param("dataCriacao") String dataCriacao,
            @Param("name") String name,
            @Param("cpf") String cpf,
            Pageable pageable);

}
