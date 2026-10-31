package com.back.carfind.model;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Reembolsos{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_reembolsos;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_pago;

@Column
private Float monto;

@Column 
private String motivo;

@Column
private LocalDate fecha;

public Long getId_reembolsos() {
	return id_reembolsos;
}

public void setId_reembolsos(Long id_reembolsos) {
	this.id_reembolsos = id_reembolsos;
}

public Long getId_pago() {
	return id_pago;
}

public void setId_pago(Long id_pago) {
	this.id_pago = id_pago;
}

public Float getMonto() {
	return monto;
}

public void setMonto(Float monto) {
	this.monto = monto;
}

public String getMotivo() {
	return motivo;
}

public void setMotivo(String motivo) {
	this.motivo = motivo;
}

public LocalDate getFecha() {
	return fecha;
}

public void setFecha(LocalDate fecha) {
	this.fecha = fecha;
}


}
