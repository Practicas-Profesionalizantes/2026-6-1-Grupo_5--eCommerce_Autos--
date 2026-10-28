package com.back.carfind.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "vistas_publicacion")
@IdClass(VistasPublicacionId.class)
public class VistasPublicacion {

    @Id
    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Id
    @Column(name = "publicacion_id", nullable = false)
    private Long publicacionId;

    @Id
    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getPublicacionId() {
        return publicacionId;
    }

    public void setPublicacionId(Long publicacionId) {
        this.publicacionId = publicacionId;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
