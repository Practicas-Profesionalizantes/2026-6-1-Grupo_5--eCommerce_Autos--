package com.back.carfind.dtos;

/**
 * Datos que devuelve la API de un registro de la tabla estadisticas_publicacion.
 */
public class EstadisticasPublicacionResponseDTO {

    private Long publicacionId;
    private Integer vistas;
    private Integer clicks;
    private Integer favoritos;
    private Integer contactos;
    private Integer compartidos;

    public EstadisticasPublicacionResponseDTO() {
    }

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
