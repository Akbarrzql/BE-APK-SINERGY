package com.impal.gabungyuk.profile.model.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import java.util.List;

@Data   
@JsonPropertyOrder({"idPengguna", "profilePicture", "namaLengkap", "email", "institusi", "bio", "keahlian", "lokasi", "whatsapp", "instagram", "facebook", "linkedin"})
public class ProfileResponse {
    private Integer idPengguna;
    private String profilePicture; 
    private String namaLengkap;
    private String email;
    private String institusi;
    private String bio;
    private List<String> keahlian;
    private String lokasi;
    private String whatsapp;
    private String instagram;
    private String facebook;
    private String linkedin;

}
