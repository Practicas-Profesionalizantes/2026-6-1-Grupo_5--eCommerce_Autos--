package com.back.carfind.model;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Resenias {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_resenia;
@Column
private Long id_comprador;
@Column
private Long id_vendedor;
@Column
private Float puntuacion;
@Column
private String comentario;
@Column
private LocalDate fecha_resenia;

public Long getId_resenia() {
	return id_resenia;
}
public void setId_resenia(Long id_resenia) {
	this.id_resenia = id_resenia;
}
public Long getId_comprador() {
	return id_comprador;
}
public void setId_comprador(Long id_comprador) {
	this.id_comprador = id_comprador;
}
public Long getId_vendedor() {
	return id_vendedor;
}
public void setId_vendedor(Long id_vendedor) {
	this.id_vendedor = id_vendedor;
}
public Float getPuntuacion() {
	return puntuacion;
}
public void setPuntuacion(Float puntuacion) {
	this.puntuacion = puntuacion;
}
public String getComentario() {
	return comentario;
}
public void setComentario(String comentario) {
	this.comentario = comentario;
}
public LocalDate getFecha_resenia() {
	return fecha_resenia;
}
public void setFecha_resenia(LocalDate fecha_resenia) {
	this.fecha_resenia = fecha_resenia;
}




}
