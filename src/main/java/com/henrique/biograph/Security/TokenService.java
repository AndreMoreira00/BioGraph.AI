package com.henrique.biograph.Security;

import com.henrique.biograph.Model.UserModel;

public interface TokenService {
    String generateToken(UserModel userModel);

    String validateToken(String token);

}
