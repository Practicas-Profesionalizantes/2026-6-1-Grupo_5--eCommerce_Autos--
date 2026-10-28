package com.back.carfind.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "chats")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "usuario1_id", nullable = false)
    private Long usuario1Id;

    @Column(name = "usuario2_id", nullable = false)
    private Long usuario2Id;

    @Column(name = "publicacion_id")
    private Long publicacionId;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
