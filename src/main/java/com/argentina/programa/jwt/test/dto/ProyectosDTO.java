package com.argentina.programa.jwt.test.dto;

import javax.validation.constraints.NotBlank;


public class ProyectosDTO {
    
    @NotBlank
    private String nombre;
    @NotBlank
    private String descripcion;

    public ProyectosDTO() {
    }

    public ProyectosDTO(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
