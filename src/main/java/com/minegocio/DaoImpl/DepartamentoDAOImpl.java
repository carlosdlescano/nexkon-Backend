/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minegocio.DaoImpl;

import com.minegocio.DAO.DepartamentoDAO;
import com.minegocio.model.Departamento;
import com.minegocio.util.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author POS
 */
@Repository
public class DepartamentoDAOImpl implements DepartamentoDAO{

    @Override
    public List<Departamento> ListarTodos() {       
        
        Connection con = null;
        CallableStatement stmt = null;
        ArrayList<Departamento> lista = new ArrayList<>();
        /**/
        try {
            con = Conexion.getConexion();
            stmt = con.prepareCall("{call spBuscarPaginadoDepartamento}");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Departamento dep = new Departamento();
                dep.setCodDepartamento(rs.getInt("codDepartamento"));
                dep.setDescripcion(rs.getString("descripcion"));

                lista.add(dep);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar artículos: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
                
            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexión: " + ex.getMessage());
            }
        }
        return lista;
    }
    
    
}
