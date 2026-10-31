package com.back.carfind.model;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class PagosPromocion {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_pagos_promocion;

@Column
private Float monto;

@Column
private String estado;

@Column
private LocalDate fecha;

public Long getId_pagos_promocion() {
	return id_pagos_promocion;
}

public void setId_pagos_promocion(Long id_pagos_promocion) {
	this.id_pagos_promocion = id_pagos_promocion;
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

public LocalDate getFecha() {
	return fecha;
}

public void setFecha(LocalDate fecha) {
	this.fecha = fecha;
}

}