/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minegocio.controller;

import com.minegocio.DAO.CompraDAO;
import com.minegocio.model.*;
import com.minegocio.dto.CompraRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author POS
 */
@RestController
@RequestMapping("/api/compras")
public class CompraControlador {

    private final CompraDAO compraDAO;

    @Autowired
    public CompraControlador(CompraDAO compraDAO) {
        this.compraDAO = compraDAO;
    }

    @PostMapping("/registrar")
    public boolean registrarCompra(@RequestBody CompraRequest request) {
        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());

        return compraDAO.grabarCompra(
                request.getProveedor(),
                request.getIdProveedor(),
                fechaActual,
                request.getMedioPago(),
                request.getDetalles()
        );
    }
    // BUSCADOR DINÁMICO DE COMPRAS (GET)
    // Ejemplo /api/compras/buscar?proveedor=Distribuidora
    @GetMapping("/buscar")
    public ArrayList<Compra> buscarCompras(
            @RequestParam(required = false) Long desde,
            @RequestParam(required = false) Long hasta,
            @RequestParam(required = false) String proveedor,
            @RequestParam(required = false) String medioPago) {

        Timestamp fechaInicio = (desde != null) ? new Timestamp(desde) : null;
        Timestamp fechaFin = (hasta != null) ? new Timestamp(hasta) : null;

        return compraDAO.buscarCompra(fechaInicio, fechaFin, proveedor, medioPago);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra> obtenerPorId(@PathVariable Integer id) {
        Compra compra = compraDAO.obtenerCompraPorId(id);

        if (compra != null) {
            return ResponseEntity.ok(compra);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
