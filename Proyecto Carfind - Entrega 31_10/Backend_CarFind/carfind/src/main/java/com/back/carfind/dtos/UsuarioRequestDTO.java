package com.back.carfind.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Datos que recibe la API para crear o actualizar un registro de la tabla usuarios (sin el id autogenerado).
 * La contraseña llega en texto plano y el controller la guarda cifrada.
 */
public class UsuarioRequestDTO {

    private String nombre;
    private String email;
    private String dni;
    private String password;
    private String telefono;
    private BigDecimal reputacion;
    private BigDecimal scoreRiesgo;
    private String verificacion;
    private LocalDateTime fechaCreacion;
    private Integer totalCompras;
    private Integer totalVentas;
    private Boolean eliminado;
    private LocalDateTime fechaEliminacion;

    public UsuarioRequestDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public BigDecimal getReputacion() {
        return reputacion;
    }

    public void setReputacion(BigDecimal reputacion) {
        this.reputacion = reputacion;
    }

    public BigDecimal getScoreRiesgo() {
        return scoreRiesgo;
    }

    public void setScoreRiesgo(BigDecimal scoreRiesgo) {
        this.scoreRiesgo = scoreRiesgo;
    }

    public String getVerificacion() {
        return verificacion;
    }

    public void setVerificacion(String verificacion) {
        this.verificacion = verificacion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getTotalCompras() {
        return totalCompras;
    }

    public void setTotalCompras(Integer totalCompras) {
        this.totalCompras = totalCompras;
    }

    public Integer getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(Integer totalVentas) {
        this.totalVentas = totalVentas;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public LocalDateTime getFechaEliminacion() {
        return fechaEliminacion;
    }

    public void setFechaEliminacion(LocalDateTime fechaEliminacion) {
        this.fechaEliminacion = fechaEliminacion;
    }
}
