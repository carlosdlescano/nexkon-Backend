package com.minegocio.DAO;

import com.minegocio.model.Compra;
import com.minegocio.model.DetalleCompra;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public interface CompraDAO {
    public boolean grabarCompra(String proveedor, Integer idProveedor, Timestamp fechaCompra, String medioPago, List<DetalleCompra> detalles);
    public ArrayList<Compra> buscarCompra(Timestamp fechaInicio, Timestamp fechaFin, String proveedor, String medioPago);
    public Compra obtenerCompraPorId(int idCompra);            
}