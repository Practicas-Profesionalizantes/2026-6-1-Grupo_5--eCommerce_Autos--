package com.back.carfind.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class TiposPromocion {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_tipos_promocion;

@Column
private String nombre;

@Column 
private Integer prioridad;

@Column
private Integer duracion_dias;

@Column
private Float precio;

public Long getId_tipos_promocion() {
	return id_tipos_promocion;
}

public void setId_tipos_promocion(Long id_tipos_promocion) {
	this.id_tipos_promocion = id_tipos_promocion;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public Integer getPrioridad() {
	return prioridad;
}

public void setPrioridad(Integer prioridad) {
	this.prioridad = prioridad;
}

public Integer getDuracion_dias() {
	return duracion_dias;
}

public void setDuracion_dias(Integer duracion_dias) {
	this.duracion_dias = duracion_dias;
}

public Float getPrecio() {
	return precio;
}

public void setPrecio(Float precio) {
	this.precio = precio;
}

}