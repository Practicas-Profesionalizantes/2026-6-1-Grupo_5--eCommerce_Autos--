package com.back.carfind.model;

import java.io.Serializable;
import java.util.Objects;

/** Clave primaria compuesta de la tabla bloqueos (usuario_id, usuario_bloqueado_id). */
public class BloqueosId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long usuarioId;
    private Long usuarioBloqueadoId;

    public BloqueosId() {
    }

    public BloqueosId(Long usuarioId, Long usuarioBloqueadoId) {
        this.usuarioId = usuarioId;
        this.usuarioBloqueadoId = usuarioBloqueadoId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BloqueosId other)) return false;
        return Objects.equals(usuarioId, other.usuarioId) && Objects.equals(usuarioBloqueadoId, other.usuarioBloqueadoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, usuarioBloqueadoId);
    }
}
