package com.henrique.biograph.Controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.henrique.biograph.DTOs.UserDTO;
import com.henrique.biograph.DTOs.Functions.LoginDTO;
import com.henrique.biograph.DTOs.Response.ResponseToLoginUserDTO;
import com.henrique.biograph.DTOs.Response.ResponseToRegisterUserDTO;
import com.henrique.biograph.Model.UserModel;
import com.henrique.biograph.Security.TokenService;
import com.henrique.biograph.Service.UserService;

@RestController
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager,
            TokenService tokenService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<ResponseToRegisterUserDTO> registerUser(@RequestBody UserDTO data) {
        ResponseToRegisterUserDTO newUser = userService.registerUser(data);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newUser.getId()).toUri();

        return ResponseEntity.created(location).body(newUser);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ResponseToLoginUserDTO> loginUser(@RequestBody LoginDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserModel) auth.getPrincipal());

        return ResponseEntity.ok(new ResponseToLoginUserDTO(token));

    }
}
