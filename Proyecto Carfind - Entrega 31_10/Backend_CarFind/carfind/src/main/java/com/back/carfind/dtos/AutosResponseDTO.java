package com.back.carfind.dtos;

import java.time.LocalDateTime;

/**
 * Datos que devuelve la API de un registro de la tabla autos.
 */
public class AutosResponseDTO {

    private Long id;
    private Long modeloId;
    private String version;
    private Integer anio;
    private Integer kms;
    private Boolean vtv;
    private Boolean grabado;
    private Long combustibleId;
    private Long transmisionId;
    private String color;
    private Integer puertas;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private Boolean eliminado;
    private LocalDateTime fechaEliminacion;

    public AutosResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getModeloId() {
        return modeloId;
    }

    public void setModeloId(Long modeloId) {
        this.modeloId = modeloId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getKms() {
        return kms;
    }

    public void setKms(Integer kms) {
        this.kms = kms;
    }

    public Boolean getVtv() {
        return vtv;
    }

    public void setVtv(Boolean vtv) {
        this.vtv = vtv;
    }

    public Boolean getGrabado() {
        return grabado;
    }

    public void setGrabado(Boolean grabado) {
        this.grabado = grabado;
    }

    public Long getCombustibleId() {
        return combustibleId;
    }

    public void setCombustibleId(Long combustibleId) {
        this.combustibleId = combustibleId;
    }

    public Long getTransmisionId() {
        return transmisionId;
    }

    public void setTransmisionId(Long transmisionId) {
        this.transmisionId = transmisionId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getPuertas() {
        return puertas;
    }

    public void setPuertas(Integer puertas) {
        this.puertas = puertas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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
