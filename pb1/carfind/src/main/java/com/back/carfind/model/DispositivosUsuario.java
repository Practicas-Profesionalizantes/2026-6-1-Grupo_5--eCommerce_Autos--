package com.back.carfind.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class DispositivosUsuario {

@Column
private Long id_usuario;
@Column
private Long token_push;
@Column
private String plataforma;

public Long getId_usuario() {
	return id_usuario;
}
public void setId_usuario(Long id_usuario) {
	this.id_usuario = id_usuario;
}
public Long getToken_push() {
	return token_push;
}
public void setToken_push(Long token_push) {
	this.token_push = token_push;
}
public String getPlataforma() {
	return plataforma;
}
public void setPlataforma(String plataforma) {
	this.plataforma = plataforma;
}



}