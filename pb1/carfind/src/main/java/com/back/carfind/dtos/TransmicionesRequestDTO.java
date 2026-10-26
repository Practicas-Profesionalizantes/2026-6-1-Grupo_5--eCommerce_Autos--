package com.back.carfind.dtos;

public class TransmicionesRequestDTO {

    private String tipo;
    private Integer marchas;
    private String descripcion;

    // Getters y Setters
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getMarchas() {
        return marchas;
    }

    public void setMarchas(Integer marchas) {
        this.marchas = marchas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
