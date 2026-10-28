package com.back.carfind.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "informe_dominio")
public class InformeDominio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "auto_id", nullable = false)
    private Long autoId;

    @Column(name = "dominio", nullable = false, length = 10)
    private String dominio;

    @Column(name = "numero_chasis", length = 50)
    private String numeroChasis;

    @Column(name = "numero_motor", length = 50)
    private String numeroMotor;

    @Column(name = "titular_actual", length = 150)
    private String titularActual;

    @Column(name = "cantidad_titulares")
    private Integer cantidadTitulares;

    @Column(name = "fecha_inscripcion")
    private LocalDate fechaInscripcion;

    @Column(name = "tiene_embargo")
    private Boolean tieneEmbargo;

    @Column(name = "tiene_prenda")
    private Boolean tienePrenda;

    @Column(name = "es_robado")
    private Boolean esRobado;

    @Column(name = "titular_inhibido")
    private Boolean titularInhibido;

    @Column(name = "deuda_patentes")
    private BigDecimal deudaPatentes;

    @Column(name = "deuda_multas")
    private BigDecimal deudaMultas;

    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDateTime fechaEmision;

    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDateTime fechaVencimiento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
