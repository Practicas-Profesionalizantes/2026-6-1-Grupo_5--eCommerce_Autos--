package com.back.carfind.model;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class HistorialPublicaciones {

@Column
private Long id_usuario;
@Column
private String estado;
@Column
private LocalDate fecha_publicacion;
public Long getId_usuario() {
	return id_usuario;
}
public void setId_usuario(Long id_usuario) {
	this.id_usuario = id_usuario;
}
public String getEstado() {
	return estado;
}
public void setEstado(String estado) {
	this.estado = estado;
}
public LocalDate getFecha_publicacion() {
	return fecha_publicacion;
}
public void setFecha_publicacion(LocalDate fecha_publicacion) {
	this.fecha_publicacion = fecha_publicacion;
}




}