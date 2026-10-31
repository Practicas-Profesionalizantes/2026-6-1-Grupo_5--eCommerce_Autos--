package com.back.carfind.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;


@Entity
public class Informe_dominio {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_informe;
@Column
private Long auto;
@Column
private String dominio;
@Column
private Integer numero_chasis;
@Column
private Integer numero_motor;
@Column
private String titular_actual;
@Column
private Integer cantidad_titulares;
@Column
private LocalDate fecha_inscripcion;
@Column
private Boolean tiene_embargo;
@Column
private Boolean tiene_prenda;
@Column
private Boolean es_robado;
@Column
private Boolean titular_inhibido;
@Column
private Double deuda_patentes;
@Column
private Double deuda_multas;
@Column
private String observaciones;
@Column
private LocalDate fecha_emision;
@Column
private LocalDate fecha_vencimiento;
public Long getId_informe() {
	return id_informe;
}
public void setId_informe(Long id_informe) {
	this.id_informe = id_informe;
}
public Long getAuto() {
	return auto;
}
public void setAuto(Long auto) {
	this.auto = auto;
}
public String getDominio() {
	return dominio;
}
public void setDominio(String dominio) {
	this.dominio = dominio;
}
public Integer getNumero_chasis() {
	return numero_chasis;
}
public void setNumero_chasis(Integer numero_chasis) {
	this.numero_chasis = numero_chasis;
}
public Integer getNumero_motor() {
	return numero_motor;
}
public void setNumero_motor(Integer numero_motor) {
	this.numero_motor = numero_motor;
}
public String getTitular_actual() {
	return titular_actual;
}
public void setTitular_actual(String titular_actual) {
	this.titular_actual = titular_actual;
}
public Integer getCantidad_titulares() {
	return cantidad_titulares;
}
public void setCantidad_titulares(Integer cantidad_titulares) {
	this.cantidad_titulares = cantidad_titulares;
}
public LocalDate getFecha_inscripcion() {
	return fecha_inscripcion;
}
public void setFecha_inscripcion(LocalDate fecha_inscripcion) {
	this.fecha_inscripcion = fecha_inscripcion;
}
public Boolean getTiene_embargo() {
	return tiene_embargo;
}
public void setTiene_embargo(Boolean tiene_embargo) {
	this.tiene_embargo = tiene_embargo;
}
public Boolean getTiene_prenda() {
	return tiene_prenda;
}
public void setTiene_prenda(Boolean tiene_prenda) {
	this.tiene_prenda = tiene_prenda;
}
public Boolean getEs_robado() {
	return es_robado;
}
public void setEs_robado(Boolean es_robado) {
	this.es_robado = es_robado;
}
public Boolean getTitular_inhibido() {
	return titular_inhibido;
}
public void setTitular_inhibido(Boolean titular_inhibido) {
	this.titular_inhibido = titular_inhibido;
}
public Double getDeuda_patentes() {
	return deuda_patentes;
}
public void setDeuda_patentes(Double deuda_patentes) {
	this.deuda_patentes = deuda_patentes;
}
public Double getDeuda_multas() {
	return deuda_multas;
}
public void setDeuda_multas(Double deuda_multas) {
	this.deuda_multas = deuda_multas;
}
public String getObservaciones() {
	return observaciones;
}
public void setObservaciones(String observaciones) {
	this.observaciones = observaciones;
}
public LocalDate getFecha_emision() {
	return fecha_emision;
}
public void setFecha_emision(LocalDate fecha_emision) {
	this.fecha_emision = fecha_emision;
}
public LocalDate getFecha_vencimiento() {
	return fecha_vencimiento;
}
public void setFecha_vencimiento(LocalDate fecha_vencimiento) {
	this.fecha_vencimiento = fecha_vencimiento;
}



}