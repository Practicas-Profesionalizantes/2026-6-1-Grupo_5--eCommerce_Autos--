package com.back.carfind.dtos;

public class DispositivosUsuarioRequestDTO {

    private Long id_usuario;
    private Long token_push;
    private String plataforma;

    // Getters y Setters
    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Long getToken_push() {
        return token_push;
    }

    public void setToken_push(Long token_push) {
        this.token_push = token_push;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }
}