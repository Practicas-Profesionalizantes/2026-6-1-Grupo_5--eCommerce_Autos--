package com.back.carfind.dtos;

import java.time.LocalDateTime;

/**
 * Datos que devuelve la API de un registro de la tabla moderacion_multimedia.
 */
public class ModeracionMultimediaResponseDTO {

    private Long id;
    private Long multimediaId;
    private String estado;
    private String motivo;
    private LocalDateTime fecha;

    public ModeracionMultimediaResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMultimediaId() {
        return multimediaId;
    }

    public void setMultimediaId(Long multimediaId) {
        this.multimediaId = multimediaId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
