package com.back.carfind.model;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Sesiones {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_sesion;
@Column
private Long id_usuario;
@Column
private String token;
@Column
private LocalDate fecha_expiracion;

public Long getId_sesion() {
	return id_sesion;
}
public void setId_sesion(Long id_sesion) {
	this.id_sesion = id_sesion;
}
public Long getId_usuario() {
	return id_usuario;
}
public void setId_usuario(Long id_usuario) {
	this.id_usuario = id_usuario;
}
public String getToken() {
	return token;
}
public void setToken(String token) {
	this.token = token;
}
public LocalDate getFecha_expiracion() {
	return fecha_expiracion;
}
public void setFecha_expiracion(LocalDate fecha_expiracion) {
	this.fecha_expiracion = fecha_expiracion;
}
}

