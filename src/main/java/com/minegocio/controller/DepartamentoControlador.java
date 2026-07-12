package com.minegocio.controller;

import com.minegocio.DAO.DepartamentoDAO;
import com.minegocio.model.Departamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/departamentos")
@CrossOrigin(origins = "http://localhost:5173")
public class DepartamentoControlador {

    @Autowired
    private DepartamentoDAO departamentoDAO;

    // Responde a un GET a http://localhost:8080/api/departamentos
    @GetMapping
    public List<Departamento> listar() {
        return departamentoDAO.ListarTodos();
    }
}