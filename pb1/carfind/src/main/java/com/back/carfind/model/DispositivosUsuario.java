package com.back.carfind.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dispositivos_usuario")
public class DispositivosUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "token_push", columnDefinition = "TEXT")
    private String tokenPush;

    @Column(name = "plataforma", length = 20)
    private String plataforma;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getTokenPush() {
        return tokenPush;
    }

    public void setTokenPush(String tokenPush) {
        this.tokenPush = tokenPush;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }
}
