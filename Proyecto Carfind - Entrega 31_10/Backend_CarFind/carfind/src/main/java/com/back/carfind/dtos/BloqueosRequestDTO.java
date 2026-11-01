package com.back.carfind.dtos;

/**
 * Datos que recibe la API para crear o actualizar un registro de la tabla bloqueos (sin el id autogenerado).
 */
public class BloqueosRequestDTO {

    private Long usuarioId;
    private Long usuarioBloqueadoId;

    public BloqueosRequestDTO() {
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
