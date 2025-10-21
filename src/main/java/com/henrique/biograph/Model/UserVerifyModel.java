package com.henrique.biograph.Model; // (ou o seu pacote correto)

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "verification_tokens")
public class UserVerifyModel {

    @Id
    @Column(name = "token", columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID token;

    @Column(nullable = false)
    private Instant dataExpiracao;

    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private UserModel usuario;

    public UserVerifyModel() {
    }

    public UserVerifyModel(UserModel usuario, Instant dataExpiracao) {
        this.token = UUID.randomUUID(); 
        this.usuario = usuario;
        this.dataExpiracao = dataExpiracao;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public Instant getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(Instant dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public UserModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UserModel usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserVerifyModel that = (UserVerifyModel) o;
        return Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }
}