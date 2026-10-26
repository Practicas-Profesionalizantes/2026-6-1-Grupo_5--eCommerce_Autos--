package com.back.carfind.dtos;

import java.time.LocalDate;

public class UsuariosResponseDTO {

    private Long id_usuario;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String telefono;
    private LocalDate fecha_registro;

    // Constructor vacío
    public UsuariosResponseDTO() {
    }

    // Constructor con parámetros
    public UsuariosResponseDTO(Long id_usuario, String nombre, String apellido, String email, 
                               String password, String telefono, LocalDate fecha_registro) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.fecha_registro = fecha_registro;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(LocalDate fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
}