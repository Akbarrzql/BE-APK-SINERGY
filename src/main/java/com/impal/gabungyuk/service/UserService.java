package com.impal.gabungyuk.service;

import com.impal.gabungyuk.entity.User;
import com.impal.gabungyuk.model.RegisterUserRequest;
import com.impal.gabungyuk.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User register(RegisterUserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        String token = UUID.randomUUID().toString();

        User user = User.builder()

                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .token(token)
                .tokenExpiredAt(System.currentTimeMillis() + (1000L * 60 * 60 * 24 * 7))
                .build();


        return userRepository.save(user);
    }
}