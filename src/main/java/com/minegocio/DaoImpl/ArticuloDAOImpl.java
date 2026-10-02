/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minegocio.DaoImpl;

import com.minegocio.DAO.ArticuloDAO;
import com.minegocio.model.Articulo;
import com.minegocio.util.Conexion;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
/**
 *
 * @author POS
 */
@Repository
public class ArticuloDAOImpl implements ArticuloDAO {

    private Connection con;

    
    @Override
    public boolean crearArticulo(Articulo art) {
        con = null;
        CallableStatement stmt = null;
        boolean exito = false;

        try {
            con = Conexion.getConexion();
            stmt = con.prepareCall("{call spCrearArticulo(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)}");

            stmt.setInt(1, art.getCodigo());
            stmt.setInt(2, art.getMarca());
            stmt.setInt(3, art.getCodDepartamento());
            stmt.setInt(4, art.getCodRubro());
            stmt.setInt(5, art.getCodFamilia());
            stmt.setString(6, art.getDescripcion());
            stmt.setInt(7, art.getStock());
            stmt.setInt(8, art.getStockCritico());
            stmt.setBigDecimal(9, BigDecimal.valueOf(art.getPrecioCosto()));
            stmt.setBigDecimal(10, BigDecimal.valueOf(art.getMargen()));
            stmt.setBigDecimal(11, BigDecimal.valueOf(art.getPrecioVenta()));
            stmt.setLong(12, art.getCodigoBarra());
            stmt.execute();
            exito = true;
        } catch (SQLException e) {
            System.out.println("Error al crear artículo: " + e.getMessage());
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
    public boolean actualizarArticulo(Articulo art) {
        Connection con = null;
        CallableStatement stmt = null;
        boolean exito = false;

        try {
            con = Conexion.getConexion();
            stmt = con.prepareCall("{call spActualizarArticulo(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");

            stmt.setInt(1, art.getIdCodArticulo()); 
            stmt.setInt(2, art.getCodigo());   
            stmt.setInt(3, art.getMarca());    
            stmt.setInt(4, art.getCodDepartamento());
            stmt.setInt(5, art.getCodRubro());
            stmt.setInt(6, art.getCodFamilia());
            stmt.setString(7, art.getDescripcion());
            stmt.setInt(8, art.getStock());
            stmt.setInt(9, art.getStockCritico());
            stmt.setDouble(10, art.getPrecioCosto());
            stmt.setDouble(11, art.getMargen());
            stmt.setDouble(12, art.getPrecioVenta());
            stmt.setLong(13, art.getCodigoBarra());

            boolean tieneResultado = stmt.execute();

            if (tieneResultado) {
                ResultSet rs = stmt.getResultSet();
                if (rs.next()) {
                    String mensaje = rs.getString(1);
                    System.out.println("Mensaje SP: " + mensaje);
                    exito = mensaje.equalsIgnoreCase("OK");
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al actualizar artículo: " + e.getMessage());
            exito = false;
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
    public boolean eliminarArticulo(int codigo, int marca) { //logicamente
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Articulo> buscar(int codigo, int marca, String descripcion, int departamento, int rubro, int familia, long codigoBarra) {

        Connection con = null;
        CallableStatement stmt = null;
        ArrayList<Articulo> lista = new ArrayList<>();
        /**/
        try {
            con = Conexion.getConexion();
            stmt = con.prepareCall("{call spBuscarArticuloFlexible(?, ?, ?, ?, ?, ?, ?)}");//se ingresan como null los que no se tiene
            //orden de parametros: codigo,marca,descipcion,departamento,rubro,familia,codigodebarra
            stmt.setObject(1, codigo != 0 ? codigo : null, Types.INTEGER);
            stmt.setObject(2, marca != 0 ? marca : null, Types.INTEGER);
            stmt.setObject(3, descripcion != null && !descripcion.isEmpty() ? descripcion : null, Types.VARCHAR);
            stmt.setObject(4, departamento != 0 ? departamento : null, Types.INTEGER);
            stmt.setObject(5, rubro != 0 ? rubro : null, Types.INTEGER);
            stmt.setObject(6, familia != 0 ? familia : null, Types.INTEGER);
            stmt.setObject(7, codigoBarra != 0 ? codigoBarra : null, Types.BIGINT);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Articulo art = new Articulo();
                art.setIdCodArticulo(rs.getInt("idCodigo"));
                art.setCodigo(rs.getInt("codigo"));
                art.setMarca(rs.getInt("marca"));
                art.setDescripcion(rs.getString("descripcion"));
                art.setCodDepartamento(rs.getInt("departamento"));
                art.setCodRubro(rs.getInt("rubro"));
                art.setCodFamilia(rs.getInt("familia"));
                art.setStock(rs.getInt("stock"));
                art.setStockCritico(rs.getInt("StockCritico"));
                art.setPrecioCosto(rs.getDouble("precioCosto"));
                art.setMargen(rs.getDouble("margen"));
                art.setPrecioVenta(rs.getDouble("precioActual"));
                art.setCodigoBarra(rs.getLong("CodigoBarra"));

                lista.add(art);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar artículos: " + e.getMessage());
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

    public ArrayList<Articulo> buscarDescripcion(String descripcion) {
        return buscar(0, 0, descripcion, 0, 0, 0, 0);
    }

    public Articulo buscarArticulo(int codigo, int marca, String descripcion, int departamento, int rubro, int familia, long codigoBarra) {

        Connection con = null;
        CallableStatement stmt = null;
        Articulo arti = null;
        /**/
        try {
            con = Conexion.getConexion();
            stmt = con.prepareCall("{call spBuscarArticuloFlexible(?, ?, ?, ?, ?, ?, ?)}");
            
            stmt.setObject(1, codigo != 0 ? codigo : null, Types.INTEGER);
            stmt.setObject(2, marca != 0 ? marca : null, Types.INTEGER);
            stmt.setObject(3, descripcion != null && !descripcion.isEmpty() ? descripcion : null, Types.VARCHAR);
            stmt.setObject(4, departamento != 0 ? departamento : null, Types.INTEGER);
            stmt.setObject(5, rubro != 0 ? rubro : null, Types.INTEGER);
            stmt.setObject(6, familia != 0 ? familia : null, Types.INTEGER);
            stmt.setObject(7, codigoBarra != 0 ? codigoBarra : null, Types.BIGINT);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                arti = new Articulo();
                arti.setIdCodArticulo(rs.getInt("idCodigo"));
                arti.setCodigo(rs.getInt("codigo"));
                arti.setMarca(rs.getInt("marca"));
                arti.setDescripcion(rs.getString("descripcion"));
                arti.setCodDepartamento(rs.getInt("departamento"));
                arti.setCodRubro(rs.getInt("rubro"));
                arti.setCodFamilia(rs.getInt("familia"));
                arti.setStock(rs.getInt("stock"));
                arti.setStockCritico(rs.getInt("StockCritico"));
                arti.setPrecioCosto(rs.getDouble("precioCosto"));
                arti.setMargen(rs.getDouble("margen"));
                arti.setPrecioVenta(rs.getDouble("precioActual"));
                arti.setCodigoBarra(rs.getLong("CodigoBarra"));
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar artículo: " + e.getMessage());
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
        return arti;
    }

    @Override
    public List<Articulo> listarTodos() {

        Connection con = null;
        CallableStatement stmt = null;
        ArrayList<Articulo> lista = new ArrayList<>();
        /**/
        try {
            con = Conexion.getConexion();
            stmt = con.prepareCall("{call spBuscarArticuloFlexible}");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Articulo art = new Articulo();
                art.setIdCodArticulo(rs.getInt("idCodigo"));
                art.setCodigo(rs.getInt("codigo"));
                art.setMarca(rs.getInt("marca"));
                art.setDescripcion(rs.getString("descripcion"));
                art.setCodDepartamento(rs.getInt("departamento"));
                art.setCodRubro(rs.getInt("rubro"));
                art.setCodFamilia(rs.getInt("familia"));
                art.setStock(rs.getInt("stock"));
                art.setStockCritico(rs.getInt("StockCritico"));
                art.setPrecioCosto(rs.getDouble("precioCosto"));
                art.setMargen(rs.getDouble("margen"));
                art.setPrecioVenta(rs.getDouble("precioActual"));
                art.setCodigoBarra(rs.getLong("CodigoBarra"));
                art.setNombreMarca(rs.getString("nombreMarca"));
                art.setNombreDepartamento(rs.getString("nombreDepartamento"));
                art.setNombreRubro(rs.getString("nombreRubro"));
                art.setNombreFamilia(rs.getString("nombreFamilia"));

                lista.add(art);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar artículos: " + e.getMessage());
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
