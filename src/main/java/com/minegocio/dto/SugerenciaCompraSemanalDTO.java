
package com.minegocio.dto;


public class SugerenciaCompraSemanalDTO {
    private Integer idArticulo;
    private String codigoArticulo;
    private String descripcion;
    private Integer stockActual;
    private Integer stockCritico;
    private Integer consumoDiarioPromedio;
    private Integer demandaEstimadaSemanal;
    private Integer cantidadSugeridaAComprar;

    public SugerenciaCompraSemanalDTO() {}
  
    public SugerenciaCompraSemanalDTO(Integer idArticulo, String codigoArticulo, String nombreArticulo, 
                                        Integer stockActual, Integer stockCritico, Integer consumoDiarioPromedio, 
                                        Integer demandaEstimadaSemanal, Integer cantidadSugeridaAComprar) {
        this.idArticulo = idArticulo;
        this.codigoArticulo = codigoArticulo;
        this.descripcion = nombreArticulo;
        this.stockActual = stockActual;
        this.stockCritico = stockCritico;
        this.consumoDiarioPromedio = consumoDiarioPromedio;
        this.demandaEstimadaSemanal = demandaEstimadaSemanal;
        this.cantidadSugeridaAComprar = cantidadSugeridaAComprar;
    }

    public Integer getIdArticulo() { return idArticulo; }
    public void setIdArticulo(Integer idArticulo) { this.idArticulo = idArticulo; }

    public String getCodigoArticulo() { return codigoArticulo; }
    public void setCodigoArticulo(String codigoArticulo) { this.codigoArticulo = codigoArticulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Integer getStockActual() { return stockActual; }
    public void setStockActual(Integer stockActual) { this.stockActual = stockActual; }

    public Integer getStockCritico() { return stockCritico; }
    public void setStockCritico(Integer stockCritico) { this.stockCritico = stockCritico; }

    public Integer getConsumoDiarioPromedio() { return consumoDiarioPromedio; }
    public void setConsumoDiarioPromedio(Integer consumoDiarioPromedio) { this.consumoDiarioPromedio = consumoDiarioPromedio; }

    public Integer getDemandaEstimadaSemanal() { return demandaEstimadaSemanal; }
    public void setDemandaEstimadaSemanal(Integer demandaEstimadaSemanal) { this.demandaEstimadaSemanal = demandaEstimadaSemanal; }

    public Integer getCantidadSugeridaAComprar() { return cantidadSugeridaAComprar; }
    public void setCantidadSugeridaAComprar(Integer cantidadSugeridaAComprar) { this.cantidadSugeridaAComprar = cantidadSugeridaAComprar; }
}