/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minegocio.dto;

/**
 *
 * @author miNegocio
 */
public class SugerenciaCompraDTO {
    private String codigo;
    private String descripcion; //private String descripcion;
    private int stockActual;
    private int stockMinimo;
    private int cantidadSugerida;

    public SugerenciaCompraDTO() {
    }

    public SugerenciaCompraDTO(String codigo, String articulo, int stockActual, int stockMinimo, int cantidadSugerida) {
        this.codigo = codigo;
        this.descripcion = articulo;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.cantidadSugerida = cantidadSugerida;
    }

    public String getCodigo() {        return codigo;    }
    public void setCodigo(String codigo) {        this.codigo = codigo;    }

    public String getDescripcion() {        return descripcion;    }
    public void setDescripcion(String descripcion) {        this.descripcion = descripcion;    }

    public int getStockActual() {        return stockActual;  }
    public void setStockActual(int stockActual) {        this.stockActual = stockActual;    }

    public int getStockMinimo() {        return stockMinimo;    }
    public void setStockMinimo(int stockMinimo) {        this.stockMinimo = stockMinimo;    }

    public int getCantidadSugerida() {        return cantidadSugerida;    }
    public void setCantidadSugerida(int cantidadSugerida) {        this.cantidadSugerida = cantidadSugerida;    }
    
    
    
}
