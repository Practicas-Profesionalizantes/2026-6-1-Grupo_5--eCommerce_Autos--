package com.back.carfind.model;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Compras {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_compra;
@Column
private Long id_comprador;
@Column
private Long id_publicacion;
@Column 
private Float precio_final;
@Column
private String estado;
@Column
private LocalDate fecha_compra;


public Long getId_compra() {
	return id_compra;
}
public void setId_compra(Long id_compra) {
	this.id_compra = id_compra;
}
public Long getId_comprador() {
	return id_comprador;
}
public void setId_comprador(Long id_comprador) {
	this.id_comprador = id_comprador;
}
public Long getId_publicacion() {
	return id_publicacion;
}
public void setId_publicacion(Long id_publicacion) {
	this.id_publicacion = id_publicacion;
}
public Float getPrecio_final() {
	return precio_final;
}
public void setPrecio_final(Float precio_final) {
	this.precio_final = precio_final;
}
public String getEstado() {
	return estado;
}
public void setEstado(String estado) {
	this.estado = estado;
}
public LocalDate getFecha_compra() {
	return fecha_compra;
}
public void setFecha_compra(LocalDate fecha_compra) {
	this.fecha_compra = fecha_compra;
}





}
