package com.minegocio.DaoImpl;

import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.minegocio.DAO.CompraDAO;
import com.minegocio.model.Compra;
import com.minegocio.model.DetalleCompra;
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

@Repository
public class CompraDAOImpl implements CompraDAO {

    private Connection con;
    private SQLServerCallableStatement stmt = null;

    @Override
    public boolean grabarCompra(String proveedor, Integer idProveedor, Timestamp fechaCompra, String medioPago, List<DetalleCompra> detalles) {
        con = null;
        boolean exito = false;

        try {
            con = Conexion.getConexion();

            // Llama a sp_RegistrarCompra(?, ?, ?, ?, ?)
            // 1: @Proveedor, 2: @idProveedor, 3: @fechaCompra, 4: @medioPago, 5: @DetallesCompra
            stmt = (SQLServerCallableStatement) con.prepareCall("{call sp_RegistrarCompra(?, ?, ?, ?, ?)}");

            // 1. @Proveedor
            stmt.setString(1, proveedor);

            // 2. @idProveedor
            if (idProveedor != null && idProveedor > 0) {
                stmt.setInt(2, idProveedor);
            } else {
                stmt.setNull(2, Types.INTEGER);
            }

            // 3. @fechaCompra
            stmt.setTimestamp(3, fechaCompra);

            // 4. @medioPago
            stmt.setString(4, medioPago);
//            if (medioPago != null && !medioPago.trim().isEmpty()) {
//                stmt.setString(4, medioPago);
//            } else {
//                stmt.setNull(4, Types.VARCHAR);
//            }

            // 5. @DetallesCompra (Estructura TVP coincidente con DetalleCompraType)
            SQLServerDataTable tvp = new SQLServerDataTable();
            tvp.addColumnMetadata("idCodArticulo", Types.INTEGER);
            tvp.addColumnMetadata("cantidad", Types.INTEGER);
            tvp.addColumnMetadata("precioCosto", Types.DECIMAL);

            for (DetalleCompra d : detalles) {
                tvp.addRow(
                        d.getIdCodArticulo(),
                        d.getCantidad(),
                        d.getPrecioCosto()
                );
            }

            stmt.setStructured(5, "DetalleCompraType", tvp);

            stmt.execute();
            exito = true;

        } catch (SQLException e) {
            System.err.println("Error en la transacción de compra: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar conexión: " + ex.getMessage());
            }
        }
        return exito;
    }

    @Override
    public ArrayList<Compra> buscarCompra(Timestamp fechaInicio, Timestamp fechaFin, String proveedor, String medioPago) {
        Connection con = null;
        CallableStatement stmt = null;
        ArrayList<Compra> lista = new ArrayList<>();

        try {
            con = Conexion.getConexion();
            stmt = con.prepareCall("{call spBuscarCompraDinamico(?, ?, ?, ?)}");

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

            // Parámetro 3: proveedor
            if (proveedor != null && !proveedor.trim().isEmpty()) {
                stmt.setString(3, proveedor);
            } else {
                stmt.setNull(3, Types.VARCHAR);
            }

            // Parámetro 4: medioPago
            if (medioPago != null && !medioPago.trim().isEmpty()) {
                stmt.setString(4, medioPago);
            } else {
                stmt.setNull(4, Types.VARCHAR);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Compra c = new Compra(
                        rs.getInt("idCompra"),
                        rs.getInt("idProveedor"),
                        rs.getString("proveedor"),
                        rs.getTimestamp("fechaCompra"),
                        rs.getString("medioPago"),
                        rs.getDouble("totalCompra")
                );
                lista.add(c);
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar compras: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }

        return lista;
    }

    public ArrayList<Compra> buscarCompraEntreFechas(Timestamp fechaInicio, Timestamp fechaFin) {
        return buscarCompra(fechaInicio, fechaFin, null, null);
    }

    @Override
    public Compra obtenerCompraPorId(int idCompra) {
        Compra compra = null;
        Connection con = null;
        CallableStatement stmt = null;
        ResultSet rs = null;

        try {
            con = Conexion.getConexion();
            stmt = con.prepareCall("{call sp_ObtenerCompraPorId(?)}");
            stmt.setInt(1, idCompra);

            boolean tieneResultados = stmt.execute();

            // 1. Mapear Cabecera
            if (tieneResultados) {
                rs = stmt.getResultSet();
                if (rs.next()) {
                    compra = new Compra();
                    compra.setIdCompra(rs.getInt("idCompra"));
                    compra.setIdProveedor(rs.getInt("idProveedor"));
                    compra.setProveedor(rs.getString("proveedor"));
                    compra.setFecha(rs.getTimestamp("fechaCompra"));
                    compra.setTotal(rs.getDouble("totalCompra"));
                    compra.setMedioPago(rs.getString("medioPago"));
                    compra.setDetalles(new ArrayList<>());
                }
            }

            // 2. Mapear Lista de Detalles
            if (compra != null && stmt.getMoreResults()) {
                rs = stmt.getResultSet();
                List<DetalleCompra> detalles = new ArrayList<>();
                while (rs.next()) {
                    DetalleCompra detalle = new DetalleCompra();
                    detalle.setIdCodArticulo(rs.getInt("idCodArticulo"));
                    detalle.setNombreArticulo(rs.getString("nombreArticulo"));
                    detalle.setCantidad(rs.getInt("cantidad"));
                    detalle.setPrecioCosto(rs.getDouble("precioCosto"));

                    detalles.add(detalle);
                }
                compra.setDetalles((ArrayList<DetalleCompra>)detalles);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener compra por ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar conexión: " + ex.getMessage());
            }
        }

        return compra;
    }
}
