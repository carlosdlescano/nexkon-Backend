package com.minegocio.model;

public class DetalleCompra {
    private int idDetalleCompra;
    private int idCompra;
    private int idCodArticulo;
    private String nombreArticulo;
    private Integer cantidad;
    private Double precioCosto;

    public DetalleCompra() {}

    public int getIdDetalleCompra() {
        return idDetalleCompra;
    }

    public void setIdDetalleCompra(int idDetalleCompra) {
        this.idDetalleCompra = idDetalleCompra;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getIdCodArticulo() {
        return idCodArticulo;
    }

    public void setIdCodArticulo(int idCodArticulo) {
        this.idCodArticulo = idCodArticulo;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioCosto() {
        return precioCosto;
    }

    public void setPrecioCosto(Double precioCosto) {
        this.precioCosto = precioCosto;
    }
    
}