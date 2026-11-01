package com.back.carfind.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Datos que recibe la API para crear o actualizar un registro de la tabla informe_dominio (sin el id autogenerado).
 */
public class InformeDominioRequestDTO {

    private Long autoId;
    private String dominio;
    private String numeroChasis;
    private String numeroMotor;
    private String titularActual;
    private Integer cantidadTitulares;
    private LocalDate fechaInscripcion;
    private Boolean tieneEmbargo;
    private Boolean tienePrenda;
    private Boolean esRobado;
    private Boolean titularInhibido;
    private BigDecimal deudaPatentes;
    private BigDecimal deudaMultas;
    private String observaciones;
    private LocalDateTime fechaEmision;
    private LocalDateTime fechaVencimiento;

    public InformeDominioRequestDTO() {
    }

    public Long getAutoId() {
        return autoId;
    }

    public void setAutoId(Long autoId) {
        this.autoId = autoId;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public String getNumeroChasis() {
        return numeroChasis;
    }

    public void setNumeroChasis(String numeroChasis) {
        this.numeroChasis = numeroChasis;
    }

    public String getNumeroMotor() {
        return numeroMotor;
    }

    public void setNumeroMotor(String numeroMotor) {
        this.numeroMotor = numeroMotor;
    }

    public String getTitularActual() {
        return titularActual;
    }

    public void setTitularActual(String titularActual) {
        this.titularActual = titularActual;
    }

    public Integer getCantidadTitulares() {
        return cantidadTitulares;
    }

    public void setCantidadTitulares(Integer cantidadTitulares) {
        this.cantidadTitulares = cantidadTitulares;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Boolean getTieneEmbargo() {
        return tieneEmbargo;
    }

    public void setTieneEmbargo(Boolean tieneEmbargo) {
        this.tieneEmbargo = tieneEmbargo;
    }

    public Boolean getTienePrenda() {
        return tienePrenda;
    }

    public void setTienePrenda(Boolean tienePrenda) {
        this.tienePrenda = tienePrenda;
    }

    public Boolean getEsRobado() {
        return esRobado;
    }

    public void setEsRobado(Boolean esRobado) {
        this.esRobado = esRobado;
    }

    public Boolean getTitularInhibido() {
        return titularInhibido;
    }

    public void setTitularInhibido(Boolean titularInhibido) {
        this.titularInhibido = titularInhibido;
    }

    public BigDecimal getDeudaPatentes() {
        return deudaPatentes;
    }

    public void setDeudaPatentes(BigDecimal deudaPatentes) {
        this.deudaPatentes = deudaPatentes;
    }

    public BigDecimal getDeudaMultas() {
        return deudaMultas;
    }

    public void setDeudaMultas(BigDecimal deudaMultas) {
        this.deudaMultas = deudaMultas;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public LocalDateTime getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDateTime fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
}
