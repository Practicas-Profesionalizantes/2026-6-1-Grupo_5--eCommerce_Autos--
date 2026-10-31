package com.back.carfind.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Carrito {

@Column
private Long id_usuario ;
@Column
private Long id_publicacion ;
@Column
private Integer cantidad ;
@Column 
private Float precio_total;

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
public Integer getCantidad() {
	return cantidad;
}
public void setCantidad(Integer cantidad) {
	this.cantidad = cantidad;
}
public Float getPrecio_total() {
	return precio_total;
}
public void setPrecio_total(Float precio_total) {
	this.precio_total = precio_total;
}





}
