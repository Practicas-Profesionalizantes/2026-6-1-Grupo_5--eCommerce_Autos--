package com.back.carfind.dtos;

/**
 * Datos que devuelve la API de un registro de la tabla bloqueos.
 */
public class BloqueosResponseDTO {

    private Long usuarioId;
    private Long usuarioBloqueadoId;

    public BloqueosResponseDTO() {
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getUsuarioBloqueadoId() {
        return usuarioBloqueadoId;
    }

    public void setUsuarioBloqueadoId(Long usuarioBloqueadoId) {
        this.usuarioBloqueadoId = usuarioBloqueadoId;
    }
}
