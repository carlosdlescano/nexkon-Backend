/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minegocio.DaoImpl;

import com.minegocio.DAO.UsuarioDAO;
import com.minegocio.model.Usuario;
import com.minegocio.util.Conexion;
import com.minegocio.util.PasswordUtils;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author miNegocio
 */
@Repository
public class UsuarioDAOImpl implements UsuarioDAO {

    @Override

    public boolean crearUsuario(Usuario usuario, String passwordPlana) {
        Connection con = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        boolean exito = false;

        try {
            con = Conexion.getConexion();
            stmt = con.prepareCall("{call spCrearUsuario(?, ?, ?, ?, ?, ?)}");

            String hash = PasswordUtils.hashPassword(passwordPlana);

            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            stmt.setString(3, usuario.getDni());
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, hash);
            stmt.setString(6, usuario.getRol());

            rs = stmt.executeQuery();
            if (rs.next()) {
                usuario.setLegajo(rs.getInt("LegajoGenerado"));
            }
            exito = true;
        } catch (SQLException e) {
            System.out.println("Error al crear usuario: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexión: " + ex.getMessage());
            }
        }
        return exito;
    }

    @Override
    public List<Usuario> buscarPaginado(Integer legajo, String dni, String nombreApellido, String rol, Boolean activo, int offset, int limit) {
        Connection con = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> lista = new ArrayList<>();

        try {
            con = Conexion.getConexion();
            stmt = con.prepareCall("{call spBuscarPaginadoUsuario(?, ?, ?, ?, ?, ?, ?)}");

            if (legajo != null) {
                stmt.setInt(1, legajo);
            } else {
                stmt.setNull(1, Types.INTEGER);
            }
            stmt.setString(2, dni);
            stmt.setString(3, nombreApellido);
            stmt.setString(4, rol);
            if (activo != null) {
                stmt.setBoolean(5, activo);
            } else {
                stmt.setNull(5, Types.BOOLEAN);
            }
            stmt.setInt(6, offset);
            stmt.setInt(7, limit);

            rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(mapResultSetToUsuario(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar usuarios paginados: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexión: " + ex.getMessage());
            }
        }
        return lista;
    }

    @Override
    public Usuario buscarPorEmailODni(String identificador) {
        Connection con = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = null;

       
        String sql = "SELECT * FROM Usuarios WHERE "
                + "(LOWER(TRIM(email)) = LOWER(?) "
                + "OR CAST(dni AS VARCHAR) = ? "
                + "OR CAST(legajo AS VARCHAR) = ?) "
                + "AND activo = 1";

        try {
            con = Conexion.getConexion();
            stmt = con.prepareCall(sql);

            String idLimpio = identificador.trim();
            stmt.setString(1, idLimpio);
            stmt.setString(2, idLimpio);

            // Si el identificador es numérico, intentamos mapearlo como ID/legajo
            try {
                int idLegajo = Integer.parseInt(idLimpio);
                stmt.setInt(3, idLegajo);
            } catch (NumberFormatException e) {
                stmt.setInt(3, -1); // ID inválido si no es número
            }

            rs = stmt.executeQuery();
            if (rs.next()) {
                usuario = mapResultSetToUsuario(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar usuario por identificador: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexión: " + ex.getMessage());
            }
        }
        return usuario;
    }

    @Override
    public boolean actualizarUsuario(Usuario usuario, String nuevaPasswordPlana) {
        Connection con = null;
        CallableStatement stmt = null;
        boolean exito = false;

        try {
            con = Conexion.getConexion();
            stmt = con.prepareCall("{call spActualizarUsuario(?, ?, ?, ?, ?, ?, ?, ?)}");

            String hash = (nuevaPasswordPlana != null && !nuevaPasswordPlana.isEmpty())
                    ? PasswordUtils.hashPassword(nuevaPasswordPlana)
                    : null;

            stmt.setInt(1, usuario.getLegajo());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getApellido());
            stmt.setString(4, usuario.getDni());
            stmt.setString(5, usuario.getEmail());
            stmt.setString(6, hash);
            stmt.setString(7, usuario.getRol());
            if (usuario.getActivo() != null) {
                stmt.setBoolean(8, usuario.getActivo());
            } else {
                stmt.setNull(8, Types.BOOLEAN);
            }

            stmt.execute();
            exito = true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexión: " + ex.getMessage());
            }
        }
        return exito;
    }

    private Usuario mapResultSetToUsuario(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setLegajo(rs.getInt("legajo"));
        u.setNombre(rs.getString("nombre"));
        u.setApellido(rs.getString("apellido"));
        u.setDni(rs.getString("dni"));
        u.setEmail(rs.getString("email"));
        u.setRol(rs.getString("rol"));
        u.setActivo(rs.getBoolean("activo"));
        u.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
        u.setUltimoLogin(rs.getTimestamp("ultimo_login"));
        u.setIntentosFallidos(rs.getInt("intentos_fallidos"));

        try {
            u.setPasswordHash(rs.getString("password_hash"));
        } catch (SQLException ignored) {
            // Se ignora si el procedimiento no devuelve el campo de hash en la consulta puntual
        }

        return u;
    }
    
    public boolean actualizarIntentosYEstado(int legajo, int intentos, int activo) {
    String sql = "UPDATE usuarios SET intentos_fallidos = ?, activo = ? WHERE legajo = ?";
    
    try (Connection con = Conexion.getConexion();            
         PreparedStatement stmt = con.prepareStatement(sql)) {

        stmt.setInt(1, intentos);
        stmt.setInt(2, activo);
        stmt.setInt(3, legajo);

        // executeUpdate devuelve el número de filas afectadas
        int filasAfectadas = stmt.executeUpdate();
        return filasAfectadas > 0;

    } catch (SQLException e) {
        System.out.println("Error al actualizar intentos y estado: " + e.getMessage());
        return false;
    }
}

    
}
