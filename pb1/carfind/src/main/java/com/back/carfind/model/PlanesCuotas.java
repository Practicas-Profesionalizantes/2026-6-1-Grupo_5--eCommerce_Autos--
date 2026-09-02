package com.back.carfind.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class PlanesCuotas {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_planes_cuotas;

@Column
private Integer cantidad_cuotas;

@Column
private Float interes;

@Column
private String descripcion;

public Long getId_planes_cuotas() {
	return id_planes_cuotas;
}

public void setId_planes_cuotas(Long id_planes_cuotas) {
	this.id_planes_cuotas = id_planes_cuotas;
}

public Integer getCantidad_cuotas() {
	return cantidad_cuotas;
}

public void setCantidad_cuotas(Integer cantidad_cuotas) {
	this.cantidad_cuotas = cantidad_cuotas;
}

public Float getInteres() {
	return interes;
}

public void setInteres(Float interes) {
	this.interes = interes;
}

public String getDescripcion() {
	return descripcion;
}

public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}

}