package com.back.carfind.dtos;

import java.time.LocalDateTime;

/**
 * Datos que recibe la API para crear o actualizar un registro de la tabla visitas_auto (sin el id autogenerado).
 */
public class VisitasAutoRequestDTO {

    private Long autoId;
    private Long compradorId;
    private LocalDateTime fecha;
    private String resultado;

    public VisitasAutoRequestDTO() {
    }

    public Long getAutoId() {
        return autoId;
    }

    public void setAutoId(Long autoId) {
        this.autoId = autoId;
    }

    public Long getCompradorId() {
        return compradorId;
    }

    public void setCompradorId(Long compradorId) {
        this.compradorId = compradorId;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
