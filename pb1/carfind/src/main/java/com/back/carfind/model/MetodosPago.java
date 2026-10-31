package com.back.carfind.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class MetodosPago {

@Column
private Long id_usuario;
@Column
private String datos_tokenizados;
@Column
private Boolean activo_metodo;
@Column
private Long id_tipo_pago;

public Long getId_usuario() {
	return id_usuario;
}
public void setId_usuario(Long id_usuario) {
	this.id_usuario = id_usuario;
}
public String getDatos_tokenizados() {
	return datos_tokenizados;
}
public void setDatos_tokenizados(String datos_tokenizados) {
	this.datos_tokenizados = datos_tokenizados;
}
public Boolean getActivo_metodo() {
	return activo_metodo;
}
public void setActivo_metodo(Boolean activo_metodo) {
	this.activo_metodo = activo_metodo;
}
public Long getId_tipo_pago() {
	return id_tipo_pago;
}
public void setId_tipo_pago(Long id_tipo_pago) {
	this.id_tipo_pago = id_tipo_pago;
}




}