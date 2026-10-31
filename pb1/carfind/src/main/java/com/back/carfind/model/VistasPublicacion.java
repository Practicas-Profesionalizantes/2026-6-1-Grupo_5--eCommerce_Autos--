package com.back.carfind.model;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class VistasPublicacion {

@Column
private Long id_usuario;
@Column
private Long id_publicacion;
@Column
private LocalDate fecha_vista;
public Long getId_usuario() {
	return id_usuario;
}
public void setId_usuario(Long id_usuario) {
	this.id_usuario = id_usuario;
}
public Long getId_publicacion() {
	return id_publicacion;
}
public void setId_publicacion(Long id_publicacion) {
	this.id_publicacion = id_publicacion;
}
public LocalDate getFecha_vista() {
	return fecha_vista;
}
public void setFecha_vista(LocalDate fecha_vista) {
	this.fecha_vista = fecha_vista;
}





}
