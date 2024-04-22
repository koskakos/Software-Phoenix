package com.software.phoenix.service.interfaces;

import com.software.phoenix.model.User;
import com.software.phoenix.model.request.SignUpRequest;
import org.hibernate.NonUniqueObjectException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.NoSuchElementException;

public interface UserService {
    UserDetailsService userDetailsService();
    User getAuthenticatedUser();
    User saveUser(User user);
    User getUserById(Long id);
    User getUserByLogin(String login);
    User createUser(SignUpRequest request, PasswordEncoder passwordEncoder);
}
