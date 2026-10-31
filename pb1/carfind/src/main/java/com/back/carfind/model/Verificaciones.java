package com.back.carfind.model;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Verificaciones {

@Column
private Long id_usuario;
@Column
private String tipo_verificacion;
@Column
private String estado_verificacion;
@Column
private LocalDate fecha_verificaion;

public Long getId_usuario() {
	return id_usuario;
}
public void setId_usuario(Long id_usuario) {
	this.id_usuario = id_usuario;
}
public String getTipo_verificacion() {
	return tipo_verificacion;
}
public void setTipo_verificacion(String tipo_verificacion) {
	this.tipo_verificacion = tipo_verificacion;
}
public String getEstado_verificacion() {
	return estado_verificacion;
}
public void setEstado_verificacion(String estado_verificacion) {
	this.estado_verificacion = estado_verificacion;
}
public LocalDate getFecha_verificaion() {
	return fecha_verificaion;
}
public void setFecha_verificaion(LocalDate fecha_verificaion) {
	this.fecha_verificaion = fecha_verificaion;
}





}