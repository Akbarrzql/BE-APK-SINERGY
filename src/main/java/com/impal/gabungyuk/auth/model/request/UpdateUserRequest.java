package com.impal.gabungyuk.auth.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserRequest {
    // private String username; diubah karena sekarang pake nama lengkap
    private String email;
    private String password;
    private String namaLengkap;
    private String profilePicture;
    private String institusi;
    private String bio;
    private String keahlian;
    private String lokasi;
    private String whatsapp;
    }
