package com.ay.wewin.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ay.wewin.api.model.User;

public interface IUserRepository extends JpaRepository<User, String>{
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
