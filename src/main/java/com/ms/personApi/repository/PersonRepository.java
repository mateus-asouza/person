package com.ms.personApi.repository;

import com.ms.personApi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Interface que extende de JpaRepository que possui métodos para persistencia em banco de dados.
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
