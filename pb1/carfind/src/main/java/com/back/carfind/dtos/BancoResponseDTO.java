package com.back.carfind.dtos;

import java.time.LocalDate;

public class BancoResponseDTO {

    private Long id_banco;
    private String nombre;
    private String codigo;
    private Boolean activo;
    private LocalDate fecha_creacion;

    // Constructor vacío
    public BancoResponseDTO() {
    }

    // Constructor con parámetros
    public BancoResponseDTO(Long id_banco, String nombre, String codigo, Boolean activo, LocalDate fecha_creacion) {
        this.id_banco = id_banco;
        this.nombre = nombre;
        this.codigo = codigo;
        this.activo = activo;
        this.fecha_creacion = fecha_creacion;
    }

    // Getters y Setters
    public Long getId_banco() {
        return id_banco;
    }

    public void setId_banco(Long id_banco) {
        this.id_banco = id_banco;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public LocalDate getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDate fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
}