package com.ms.personApi.repository;

import com.ms.personApi.entity.Customer;
import com.ms.personApi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value="SELECT u FROM User u WHERE u.login LIKE %?1%")
    public Optional<User> findByLogin(String login);
}
