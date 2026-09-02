package com.back.carfind.model;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Cuotas{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_cuotas;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_pago_cuotas;

@Column
private Integer numero_cuota;

@Column
private Float monto;

@Column
private String estado;

@Column 
private LocalDate fecha_vencimiento;

@Column
private LocalDate fecha_pago;

public Long getId_cuotas() {
	return id_cuotas;
}

public void setId_cuotas(Long id_cuotas) {
	this.id_cuotas = id_cuotas;
}

public Long getId_pago_cuotas() {
	return id_pago_cuotas;
}

public void setId_pago_cuotas(Long id_pago_cuotas) {
	this.id_pago_cuotas = id_pago_cuotas;
}

public Integer getNumero_cuota() {
	return numero_cuota;
}

public void setNumero_cuota(Integer numero_cuota) {
	this.numero_cuota = numero_cuota;
}

public Float getMonto() {
	return monto;
}

public void setMonto(Float monto) {
	this.monto = monto;
}

public String getEstado() {
	return estado;
}

public void setEstado(String estado) {
	this.estado = estado;
}

public LocalDate getFecha_vencimiento() {
	return fecha_vencimiento;
}

public void setFecha_vencimiento(LocalDate fecha_vencimiento) {
	this.fecha_vencimiento = fecha_vencimiento;
}

public LocalDate getFecha_pago() {
	return fecha_pago;
}

public void setFecha_pago(LocalDate fecha_pago) {
	this.fecha_pago = fecha_pago;
}

}