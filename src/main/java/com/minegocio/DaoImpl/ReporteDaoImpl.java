/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minegocio.DaoImpl;

import com.minegocio.DAO.ReporteDAO;
import com.minegocio.dto.SugerenciaCompraSemanalDTO;
import com.minegocio.util.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author miNegocio
 */
public class ReporteDaoImpl implements ReporteDAO {

    @Override
    public List<SugerenciaCompraSemanalDTO> obtenerSugerenciaCompraSemanal() throws Exception {
        List<SugerenciaCompraSemanalDTO> lista = new ArrayList<>();
        String sql = "{CALL sp_ObtenerSugerenciaCompraSemanal}";

        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                SugerenciaCompraSemanalDTO dto = new SugerenciaCompraSemanalDTO();
                dto.setIdArticulo(rs.getInt("idArticulo"));
                dto.setCodigoArticulo(rs.getString("codigoArticulo"));
                dto.setDescripcion(rs.getString("nombreArticulo"));
                dto.setStockActual(rs.getInt("stockActual"));
                dto.setStockCritico(rs.getInt("stockCritico"));
                dto.setConsumoDiarioPromedio(rs.getInt("consumoDiarioPromedio"));
                dto.setDemandaEstimadaSemanal(rs.getInt("demandaEstimadaSemanal"));
                dto.setCantidadSugeridaAComprar(rs.getInt("cantidadSugeridaAComprar"));

                lista.add(dto);
            }
        }
        return lista;
    }
}