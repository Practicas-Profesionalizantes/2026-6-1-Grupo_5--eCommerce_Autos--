package com.back.carfind.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class EstadisticasPublicacion {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_publicacion;

@Column
private Integer vistas;

@Column
private Integer clicks;

@Column
private Integer favoritos;

@Column
private Integer contactos;

@Column
private Integer compartidos;

public Long getId_publicacion() {
	return id_publicacion;
}

public void setId_publicacion(Long id_publicacion) {
	this.id_publicacion = id_publicacion;
}

public Integer getVistas() {
	return vistas;
}

public void setVistas(Integer vistas) {
	this.vistas = vistas;
}

public Integer getClicks() {
	return clicks;
}

public void setClicks(Integer clicks) {
	this.clicks = clicks;
}

public Integer getFavoritos() {
	return favoritos;
}

public void setFavoritos(Integer favoritos) {
	this.favoritos = favoritos;
}

public Integer getContactos() {
	return contactos;
}

public void setContactos(Integer contactos) {
	this.contactos = contactos;
}

public Integer getCompartidos() {
	return compartidos;
}

public void setCompartidos(Integer compartidos) {
	this.compartidos = compartidos;
}

}
