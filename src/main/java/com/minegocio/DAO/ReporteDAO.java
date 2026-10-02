/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.minegocio.DAO;

import com.minegocio.dto.SugerenciaCompraSemanalDTO;
import java.util.List;

/**
 *
 * @author miNegocio
 */
public interface ReporteDAO {
    List<SugerenciaCompraSemanalDTO> obtenerSugerenciaCompraSemanal() throws Exception;
}