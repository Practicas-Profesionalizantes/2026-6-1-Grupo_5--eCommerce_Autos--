package com.back.carfind.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;


@Entity
public class Multimedia {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_multimedia;
@Column
private Long id_publicacion;
@Column
private String url;
@Column
private String tipo;
@Column
private Integer orden;
@Column
private LocalDate fecha_creacion;

public Long getId_multimedia() {
	return id_multimedia;
}
public void setId_multimedia(Long id_multimedia) {
	this.id_multimedia = id_multimedia;
}
public Long getId_publicacion() {
	return id_publicacion;
}
public void setId_publicacion(Long id_publicacion) {
	this.id_publicacion = id_publicacion;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public String getTipo() {
	return tipo;
}
public void setTipo(String tipo) {
	this.tipo = tipo;
}
public Integer getOrden() {
	return orden;
}
public void setOrden(Integer orden) {
	this.orden = orden;
}
public LocalDate getFecha_creacion() {
	return fecha_creacion;
}
public void setFecha_creacion(LocalDate fecha_creacion) {
	this.fecha_creacion = fecha_creacion;
}




}