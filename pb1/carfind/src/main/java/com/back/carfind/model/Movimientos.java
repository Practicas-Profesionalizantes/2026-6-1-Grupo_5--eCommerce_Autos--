package com.back.carfind.model;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Movimientos{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_movimientos;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_usuario;

@Column
private String tipo;

@Column
private Float monto;

@Column 
private String descripcion;

@Column
private LocalDate fecha;

public Long getId_movimientos() {
	return id_movimientos;
}

public void setId_movimientos(Long id_movimientos) {
	this.id_movimientos = id_movimientos;
}

public Long getId_usuario() {
	return id_usuario;
}

public void setId_usuario(Long id_usuario) {
	this.id_usuario = id_usuario;
}

public String getTipo() {
	return tipo;
}

public void setTipo(String tipo) {
	this.tipo = tipo;
}

public Float getMonto() {
	return monto;
}

public void setMonto(Float monto) {
	this.monto = monto;
}

public String getDescripcion() {
	return descripcion;
}

public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}

public LocalDate getFecha() {
	return fecha;
}

public void setFecha(LocalDate fecha) {
	this.fecha = fecha;
}

}
