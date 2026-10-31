package com.back.carfind.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;


@Entity
public class ActividadUsuario {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_actividadUsuario;
@Column
private String accion;
@Column
private String descripcion;
@Column
private LocalDate Fecha_accion;


public Long getId_actividadUsuario() {
	return id_actividadUsuario;
}
public void setId_actividadUsuario(Long id_actividadUsuario) {
	this.id_actividadUsuario = id_actividadUsuario;
}
public String getAccion() {
	return accion;
}
public void setAccion(String accion) {
	this.accion = accion;
}
public String getDescripcion() {
	return descripcion;
}
public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}
public LocalDate getFecha_accion() {
	return Fecha_accion;
}
public void setFecha_accion(LocalDate fecha_accion) {
	Fecha_accion = fecha_accion;
}
	

	
	
}