/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.minegocio.DAO;

import com.minegocio.dto.DashboardMetricsDTO;
import java.time.LocalDate;

/**
 *
 * @author miNegocio
 */
public interface DashboardDAO {
    DashboardMetricsDTO obtenerMetricasDelDia(LocalDate fecha) throws Exception;
    
}
