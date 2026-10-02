package com.minegocio.model;

public class Proveedor {

    private int idProveedor;
    private String nombre;
    private String cuit;
    private String telefono;
    private String direccion;
    private boolean activo;

    public Proveedor() {
    }

    public Proveedor(String nombre, String cuit, String telefono, String direccion) {
        this.nombre = nombre;
        this.cuit = cuit;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}