

package com.minegocio.servicesImp;


import com.minegocio.DAO.ReporteDAO;
import com.minegocio.DaoImpl.ReporteDaoImpl;
import com.minegocio.dto.SugerenciaCompraSemanalDTO;
import com.minegocio.services.ReporteService;
import java.util.List;

public class ReporteServiceImpl implements ReporteService {

    private final ReporteDAO reporteDAO;

    public ReporteServiceImpl() {
        this.reporteDAO = new ReporteDaoImpl();
    }

    @Override
    public List<SugerenciaCompraSemanalDTO> obtenerSugerenciaCompraSemanal() throws Exception {
        return reporteDAO.obtenerSugerenciaCompraSemanal();
    }
}