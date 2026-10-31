package com.back.carfind.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class TarjetasUsuario {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_tarjetas_usuario;

@Column
private String marca;

@Column 
private String ultimos4;

@Column
private Long token;

@Column
private Integer vencimiento_mes;

@Column 
private Integer vencmiento_anio;

public Long getId_tarjetas_usuario() {
	return id_tarjetas_usuario;
}

public void setId_tarjetas_usuario(Long id_tarjetas_usuario) {
	this.id_tarjetas_usuario = id_tarjetas_usuario;
}

public String getMarca() {
	return marca;
}

public void setMarca(String marca) {
	this.marca = marca;
}

public String getUltimos4() {
	return ultimos4;
}

public void setUltimos4(String ultimos4) {
	this.ultimos4 = ultimos4;
}

public Long getToken() {
	return token;
}

public void setToken(Long token) {
	this.token = token;
}

public Integer getVencimiento_mes() {
	return vencimiento_mes;
}

public void setVencimiento_mes(Integer vencimiento_mes) {
	this.vencimiento_mes = vencimiento_mes;
}

public Integer getVencmiento_anio() {
	return vencmiento_anio;
}

public void setVencmiento_anio(Integer vencmiento_anio) {
	this.vencmiento_anio = vencmiento_anio;
}


}