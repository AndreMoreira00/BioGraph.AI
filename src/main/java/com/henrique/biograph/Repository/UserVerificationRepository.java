package com.henrique.biograph.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.henrique.biograph.Model.UserVerifyModel;

public interface UserVerificationRepository extends JpaRepository<UserVerifyModel, UUID> {
    // O JpaRepository já fornece o findById(UUID token)
    // Não precisamos de métodos adicionais
}   
