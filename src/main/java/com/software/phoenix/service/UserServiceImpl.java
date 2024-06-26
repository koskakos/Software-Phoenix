package com.software.phoenix.service;

import com.software.phoenix.model.Order;
import com.software.phoenix.model.User;
import com.software.phoenix.model.request.SignUpRequest;
import com.software.phoenix.model.request.UpdateUserRequest;
import com.software.phoenix.repository.UserRepository;
import com.software.phoenix.service.interfaces.UserService;
import org.hibernate.NonUniqueObjectException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetailsService userDetailsService() {
        return username -> userRepository.findUserByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public User getAuthenticatedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public User createUser(SignUpRequest request, PasswordEncoder passwordEncoder) {
        if(userRepository.existsByLogin(request.getLogin()))
            throw new NonUniqueObjectException("", null, request.getLogin());
        User user = User.builder()
                .login(request.getLogin())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullname(request.getFullname().trim())
                .build();
        if (request.getAvatarUrl() != null) {
            user.setAvatarUrl(request.getAvatarUrl().trim());
        }
        return userRepository.save(user);
    }

    public User updateUser(UpdateUserRequest request) {
        User user = getAuthenticatedUser();

        if (request.getFullname() != null) {
            user.setFullname(request.getFullname().trim());
        }
        if (request.getAvatarUrl() != null) {
            user.setAvatarUrl(request.getAvatarUrl().trim());
        }

        return userRepository.save(user);
    }

    public List<Order> getAllOrders(User user) {
        return user.getOrders();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()
                -> new NoSuchElementException(String.format("User with id '%d' not found", id)));
    }

    public User getUserByLogin(String login) {
        return userRepository.findUserByLogin(login).orElseThrow(()
                -> new NoSuchElementException(String.format("User with login '%d' not found", login)));
    }
}
