package com.minegocio.services;

import com.minegocio.dto.*;
import java.time.LocalDate;
import java.util.List;

public interface DashboardService {
    public DashboardMetricsDTO obtenerMetricasDelDia(LocalDate fecha) throws Exception;
    public List<VentasMensualesDTO> obtenerVentasMensuales(Integer anio)throws Exception;
    public List<TopArticuloDTO> obtenerTopArticulos(Integer anio, Integer mes) throws Exception;    
    public List<VentaDetalleDTO> obtenerUltimasVentas(LocalDate fecha) throws Exception;    
    public List<SugerenciaCompraDTO> obtenerSugerenciasCompra() throws Exception;
}