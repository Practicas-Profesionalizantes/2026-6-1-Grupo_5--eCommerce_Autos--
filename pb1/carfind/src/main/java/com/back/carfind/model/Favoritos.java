package com.back.carfind.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity
public class Favoritos {
	
@Id
@GeneratedValue
private Long id_usuario;
@Column
private Long id_publicacion;

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



}