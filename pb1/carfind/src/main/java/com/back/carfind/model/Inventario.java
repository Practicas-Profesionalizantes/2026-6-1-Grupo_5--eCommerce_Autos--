package com.back.carfind.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Inventario {

@Id
private Long publicacion_id;

@Column
private Integer stock;

public Long getPublicacion_id() {
	return publicacion_id;
}

public void setPublicacion_id(Long publicacion_id) {
	this.publicacion_id = publicacion_id;
}

public Integer getStock() {
	return stock;
}

public void setStock(Integer stock) {
	this.stock = stock;
}

}