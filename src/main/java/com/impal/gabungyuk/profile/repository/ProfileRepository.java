package com.impal.gabungyuk.profile.repository;

import com.impal.gabungyuk.profile.entitiy.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    Optional<Profile> findByIdPengguna(Integer idPengguna);
}