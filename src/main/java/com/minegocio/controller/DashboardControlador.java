package com.minegocio.controller;

import com.minegocio.dto.*;
import com.minegocio.services.DashboardService;
import com.minegocio.servicesImp.DashboardServiceImpl;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
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
    
    @GetMapping("/ventas-mensuales")
    public ResponseEntity<?> getVentasMensuales(
            @RequestParam(value = "anio", required = false) Integer anio) {
        try {
            List<VentasMensualesDTO> datosGrafico = dashboardService.obtenerVentasMensuales(anio);
            return ResponseEntity.ok(datosGrafico);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener las ventas mensuales: " + e.getMessage());
        }
    }
    
    @GetMapping("/top-articulos")
    public ResponseEntity<?> getTopArticulos(
            @RequestParam(required = false) Integer anio,
            @RequestParam(required = false) Integer mes) {
        try {
            List<TopArticuloDTO> resultado = dashboardService.obtenerTopArticulos(anio, mes);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener el top de artículos: " + e.getMessage());
        }
    }

    @GetMapping("/ultimas-ventas")
    public ResponseEntity<?> getUltimasVentas(
            @RequestParam(required = false) 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        try {
            List<VentaDetalleDTO> resultado = dashboardService.obtenerUltimasVentas(fecha);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener las últimas ventas: " + e.getMessage());
        }
    }

    @GetMapping("/sugerencias-compra")
    public ResponseEntity<?> getSugerenciasCompra() {
        try {
            List<SugerenciaCompraDTO> resultado = dashboardService.obtenerSugerenciasCompra();
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener las sugerencias de compra: " + e.getMessage());
        }
    }
    
    
}