package com.back.carfind.model;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Ofertas{

@Column
private Long id_publicacion;
@Column
private Long id_comprador;
@Column
private Float monto_reporte;
@Column
private String estado;
@Column 
private LocalDate fecha_oferta;

public Long getId_publicacion() {
	return id_publicacion;
}
public void setId_publicacion(Long id_publicacion) {
	this.id_publicacion = id_publicacion;
}
public Long getId_comprador() {
	return id_comprador;
}
public void setId_comprador(Long id_comprador) {
	this.id_comprador = id_comprador;
}
public Float getMonto_reporte() {
	return monto_reporte;
}
public void setMonto_reporte(Float monto_reporte) {
	this.monto_reporte = monto_reporte;
}
public String getEstado() {
	return estado;
}
public void setEstado(String estado) {
	this.estado = estado;
}
public LocalDate getFecha_oferta() {
	return fecha_oferta;
}
public void setFecha_oferta(LocalDate fecha_oferta) {
	this.fecha_oferta = fecha_oferta;
}








}