package com.henrique.biograph.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.henrique.biograph.Model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String>{
    UserDetails findByEmail(String email);
} 
