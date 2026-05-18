package com.impal.gabungyuk.auth.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterUserRequest {
    private String namaLengkap;
    private String email;
    private String password;
    private String institusi;
    private String bio;
    private List<String> keahlian;
    private String lokasi;
    private String whatsapp;
    private String instagram;
    private String facebook;
    private String linkedin;
}