package com.back.carfind.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "estadisticas_publicacion")
public class EstadisticasPublicacion {

    @Id
    @Column(name = "publicacion_id", nullable = false)
    private Long publicacionId;

    @Column(name = "vistas")
    private Integer vistas;

    @Column(name = "clicks")
    private Integer clicks;

    @Column(name = "favoritos")
    private Integer favoritos;

    @Column(name = "contactos")
    private Integer contactos;

    @Column(name = "compartidos")
    private Integer compartidos;

    public Long getPublicacionId() {
        return publicacionId;
    }

    public void setPublicacionId(Long publicacionId) {
        this.publicacionId = publicacionId;
    }

    public Integer getVistas() {
        return vistas;
    }

    public void setVistas(Integer vistas) {
        this.vistas = vistas;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    public Integer getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(Integer favoritos) {
        this.favoritos = favoritos;
    }

    public Integer getContactos() {
        return contactos;
    }

    public void setContactos(Integer contactos) {
        this.contactos = contactos;
    }

    public Integer getCompartidos() {
        return compartidos;
    }

    public void setCompartidos(Integer compartidos) {
        this.compartidos = compartidos;
    }
}
