package com.back.carfind.model;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class ModeracionMultimedia {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_moderacion_multimedia;

@Column
private String estado;

@Column
private String motivo;

@Column
private LocalDate fecha;

public Long getId_moderacion_multimedia() {
	return id_moderacion_multimedia;
}

public void setId_moderacion_multimedia(Long id_moderacion_multimedia) {
	this.id_moderacion_multimedia = id_moderacion_multimedia;
}

public String getEstado() {
	return estado;
}

public void setEstado(String estado) {
	this.estado = estado;
}

public String getMotivo() {
	return motivo;
}

public void setMotivo(String motivo) {
	this.motivo = motivo;
}

public LocalDate getFecha() {
	return fecha;
}

public void setFecha(LocalDate fecha) {
	this.fecha = fecha;
}

}