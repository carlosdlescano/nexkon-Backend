/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minegocio.DaoImpl;

import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.minegocio.DAO.VentaDAO;
import com.minegocio.model.DetalleVenta;
import com.minegocio.model.Venta;
import com.minegocio.util.Conexion;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author POS
 */
@Repository
public class VentaDAOImpl implements VentaDAO {

    private Connection con;
    private SQLServerCallableStatement stmt = null; 
    private boolean exito = false;
    
    @Override
    public boolean grabarVenta(String cliente, Timestamp fechaVenta, String medioPago, List<DetalleVenta> detalles) {
        con = null;

        try {
            con = Conexion.getConexion();
            stmt = (SQLServerCallableStatement) con.prepareCall("{call sp_RegistrarVenta(?, ?, ?, ?)}");

            stmt.setString(1, cliente);
            stmt.setTimestamp(2, fechaVenta);
            stmt.setString(3, medioPago);

            SQLServerDataTable tvp = new SQLServerDataTable();
            tvp.addColumnMetadata("idCodArticulo", java.sql.Types.INTEGER);
            tvp.addColumnMetadata("cantidad", java.sql.Types.INTEGER);

            for (DetalleVenta d : detalles) {
                tvp.addRow(d.getIdCodArticulo(), d.getCantidad());
            }

            stmt.setStructured(4, "DetalleVentaType", tvp);
            stmt.execute();
            stmt.close();
            exito = true;
        } catch (SQLException e) {
            System.err.println("Error en la transacción de venta: " + e.getMessage());
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
        return exito;
    }
    @Override
    public ArrayList<Venta> buscarVenta(Timestamp fechaInicio, Timestamp fechaFin, String cliente, String medioPago) {
        Connection con = null;
        CallableStatement stmt = null;
        ArrayList<Venta> lista = new ArrayList<>();

        try {
            con = Conexion.getConexion();
            stmt = con.prepareCall("{call spBuscarVentaDinamico(?, ?, ?, ?)}");

            // Parámetro 1: fechaDesde
            if (fechaInicio != null) {
                stmt.setTimestamp(1, fechaInicio);
            } else {
                stmt.setNull(1, Types.TIMESTAMP);
            }

            // Parámetro 2: fechaHasta
            if (fechaFin != null) {
                stmt.setTimestamp(2, fechaFin);
            } else {
                stmt.setNull(2, Types.TIMESTAMP);
            }

            // Parámetro 3: cliente
            if (cliente != null && !cliente.isEmpty()) {
                stmt.setString(3, cliente);
            } else {
                stmt.setNull(3, Types.VARCHAR);
            }

            // Parámetro 4: medioPago
            if (medioPago != null && !medioPago.isEmpty()) {
                stmt.setString(4, medioPago);
            } else {
                stmt.setNull(4, Types.VARCHAR);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Venta v = new Venta(
                        rs.getInt("idVenta"),
                        rs.getString("cliente"),
                        rs.getTimestamp("fechaVenta"),
                        rs.getDouble("totalVenta"),
                        rs.getString("medioPago")
                );
                lista.add(v);
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
    
    public ArrayList<Venta> buscarVentaEntrefechas(Timestamp fechaInicio, Timestamp fechaFin) {
        return buscarVenta(fechaInicio, fechaFin, null, null);
    }
    

}

