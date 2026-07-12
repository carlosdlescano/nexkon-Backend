package com.minegocio.controller;

import com.minegocio.DAO.DetalleVentaDAO;
import com.minegocio.model.DetalleVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/detalles-ventas")
@CrossOrigin(origins = "http://localhost:5173")
public class DetalleVentaControlador {

    @Autowired
    private DetalleVentaDAO detalleVentaDAO;

    // 1. FILTRO DINÁMICO: Responde a GET /api/detalles-ventas/buscar?idVenta=24
    @GetMapping("/buscar")
    public ArrayList<DetalleVenta> buscar(
            @RequestParam(required = false) Integer idVenta,
            @RequestParam(required = false) Integer idCodArticulo,
            @RequestParam(required = false) Double precioMin,
            @RequestParam(required = false) Double precioMax) {
        
        return detalleVentaDAO.buscarVenta(idVenta, idCodArticulo, precioMin, precioMax);
    }

    // 2. BUSCAR POR NRO DE VENTA DIRECTO: Responde a GET /api/detalles-ventas/venta/24
    @GetMapping("/venta/{idVenta}")
    public ArrayList<DetalleVenta> buscarPorVentaNro(@PathVariable Integer idVenta) {
        return detalleVentaDAO.buscarVentaNro(idVenta);
    }
}