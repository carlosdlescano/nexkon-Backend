/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minegocio.controller;

import com.minegocio.DAO.VentaDAO;
import com.minegocio.model.*;
import com.minegocio.dto.VentaRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author POS
 */
@RestController
@RequestMapping("/api/ventas")
public class VentaControlador {
   
   private final VentaDAO ventaDAO;
   @Autowired
    public VentaControlador(VentaDAO ventaDAO) {
        this.ventaDAO = ventaDAO;
    }
    
    @PostMapping("/registrar")
    public boolean registrarVenta(@RequestBody VentaRequest request) {
        // fecha del sistema al momento exacto de procesar en el servidor
        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
        
        return ventaDAO.grabarVenta(
                request.getCliente(), 
                fechaActual, 
                request.getMedioPago(), 
                request.getDetalles()
        );
    }

    // 2. BUSCADOR DINÁMICO DE VENTAS (GET)
    // Ejemplo /api/ventas/buscar?cliente=Juan
    @GetMapping("/buscar")
    public ArrayList<Venta> buscarVentas(
            @RequestParam(required = false) Long desde, 
            @RequestParam(required = false) Long hasta,
            @RequestParam(required = false) String cliente,
            @RequestParam(required = false) String medioPago) {

        Timestamp fechaInicio = (desde != null) ? new Timestamp(desde) : null;
        Timestamp fechaFin = (hasta != null) ? new Timestamp(hasta) : null;

        return ventaDAO.buscarVenta(fechaInicio, fechaFin, cliente, medioPago);
    }

}

