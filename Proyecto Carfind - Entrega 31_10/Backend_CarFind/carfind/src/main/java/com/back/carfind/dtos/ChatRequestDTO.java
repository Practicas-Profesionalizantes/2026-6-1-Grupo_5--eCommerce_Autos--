package com.back.carfind.dtos;

import java.time.LocalDateTime;

/**
 * Datos que recibe la API para crear o actualizar un registro de la tabla chats (sin el id autogenerado).
 */
public class ChatRequestDTO {

    private Long usuario1Id;
    private Long usuario2Id;
    private Long publicacionId;
    private LocalDateTime fechaCreacion;

    public ChatRequestDTO() {
    }

    public Long getUsuario1Id() {
        return usuario1Id;
    }

    public void setUsuario1Id(Long usuario1Id) {
        this.usuario1Id = usuario1Id;
    }

    public Long getUsuario2Id() {
        return usuario2Id;
    }

    public void setUsuario2Id(Long usuario2Id) {
        this.usuario2Id = usuario2Id;
    }

    public Long getPublicacionId() {
        return publicacionId;
    }

    public void setPublicacionId(Long publicacionId) {
        this.publicacionId = publicacionId;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
