package com.back.carfind.model;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Usuario {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@Column(nullable = false)
private String nombre;
@Column
private String email;
@Id
@Column
private Long dni;
@Column
private String password;
@Column
private Integer telefono;
@Column 
private Double reputacion;
@Column
private Double score_riesgo;
@Column 
private Boolean verificado;
@Column
private LocalDate fecha_creacion;
@Column
private Integer total_compras;
@Column 
private Integer total_ventas;



public Long getId() {
return id;
}
public void setId(Long id) {
this.id = id;
}


public String getNombre() {
return nombre;
}
public void setNombre(String nombre) {
this.nombre = nombre;
}


public String getEmail() {
return email;
}
public void setEmail(String email) {
this.email = email;
}


public Long getDni() {
return dni;
}
public void setDni(Long dni) {

this.dni = dni;
}

public String getPassword() {
return password;
}//HAY Q ARREGLAR TIPO DE DATO
public void setPassword(String password) {

this.password=password;
}

public Integer getTelefono() {
return telefono;
}
public void setTelefono(Integer telefono) {

this.telefono = telefono;
}

public Double getReputacion() {
return reputacion;
}
public void setReputacion(Double reputacion) {

this.reputacion = reputacion;
}

public Double getScore_riesgo() {
return score_riesgo;
}
public void setScore_riesgo(Double score_riesgo) {

this.score_riesgo = score_riesgo;
}

public Boolean getVerificado() {
return verificado;
}
public void setVerificado(Boolean verificado) {

this.verificado = verificado;
}

public LocalDate getFecha_creacion() {
return fecha_creacion;
}
public void setFecha_creacion(LocalDate fecha_creacion) {

this.fecha_creacion= fecha_creacion;
}

public Integer getTotal_compras() {
return total_compras;
}
public void setTotal_compras(Integer total_compras) {

this.total_compras = total_compras;
}

public Integer getTotal_ventas() {
return total_ventas;
}
public void setTotal_ventas(Integer total_ventas) {

this.total_ventas = total_ventas;
}

}