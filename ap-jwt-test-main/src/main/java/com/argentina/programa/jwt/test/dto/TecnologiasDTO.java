package com.argentina.programa.jwt.test.dto;

import javax.validation.constraints.NotBlank;


public class TecnologiasDTO {
    
    @NotBlank
    private String habilidad;
    @NotBlank
    private int porcentaje;

    public TecnologiasDTO() {
    }

    public TecnologiasDTO(String habilidad, int porcentaje) {
        this.habilidad = habilidad;
        this.porcentaje = porcentaje;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }
    
    
}
