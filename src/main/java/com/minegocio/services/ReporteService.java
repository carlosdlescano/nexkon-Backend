

package com.minegocio.services;

import com.minegocio.dto.SugerenciaCompraSemanalDTO;
import java.util.List;

public interface ReporteService {
    public List<SugerenciaCompraSemanalDTO> obtenerSugerenciaCompraSemanal()throws Exception;
}