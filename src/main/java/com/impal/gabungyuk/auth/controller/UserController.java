package com.impal.gabungyuk.auth.controller;

import com.impal.gabungyuk.auth.model.request.LoginUserRequest;
import com.impal.gabungyuk.auth.model.request.RegisterUserRequest;
import com.impal.gabungyuk.auth.model.request.UpdateUserRequest;
import com.impal.gabungyuk.auth.model.response.AuthUserResponse;
import com.impal.gabungyuk.core.model.SuccessResponse;
import com.impal.gabungyuk.auth.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;


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
    public SuccessResponse<AuthUserResponse> register(@RequestBody RegisterUserRequest request) {

        AuthUserResponse response = userService.register(request);

        return SuccessResponse.<AuthUserResponse>builder()
                .status(200)
                .message("User registered successfully")
                .data(response)
                .build();
    }

    @PostMapping(
            value = "/api/v1/users/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public SuccessResponse<AuthUserResponse> login(@RequestBody LoginUserRequest request) {
        AuthUserResponse response = userService.login(request);

        return SuccessResponse.<AuthUserResponse>builder()
                .status(200)
                .message("Login successful")
                .data(response)
                .build();
    }

    @GetMapping(
            value = "/api/v1/users/current",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public SuccessResponse<AuthUserResponse> getCurrentUser(@RequestHeader("Authorization") String authorizationHeader) {
        AuthUserResponse response = userService.getCurrentUser(authorizationHeader);

        return SuccessResponse.<AuthUserResponse>builder()
                .status(200)
                .message("Get user successful")
                .data(response)
                .build();
    }

    @PatchMapping(
            value = "/api/v1/update/users/current",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public SuccessResponse<AuthUserResponse> updateUser(
        @RequestHeader("Authorization") String authorizationHeader,
        @RequestParam(value = "namaLengkap", required = false) String namaLengkap,
        @RequestParam(value = "email", required = false) String email,
        @RequestParam(value = "password", required = false) String password,
        @RequestParam(value = "institusi", required = false) String institusi,
        @RequestParam(value = "bio", required = false) String bio,
        @RequestParam(value = "keahlian", required = false) String keahlian,
        @RequestParam(value = "lokasi", required = false) String lokasi,
        @RequestParam(value = "whatsapp", required = false) String whatsapp,
        @RequestParam(value = "instagram", required = false) String instagram,
        @RequestParam(value = "facebook", required = false) String facebook,
        @RequestParam(value = "linkedin", required = false) String linkedin,

        @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture
    ){
        UpdateUserRequest request = new UpdateUserRequest();
        request.setNamaLengkap(namaLengkap);
        request.setEmail(email);
        request.setPassword(password);
        request.setInstitusi(institusi);
        request.setBio(bio);
        request.setKeahlian(Arrays.asList(keahlian.split(",")));
        request.setLokasi(lokasi);
        request.setWhatsapp(whatsapp);
        request.setInstagram(instagram);
        request.setFacebook(facebook);
        request.setLinkedin(linkedin);


        AuthUserResponse response = userService.updateCurrentUser(
            authorizationHeader,
            request,
            profilePicture
    );

        return SuccessResponse.<AuthUserResponse>builder()
                .status(200)
                .message("Update user successful")
                .data(response)
                .build();
    } 


    @DeleteMapping(
            value = "/api/v1/delete/users/current",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public SuccessResponse<Object> deleteCurrentUser(@RequestHeader("Authorization") String authorizationHeader) {
        userService.deleteCurrentUser(authorizationHeader);

        return SuccessResponse.builder()
                .status(200)
                .message("Delete user successful")
                .data(java.util.Map.of("message", "User deleted successfully"))
                .build();
    }
}
