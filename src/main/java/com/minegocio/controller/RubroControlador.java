/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minegocio.controller;

import com.minegocio.DAO.RubroDAO;
import com.minegocio.model.Rubro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/rubros")
@CrossOrigin(origins = "http://localhost:5173")
public class RubroControlador {

    @Autowired
    private RubroDAO rubroDAO;

    // Responde a un GET a http://localhost:8080/api/rubros
    @GetMapping
    public List<Rubro> listar() {
        return rubroDAO.ListarTodos();
    }
}