package com.back.carfind.dtos;

/**
 * Datos que devuelve la API de un registro de la tabla tarjetas_usuario.
 */
public class TarjetasUsuarioResponseDTO {

    private Long id;
    private Long usuarioId;
    private String marca;
    private String ultimos4;
    private String token;
    private Integer vencimientoMes;
    private Integer vencimientoAnio;

    public TarjetasUsuarioResponseDTO() {
    }

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
