package com.example.antrasdarbas.repos;

import com.example.antrasdarbas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRep extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.login = :login AND u.password = :password")
    Optional<? extends User> findByLoginAndPassword(@Param("login") String login, @Param("password") String password);}
