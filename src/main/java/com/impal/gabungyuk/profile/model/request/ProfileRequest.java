package com.impal.gabungyuk.profile.model.request;
import lombok.Data;
@Data

public class ProfileRequest {
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
