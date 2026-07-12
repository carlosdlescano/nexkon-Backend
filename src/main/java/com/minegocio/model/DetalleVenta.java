/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minegocio.model;


/**
 *
 * @author POS
 */
public class DetalleVenta {

    private int idDetalle;
    private int idVenta;
    private int idCodArticulo;
    private int cantidad;
    private double precio;    
    private String nombreArticulo;

    public DetalleVenta() {
    }

    public DetalleVenta(int idVenta, int idCodArticulo, int cantidad, double precio) {
        this.idVenta = idVenta;
        this.idCodArticulo = idCodArticulo;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public DetalleVenta(int idDetalle, int idVenta, int idCodArticulo, int cantidad, double precio, String nombreArticulo) {
        this.idDetalle = idDetalle;
        this.idVenta = idVenta;
        this.idCodArticulo = idCodArticulo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.nombreArticulo = nombreArticulo;
    }

    
    public DetalleVenta(int idVenta, int idCodArticulo, double precio) {
        this.idVenta = idVenta;
        this.idCodArticulo = idCodArticulo;
        this.precio = precio;
    }
 
    public DetalleVenta(int idCodArticulo, int cantidad) {
        this.idCodArticulo = idCodArticulo;
        this.cantidad = cantidad;
    }
    
    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }
    
    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdCodArticulo() {
        return idCodArticulo;
    }

    public void setIdCodArticulo(int idCodArticulo) {
        this.idCodArticulo = idCodArticulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getSubtotal() {
        return precio * cantidad;
    }

}
