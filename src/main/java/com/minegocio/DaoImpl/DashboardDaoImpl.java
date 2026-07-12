package com.minegocio.DaoImpl;

import com.minegocio.DAO.DashboardDAO;
import com.minegocio.dto.DashboardMetricsDTO;
import com.minegocio.util.Conexion;
import java.sql.CallableStatement;
// Importá acá tu clase de conexión utilitaria si está en com.minegocio.util (ej: ConectarDB o Database)
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

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
}
