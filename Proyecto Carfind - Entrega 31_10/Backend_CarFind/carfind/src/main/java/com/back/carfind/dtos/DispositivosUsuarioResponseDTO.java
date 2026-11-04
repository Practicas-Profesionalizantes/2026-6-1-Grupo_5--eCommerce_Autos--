package com.back.carfind.dtos;

/**
 * Datos que devuelve la API de un registro de la tabla dispositivos_usuario.
 */
public class DispositivosUsuarioResponseDTO {

    private Long id;
    private Long usuarioId;
    private String tokenPush;
    private String plataforma;

    public DispositivosUsuarioResponseDTO() {
    }

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
