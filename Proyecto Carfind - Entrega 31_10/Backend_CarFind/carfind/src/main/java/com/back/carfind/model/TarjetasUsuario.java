package com.back.carfind.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tarjetas_usuario")
public class TarjetasUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "marca", length = 20)
    private String marca;

    @Column(name = "ultimos4", length = 4)
    private String ultimos4;

    @Column(name = "token", columnDefinition = "TEXT")
    private String token;

    @Column(name = "vencimiento_mes")
    private Integer vencimientoMes;

    @Column(name = "vencimiento_anio")
    private Integer vencimientoAnio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getUltimos4() {
        return ultimos4;
    }

    public void setUltimos4(String ultimos4) {
        this.ultimos4 = ultimos4;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getVencimientoMes() {
        return vencimientoMes;
    }

    public void setVencimientoMes(Integer vencimientoMes) {
        this.vencimientoMes = vencimientoMes;
    }

    public Integer getVencimientoAnio() {
        return vencimientoAnio;
    }

    public void setVencimientoAnio(Integer vencimientoAnio) {
        this.vencimientoAnio = vencimientoAnio;
    }
}
