package com.back.carfind.dtos;

public class BloqueosResponseDTO {

    private Long id_usuario;
    private Long usuario_bloqueado_id;

    // Constructor vacío
    public BloqueosResponseDTO() {
    }

    // Constructor con parámetros
    public BloqueosResponseDTO(Long id_usuario, Long usuario_bloqueado_id) {
        this.id_usuario = id_usuario;
        this.usuario_bloqueado_id = usuario_bloqueado_id;
    }

    // Getters y Setters
    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Long getUsuario_bloqueado_id() {
        return usuario_bloqueado_id;
    }

    public void setUsuario_bloqueado_id(Long usuario_bloqueado_id) {
        this.usuario_bloqueado_id = usuario_bloqueado_id;
    }
}