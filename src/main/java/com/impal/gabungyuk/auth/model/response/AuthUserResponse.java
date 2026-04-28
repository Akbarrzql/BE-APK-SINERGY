package com.impal.gabungyuk.auth.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthUserResponse {
    private Integer userId;
    //private String username;
    private String email;
    private String token;
    private Long expiredAt;
    private String namaLengkap;
    private String profilePicture;
    private String institusi;
    private String bio;
    private String keahlian;
    private String lokasi;
    private String whatsapp;
}
