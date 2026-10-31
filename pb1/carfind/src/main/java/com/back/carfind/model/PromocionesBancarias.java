package com.back.carfind.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class PromocionesBancarias{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_promociones_bancarias;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_banco;

@Column
private String tarjeta;

@Column
private Integer cuotas;

@Column
private Float interes;

@Column 
private Boolean activa;

public Long getId_promociones_bancarias() {
	return id_promociones_bancarias;
}

public void setId_promociones_bancarias(Long id_promociones_bancarias) {
	this.id_promociones_bancarias = id_promociones_bancarias;
}

public Long getId_banco() {
	return id_banco;
}

public void setId_banco(Long id_banco) {
	this.id_banco = id_banco;
}

public String getTarjeta() {
	return tarjeta;
}

public void setTarjeta(String tarjeta) {
	this.tarjeta = tarjeta;
}

public Integer getCuotas() {
	return cuotas;
}

public void setCuotas(Integer cuotas) {
	this.cuotas = cuotas;
}

public Float getInteres() {
	return interes;
}

public void setInteres(Float interes) {
	this.interes = interes;
}

public Boolean getActiva() {
	return activa;
}

public void setActiva(Boolean activa) {
	this.activa = activa;
}

}
