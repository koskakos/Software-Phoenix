package com.software.phoenix.repository;

import com.software.phoenix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByLogin(String login);

    boolean existsByLogin(String login);
}

