package com.henrique.biograph.DTOs.Response;

public class ResponseToLoginUserDTO {
    private String token;

    public ResponseToLoginUserDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
