/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minegocio.dto;

import com.minegocio.model.DetalleVenta;
import java.util.List;

/**
 *
 * @author miNegocio
 */
public class VentaRequest {
    private String cliente;
    private String medioPago;
    private List<DetalleVenta> detalles;
    
    public VentaRequest(){}

    // Getters y Setters necesarios para que Spring traduzca el JSON
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public String getMedioPago() { return medioPago; }
    public void setMedioPago(String medioPago) { this.medioPago = medioPago; }
    public List<DetalleVenta> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleVenta> detalles) { this.detalles = detalles; }
    
}
