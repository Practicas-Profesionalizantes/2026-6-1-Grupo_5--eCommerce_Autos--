package com.back.carfind.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class VariantesOpciones {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long variante_id;

public Long getVariante_id() {
	return variante_id;
}

public void setVariante_id(Long variante_id) {
	this.variante_id = variante_id;
}

public String getValor() {
	return valor;
}

public void setValor(String valor) {
	this.valor = valor;
}

@Column
private String valor;

}