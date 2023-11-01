package com.example.livres.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.livres.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
