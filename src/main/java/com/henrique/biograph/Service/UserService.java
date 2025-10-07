package com.henrique.biograph.Service;

import com.henrique.biograph.DTOs.UserDTO;
import com.henrique.biograph.DTOs.Functions.LoginDTO;
import com.henrique.biograph.DTOs.Response.ResponseToLoginUserDTO;
import com.henrique.biograph.DTOs.Response.ResponseToRegisterUserDTO;

public interface UserService {
    ResponseToRegisterUserDTO registerUser(UserDTO data);
    ResponseToLoginUserDTO loginUser(LoginDTO data);
} 
