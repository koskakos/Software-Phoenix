package com.software.phoenix.service.interfaces;

import com.software.phoenix.model.Order;
import com.software.phoenix.model.User;
import com.software.phoenix.model.request.SignUpRequest;
import com.software.phoenix.model.request.UpdateUserRequest;
import org.hibernate.NonUniqueObjectException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.NoSuchElementException;

public interface UserService {
    UserDetailsService userDetailsService();
    User getAuthenticatedUser();
    User getUserById(Long id);
    User getUserByLogin(String login);
    User createUser(SignUpRequest request, PasswordEncoder passwordEncoder);
    User updateUser(UpdateUserRequest updateUserRequest);
    List<Order> getAllOrders(User user);
}
