package com.back.carfind.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "email", nullable = false, length = 120)
    private String email;

    @Column(name = "dni", nullable = false, length = 8)
    private String dni;

    @Column(name = "password_hash", nullable = false, length = 150)
    private String passwordHash;

    @Column(name = "telefono", nullable = false, length = 20)
    private String telefono;

    @Column(name = "reputacion")
    private BigDecimal reputacion;

    @Column(name = "score_riesgo")
    private BigDecimal scoreRiesgo;

    @Column(name = "verificacion")
    private String verificacion;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "total_compras")
    private Integer totalCompras;

    @Column(name = "total_ventas")
    private Integer totalVentas;

    @Column(name = "eliminado")
    private Boolean eliminado;

    @Column(name = "fecha_eliminacion")
    private LocalDateTime fechaEliminacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
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
