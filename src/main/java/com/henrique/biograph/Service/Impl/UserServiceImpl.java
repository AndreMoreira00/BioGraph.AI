package com.henrique.biograph.Service.Impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.henrique.biograph.DTOs.UserDTO;
import com.henrique.biograph.DTOs.Functions.LoginDTO;
import com.henrique.biograph.DTOs.Response.ResponseToLoginUserDTO;
import com.henrique.biograph.DTOs.Response.ResponseToRegisterUserDTO;
import com.henrique.biograph.Enums.UserRoleEnum;
import com.henrique.biograph.Model.UserModel;
import com.henrique.biograph.Model.UserVerifyModel;
import com.henrique.biograph.Repository.UserRepository;
import com.henrique.biograph.Repository.UserVerificationRepository;
import com.henrique.biograph.Service.EmailService;
import com.henrique.biograph.Service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final UserVerificationRepository userVerificationRepository;

    // @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, EmailService emailService,
            UserVerificationRepository userVerificationRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.userVerificationRepository = userVerificationRepository;
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

        createAndSendVerificationLink(userModel);

        return registerToDTO(data, userModel.getId(), userModel.getEmailVerified(), userModel.getRole());
    }

    // Em UserServiceImpl.java

    @Override
    public UUID createAndSendVerificationLink(UserModel userModel) {

        userVerificationRepository.findByUsuario(userModel).ifPresent(oldToken -> {
            userVerificationRepository.delete(oldToken);
        });

        UserVerifyModel verification_tokens = new UserVerifyModel(userModel,
                Instant.now().plus(15, ChronoUnit.MINUTES)); //

        userVerificationRepository.save(verification_tokens); //

        emailService.enviarEmailDeVerificacao(userModel, verification_tokens.getToken().toString()); 

        return verification_tokens.getToken();
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

    @Override
    public String verificarToken(UUID token) {
        var verification = userVerificationRepository.findById(token).orElse(null);
        if (verification == null)
            return "Token não encontrado";

        if (verification.getDataExpiracao().isBefore(Instant.now())) {
            userVerificationRepository.deleteById(token);
            return "Token expirado";
        }

        var user = verification.getUsuario();
        if (Boolean.TRUE.equals(user.getEmailVerified())) {
            userVerificationRepository.deleteById(token);
            return "Email já verificado";
        }

        user.setEmailVerified(true);
        userRepository.save(user);
        userVerificationRepository.deleteById(token);
        return "Email verificado com sucesso";
    }

    @Override
    public UserModel getUserByEmail(String email) {
        UserModel userModel = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email Não Encontrado!"));
        return userModel;
    }

}
