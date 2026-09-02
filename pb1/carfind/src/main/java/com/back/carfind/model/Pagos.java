package com.back.carfind.model;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Pagos {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_pago;
@Column
private Long id_compra;
@Column
private Long tipo_pago_id;
@Column 
private String estado;
@Column
private Integer numero_transaccion;
@Column
private Long id_externo;
@Column
private LocalDate fecha_pago;
public Long getId_pago() {
	return id_pago;
}
public void setId_pago(Long id_pago) {
	this.id_pago = id_pago;
}
public Long getId_compra() {
	return id_compra;
}
public void setId_compra(Long id_compra) {
	this.id_compra = id_compra;
}
public Long getTipo_pago_id() {
	return tipo_pago_id;
}
public void setTipo_pago_id(Long tipo_pago_id) {
	this.tipo_pago_id = tipo_pago_id;
}
public String getEstado() {
	return estado;
}
public void setEstado(String estado) {
	this.estado = estado;
}
public Integer getNumero_transaccion() {
	return numero_transaccion;
}
public void setNumero_transaccion(Integer numero_transaccion) {
	this.numero_transaccion = numero_transaccion;
}
public Long getId_externo() {
	return id_externo;
}
public void setId_externo(Long id_externo) {
	this.id_externo = id_externo;
}
public LocalDate getFecha_pago() {
	return fecha_pago;
}
public void setFecha_pago(LocalDate fecha_pago) {
	this.fecha_pago = fecha_pago;
}





}
