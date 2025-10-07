package com.henrique.biograph.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.henrique.biograph.DTOs.UserDTO;
import com.henrique.biograph.DTOs.Functions.LoginDTO;
import com.henrique.biograph.DTOs.Response.ResponseToLoginUserDTO;
import com.henrique.biograph.DTOs.Response.ResponseToRegisterUserDTO;
import com.henrique.biograph.Enums.UserRoleEnum;
import com.henrique.biograph.Model.UserModel;
import com.henrique.biograph.Repository.UserRepository;
import com.henrique.biograph.Service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseToRegisterUserDTO registerUser(UserDTO data) {
        UserModel userModel = new UserModel();

        userModel.setName(data.getName());
        userModel.setEmail(data.getEmail());
        userModel.setPassword(passwordEncoder.encode(data.getPassword()));
        userModel.setEmailVerified(false);
        userModel.setRole(UserRoleEnum.ROLE_ADMIN);

        userRepository.save(userModel);

        return registerToDTO(data, userModel.getId(), userModel.getEmailVerified(), userModel.getRole());
    }

    @Override
    public ResponseToLoginUserDTO loginUser(LoginDTO data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loginUser'");
    }

    public ResponseToRegisterUserDTO registerToDTO(UserDTO data, String id, Boolean emailVerified, UserRoleEnum role) {
        ResponseToRegisterUserDTO responseToRegisterUserDTO = new ResponseToRegisterUserDTO();

        responseToRegisterUserDTO.setId(id);
        responseToRegisterUserDTO.setName(data.getName());
        responseToRegisterUserDTO.setEmail(data.getEmail());
        responseToRegisterUserDTO.setEmailVerified(emailVerified);
        responseToRegisterUserDTO.setRole(role);

        return responseToRegisterUserDTO;
    }

}
