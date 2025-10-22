package com.henrique.biograph.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.henrique.biograph.Model.UserModel;
import com.henrique.biograph.Model.UserVerifyModel;

public interface UserVerificationRepository extends JpaRepository<UserVerifyModel, UUID> {
    Optional<UserVerifyModel> findByUsuario(UserModel usuario);
}   
