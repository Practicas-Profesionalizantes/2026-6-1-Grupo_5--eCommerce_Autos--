package com.back.carfind.dtos;

public class PlanesFinanciamientoRequestDTO {

    private String nombre;
    private String institucion_financiera;
    private Float tasa_interes;
    private Integer plazo_maximo_meses;
    private Boolean activo;

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInstitucion_financiera() {
        return institucion_financiera;
    }

    public void setInstitucion_financiera(String institucion_financiera) {
        this.institucion_financiera = institucion_financiera;
    }

    public Float getTasa_interes() {
        return tasa_interes;
    }

    public void setTasa_interes(Float tasa_interes) {
        this.tasa_interes = tasa_interes;
    }

    public Integer getPlazo_maximo_meses() {
        return plazo_maximo_meses;
    }

    public void setPlazo_maximo_meses(Integer plazo_maximo_meses) {
        this.plazo_maximo_meses = plazo_maximo_meses;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
