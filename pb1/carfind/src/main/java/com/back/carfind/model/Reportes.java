package com.back.carfind.model;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Reportes {

@Column
private Long id_usuario;
@Column
private Long id_publicacion;
@Column
private String motivo;
@Column
private String descripcion;
@Column
private String estado;
@Column
private LocalDate fecha_reporte;
public Long getId_usuario() {
	return id_usuario;
}
public void setId_usuario(Long id_usuario) {
	this.id_usuario = id_usuario;
}
public Long getId_publicacion() {
	return id_publicacion;
}
public void setId_publicacion(Long id_publicacion) {
	this.id_publicacion = id_publicacion;
}
public String getMotivo() {
	return motivo;
}
public void setMotivo(String motivo) {
	this.motivo = motivo;
}
public String getDescripcion() {
	return descripcion;
}
public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}
public String getEstado() {
	return estado;
}
public void setEstado(String estado) {
	this.estado = estado;
}
public LocalDate getFecha_reporte() {
	return fecha_reporte;
}
public void setFecha_reporte(LocalDate fecha_reporte) {
	this.fecha_reporte = fecha_reporte;
}



}