package com.henrique.biograph.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.henrique.biograph.Model.UserModel;
import com.henrique.biograph.Service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String remetente;

    @Value("${biograph.ai.base-url}") 
    private String baseUrl;

    @Override
    public void enviarEmailDeVerificacao(UserModel usuario, String token) {
        String verificationLink = baseUrl + "/auth/verificarCadastro/" + token;
        String subject = "Bem-vindo ao BioGraph.AI - Confirme seu E-mail";
        String message = "Olá " + usuario.getName() + ",\n\n"
                       + "Seu cadastro está quase pronto. Por favor, clique no link abaixo para ativar sua conta:\n"
                       + verificationLink + "\n\n"
                       + "Este link expira em 15 minutos.\n\n"
                       + "Atenciosamente,\nEquipe BioGraph.AI";
        
        this.enviarEmailTexto(usuario.getEmail(), subject, message);
    }

    private String enviarEmailTexto(String destinatario, String assunto, String menssagem) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(remetente);
            simpleMailMessage.setTo(destinatario);
            simpleMailMessage.setSubject(assunto);
            simpleMailMessage.setText(menssagem);
            javaMailSender.send(simpleMailMessage);
            return "Email enviado";
        } catch (Exception e) {
            return "Erro ao enviar email: " + e.getMessage();
        }
    }
}
