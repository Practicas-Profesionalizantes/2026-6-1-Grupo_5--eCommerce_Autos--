package com.back.carfind.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Modelos {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_modelo;
@Column(nullable = false)
private String nombre_modelo;
@Column
private Long marca_id;

public Long getId_modelo() {
	return id_modelo;
}
public void setId_modelo(Long id_modelo) {
	this.id_modelo = id_modelo;
}
public String getNombre_modelo() {
	return nombre_modelo;
}
public void setNombre_modelo(String nombre_modelo) {
	this.nombre_modelo = nombre_modelo;
}
public Long getMarca_id() {
	return marca_id;
}
public void setMarca_id(Long marca_id) {
	this.marca_id = marca_id;
}


}