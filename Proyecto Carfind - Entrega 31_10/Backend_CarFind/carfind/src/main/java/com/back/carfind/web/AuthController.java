package com.back.carfind.web;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Registro e inicio de sesión.
 *
 *   POST /api/web/auth/registro  → { token, usuario }   JSON { dni, email, nombre, telefono, password }
 *   POST /api/web/auth/login     → { token, usuario }   JSON { email, password }
 *   GET  /api/web/auth/sesion    → { usuario }          usuario = { id, nombre, apellido, email, fotoUrl, esAdmin }
 *   POST /api/web/auth/logout    → 204
 */
@RestController
@RequestMapping("/api/web/auth")
public class AuthController {

    private static final int CONTRASENA_MIN_LARGO = 8;

    private final JdbcTemplate jdbc;
    private final SesionService sesiones;
    private final PasswordEncoder encoder;

    public AuthController(JdbcTemplate jdbc, SesionService sesiones, PasswordEncoder encoder) {
        this.jdbc = jdbc;
        this.sesiones = sesiones;
        this.encoder = encoder;
    }

    public record Registro(String dni, String email, String nombre, String telefono, String password) {
    }

    public record Login(String email, String password) {
    }

    @PostMapping("/registro")
    @Transactional
    public Map<String, Object> registrar(@RequestBody Registro r) {
        String dni = Json.texto(r.dni()).replaceAll("\\D", "");
        String email = Json.texto(r.email()).trim().toLowerCase();
        String nombre = Json.texto(r.nombre()).trim();
        String telefono = Json.texto(r.telefono()).trim();
        String password = Json.texto(r.password());

        if (dni.length() < 7 || dni.length() > 8) throw Json.error(HttpStatus.BAD_REQUEST, "El DNI debe tener 7 u 8 números.");
        if (!email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) throw Json.error(HttpStatus.BAD_REQUEST, "El correo no es válido.");
        if (nombre.isEmpty()) throw Json.error(HttpStatus.BAD_REQUEST, "Ingresá tu nombre y apellido.");
        if (telefono.isEmpty() || telefono.length() > 20) throw Json.error(HttpStatus.BAD_REQUEST, "Ingresá un teléfono válido.");
        if (password.length() < CONTRASENA_MIN_LARGO) {
            throw Json.error(HttpStatus.BAD_REQUEST, "La contraseña debe tener al menos " + CONTRASENA_MIN_LARGO + " caracteres.");
        }
        if (existe("SELECT COUNT(*) FROM usuarios WHERE LOWER(email) = ?", email)) {
            throw Json.error(HttpStatus.CONFLICT, "Ya existe una cuenta con ese correo.");
        }
        if (existe("SELECT COUNT(*) FROM usuarios WHERE dni = ?", dni)) {
            throw Json.error(HttpStatus.CONFLICT, "Ya existe una cuenta con ese DNI.");
        }

        long id = Consultas.insertar(jdbc, """
                INSERT INTO usuarios (nombre, email, dni, password_hash, telefono, fecha_creacion)
                VALUES (?, ?, ?, ?, ?, NOW())
                """, nombre, email, dni, encoder.encode(password), telefono);
        jdbc.update("INSERT INTO usuario_roles (usuario_id, rol_id) SELECT ?, id FROM roles WHERE nombre = 'USUARIO'", id);
        jdbc.update("INSERT INTO preferencias_usuario (usuario_id) VALUES (?)", id);
        return Json.obj("token", sesiones.crearSesion(id), "usuario", usuario(id));
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Login l) {
        List<Map<String, Object>> filas = jdbc.queryForList(
                "SELECT id, password_hash FROM usuarios WHERE LOWER(email) = ? AND COALESCE(eliminado, 0) = 0",
                Json.texto(l.email()).trim().toLowerCase());
        if (filas.isEmpty() || !coincide(Json.texto(l.password()), (String) filas.get(0).get("password_hash"))) {
            throw Json.error(HttpStatus.UNAUTHORIZED, "Correo o contraseña incorrectos.");
        }
        long id = Json.entero(filas.get(0).get("id"));
        return Json.obj("token", sesiones.crearSesion(id), "usuario", usuario(id));
    }

    @GetMapping("/sesion")
    public Map<String, Object> sesion(HttpServletRequest request) {
        return Json.obj("usuario", usuario(sesiones.usuarioId(request)));
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(HttpServletRequest request) {
        sesiones.cerrarSesion(request);
    }

    private Map<String, Object> usuario(long id) {
        Map<String, Object> u = jdbc.queryForMap("SELECT id, nombre, email, foto_url FROM usuarios WHERE id = ?", id);
        String[] nombre = Json.separarNombre((String) u.get("nombre"));
        return Json.obj("id", id, "nombre", nombre[0], "apellido", nombre[1], "email", u.get("email"),
                "fotoUrl", u.get("foto_url"), "esAdmin", sesiones.esAdmin(id));
    }

    private boolean coincide(String password, String hash) {
        try {
            return hash != null && encoder.matches(password, hash);
        } catch (IllegalArgumentException e) {
            return false; // hash con formato inválido
        }
    }

    private boolean existe(String sql, Object valor) {
        Integer cantidad = jdbc.queryForObject(sql, Integer.class, valor);
        return cantidad != null && cantidad > 0;
    }
}
