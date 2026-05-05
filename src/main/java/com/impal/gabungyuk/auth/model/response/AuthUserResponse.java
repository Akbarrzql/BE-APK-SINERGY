package com.impal.gabungyuk.auth.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthUserResponse {
    private Integer userId;
    private String email;
    private String token;
    private Long expiredAt;
    private String namaLengkap;
    private String profilePicture;
    private String institusi;
    private String bio;
    private List<String> keahlian;
    private String lokasi;
    private String whatsapp;
    private String instagram;
    private String facebook;
    private String linkedin;
}
