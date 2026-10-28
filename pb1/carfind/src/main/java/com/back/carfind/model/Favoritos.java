package com.back.carfind.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "favoritos")
@IdClass(FavoritosId.class)
public class Favoritos {

    @Id
    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Id
    @Column(name = "publicacion_id", nullable = false)
    private Long publicacionId;

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
}
