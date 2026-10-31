package com.back.carfind.dtos;

public class BilleterasResponseDTO {

    private Long id_billeteras;
    private Long id_usuario;
    private Float saldo;

    // Constructor vacío
    public BilleterasResponseDTO() {
    }

    // Constructor con parámetros
    public BilleterasResponseDTO(Long id_billeteras, Long id_usuario, Float saldo) {
        this.id_billeteras = id_billeteras;
        this.id_usuario = id_usuario;
        this.saldo = saldo;
    }

    // Getters y Setters
    public Long getId_billeteras() {
        return id_billeteras;
    }

    public void setId_billeteras(Long id_billeteras) {
        this.id_billeteras = id_billeteras;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }
}