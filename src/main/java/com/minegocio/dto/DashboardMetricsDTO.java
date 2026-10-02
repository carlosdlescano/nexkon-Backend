
package com.minegocio.dto;

import java.math.BigDecimal;
import java.util.List;

public class DashboardMetricsDTO {
    private BigDecimal totalFacturadoHoy;
    private int cantidadVentasHoy;
    private int articulosEnStockCritico;
    private List<VentasMensualesDTO> ventasMensuales;

    
    public DashboardMetricsDTO() {}

    
    public DashboardMetricsDTO(BigDecimal totalFacturadoHoy, int cantidadVentasHoy, int articulosEnStockCritico) {
        this.totalFacturadoHoy = totalFacturadoHoy;
        this.cantidadVentasHoy = cantidadVentasHoy;
        this.articulosEnStockCritico = articulosEnStockCritico;
    }

    public DashboardMetricsDTO(BigDecimal totalFacturadoHoy, int cantidadVentasHoy, int articulosEnStockCritico, List<VentasMensualesDTO> ventasMensuales) {
        this.totalFacturadoHoy = totalFacturadoHoy;
        this.cantidadVentasHoy = cantidadVentasHoy;
        this.articulosEnStockCritico = articulosEnStockCritico;
        this.ventasMensuales = ventasMensuales;
    }
    
    public BigDecimal getTotalFacturadoHoy() { return totalFacturadoHoy; }
    public void setTotalFacturadoHoy(BigDecimal totalFacturadoHoy) { this.totalFacturadoHoy = totalFacturadoHoy; }

    public int getCantidadVentasHoy() { return cantidadVentasHoy; }
    public void setCantidadVentasHoy(int cantidadVentasHoy) { this.cantidadVentasHoy = cantidadVentasHoy; }

    public int getArticulosEnStockCritico() { return articulosEnStockCritico; }
    public void setArticulosEnStockCritico(int articulosEnStockCritico) { this.articulosEnStockCritico = articulosEnStockCritico; }

    public List<VentasMensualesDTO> getVentasMensuales() {  return ventasMensuales;    }
    public void setVentasMensuales(List<VentasMensualesDTO> ventasMensuales) {        this.ventasMensuales = ventasMensuales;    }
    
    
}
