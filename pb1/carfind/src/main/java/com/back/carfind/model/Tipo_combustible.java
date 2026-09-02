package com.back.carfind.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Tipo_combustible{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_combustible;
@Column(nullable = false)
private String nombre_combustible;

public Long getId_combustible() {
	return id_combustible;
}
public void setId_combustible(Long id_combustible) {
	this.id_combustible = id_combustible;
}
public String getNombre_combustible() {
	return nombre_combustible;
}
public void setNombre_combustible(String nombre_combustible) {
	this.nombre_combustible = nombre_combustible;
}


}