

package com.minegocio.DaoImpl;

import com.minegocio.DAO.ProveedorDAO;
import com.minegocio.model.Proveedor;
import com.minegocio.util.Conexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ProveedorDAOImpl implements ProveedorDAO {

    @Override
    public int crear(Proveedor proveedor) throws Exception {
        int idGenerado = 0;
        String sql = "{CALL spCrearProveedor(?, ?, ?, ?)}";

        try (Connection cn = Conexion.getConexion();
             CallableStatement cs = cn.prepareCall(sql)) {

            cs.setString(1, proveedor.getNombre());
            cs.setString(2, proveedor.getCuit());
            cs.setString(3, proveedor.getTelefono());
            cs.setString(4, proveedor.getDireccion());

            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    idGenerado = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al insertar el proveedor: " + e.getMessage(), e);
        }
        return idGenerado;
    }

    @Override
    public List<Proveedor> listar() throws Exception {
        // Solo lista los proveedores con activo = 1 (Baja lógica respetada)
        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT idproveedor, nombre, cuit, telefono, direccion, activo FROM proveedores WHERE activo = 1";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setIdProveedor(rs.getInt("idproveedor"));
                p.setNombre(rs.getString("nombre"));
                p.setCuit(rs.getString("cuit"));
                p.setTelefono(rs.getString("telefono"));
                p.setDireccion(rs.getString("direccion"));
                p.setActivo(rs.getBoolean("activo"));
                lista.add(p);
            }
        } catch (SQLException e) {
            throw new Exception("Error al listar proveedores activos: " + e.getMessage(), e);
        }
        return lista;
    }

    @Override
    public List<Proveedor> listarTodos() throws Exception {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT idproveedor, nombre, cuit, telefono, direccion, activo FROM proveedores";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setIdProveedor(rs.getInt("idproveedor"));
                p.setNombre(rs.getString("nombre"));
                p.setCuit(rs.getString("cuit"));
                p.setTelefono(rs.getString("telefono"));
                p.setDireccion(rs.getString("direccion"));
                p.setActivo(rs.getBoolean("activo"));
                lista.add(p);
            }
        } catch (SQLException e) {
            throw new Exception("Error al listar todos los proveedores: " + e.getMessage(), e);
        }
        return lista;
    }

    @Override
    public Proveedor obtenerPorId(int idProveedor) throws Exception {
        Proveedor p = null;
        String sql = "SELECT idproveedor, nombre, cuit, telefono, direccion, activo FROM proveedores WHERE idproveedor = ?";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idProveedor);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    p = new Proveedor();
                    p.setIdProveedor(rs.getInt("idproveedor"));
                    p.setNombre(rs.getString("nombre"));
                    p.setCuit(rs.getString("cuit"));
                    p.setTelefono(rs.getString("telefono"));
                    p.setDireccion(rs.getString("direccion"));
                    p.setActivo(rs.getBoolean("activo"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al obtener proveedor por ID: " + e.getMessage(), e);
        }
        return p;
    }

    @Override
    public boolean actualizar(Proveedor proveedor) throws Exception {
        String sql = "UPDATE proveedores SET nombre = ?, cuit = ?, telefono = ?, direccion = ?, activo = ? WHERE idproveedor = ?";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getCuit());
            ps.setString(3, proveedor.getTelefono());
            ps.setString(4, proveedor.getDireccion());
            ps.setBoolean(5, proveedor.isActivo());
            ps.setInt(6, proveedor.getIdProveedor());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar proveedor: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean cambiarEstado(int idProveedor, boolean activo) throws Exception {
        String sql = "UPDATE proveedores SET activo = ? WHERE idproveedor = ?";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setBoolean(1, activo);
            ps.setInt(2, idProveedor);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new Exception("Error al cambiar estado del proveedor: " + e.getMessage(), e);
        }
    }
}