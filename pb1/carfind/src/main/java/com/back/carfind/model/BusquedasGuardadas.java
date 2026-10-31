package com.back.carfind.model;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class BusquedasGuardadas {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long usuario_id;

@Column 
private String query;

@Column
private LocalDate fecha;

public Long getUsuario_id() {
	return usuario_id;
}

public void setUsuario_id(Long usuario_id) {
	this.usuario_id = usuario_id;
}

public String getQuery() {
	return query;
}

public void setQuery(String query) {
	this.query = query;
}

public LocalDate getFecha() {
	return fecha;
}

public void setFecha(LocalDate fecha) {
	this.fecha = fecha;
}

}