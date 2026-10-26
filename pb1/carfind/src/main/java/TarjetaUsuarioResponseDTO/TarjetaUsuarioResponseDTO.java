package com.back.carfind.dtos;

import java.time.LocalDate;

public class TarjetaUsuarioResponseDTO {

    private Long id_tarjeta;
    private Long id_usuario;
    private Long id_banco;
    private String titular;
    private String numero_tarjeta;
    private LocalDate fecha_vencimiento;
    private String tipo_tarjeta;

    // Constructor vacío
    public TarjetaUsuarioResponseDTO() {
    }

    // Constructor con parámetros
    public TarjetaUsuarioResponseDTO(Long id_tarjeta, Long id_usuario, Long id_banco, 
                                     String titular, String numero_tarjeta, 
                                     LocalDate fecha_vencimiento, String tipo_tarjeta) {
        this.id_tarjeta = id_tarjeta;
        this.id_usuario = id_usuario;
        this.id_banco = id_banco;
        this.titular = titular;
        this.numero_tarjeta = numero_tarjeta;
        this.fecha_vencimiento = fecha_vencimiento;
        this.tipo_tarjeta = tipo_tarjeta;
    }

    // Getters y Setters
    public Long getId_tarjeta() {
        return id_tarjeta;
    }

    public void setId_tarjeta(Long id_tarjeta) {
        this.id_tarjeta = id_tarjeta;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Long getId_banco() {
        return id_banco;
    }

    public void setId_banco(Long id_banco) {
        this.id_banco = id_banco;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getNumero_tarjeta() {
        return numero_tarjeta;
    }

    public void setNumero_tarjeta(String numero_tarjeta) {
        this.numero_tarjeta = numero_tarjeta;
    }

    public LocalDate getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(LocalDate fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public String getTipo_tarjeta() {
        return tipo_tarjeta;
    }

    public void setTipo_tarjeta(String tipo_tarjeta) {
        this.tipo_tarjeta = tipo_tarjeta;
    }
}
