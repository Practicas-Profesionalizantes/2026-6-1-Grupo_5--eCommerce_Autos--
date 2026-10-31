package com.back.carfind.dtos;

import java.time.LocalDate;

public class EstadisticasPublicacionResponseDTO {

    private Long id_estadistica;
    private Long id_publicacion;
    private Integer vistas;
    private Integer favoritos;
    private Integer preguntas;
    private LocalDate fecha_actualizacion;

    // Constructor vacío
    public EstadisticasPublicacionResponseDTO() {
    }

    // Constructor con parámetros
    public EstadisticasPublicacionResponseDTO(Long id_estadistica, Long id_publicacion, Integer vistas, 
                                              Integer favoritos, Integer preguntas, LocalDate fecha_actualizacion) {
        this.id_estadistica = id_estadistica;
        this.id_publicacion = id_publicacion;
        this.vistas = vistas;
        this.favoritos = favoritos;
        this.preguntas = preguntas;
        this.fecha_actualizacion = fecha_actualizacion;
    }

    // Getters y Setters
    public Long getId_estadistica() {
        return id_estadistica;
    }

    public void setId_estadistica(Long id_estadistica) {
        this.id_estadistica = id_estadistica;
    }

    public Long getId_publicacion() {
        return id_publicacion;
    }

    public void setId_publicacion(Long id_publicacion) {
        this.id_publicacion = id_publicacion;
    }

    public Integer getVistas() {
        return vistas;
    }

    public void setVistas(Integer vistas) {
        this.vistas = vistas;
    }

    public Integer getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(Integer favoritos) {
        this.favoritos = favoritos;
    }

    public Integer getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(Integer preguntas) {
        this.preguntas = preguntas;
    }

    public LocalDate getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public void setFecha_actualizacion(LocalDate fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }
}