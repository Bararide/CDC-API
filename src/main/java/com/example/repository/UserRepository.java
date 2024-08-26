package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.models.User;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsernameAndPasswordAndEmail(String username, String password, String email);
}