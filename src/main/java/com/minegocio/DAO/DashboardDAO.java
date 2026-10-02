/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.minegocio.DAO;

import com.minegocio.dto.*;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author miNegocio
 */
public interface DashboardDAO {
    public DashboardMetricsDTO obtenerMetricasDelDia(LocalDate fecha) throws Exception;
    public List<VentasMensualesDTO> obtenerVentasMensuales(Integer anio) throws Exception;
    public List<TopArticuloDTO> obtenerTopArticulos(Integer anio, Integer mes) throws Exception;    
    public List<VentaDetalleDTO> obtenerUltimasVentas(LocalDate fecha) throws Exception;
    public List<SugerenciaCompraDTO> obtenerSugerenciasCompra()throws Exception;
}
