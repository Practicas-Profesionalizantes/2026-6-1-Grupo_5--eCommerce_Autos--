package com.back.carfind.dtos;

import java.time.LocalDate;

public class PagosPromocionResponseDTO {

    private Long id_pago_promocion;
    private Long id_pago;
    private Long id_promocion;
    private Float monto_descuento;
    private LocalDate fecha_aplicacion;

    // Constructor vacío
    public PagosPromocionResponseDTO() {
    }

    // Constructor con parámetros
    public PagosPromocionResponseDTO(Long id_pago_promocion, Long id_pago, Long id_promocion, 
                                    Float monto_descuento, LocalDate fecha_aplicacion) {
        this.id_pago_promocion = id_pago_promocion;
        this.id_pago = id_pago;
        this.id_promocion = id_promocion;
        this.monto_descuento = monto_descuento;
        this.fecha_aplicacion = fecha_aplicacion;
    }

    // Getters y Setters
    public Long getId_pago_promocion() {
        return id_pago_promocion;
    }

    public void setId_pago_promocion(Long id_pago_promocion) {
        this.id_pago_promocion = id_pago_promocion;
    }

    public Long getId_pago() {
        return id_pago;
    }

    public void setId_pago(Long id_pago) {
        this.id_pago = id_pago;
    }

    public Long getId_promocion() {
        return id_promocion;
    }

    public void setId_promocion(Long id_promocion) {
        this.id_promocion = id_promocion;
    }

    public Float getMonto_descuento() {
        return monto_descuento;
    }

    public void setMonto_descuento(Float monto_descuento) {
        this.monto_descuento = monto_descuento;
    }

    public LocalDate getFecha_aplicacion() {
        return fecha_aplicacion;
    }

    public void setFecha_aplicacion(LocalDate fecha_aplicacion) {
        this.fecha_aplicacion = fecha_aplicacion;
    }
}