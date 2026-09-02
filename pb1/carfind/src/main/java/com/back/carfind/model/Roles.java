package com.back.carfind.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Roles {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_rol;
@Column
private String nombre_rol;
@Column
private Boolean activo_rol;

public Long getId_rol() {
return id_rol;
}
public void setId_rol(Long id_rol) {
this.id_rol = id_rol;
}

public String getNombre_rol() {
return nombre_rol;
}
public void setNombre_rol(String nombre_rol) {
this.nombre_rol = nombre_rol;
}

public Boolean getActivo_rol() {
return activo_rol;
}
public void setActivo_rol(Boolean activo_rol) {
this.activo_rol = activo_rol;
}



}