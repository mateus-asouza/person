package com.ms.personApi.repository;

import com.ms.personApi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
//Interface que extende de JpaRepository que possui métodos para persistencia em banco de dados.
public interface PersonRepository extends JpaRepository<Person, Long> {

}
