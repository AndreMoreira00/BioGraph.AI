package com.henrique.biograph.Service;

import com.henrique.biograph.Model.UserModel;

public interface EmailService {
    void enviarEmailDeVerificacao(UserModel usuario, String token);
}
