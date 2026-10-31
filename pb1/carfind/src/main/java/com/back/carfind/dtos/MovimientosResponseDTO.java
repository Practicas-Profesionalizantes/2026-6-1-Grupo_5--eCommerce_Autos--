package com.back.carfind.dtos;

import java.time.LocalDate;

public class MovimientosResponseDTO {

    private Long id_movimiento;
    private Long id_billetera;
    private Float monto;
    private String tipo_movimiento;
    private String descripcion;
    private LocalDate fecha_movimiento;

    // Constructor vacío
    public MovimientosResponseDTO() {
    }

    // Constructor con parámetros
    public MovimientosResponseDTO(Long id_movimiento, Long id_billetera, Float monto, 
                                  String tipo_movimiento, String descripcion, LocalDate fecha_movimiento) {
        this.id_movimiento = id_movimiento;
        this.id_billetera = id_billetera;
        this.monto = monto;
        this.tipo_movimiento = tipo_movimiento;
        this.descripcion = descripcion;
        this.fecha_movimiento = fecha_movimiento;
    }

    // Getters y Setters
    public Long getId_movimiento() {
        return id_movimiento;
    }

    public void setId_movimiento(Long id_movimiento) {
        this.id_movimiento = id_movimiento;
    }

    public Long getId_billetera() {
        return id_billetera;
    }

    public void setId_billetera(Long id_billetera) {
        this.id_billetera = id_billetera;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public String getTipo_movimiento() {
        return tipo_movimiento;
    }

    public void setTipo_movimiento(String tipo_movimiento) {
        this.tipo_movimiento = tipo_movimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha_movimiento() {
        return fecha_movimiento;
    }

    public void setFecha_movimiento(LocalDate fecha_movimiento) {
        this.fecha_movimiento = fecha_movimiento;
    }
}