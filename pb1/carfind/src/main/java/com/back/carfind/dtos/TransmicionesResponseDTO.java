package com.back.carfind.dtos;

public class TransmicionesResponseDTO {

    private Long id_transmision;
    private String tipo;
    private Integer marchas;
    private String descripcion;

    // Constructor vacío
    public TransmicionesResponseDTO() {
    }

    // Constructor con parámetros
    public TransmicionesResponseDTO(Long id_transmision, String tipo, Integer marchas, String descripcion) {
        this.id_transmision = id_transmision;
        this.tipo = tipo;
        this.marchas = marchas;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Long getId_transmision() {
        return id_transmision;
    }

    public void setId_transmision(Long id_transmision) {
        this.id_transmision = id_transmision;
    }

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
