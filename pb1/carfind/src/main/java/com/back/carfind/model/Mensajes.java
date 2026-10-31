package com.back.carfind.model;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Mensajes {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_mensaje;
@Column
private Long id_chat;
@Column
private Long id_emisor;
@Column
private String contenido;
@Column
private LocalDate fecha;
@Column
private Boolean leido;
public Long getId_mensaje() {
	return id_mensaje;
}
public void setId_mensaje(Long id_mensaje) {
	this.id_mensaje = id_mensaje;
}
public Long getId_chat() {
	return id_chat;
}
public void setId_chat(Long id_chat) {
	this.id_chat = id_chat;
}
public Long getId_emisor() {
	return id_emisor;
}
public void setId_emisor(Long id_emisor) {
	this.id_emisor = id_emisor;
}
public String getContenido() {
	return contenido;
}
public void setContenido(String contenido) {
	this.contenido = contenido;
}
public LocalDate getFecha() {
	return fecha;
}
public void setFecha(LocalDate fecha) {
	this.fecha = fecha;
}
public Boolean getLeido() {
	return leido;
}
public void setLeido(Boolean leido) {
	this.leido = leido;
}







}
