/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minegocio.dto;

/**
 *
 * @author miNegocio
 */
public class TopArticuloDTO {
    private String nombre;
    private int unidades;

    public TopArticuloDTO() {
    }

    public TopArticuloDTO(String nombre, int unidades) {
        this.nombre = nombre;
        this.unidades = unidades;
    }

    public String getNombre() {        return nombre;    }
    public void setNombre(String nombre) {        this.nombre = nombre;    }

    public int getUnidades() {        return unidades;    }
    public void setUnidades(int unidades) {        this.unidades = unidades;    }
    
    
    
    
}
