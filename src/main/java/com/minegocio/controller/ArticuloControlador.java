
package com.minegocio.controller;

import com.minegocio.DAO.ArticuloDAO;
import com.minegocio.model.Articulo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/articulos")
@CrossOrigin(origins = "http://localhost:5173") // Crucial: Permite que tu React (corriendo en Vite) lo consuma localmente
public class ArticuloControlador {

    @Autowired
    private ArticuloDAO articuloDAO; // Spring inyecta automáticamente tu ArticuloDAOImpl

    // 1. LISTAR TODOS: Responderá a un GET a http://localhost:8080/api/articulos
    @GetMapping
    public List<Articulo> listar() {
        return articuloDAO.listarTodos();
    }

    // 2. CREAR ARTÍCULO: Responderá a un POST recibiendo el JSON desde el formulario de React
    @PostMapping
    public boolean crear(@RequestBody Articulo articulo) {
        return articuloDAO.crearArticulo(articulo);
    }

    // 3. ACTUALIZAR ARTÍCULO: Responderá a un PUT para modificar un registro existente
    @PutMapping
    public boolean actualizar(@RequestBody Articulo articulo) {
        return articuloDAO.actualizarArticulo(articulo);
    }
 
    // 4. BUSQUEDA FLEXIBLE: Responderá a peticiones con parámetros opcionales
    // Ejemplo desde React: http://localhost:8080/api/articulos/buscar?codigoBarra=7798422620014
    @GetMapping("/buscar")
    public ArrayList<Articulo> buscar(
            @RequestParam(required = false, defaultValue = "0") int codigo,
            @RequestParam(required = false, defaultValue = "0") int marca,
            @RequestParam(required = false) String descripcion,
            @RequestParam(required = false, defaultValue = "0") int departamento,
            @RequestParam(required = false, defaultValue = "0") int rubro,
            @RequestParam(required = false, defaultValue = "0") int familia,
            @RequestParam(required = false, defaultValue = "0") long codigoBarra) {
        
        return articuloDAO.buscar(codigo, marca, descripcion, departamento, rubro, familia, codigoBarra);
    }
}