package com.back.carfind.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Transmisiones {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_transmision;
@Column(nullable = false)
private String nombre_transmision;

public Long getId_transmision() {
	return id_transmision;
}
public void setId_transmision(Long id_transmision) {
	this.id_transmision = id_transmision;
}
public String getNombre_transmision() {
	return nombre_transmision;
}
public void setNombre_transmision(String nombre_transmision) {
	this.nombre_transmision = nombre_transmision;
}


}