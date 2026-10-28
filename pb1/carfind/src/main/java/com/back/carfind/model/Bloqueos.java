package com.back.carfind.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "bloqueos")
@IdClass(BloqueosId.class)
public class Bloqueos {

    @Id
    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Id
    @Column(name = "usuario_bloqueado_id", nullable = false)
    private Long usuarioBloqueadoId;

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
