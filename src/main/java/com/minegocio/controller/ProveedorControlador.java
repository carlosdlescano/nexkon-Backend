

package com.minegocio.controller;

import com.minegocio.DAO.ProveedorDAO;
import com.minegocio.model.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorControlador {

    @Autowired
    private ProveedorDAO proveedorDAO;

    /**
     * BUSCAR / LISTAR CON FILTROS (GET)
     * GET http://localhost:8080/api/proveedores
     */
    @GetMapping
    public ResponseEntity<List<Proveedor>> buscar(
            @RequestParam(required = false) Integer idProveedor,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String cuit,
            @RequestParam(required = false) Boolean activo) {
        try {
            // Si el DAO tiene búsqueda por filtros usa esa, de lo contrario lista activos/todos
            List<Proveedor> lista = proveedorDAO.listar();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * OBTENER POR ID (GET)
     * GET http://localhost:8080/api/proveedores/1
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        try {
            Proveedor proveedor = proveedorDAO.obtenerPorId(id);
            if (proveedor == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proveedor no encontrado.");
            }
            return ResponseEntity.ok(proveedor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al consultar el proveedor.");
        }
    }

    /**
     * CREAR PROVEEDOR (POST)
     * POST http://localhost:8080/api/proveedores
     */
    @PostMapping
    public ResponseEntity<?> crearProveedor(@RequestBody Proveedor proveedor) {
        if (proveedor == null || proveedor.getNombre() == null || proveedor.getNombre().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre del proveedor es obligatorio.");
        }
        try {
            int idGenerado = proveedorDAO.crear(proveedor);
            if (idGenerado > 0) {
                proveedor.setIdProveedor(idGenerado);
                return ResponseEntity.status(HttpStatus.CREATED).body(proveedor);
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo crear el proveedor.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
    @GetMapping("/todos")
    public ResponseEntity<?> listarTodos() {
        try {
            List<Proveedor> lista = proveedorDAO.listarTodos();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar todos los proveedores: " + e.getMessage());
        }
    }

    /**
     * ACTUALIZAR PROVEEDOR (PUT)
     * PUT http://localhost:8080/api/proveedores
     */
    @PutMapping
    public ResponseEntity<?> actualizarProveedor(@RequestBody Proveedor proveedor) {
        if (proveedor == null || proveedor.getIdProveedor() <= 0) {
            return ResponseEntity.badRequest().body("ID de proveedor inválido para actualización.");
        }
        try {
            boolean actualizado = proveedorDAO.actualizar(proveedor);
            if (actualizado) {
                return ResponseEntity.ok(true);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proveedor no encontrado para actualizar.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    /**
     * DESACTIVAR PROVEEDOR / BAJA LÓGICA (PUT)
     * PUT http://localhost:8080/api/proveedores/1/desactivar
     */
    @PutMapping("/{id}/desactivar")
    public ResponseEntity<?> desactivarProveedor(@PathVariable Integer id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().body("ID de proveedor inválido.");
        }
        try {
            boolean respuesta = proveedorDAO.cambiarEstado(id, false);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al dar de baja el proveedor.");
        }
    }

    /**
     * CAMBIAR ESTADO (REACTIVAR / DESACTIVAR)
     * PUT http://localhost:8080/api/proveedores/1/estado?activo=true
     */
    @PutMapping("/{id}/estado")
    public ResponseEntity<?> cambiarEstado(@PathVariable Integer id, @RequestParam Boolean activo) {
        if (id == null || activo == null) {
            return ResponseEntity.badRequest().body("Parámetros incompletos.");
        }
        try {
            boolean respuesta = proveedorDAO.cambiarEstado(id, activo);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al cambiar estado.");
        }
    }
}