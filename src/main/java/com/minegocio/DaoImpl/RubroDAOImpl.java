/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minegocio.DaoImpl;

import com.minegocio.DAO.RubroDAO;
import com.minegocio.model.Rubro;
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
public class RubroDAOImpl implements RubroDAO {

    @Override
    public List<Rubro> ListarTodos() {
        Connection con = null;
        CallableStatement stmt = null;
        ArrayList<Rubro> lista = new ArrayList<>();
        /**/
        try {
            con = Conexion.getConexion();
            stmt = con.prepareCall("{call spBuscarPaginadoRubro}");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Rubro rub = new Rubro();
                rub.setCodRubro(rs.getInt("codRubro"));
                rub.setCodDepartamento(rs.getInt("codDepartamento"));
                rub.setDescripcion(rs.getString("descripcion"));

                lista.add(rub);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar Rubros: " + e.getMessage());
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
