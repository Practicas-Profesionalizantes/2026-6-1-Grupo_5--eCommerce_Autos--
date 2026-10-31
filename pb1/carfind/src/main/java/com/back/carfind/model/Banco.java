package com.back.carfind.model;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Banco{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_banco;

@Column
private String nombre;

@Column
private String codigo;

@Column 
private Boolean activo;

@Column
private LocalDate fecha_creacion;

public Long getId_banco() {
	return id_banco;
}

public void setId_banco(Long id_banco) {
	this.id_banco = id_banco;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getCodigo() {
	return codigo;
}

public void setCodigo(String codigo) {
	this.codigo = codigo;
}

public Boolean getActivo() {
	return activo;
}

public void setActivo(Boolean activo) {
	this.activo = activo;
}

public LocalDate getFecha_creacion() {
	return fecha_creacion;
}

public void setFecha_creacion(LocalDate fecha_creacion) {
	this.fecha_creacion = fecha_creacion;
}

}
