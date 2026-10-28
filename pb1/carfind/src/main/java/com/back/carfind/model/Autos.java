package com.back.carfind.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "autos")
public class Autos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "modelo_id", nullable = false)
    private Long modeloId;

    @Column(name = "versionn", length = 50)
    private String version;

    @Column(name = "anio")
    private Integer anio;

    @Column(name = "kms")
    private Integer kms;

    @Column(name = "vtv")
    private Boolean vtv;

    @Column(name = "grabado")
    private Boolean grabado;

    @Column(name = "combustible_id")
    private Long combustibleId;

    @Column(name = "transmision_id")
    private Long transmisionId;

    @Column(name = "color", length = 30)
    private String color;

    @Column(name = "puertas")
    private Integer puertas;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

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
