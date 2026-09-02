package com.back.carfind.model;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Encuentros{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_encuentros;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_compra;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_vendedor;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_comprador;

@Column
private LocalDate fecha_programada;

@Column
private String lugar;

@Column
private String estado;

@Column
private String notas;

@Column 
private LocalDate fecha_creacion;

public Long getId_encuentros() {
	return id_encuentros;
}

public void setId_encuentros(Long id_encuentros) {
	this.id_encuentros = id_encuentros;
}

public Long getId_compra() {
	return id_compra;
}

public void setId_compra(Long id_compra) {
	this.id_compra = id_compra;
}

public Long getId_vendedor() {
	return id_vendedor;
}

public void setId_vendedor(Long id_vendedor) {
	this.id_vendedor = id_vendedor;
}

public Long getId_comprador() {
	return id_comprador;
}

public void setId_comprador(Long id_comprador) {
	this.id_comprador = id_comprador;
}

public LocalDate getFecha_programada() {
	return fecha_programada;
}

public void setFecha_programada(LocalDate fecha_programada) {
	this.fecha_programada = fecha_programada;
}

public String getLugar() {
	return lugar;
}

public void setLugar(String lugar) {
	this.lugar = lugar;
}

public String getEstado() {
	return estado;
}

public void setEstado(String estado) {
	this.estado = estado;
}

public String getNotas() {
	return notas;
}

public void setNotas(String notas) {
	this.notas = notas;
}

public LocalDate getFecha_creacion() {
	return fecha_creacion;
}

public void setFecha_creacion(LocalDate fecha_creacion) {
	this.fecha_creacion = fecha_creacion;
}

}