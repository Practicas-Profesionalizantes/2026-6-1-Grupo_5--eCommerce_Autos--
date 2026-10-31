package com.back.carfind.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Billeteras{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_billeteras;


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_usuario;

@Column
private Float saldo;

public Long getId_billeteras() {
	return id_billeteras;
}

public void setId_billeteras(Long id_billeteras) {
	this.id_billeteras = id_billeteras;
}

public Long getId_usuario() {
	return id_usuario;
}

public void setId_usuario(Long id_usuario) {
	this.id_usuario = id_usuario;
}

public Float getSaldo() {
	return saldo;
}

public void setSaldo(Float saldo) {
	this.saldo = saldo;
}

}
