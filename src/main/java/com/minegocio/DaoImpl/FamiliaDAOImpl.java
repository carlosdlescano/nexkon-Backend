/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minegocio.DaoImpl;

import com.minegocio.DAO.FamiliaDAO;
import com.minegocio.model.Familia;
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
 * @author miNegocio
 */
@Repository
public class FamiliaDAOImpl implements FamiliaDAO{
    
    @Override
    public List<Familia> ListarTodos() {       
        
        Connection con = null;
        CallableStatement stmt = null;
        ArrayList<Familia> lista = new ArrayList<>();
        /**/
        try {
            con = Conexion.getConexion();
            stmt = con.prepareCall("{call spBuscarPaginadoFamilia}");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Familia fam = new Familia();
                fam.setCodFamilia(rs.getInt("codFamilia"));
                fam.setCodRubro(rs.getInt("codRubro"));
                fam.setDescripcion(rs.getString("descripcion"));

                lista.add(fam);
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

