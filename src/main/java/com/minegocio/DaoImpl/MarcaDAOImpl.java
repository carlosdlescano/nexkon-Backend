/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minegocio.DaoImpl;

import com.minegocio.DAO.MarcaDAO;
import com.minegocio.model.Marca;
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
public class MarcaDAOImpl implements MarcaDAO {

    @Override
    public List<Marca> ListarTodos() {
        Connection con = null;
        CallableStatement stmt = null;
        ArrayList<Marca> lista = new ArrayList<>();
        /**/
        try {
            con = Conexion.getConexion();
            stmt = con.prepareCall("{call spBuscarPaginadoMarca}");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Marca mar = new Marca();
                mar.setCodMarca(rs.getInt("codMarca"));
                mar.setDescripcion(rs.getString("descripcion"));

                lista.add(mar);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar marcas: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexión: " + ex.getMessage());
            }
        }
        return lista;
    }

}
