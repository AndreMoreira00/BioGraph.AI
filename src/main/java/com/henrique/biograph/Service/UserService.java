package com.henrique.biograph.Service;

import java.util.UUID;

import com.henrique.biograph.DTOs.UserDTO;
import com.henrique.biograph.DTOs.Functions.LoginDTO;
import com.henrique.biograph.DTOs.Response.ResponseToLoginUserDTO;
import com.henrique.biograph.DTOs.Response.ResponseToRegisterUserDTO;
import com.henrique.biograph.Model.UserModel;

public interface UserService {
    ResponseToRegisterUserDTO registerUser(UserDTO data);
    ResponseToLoginUserDTO loginUser(LoginDTO data);
    String verificarToken(UUID token);
    UserModel getUserByEmail(String email);
    UUID createAndSendVerificationLink(UserModel userModel);
} 
