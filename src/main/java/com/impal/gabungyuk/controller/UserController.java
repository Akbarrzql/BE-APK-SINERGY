package com.impal.gabungyuk.controller;

import com.impal.gabungyuk.entity.User;
import com.impal.gabungyuk.model.RegisterUserRequest;
import com.impal.gabungyuk.model.SuccessResponse;
import com.impal.gabungyuk.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(
            value = "/api/v1/users/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public SuccessResponse<Object> register(@RequestBody RegisterUserRequest request) {

        User user = userService.register(request);

        return SuccessResponse.builder()
                .status(200)
                .message("User registered successfully")
                .data(Map.of(
                        "userId", user.getIdPengguna(),
                        "username", user.getUsername(),
                        "token", user.getToken()
                ))
                .build();
    }
}