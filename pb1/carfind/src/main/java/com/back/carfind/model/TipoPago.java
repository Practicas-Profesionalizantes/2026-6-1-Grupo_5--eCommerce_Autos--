package com.back.carfind.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class TipoPago {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_tipo_pago;
@Column
private String nombre_tipo_pago;


public Long getId_tipo_pago() {
	return id_tipo_pago;
}
public void setId_tipo_pago(Long id_tipo_pago) {
	this.id_tipo_pago = id_tipo_pago;
}
public String getNombre_tipo_pago() {
	return nombre_tipo_pago;
}
public void setNombre_tipo_pago(String nombre_tipo_pago) {
	this.nombre_tipo_pago = nombre_tipo_pago;
}




}