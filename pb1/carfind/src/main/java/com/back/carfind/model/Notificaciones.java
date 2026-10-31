package com.back.carfind.model;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Notificaciones {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_notificacion;
@Column(nullable = false)
private Long usuario_id;
@Column
private String tipo;
@Column
private String mensaje;
@Column
private Boolean leido;
@Column
private Long id_entidad;
@Column
private LocalDate fecha_notificacion;

public Long getId_notificacion() {
	return id_notificacion;
}
public void setId_notificacion(Long id_notificacion) {
	this.id_notificacion = id_notificacion;
}
public Long getUsuario_id() {
	return usuario_id;
}
public void setUsuario_id(Long usuario_id) {
	this.usuario_id = usuario_id;
}
public String getTipo() {
	return tipo;
}
public void setTipo(String tipo) {
	this.tipo = tipo;
}
public String getMensaje() {
	return mensaje;
}
public void setMensaje(String mensaje) {
	this.mensaje = mensaje;
}
public Boolean getLeido() {
	return leido;
}
public void setLeido(Boolean leido) {
	this.leido = leido;
}
public Long getId_entidad() {
	return id_entidad;
}
public void setId_entidad(Long id_entidad) {
	this.id_entidad = id_entidad;
}
public LocalDate getFecha_notificacion() {
	return fecha_notificacion;
}
public void setFecha_notificacion(LocalDate fecha_notificacion) {
	this.fecha_notificacion = fecha_notificacion;
}


}