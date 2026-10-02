package com.minegocio.servicesImp;

import com.minegocio.DAO.DashboardDAO;
import com.minegocio.DaoImpl.DashboardDaoImpl;
import com.minegocio.dto.*;
import com.minegocio.services.DashboardService;
import java.time.LocalDate;
import java.util.List;

public class DashboardServiceImpl implements DashboardService {

    
    private final DashboardDAO dashboardDAO;

    public DashboardServiceImpl() {
        
        this.dashboardDAO = new DashboardDaoImpl();
    }

    @Override
    public DashboardMetricsDTO obtenerMetricasDelDia(LocalDate fecha) throws Exception {        
        return dashboardDAO.obtenerMetricasDelDia(fecha);
    }
    @Override
    public List<VentasMensualesDTO> obtenerVentasMensuales(Integer anio) throws Exception {
        return dashboardDAO.obtenerVentasMensuales(anio);
    }

    @Override
    public List<TopArticuloDTO> obtenerTopArticulos(Integer anio, Integer mes) throws Exception {
        return dashboardDAO.obtenerTopArticulos(anio, mes);
    }

    @Override
    public List<VentaDetalleDTO> obtenerUltimasVentas(LocalDate fecha) throws Exception {
        return dashboardDAO.obtenerUltimasVentas(fecha);
    }

    @Override
    public List<SugerenciaCompraDTO> obtenerSugerenciasCompra() throws Exception {
        return dashboardDAO.obtenerSugerenciasCompra();
    }
}