package com.minegocio.controller;

import com.minegocio.DAO.FamiliaDAO;
import com.minegocio.model.Familia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/familias")
@CrossOrigin(origins = "http://localhost:5173")
public class FamiliaControlador {

    @Autowired
    private FamiliaDAO familiaDAO;

    // Responde a GET http://localhost:8080/api/familias
    @GetMapping
    public List<Familia> listar() {
        return familiaDAO.ListarTodos();
    }
}