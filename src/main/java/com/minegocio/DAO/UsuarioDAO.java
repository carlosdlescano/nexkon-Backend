/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.minegocio.DAO;

import com.minegocio.model.Usuario;
import java.util.List;

/**
 *
 * @author miNegocio
 */
public interface UsuarioDAO {
    boolean crearUsuario(Usuario usuario, String passwordPlana);
    Usuario buscarPorEmailODni(String identificador);
    List<Usuario> buscarPaginado(Integer legajo, String dni, String nombreApellido, String rol, Boolean activo, int offset, int limit);
    boolean actualizarUsuario(Usuario usuario, String nuevaPasswordPlana);
    public boolean actualizarIntentosYEstado(int legajo, int intentos, int activo);
}
