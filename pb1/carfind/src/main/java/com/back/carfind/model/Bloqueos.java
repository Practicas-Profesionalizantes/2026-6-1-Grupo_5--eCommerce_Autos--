package com.back.carfind.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Bloqueos {

@Column
private Long id_usuario;
@Column
private Long usuario_bloqueado_id;

public Long getId_usuario() {
	return id_usuario;
}
public void setId_usuario(Long id_usuario) {
	this.id_usuario = id_usuario;
}
public Long getUsuario_bloqueado_id() {
	return usuario_bloqueado_id;
}
public void setUsuario_bloqueado_id(Long usuario_bloqueado_id) {
	this.usuario_bloqueado_id = usuario_bloqueado_id;
}



}