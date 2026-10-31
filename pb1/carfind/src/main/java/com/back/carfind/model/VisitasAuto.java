package com.back.carfind.model;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class VisitasAuto{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_visitas_auto;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_auto;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_comprador;

@Column
private LocalDate fecha;

@Column
private String resultado;

public Long getId_visitas_auto() {
	return id_visitas_auto;
}

public void setId_visitas_auto(Long id_visitas_auto) {
	this.id_visitas_auto = id_visitas_auto;
}

public Long getId_auto() {
	return id_auto;
}

public void setId_auto(Long id_auto) {
	this.id_auto = id_auto;
}

public Long getId_comprador() {
	return id_comprador;
}

public void setId_comprador(Long id_comprador) {
	this.id_comprador = id_comprador;
}

public LocalDate getFecha() {
	return fecha;
}

public void setFecha(LocalDate fecha) {
	this.fecha = fecha;
}

public String getResultado() {
	return resultado;
}

public void setResultado(String resultado) {
	this.resultado = resultado;
}

}
