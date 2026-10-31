package com.back.carfind.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Promociones {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_publicacion;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_tipo_promocion;

@Column
private Integer fecha_inicio;

@Column
private Integer fehca_fin;

@Column 
private String estado;

public Long getId_publicacion() {
	return id_publicacion;
}

public void setId_publicacion(Long id_publicacion) {
	this.id_publicacion = id_publicacion;
}

public Long getId_tipo_promocion() {
	return id_tipo_promocion;
}

public void setId_tipo_promocion(Long id_tipo_promocion) {
	this.id_tipo_promocion = id_tipo_promocion;
}

public Integer getFecha_inicio() {
	return fecha_inicio;
}

public void setFecha_inicio(Integer fecha_inicio) {
	this.fecha_inicio = fecha_inicio;
}

public Integer getFehca_fin() {
	return fehca_fin;
}

public void setFehca_fin(Integer fehca_fin) {
	this.fehca_fin = fehca_fin;
}

public String getEstado() {
	return estado;
}

public void setEstado(String estado) {
	this.estado = estado;
}

}