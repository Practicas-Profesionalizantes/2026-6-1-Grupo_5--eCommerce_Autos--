package com.back.carfind.web;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Sesiones con token: al iniciar sesión se guarda un token en la tabla sesiones y el frontend
 * lo manda en cada pedido como "Authorization: Bearer <token>" (ver src/services/api.js).
 */
@Service
public class SesionService {

    private static final int DIAS_VALIDEZ = 7;

    private final JdbcTemplate jdbc;

    public SesionService(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public String crearSesion(long usuarioId) {
        String token = UUID.randomUUID().toString().replace("-", "") + UUID.randomUUID().toString().replace("-", "");
        jdbc.update("INSERT INTO sesiones (usuario_id, token, fecha_expiracion) VALUES (?, ?, ?)",
                usuarioId, token, LocalDateTime.now().plusDays(DIAS_VALIDEZ));
        return token;
    }

    public void cerrarSesion(HttpServletRequest request) {
        String token = token(request);
        if (token != null) jdbc.update("DELETE FROM sesiones WHERE token = ?", token);
    }

    /** Id del usuario logueado, o null si el pedido no trae una sesión válida. */
    public Long usuarioIdOpcional(HttpServletRequest request) {
        String token = token(request);
        if (token == null) return null;
        List<Long> ids = jdbc.queryForList(
                "SELECT s.usuario_id FROM sesiones s JOIN usuarios u ON u.id = s.usuario_id "
                        + "WHERE s.token = ? AND s.fecha_expiracion > NOW() AND COALESCE(u.eliminado, 0) = 0",
                Long.class, token);
        return ids.isEmpty() ? null : ids.get(0);
    }

    /** Id del usuario logueado. Responde 401 si no hay sesión. */
    public long usuarioId(HttpServletRequest request) {
        Long id = usuarioIdOpcional(request);
        if (id == null) throw Json.error(HttpStatus.UNAUTHORIZED, "Tenés que iniciar sesión para continuar.");
        return id;
    }

    public boolean esAdmin(long usuarioId) {
        Integer cantidad = jdbc.queryForObject(
                "SELECT COUNT(*) FROM usuario_roles ur JOIN roles r ON r.id = ur.rol_id "
                        + "WHERE ur.usuario_id = ? AND r.nombre = 'ADMIN' AND COALESCE(r.activo, 1) = 1",
                Integer.class, usuarioId);
        return cantidad != null && cantidad > 0;
    }

    /** Id del administrador logueado. Responde 401 sin sesión y 403 si no es administrador. */
    public long adminId(HttpServletRequest request) {
        long id = usuarioId(request);
        if (!esAdmin(id)) throw Json.error(HttpStatus.FORBIDDEN, "Esta sección es solo para administradores.");
        return id;
    }

    private static String token(HttpServletRequest request) {
        String cabecera = request.getHeader("Authorization");
        if (cabecera == null || !cabecera.startsWith("Bearer ")) return null;
        String token = cabecera.substring("Bearer ".length()).trim();
        return token.isEmpty() ? null : token;
    }
}
