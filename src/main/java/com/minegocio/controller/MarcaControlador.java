package com.minegocio.controller;

import com.minegocio.DAO.MarcaDAO;
import com.minegocio.model.Marca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/marcas")
@CrossOrigin(origins = "http://localhost:5173")
public class MarcaControlador {

    @Autowired
    private MarcaDAO marcaDAO;

    // Responde a un GET a http://localhost:8080/api/marcas
    @GetMapping
    public List<Marca> listar() {
        return marcaDAO.ListarTodos();
    }
}