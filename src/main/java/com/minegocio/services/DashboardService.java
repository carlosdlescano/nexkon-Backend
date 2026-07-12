package com.minegocio.services;

import com.minegocio.dto.DashboardMetricsDTO;
import java.time.LocalDate;

public interface DashboardService {
    DashboardMetricsDTO obtenerMetricasDelDia(LocalDate fecha) throws Exception;
}