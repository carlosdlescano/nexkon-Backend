package com.minegocio.dto;

import java.math.BigDecimal;

public class VentasMensualesDTO {
    private String mes;
    private BigDecimal ventas;

    
    public VentasMensualesDTO() {}

    
    public VentasMensualesDTO(String mes, BigDecimal ventas) {
        this.mes = mes;
        this.ventas = ventas;
    }

    
    public String getMes() { return mes; }
    public void setMes(String mes) { this.mes = mes; }

    public BigDecimal getVentas() { return ventas; }
    public void setVentas(BigDecimal ventas) { this.ventas = ventas; }
}