package com.back.carfind.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;


@Entity
public class Preguntas {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_pregunta;
@Column
private Long id_publicacion;
@Column
private Long id_usaurio;
@Column
private String descripcion;
@Column
private LocalDate fecha_pregunta;

public Long getId_pregunta() {
	return id_pregunta;
}
public void setId_pregunta(Long id_pregunta) {
	this.id_pregunta = id_pregunta;
}
public Long getId_publicacion() {
	return id_publicacion;
}
public void setId_publicacion(Long id_publicacion) {
	this.id_publicacion = id_publicacion;
}
public Long getId_usaurio() {
	return id_usaurio;
}
public void setId_usaurio(Long id_usaurio) {
	this.id_usaurio = id_usaurio;
}
public String getDescripcion() {
	return descripcion;
}
public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}
public LocalDate getFecha_pregunta() {
	return fecha_pregunta;
}
public void setFecha_pregunta(LocalDate fecha_pregunta) {
	this.fecha_pregunta = fecha_pregunta;
}



}