package com.back.carfind.model;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class PagosCuotas{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_pagos_cuotas;

public Long getId_pagos_cuotas() {
	return id_pagos_cuotas;
}

public void setId_pagos_cuotas(Long id_pagos_cuotas) {
	this.id_pagos_cuotas = id_pagos_cuotas;
}

public Long getId_pago() {
	return id_pago;
}

public void setId_pago(Long id_pago) {
	this.id_pago = id_pago;
}

public Long getId_plan() {
	return id_plan;
}

public void setId_plan(Long id_plan) {
	this.id_plan = id_plan;
}

public Integer getCantidad_cuotas() {
	return cantidad_cuotas;
}

public void setCantidad_cuotas(Integer cantidad_cuotas) {
	this.cantidad_cuotas = cantidad_cuotas;
}

public Float getInteres_aplicado() {
	return interes_aplicado;
}

public void setInteres_aplicado(Float interes_aplicado) {
	this.interes_aplicado = interes_aplicado;
}

public Float getMonto_original() {
	return monto_original;
}

public void setMonto_original(Float monto_original) {
	this.monto_original = monto_original;
}

public Float getMonto_total() {
	return monto_total;
}

public void setMonto_total(Float monto_total) {
	this.monto_total = monto_total;
}

public Float getMonto_cuota() {
	return monto_cuota;
}

public void setMonto_cuota(Float monto_cuota) {
	this.monto_cuota = monto_cuota;
}

public String getMoneda() {
	return moneda;
}

public void setMoneda(String moneda) {
	this.moneda = moneda;
}

public LocalDate getFecha_inicio() {
	return fecha_inicio;
}

public void setFecha_inicio(LocalDate fecha_inicio) {
	this.fecha_inicio = fecha_inicio;
}

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_pago;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_plan;

@Column
private Integer cantidad_cuotas;

@Column
private Float interes_aplicado;

@Column 
private Float monto_original;

@Column 
private Float monto_total;

@Column 
private Float monto_cuota;

@Column 
private String moneda;

@Column 
private LocalDate fecha_inicio;

}