package com.minegocio.controller;

import com.minegocio.dto.DashboardMetricsDTO;
import com.minegocio.services.DashboardService;
import com.minegocio.servicesImp.DashboardServiceImpl;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardControlador {

    private final DashboardService dashboardService;

    public DashboardControlador() {
        this.dashboardService = new DashboardServiceImpl();
    }

    @GetMapping("/metricas")
    public ResponseEntity<DashboardMetricsDTO> obtenerMetricas(
            @RequestParam(value = "fecha", required = false) 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        try {
            if (fecha == null) {
                fecha = LocalDate.now();
            }
            DashboardMetricsDTO metricas = dashboardService.obtenerMetricasDelDia(fecha);
            return ResponseEntity.ok(metricas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}