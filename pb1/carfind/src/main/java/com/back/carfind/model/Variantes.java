package com.back.carfind.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Variantes {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long publicacion_id;

@Column
private String nombre;

public Long getPublicacion_id() {
	return publicacion_id;
}

public void setPublicacion_id(Long publicacion_id) {
	this.publicacion_id = publicacion_id;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

}