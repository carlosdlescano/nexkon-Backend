package com.minegocio.DAO;

import com.minegocio.model.Proveedor;
import java.util.List;

public interface ProveedorDAO {
    
    public int crear(Proveedor proveedor) throws Exception;
    
    public List<Proveedor> listar() throws Exception;
    
    public List<Proveedor> listarTodos() throws Exception; // Incluye inactivos
    
    public Proveedor obtenerPorId(int idProveedor) throws Exception;
    
    public boolean actualizar(Proveedor proveedor) throws Exception;
    
    public boolean cambiarEstado(int idProveedor, boolean activo) throws Exception;
}
