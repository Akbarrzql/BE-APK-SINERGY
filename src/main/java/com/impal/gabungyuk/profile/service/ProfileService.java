package com.impal.gabungyuk.profile.service;

import com.impal.gabungyuk.core.service.TokenService;
import com.impal.gabungyuk.profile.entitiy.Profile;
import com.impal.gabungyuk.profile.model.response.ProfileResponse;
import com.impal.gabungyuk.profile.repository.ProfileRepository;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final TokenService tokenService;

    public ProfileService(ProfileRepository profileRepository, TokenService tokenService) {
        this.profileRepository = profileRepository;
        this.tokenService = tokenService;
    }
  
    @Transactional
    public ProfileResponse getMyProfile(String authorizationHeader) {
        Integer userId = tokenService.extractUserIdFromAuthorizationHeader(authorizationHeader);

        Profile profile = profileRepository.findByIdPengguna(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found"));

        return toProfileResponse(profile);
    }

    
    private ProfileResponse toProfileResponse(Profile profile) {
        ProfileResponse response = new ProfileResponse();

        response.setIdPengguna(profile.getIdPengguna());
        response.setProfilePicture(profile.getProfilePicture());
        response.setNamaLengkap(profile.getNamaLengkap());
        response.setEmail(profile.getEmail());
        response.setInstitusi(profile.getInstitusi());
        response.setBio(profile.getBio());
        response.setKeahlian(profile.getKeahlian());
        response.setLokasi(profile.getLokasi());
        response.setWhatsapp(profile.getWhatsapp());

        return response;
    }
}