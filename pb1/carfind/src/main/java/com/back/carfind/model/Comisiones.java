package com.back.carfind.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Comisiones {

@Column
private Long id_compra;
@Column
private Float porcentaje;
@Column
private Float monto;


public Long getId_compra() {
	return id_compra;
}
public void setId_compra(Long id_compra) {
	this.id_compra = id_compra;
}
public Float getPorcentaje() {
	return porcentaje;
}
public void setPorcentaje(Float porcentaje) {
	this.porcentaje = porcentaje;
}
public Float getMonto() {
	return monto;
}
public void setMonto(Float monto) {
	this.monto = monto;
}


}