/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minegocio.DaoImpl;

import com.minegocio.DAO.DetalleVentaDAO;
import com.minegocio.model.Articulo;
import com.minegocio.model.DetalleVenta;
import com.minegocio.util.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;


/**
 *
 * @author miNegocio
 */
@Repository
public class DetalleVentaDAOImpl implements DetalleVentaDAO {

    @Override
    public ArrayList<DetalleVenta> buscarVenta(Integer idVenta, Integer idCodArticulo,Double precioMin,Double precioMax) {
        Connection con = null;
        CallableStatement stmt = null;
        ArrayList<DetalleVenta> lista = new ArrayList<>();

        try {
            con = Conexion.getConexion();
            stmt = con.prepareCall("{call spBuscarDetalleVentaDinamico(?, ?, ?, ?)}");

            
            if (idVenta != null) {
                stmt.setInt(1, idVenta);
            } else {
                stmt.setNull(1, Types.INTEGER);
            }

            
            if (idCodArticulo != null) {
                stmt.setInt(2, idCodArticulo);
            } else {
                stmt.setNull(2, Types.INTEGER);
            }

            
            if (precioMin != null) {
                stmt.setDouble(3, precioMin);
            } else {
                stmt.setNull(3, Types.DECIMAL);
            }

            
            if (precioMax != null) {
                stmt.setDouble(4, precioMax);
            } else {
                stmt.setNull(4, Types.DECIMAL);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                
                Articulo art = new Articulo(
                        rs.getInt("idCodArticulo"),
                        rs.getInt("codigo"),
                        rs.getInt("marca"),
                        rs.getString("nombreArticulo") 
                );

                
                DetalleVenta d = new DetalleVenta(
                        rs.getInt("idDetalle"),
                        rs.getInt("idVenta"),
                        rs.getInt("idCodArticulo"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precioUnitario"),
                        rs.getString("nombreArticulo")
                );
                
                lista.add(d);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return lista;
    }
    
    public ArrayList<DetalleVenta> buscarVentaNro(Integer idVenta){
        return buscarVenta(idVenta, null, null, null);               
              
    
    }


}
