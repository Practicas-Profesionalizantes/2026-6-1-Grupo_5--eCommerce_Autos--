package com.back.carfind.model;

import java.io.Serializable;
import java.util.Objects;

/** Clave primaria compuesta de la tabla item_carrito (usuario_id, publicacion_id). */
public class ItemCarritoId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long usuarioId;
    private Long publicacionId;

    public ItemCarritoId() {
    }

    public ItemCarritoId(Long usuarioId, Long publicacionId) {
        this.usuarioId = usuarioId;
        this.publicacionId = publicacionId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemCarritoId other)) return false;
        return Objects.equals(usuarioId, other.usuarioId) && Objects.equals(publicacionId, other.publicacionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, publicacionId);
    }
}
