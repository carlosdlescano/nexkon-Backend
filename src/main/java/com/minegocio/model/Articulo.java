/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minegocio.model;

//import java.math.BigDecimal;
/**
 *
 * @author POS
 */
/*
 Articulo      |
+----------------+    
| int IDcodigo    |
| int codigo    |
| Marca marca   |
| Departamento departamento |
| Rubro rubro  |
| Familia familia |
| String descripcion |
| int stock   |
| int StockCritico   |
| double precioCosto |
| double margen |
| double precioVenta |
| Estado estado |
| Long codigoDeBarra |
+----------------+

 */
public class Articulo {

    private int idCodArticulo;
    private int codigo;
    private int marca;
    private int codDepartamento;
    private int codRubro;
    private int codFamilia;
    private String descripcion;
    private int stock;
    private int StockCritico;
    private double precioCosto;
    private double margen;
    private double precioVenta;
    private int estado;
    private long codigoBarra;
    private String nombreMarca;
    private String nombreDepartamento;
    private String nombreRubro;
    private String nombreFamilia;

    public Articulo(int codigo, int marca, int departamento, int rubro, int familia, String descipcion, int stock, int StockCritico, double precioCosto, double margen, double precioVenta, int estado, long codigoBarra) {
        this.codigo = codigo;
        this.marca = marca;
        this.codDepartamento = departamento;
        this.codRubro = rubro;
        this.codFamilia = familia;
        this.descripcion = descipcion;
        this.stock = stock;
        this.StockCritico = StockCritico;
        this.precioCosto = precioCosto;
        this.margen = margen;
        this.precioVenta = precioVenta;
        this.estado = estado;
        this.codigoBarra = codigoBarra;
    }

    public Articulo(int idCodArticulo, int codigo, int marca, String descripcion) {
        this.idCodArticulo = idCodArticulo;
        this.codigo = codigo;
        this.marca = marca;
        this.descripcion = descripcion;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public String getNombreRubro() {
        return nombreRubro;
    }

    public void setNombreRubro(String nombreRubro) {
        this.nombreRubro = nombreRubro;
    }

    public String getNombreFamilia() {
        return nombreFamilia;
    }

    public void setNombreFamilia(String nombreFamilia) {
        this.nombreFamilia = nombreFamilia;
    }  
    
    public int getStockCritico() {
        return StockCritico;
    }

    public void setStockCritico(int StockCritico) {
        this.StockCritico = StockCritico;
    }

    public Articulo() {
    }

    public int getIdCodArticulo() {
        return idCodArticulo;
    }

    public void setIdCodArticulo(int idCodArticulo) {
        this.idCodArticulo = idCodArticulo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getMarca() {
        return marca;
    }

    public void setMarca(int marca) {
        this.marca = marca;
    }

    public int getCodDepartamento() {
        return codDepartamento;
    }

    public void setCodDepartamento(int codDepartamento) {
        this.codDepartamento = codDepartamento;
    }

    public int getCodRubro() {
        return codRubro;
    }

    public void setCodRubro(int codRubro) {
        this.codRubro = codRubro;
    }

    public int getCodFamilia() {
        return codFamilia;
    }

    public void setCodFamilia(int codFamilia) {
        this.codFamilia = codFamilia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecioCosto() {
        return precioCosto;
    }

    public void setPrecioCosto(double precioCosto) {
        this.precioCosto = precioCosto;
    }

    public double getMargen() {
        return margen;
    }

    public void setMargen(double margen) {
        this.margen = margen;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public long getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(long codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    @Override
    public String toString() {
        return "Articulo{" + "idCodArticulo=" + idCodArticulo + ", codigo=" + codigo + ", marca=" + marca + ", codDepartamento=" + codDepartamento + ", codRubro=" + codRubro + ", codFamilia=" + codFamilia + ", descripcion=" + descripcion + ", stock=" + stock + ", precioCosto=" + precioCosto + ", margen=" + margen + ", precioVenta=" + precioVenta + ", estado=" + estado + ", codigoBarra=" + codigoBarra + '}';
    }

}
