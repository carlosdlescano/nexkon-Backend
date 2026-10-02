package com.minegocio.model;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Compra {
    private int idCompra;
    private int idProveedor;
    private String proveedor;
    private Timestamp fecha;
    private String medioPago;
    private Double total;
    private ArrayList<DetalleCompra> detalles;

    public Compra() {}

    public Compra(int idCompra, int idProveedor, String proveedor, Timestamp fecha, String medioPago, Double total) {
        this.idCompra = idCompra;
        this.idProveedor = idProveedor;
        this.proveedor = proveedor;
        this.fecha = fecha;
        this.medioPago = medioPago;
        this.total = total;
    }

    public ArrayList<DetalleCompra> getDetalles() {
        return detalles;
    }

    public void setDetalles(ArrayList<DetalleCompra> detalles) {
        this.detalles = detalles;
    }
    
    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }      

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    
}