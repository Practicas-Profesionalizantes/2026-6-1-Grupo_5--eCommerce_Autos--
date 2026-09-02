package com.back.carfind.model;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Autos {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_auto;
@Column
private Long modelo_id ;
@Column
private Double version;
@Column
private Integer anio;
@Column
private Integer kms;
@Column
private Boolean vtv;
@Column 
private Boolean grabado;
@Column
private Long combustible_id;
@Column
private Long transmision_id;
@Column
private String color;
@Column
private Integer puertas;
@Column
private String descripcion;
@Column
private LocalDate fecha_creacion;

public Long getId_auto() {
	return id_auto;
}
public void setId_auto(Long id_auto) {
	this.id_auto = id_auto;
}
public Long getModelo_id() {
	return modelo_id;
}
public void setModelo_id(Long modelo_id) {
	this.modelo_id = modelo_id;
}
public Double getVersion() {
	return version;
}
public void setVersion(Double version) {
	this.version = version;
}
public Integer getAnio() {
	return anio;
}
public void setAnio(Integer anio) {
	this.anio = anio;
}
public Integer getKms() {
	return kms;
}
public void setKms(Integer kms) {
	this.kms = kms;
}
public Boolean getVtv() {
	return vtv;
}
public void setVtv(Boolean vtv) {
	this.vtv = vtv;
}
public Boolean getGrabado() {
	return grabado;
}
public void setGrabado(Boolean grabado) {
	this.grabado = grabado;
}
public Long getCombustible_id() {
	return combustible_id;
}
public void setCombustible_id(Long combustible_id) {
	this.combustible_id = combustible_id;
}
public Long getTransmision_id() {
	return transmision_id;
}
public void setTransmision_id(Long transmision_id) {
	this.transmision_id = transmision_id;
}
public String getColor() {
	return color;
}
public void setColor(String color) {
	this.color = color;
}
public Integer getPuertas() {
	return puertas;
}
public void setPuertas(Integer puertas) {
	this.puertas = puertas;
}
public String getDescripcion() {
	return descripcion;
}
public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}
public LocalDate getFecha_creacion() {
	return fecha_creacion;
}
public void setFecha_creacion(LocalDate fecha_creacion) {
	this.fecha_creacion = fecha_creacion;
}



}