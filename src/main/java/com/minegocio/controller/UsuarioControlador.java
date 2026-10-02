
package com.minegocio.controller;

import com.minegocio.DAO.UsuarioDAO;
import com.minegocio.model.Usuario;
import com.minegocio.util.PasswordUtils;
import com.minegocio.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")

public class UsuarioControlador {
    @Autowired
    private UsuarioDAO usuarioDAO;

    public static class LoginRequest {
        private String identificador;
        private String password;

        public String getIdentificador() { return identificador; }
        public void setIdentificador(String identificador) { this.identificador = identificador; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    /*@PostMapping("/login")
    public ResponseEntity<?> autenticar(@RequestBody LoginRequest request) {
        if (request.getIdentificador() == null || request.getIdentificador().trim().isEmpty() || 
            request.getPassword() == null || request.getPassword().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Credenciales incompletas.");
        }

        Usuario usuario = usuarioDAO.buscarPorEmailODni(request.getIdentificador().trim());

        if (usuario == null || (usuario.getActivo() != null && !usuario.getActivo())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado o inactivo.");
        }

        if (PasswordUtils.checkPassword(request.getPassword(), usuario.getPasswordHash())) {
            usuario.setPasswordHash(null); 
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta.");
        }
    }*/
@PostMapping("/login")
public ResponseEntity<?> autenticar(@RequestBody LoginRequest request) {
    
    
    if (request.getIdentificador() == null || request.getIdentificador().trim().isEmpty() || 
        request.getPassword() == null || request.getPassword().trim().isEmpty()) {
        return ResponseEntity.badRequest().body("Credenciales incompletas.");
    }

    String identificador = request.getIdentificador().trim();
    Usuario usuario = usuarioDAO.buscarPorEmailODni(identificador);

    if (usuario == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado.");
    }

    // 1. Verificar si el usuario está inactivo (bloqueado)
    if (usuario.getActivo() != null && !usuario.getActivo()) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("La cuenta está desactivada por exceder los intentos fallidos.");
    }
   
    // 2. Validar contraseña
    if (PasswordUtils.checkPassword(request.getPassword(), usuario.getPasswordHash())) {
        
        // LOGIN EXITOSO: Si tenía intentos acumulados, los reseteamos a 0
        if (usuario.getIntentosFallidos() > 0) {
            usuarioDAO.actualizarIntentosYEstado(usuario.getLegajo(), 0, 1);
        }

        usuario.setPasswordHash(null); 
        return ResponseEntity.ok(usuario);
        
    } else {
        // 3. CONTRASEÑA INCORRECTA: Incrementar intentos fallidos
        int intentosActuales = usuario.getIntentosFallidos();
            intentosActuales++;

        // Si llega a 5, bloqueamos la cuenta (activo = false)
        if (intentosActuales >= 5) {
            usuarioDAO.actualizarIntentosYEstado(usuario.getLegajo(), intentosActuales, 0);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Cuenta bloqueada por seguridad tras 5 intentos fallidos.");
        }

        // Si aún no llega a 5, solo actualizamos el contador manteniendo activo = true
        usuarioDAO.actualizarIntentosYEstado(usuario.getLegajo(), intentosActuales, 1);
        
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta. Intento " + intentosActuales + " de 5.");
    }
}

    @GetMapping
    public List<Usuario> buscarPaginado(
            @RequestParam(required = false) Integer legajo,
            @RequestParam(required = false) String dni,
            @RequestParam(required = false) String nombreApellido,
            @RequestParam(required = false) String rol,
            @RequestParam(required = false) Boolean activo,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        return usuarioDAO.buscarPaginado(legajo, dni, nombreApellido, rol, activo, offset, limit);
    }

    /**
     * FIX 2: CREAR USUARIO (Recibe JSON con usuario y passwordPlana)
     */
    @PostMapping
    public boolean crearUsuario(@RequestBody UsuarioDTO dto) {
        if (dto == null || dto.getUsuario() == null || dto.getPasswordPlana() == null || dto.getPasswordPlana().trim().isEmpty()) {
            return false;
        }
        return usuarioDAO.crearUsuario(dto.getUsuario(), dto.getPasswordPlana());
    }

    /**
     * FIX 1: ACTUALIZAR USUARIO (Permite editar contraseña opcionalmente)
     */
    @PutMapping
    public boolean actualizarUsuario(@RequestBody UsuarioDTO dto) {
        if (dto == null || dto.getUsuario() == null || dto.getUsuario().getLegajo() == null) {
            return false;
        }
        // Si passwordPlana viene nula o vacía, DAO conservará la contraseña actual
        return usuarioDAO.actualizarUsuario(dto.getUsuario(), dto.getPasswordPlana());
    }

    @PutMapping("/{legajo}/desactivar")
    public boolean desactivarUsuario(@PathVariable Integer legajo) {
        if (legajo == null) return false;
        Usuario usuario = new Usuario();
        usuario.setLegajo(legajo);
        usuario.setActivo(false);
        return usuarioDAO.actualizarUsuario(usuario, null);
    }
}