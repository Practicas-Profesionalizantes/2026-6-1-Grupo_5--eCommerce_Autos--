package com.back.carfind.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/** Clave primaria compuesta de la tabla vistas_publicacion (usuario_id, publicacion_id, fecha). */
public class VistasPublicacionId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long usuarioId;
    private Long publicacionId;
    private LocalDateTime fecha;

    public VistasPublicacionId() {
    }

    public VistasPublicacionId(Long usuarioId, Long publicacionId, LocalDateTime fecha) {
        this.usuarioId = usuarioId;
        this.publicacionId = publicacionId;
        this.fecha = fecha;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VistasPublicacionId other)) return false;
        return Objects.equals(usuarioId, other.usuarioId) && Objects.equals(publicacionId, other.publicacionId) && Objects.equals(fecha, other.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, publicacionId, fecha);
    }
}
