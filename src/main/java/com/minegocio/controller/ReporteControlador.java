


package com.minegocio.controller;

import com.minegocio.dto.SugerenciaCompraSemanalDTO;
import com.minegocio.services.ReporteService;
import com.minegocio.servicesImp.ReporteServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
//@CrossOrigin(origins = "*") // Permite las peticiones desde el cliente React
public class ReporteControlador {

    private final ReporteService reporteService;

    public ReporteControlador() {
        this.reporteService = new ReporteServiceImpl();
    }

    @GetMapping("/sugerencia-semanal")
    public ResponseEntity<?> obtenerSugerenciaCompraSemanal() {
        try {
            List<SugerenciaCompraSemanalDTO> sugerencias = reporteService.obtenerSugerenciaCompraSemanal();
            return ResponseEntity.ok(sugerencias);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al generar la sugerencia de compra semanal: " + e.getMessage());
        }
    }
}