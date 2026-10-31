package com.back.carfind.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;


@Entity
public class Publicaciones {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_publicacion;
@Column
private Long id_usuario;
@Column
private Long id_auto;
@Column
private Double precio;
@Column
private String moneda;
@Column
private String estado;
@Column
private Double score;
@Column
private Boolean destacado;
@Column
private Integer visitas;
@Column
private String descripcion;
@Column
private LocalDate fecha_publicacion;
@Column
private LocalDate fecha_expiracion;
public Long getId_publicacion() {
	return id_publicacion;
}
public void setId_publicacion(Long id_publicacion) {
	this.id_publicacion = id_publicacion;
}
public Long getId_usuario() {
	return id_usuario;
}
public void setId_usuario(Long id_usuario) {
	this.id_usuario = id_usuario;
}
public Long getId_auto() {
	return id_auto;
}
public void setId_auto(Long id_auto) {
	this.id_auto = id_auto;
}
public Double getPrecio() {
	return precio;
}
public void setPrecio(Double precio) {
	this.precio = precio;
}
public String getMoneda() {
	return moneda;
}
public void setMoneda(String moneda) {
	this.moneda = moneda;
}
public String getEstado() {
	return estado;
}
public void setEstado(String estado) {
	this.estado = estado;
}
public Double getScore() {
	return score;
}
public void setScore(Double score) {
	this.score = score;
}
public Boolean getDestacado() {
	return destacado;
}
public void setDestacado(Boolean destacado) {
	this.destacado = destacado;
}
public Integer getVisitas() {
	return visitas;
}
public void setVisitas(Integer visitas) {
	this.visitas = visitas;
}
public String getDescripcion() {
	return descripcion;
}
public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}
public LocalDate getFecha_publicacion() {
	return fecha_publicacion;
}
public void setFecha_publicacion(LocalDate fecha_publicacion) {
	this.fecha_publicacion = fecha_publicacion;
}
public LocalDate getFecha_expiracion() {
	return fecha_expiracion;
}
public void setFecha_expiracion(LocalDate fecha_expiracion) {
	this.fecha_expiracion = fecha_expiracion;
}



}