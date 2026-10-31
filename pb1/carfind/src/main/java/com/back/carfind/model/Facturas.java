package com.back.carfind.model;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Facturas {

@Column
private Long id_usuario;
@Column
private Long id_compra;
@Column
private Float total_factura;
@Column
private LocalDate fehca_factura;

public Long getId_usuario() {
	return id_usuario;
}
public void setId_usuario(Long id_usuario) {
	this.id_usuario = id_usuario;
}
public Long getId_compra() {
	return id_compra;
}
public void setId_compra(Long id_compra) {
	this.id_compra = id_compra;
}
public Float getTotal_factura() {
	return total_factura;
}
public void setTotal_factura(Float total_factura) {
	this.total_factura = total_factura;
}
public LocalDate getFehca_factura() {
	return fehca_factura;
}
public void setFehca_factura(LocalDate fehca_factura) {
	this.fehca_factura = fehca_factura;
}



}