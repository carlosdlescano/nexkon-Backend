package com.minegocio.servicesImp;

import com.minegocio.DAO.DashboardDAO;
import com.minegocio.DaoImpl.DashboardDaoImpl;
import com.minegocio.dto.DashboardMetricsDTO;
import com.minegocio.services.DashboardService;
import java.time.LocalDate;

public class DashboardServiceImpl implements DashboardService {

    // Se declara la interfaz del DAO, manteniendo el desacoplamiento
    private final DashboardDAO dashboardDAO;

    public DashboardServiceImpl() {
        // Inicializamos apuntando a tu implementación concreta de datos
        this.dashboardDAO = new DashboardDaoImpl();
    }

    @Override
    public DashboardMetricsDTO obtenerMetricasDelDia(LocalDate fecha) throws Exception {
        // Por ahora, funciona como pasamanos directo hacia el DAO
        // Acá es donde a futuro acoplaremos las alertas automatizadas de Telegram
        return dashboardDAO.obtenerMetricasDelDia(fecha);
    }
}