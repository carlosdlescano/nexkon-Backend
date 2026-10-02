package com.minegocio.DaoImpl;

import com.minegocio.DAO.DashboardDAO;
import com.minegocio.dto.*;
import com.minegocio.util.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DashboardDaoImpl implements DashboardDAO {

    @Override
    public DashboardMetricsDTO obtenerMetricasDelDia(LocalDate fecha) throws Exception {
        DashboardMetricsDTO dto = new DashboardMetricsDTO();
        Connection con = null;
        CallableStatement stmt = null;

        String sql = "{call sp_ObtenerMetricasMontoCantidad(?)}";
        try {
            con = Conexion.getConexion();
            stmt = con.prepareCall(sql);
            if (fecha != null) {
                stmt.setDate(1, java.sql.Date.valueOf(fecha));
            } else {
                stmt.setNull(1, java.sql.Types.DATE);
            }
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                dto.setTotalFacturadoHoy(rs.getBigDecimal("TotalFacturadoHoy"));
                dto.setCantidadVentasHoy(rs.getInt("CantidadVentasHoy"));
                dto.setArticulosEnStockCritico(rs.getInt("ArticulosEnStockCritico"));

            }

        } catch (SQLException e) {
            System.out.println("Error al obtener métricas del dashboard: " + e.getMessage());
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return dto;
    }

    @Override
    public List<VentasMensualesDTO> obtenerVentasMensuales(Integer anio) throws Exception {
        List<VentasMensualesDTO> lista = new ArrayList<>();
        Connection con = null;
        CallableStatement stmt = null;
        ResultSet rs = null;

        String sql = "{call sp_ObtenerVentasMensuales(?)}";

        try {
            con = Conexion.getConexion();
            stmt = con.prepareCall(sql);
            if (anio == null) {
                anio = LocalDate.now().getYear();
            }
            stmt.setInt(1, anio);
            rs = stmt.executeQuery();

            while (rs.next()) {
                VentasMensualesDTO dto = new VentasMensualesDTO();
                dto.setMes(rs.getString("mes"));
                dto.setVentas(rs.getBigDecimal("ventas"));

                lista.add(dto);
            }

        } catch (Exception e) {
            System.err.println("Error en obtenerVentasMensuales DAO: " + e.getMessage());
            throw e;
        } finally {

            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return lista;
    }

    @Override
    public List<TopArticuloDTO> obtenerTopArticulos(Integer anio, Integer mes) throws Exception {

        List<TopArticuloDTO> lista = new ArrayList<>();
        Connection con = null;
        CallableStatement stmt = null;
        ResultSet rs = null;

        String sql = "{call ObtenerTopArticulosMensual (?,?)}";

        try {
            con = Conexion.getConexion();
            stmt = con.prepareCall(sql);
            if (anio == null || mes == null) {
                anio = LocalDate.now().getYear();
                mes = LocalDate.now().getMonthValue();
            }
            stmt.setInt(1, anio);
            stmt.setInt(2, mes);

            rs = stmt.executeQuery();

            while (rs.next()) {
                TopArticuloDTO dto = new TopArticuloDTO();
                dto.setNombre(rs.getString("nombre"));
                dto.setUnidades(rs.getInt("unidades"));

                lista.add(dto);
            }

        } catch (Exception e) {
            System.err.println("Error en obtenerVentasMensuales DAO: " + e.getMessage());
            throw e;
        } finally {

            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return lista;
    }

    @Override
    public List<VentaDetalleDTO> obtenerUltimasVentas(LocalDate fecha) throws Exception {
        List<VentaDetalleDTO> lista = new ArrayList<>();
        Connection con = null;
        CallableStatement stmt = null;
        ResultSet rs = null;

        String sql = "{call ObtenerUltimasVentasDetalle (?)}";

        try {
            con = Conexion.getConexion();
            stmt = con.prepareCall(sql);
            if (fecha == null) {
                fecha = LocalDate.now();
            }

            stmt.setDate(1, java.sql.Date.valueOf(fecha));

            rs = stmt.executeQuery();

            while (rs.next()) {
                VentaDetalleDTO dto = new VentaDetalleDTO();

                dto.setIdVenta(rs.getInt("nro_ticket"));
                dto.setMonto(rs.getDouble("monto"));
                dto.setHora(rs.getString("hora"));
                dto.setArticulo(rs.getString("articulo"));
                dto.setCantidad(rs.getInt("cantidad"));

                lista.add(dto);
            }

        } catch (Exception e) {
            System.err.println("Error en obtenerVentasMensuales DAO: " + e.getMessage());
            throw e;
        } finally {

            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return lista;
    }

    @Override
    public List<SugerenciaCompraDTO> obtenerSugerenciasCompra() throws Exception {
        List<SugerenciaCompraDTO> lista = new ArrayList<>();
        Connection con = null;
        CallableStatement stmt = null;
        ResultSet rs = null;

        String sql = "{call ObtenerSugerenciasCompra}";

        try {
            con = Conexion.getConexion();
            stmt = con.prepareCall(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                SugerenciaCompraDTO dto = new SugerenciaCompraDTO();

                dto.setCodigo(rs.getString("codigo"));
                dto.setDescripcion(rs.getString("articulo"));
                dto.setStockActual(rs.getInt("stock_actual"));
                dto.setStockMinimo(rs.getInt("stock_critico"));
                dto.setCantidadSugerida(rs.getInt("cantidad_sugerida"));

                lista.add(dto);
            }

        } catch (Exception e) {
            System.err.println("Error en obtenerVentasMensuales DAO: " + e.getMessage());
            throw e;
        } finally {

            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return lista;

    }
}
