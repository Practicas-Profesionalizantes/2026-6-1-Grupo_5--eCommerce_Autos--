 package com.back.carfind.model;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Direcciones {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_direccion;
@Column
private String pais;
@Column
private String provincia;
@Column
private String ciudad;
@Column
private String direccion;
@Column
private String tipo;
@Column
private Boolean es_principal;
@Column
private LocalDate fecha_creacion_direccion;
public Long getId_direccion() {
	return id_direccion;
}
public void setId_direccion(Long id_direccion) {
	this.id_direccion = id_direccion;
}
public String getPais() {
	return pais;
}
public void setPais(String pais) {
	this.pais = pais;
}
public String getProvincia() {
	return provincia;
}
public void setProvincia(String provincia) {
	this.provincia = provincia;
}
public String getCiudad() {
	return ciudad;
}
public void setCiudad(String ciudad) {
	this.ciudad = ciudad;
}
public String getDireccion() {
	return direccion;
}
public void setDireccion(String direccion) {
	this.direccion = direccion;
}
public String getTipo() {
	return tipo;
}
public void setTipo(String tipo) {
	this.tipo = tipo;
}
public Boolean getEs_principal() {
	return es_principal;
}
public void setEs_principal(Boolean es_principal) {
	this.es_principal = es_principal;
}
public LocalDate getFecha_creacion_direccion() {
	return fecha_creacion_direccion;
}
public void setFecha_creacion_direccion(LocalDate fecha_creacion_direccion) {
	this.fecha_creacion_direccion = fecha_creacion_direccion;
}

}