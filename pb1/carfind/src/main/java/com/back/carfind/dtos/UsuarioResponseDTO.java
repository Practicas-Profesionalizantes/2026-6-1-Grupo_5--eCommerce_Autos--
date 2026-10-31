package com.back.carfind.dtos;

import java.time.LocalDate;

public class UsuarioResponseDTO {

    private Long id_usuario;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private Long id_rol;
    private LocalDate fecha_registro;
    private Boolean activo;

    // Constructor vacío
    public UsuarioResponseDTO() {
    }

    // Constructor con parámetros
    public UsuarioResponseDTO(Long id_usuario, String nombre, String apellido, String email, 
                              String telefono, Long id_rol, LocalDate fecha_registro, Boolean activo) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.id_rol = id_rol;
        this.fecha_registro = fecha_registro;
        this.activo = activo;
    }

    // Getters y Setters
    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Long getId_rol() {
        return id_rol;
    }

    public void setId_rol(Long id_rol) {
        this.id_rol = id_rol;
    }

    public LocalDate getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(LocalDate fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}