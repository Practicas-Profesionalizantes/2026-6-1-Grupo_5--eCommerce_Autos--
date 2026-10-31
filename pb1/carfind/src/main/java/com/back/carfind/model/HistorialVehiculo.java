package com.back.carfind.model;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class HistorialVehiculo {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_historial;
@Column
private Long id_auto;
@Column
private String tipo_historial;
@Column
private String descripcion_historial;
@Column 
private LocalDate fecha_historial;

public Long getId_historial() {
	return id_historial;
}
public void setId_historial(Long id_historial) {
	this.id_historial = id_historial;
}
public Long getId_auto() {
	return id_auto;
}
public void setId_auto(Long id_auto) {
	this.id_auto = id_auto;
}
public String getTipo_historial() {
	return tipo_historial;
}
public void setTipo_historial(String tipo_historial) {
	this.tipo_historial = tipo_historial;
}
public String getDescripcion_historial() {
	return descripcion_historial;
}
public void setDescripcion_historial(String descripcion_historial) {
	this.descripcion_historial = descripcion_historial;
}
public LocalDate getFecha_historial() {
	return fecha_historial;
}
public void setFecha_historial(LocalDate fecha_historial) {
	this.fecha_historial = fecha_historial;
}



}