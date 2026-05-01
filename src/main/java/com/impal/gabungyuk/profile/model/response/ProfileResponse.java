package com.impal.gabungyuk.profile.model.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data   
@JsonPropertyOrder({"idPengguna", "profilePicture", "namaLengkap", "email", "institusi", "bio", "keahlian", "lokasi", "whatsapp"})
public class ProfileResponse {
    private Integer idPengguna;
    private String profilePicture; 
    private String namaLengkap;
    private String email;
    private String institusi;
    private String bio;
    private String keahlian;
    private String lokasi;
    private String whatsapp;
}
