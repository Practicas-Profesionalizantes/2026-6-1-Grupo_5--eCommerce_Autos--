package com.back.carfind.model;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class HistorialPrecios{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_historial_precios;
@Column
private Long id_publicacion;
@Column
private Float precio;
@Column
private LocalDate fecha_precio;


public Long getId_historial_precios() {
	return id_historial_precios;
}
public void setId_historial_precios(Long id_historial_precios) {
	this.id_historial_precios = id_historial_precios;
}
public Long getId_publicacion() {
	return id_publicacion;
}
public void setId_publicacion(Long id_publicacion) {
	this.id_publicacion = id_publicacion;
}
public Float getPrecio() {
	return precio;
}
public void setPrecio(Float precio) {
	this.precio = precio;
}
public LocalDate getFecha_precio() {
	return fecha_precio;
}
public void setFecha_precio(LocalDate fecha_precio) {
	this.fecha_precio = fecha_precio;
}



}
