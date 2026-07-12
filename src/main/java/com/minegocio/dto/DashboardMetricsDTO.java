
package com.minegocio.dto;

import java.math.BigDecimal;

public class DashboardMetricsDTO {
    private BigDecimal totalFacturadoHoy;
    private int cantidadVentasHoy;
    private int articulosEnStockCritico;

    // Constructor vacío
    public DashboardMetricsDTO() {}

    // Constructor completo
    public DashboardMetricsDTO(BigDecimal totalFacturadoHoy, int cantidadVentasHoy, int articulosEnStockCritico) {
        this.totalFacturadoHoy = totalFacturadoHoy;
        this.cantidadVentasHoy = cantidadVentasHoy;
        this.articulosEnStockCritico = articulosEnStockCritico;
    }

    // Getters y Setters
    public BigDecimal getTotalFacturadoHoy() { return totalFacturadoHoy; }
    public void setTotalFacturadoHoy(BigDecimal totalFacturadoHoy) { this.totalFacturadoHoy = totalFacturadoHoy; }

    public int getCantidadVentasHoy() { return cantidadVentasHoy; }
    public void setCantidadVentasHoy(int cantidadVentasHoy) { this.cantidadVentasHoy = cantidadVentasHoy; }

    public int getArticulosEnStockCritico() { return articulosEnStockCritico; }
    public void setArticulosEnStockCritico(int articulosEnStockCritico) { this.articulosEnStockCritico = articulosEnStockCritico; }
}
