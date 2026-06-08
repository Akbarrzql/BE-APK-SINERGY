package com.impal.gabungyuk.auth.controller;

import com.impal.gabungyuk.auth.model.request.UpdateUserRequest;
import com.impal.gabungyuk.auth.model.response.AuthUserResponse;
import com.impal.gabungyuk.auth.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void updateUserMultipartShouldReturnSuccess() throws Exception {
        AuthUserResponse response = AuthUserResponse.builder()
                .userId(1)
                .namaLengkap("Nama Baru")
                .email("baru@example.com")
                .profilePicture("http://localhost:8080/uploads/profile/pic.jpg")
                .keahlian(List.of("Java", "Spring"))
                .build();

        when(userService.updateCurrentUser(
                eq("Bearer token"),
                any(UpdateUserRequest.class),
                any(HttpServletRequest.class),
                any(MultipartFile.class)
        )).thenReturn(response);

        MockMultipartFile profilePictureFile = new MockMultipartFile(
                "profilePictureFile",
                "profile.jpg",
                "image/jpeg",
                "image-data".getBytes()
        );

        mockMvc.perform(multipart("/api/v1/update/users/current")
                        .file(profilePictureFile)
                        .param("namaLengkap", "Nama Baru")
                        .param("email", "baru@example.com")
                        .param("keahlian", "Java")
                        .param("keahlian", "Spring")
                        .header("Authorization", "Bearer token")
                        .with(request -> {
                            request.setMethod("PATCH");
                            return request;
                        }))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Update user successful"))
                .andExpect(jsonPath("$.data.namaLengkap").value("Nama Baru"))
                .andExpect(jsonPath("$.data.email").value("baru@example.com"));
    }
}
