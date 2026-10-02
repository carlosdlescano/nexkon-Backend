/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minegocio.dto;

import com.minegocio.model.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class UsuarioDTO {
        private Usuario usuario;
        @NotBlank
        @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
        private String password;
        private String passwordPlana;

        public Usuario getUsuario() { return usuario; }
        public void setUsuario(Usuario usuario) { this.usuario = usuario; }
        public String getPasswordPlana() { return passwordPlana; }
        public void setPasswordPlana(String passwordPlana) { this.passwordPlana = passwordPlana; }
    }
