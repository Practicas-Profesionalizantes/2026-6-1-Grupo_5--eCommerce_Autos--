package com.back.carfind.web;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Perfil propio y perfil público de otros usuarios. Formas en src/types/perfil.js y perfilPublico.js.
 *
 *   GET    /api/web/perfil         → { usuario, publicaciones }
 *   PUT    /api/web/perfil         → { ok, usuario }   JSON { nombre, apellido, ubicacion, telefono }
 *   POST   /api/web/perfil/foto    → { ok, usuario }   multipart, campo "foto"
 *   DELETE /api/web/perfil/foto    → { ok, usuario }
 *   GET    /api/web/usuarios/:id   → { usuario, publicaciones }
 */
@RestController
@RequestMapping("/api/web")
public class PerfilController {

    private final JdbcTemplate jdbc;
    private final SesionService sesiones;
    private final PublicacionWebService publicaciones;
    private final ArchivoService archivos;

    public PerfilController(JdbcTemplate jdbc, SesionService sesiones, PublicacionWebService publicaciones,
            ArchivoService archivos) {
        this.jdbc = jdbc;
        this.sesiones = sesiones;
        this.publicaciones = publicaciones;
        this.archivos = archivos;
    }

    public record PerfilEditable(String nombre, String apellido, String ubicacion, String telefono) {
    }

    @GetMapping("/perfil")
    public Map<String, Object> perfil(HttpServletRequest request) {
        long id = sesiones.usuarioId(request);
        return Json.obj("usuario", usuario(id), "publicaciones", publicaciones.deUsuario(id, true));
    }

    @PutMapping("/perfil")
    public Map<String, Object> actualizar(HttpServletRequest request, @RequestBody PerfilEditable datos) {
        long id = sesiones.usuarioId(request);
        String nombre = Json.unirNombre(datos.nombre(), datos.apellido());
        String telefono = Json.texto(datos.telefono()).trim();
        if (Json.texto(datos.nombre()).isBlank()) throw Json.error(HttpStatus.BAD_REQUEST, "El nombre es obligatorio.");
        if (telefono.isEmpty() || telefono.length() > 20) throw Json.error(HttpStatus.BAD_REQUEST, "Ingresá un teléfono válido.");

        jdbc.update("UPDATE usuarios SET nombre = ?, telefono = ? WHERE id = ?", nombre, telefono, id);
        guardarUbicacion(id, Json.texto(datos.ubicacion()).trim());
        return Json.obj("ok", true, "usuario", usuario(id));
    }

    @PostMapping("/perfil/foto")
    public Map<String, Object> subirFoto(HttpServletRequest request, @RequestParam("foto") MultipartFile foto) {
        long id = sesiones.usuarioId(request);
        String anterior = jdbc.queryForObject("SELECT foto_url FROM usuarios WHERE id = ?", String.class, id);
        jdbc.update("UPDATE usuarios SET foto_url = ? WHERE id = ?", archivos.guardarImagen(foto), id);
        archivos.eliminar(anterior);
        return Json.obj("ok", true, "usuario", usuario(id));
    }

    @DeleteMapping("/perfil/foto")
    public Map<String, Object> eliminarFoto(HttpServletRequest request) {
        long id = sesiones.usuarioId(request);
        String anterior = jdbc.queryForObject("SELECT foto_url FROM usuarios WHERE id = ?", String.class, id);
        jdbc.update("UPDATE usuarios SET foto_url = NULL WHERE id = ?", id);
        archivos.eliminar(anterior);
        return Json.obj("ok", true, "usuario", usuario(id));
    }

    @GetMapping("/usuarios/{id}")
    public Map<String, Object> perfilPublico(@PathVariable long id) {
        return Json.obj("usuario", usuario(id), "publicaciones", publicaciones.deUsuario(id, false));
    }

    /** Usuario con la forma Perfil de src/types/perfil.js. */
    private Map<String, Object> usuario(long id) {
        List<Map<String, Object>> filas = jdbc.queryForList(
                "SELECT id, nombre, telefono, reputacion, fecha_creacion, foto_url FROM usuarios "
                        + "WHERE id = ? AND COALESCE(eliminado, 0) = 0", id);
        if (filas.isEmpty()) throw Json.error(HttpStatus.NOT_FOUND, "El usuario no existe.");
        Map<String, Object> u = filas.get(0);
        String[] nombre = Json.separarNombre((String) u.get("nombre"));
        String fechaCreacion = Json.fechaIso(u.get("fecha_creacion"));
        return Json.obj(
                "id", id,
                "nombre", nombre[0],
                "apellido", nombre[1],
                "ubicacion", Consultas.ubicacion(jdbc, id),
                "reputacion", Json.decimal(u.get("reputacion")),
                "fechaCreacion", fechaCreacion == null ? null : fechaCreacion.substring(0, 10),
                "telefono", Json.texto(u.get("telefono")),
                "fotoUrl", u.get("foto_url"));
    }

    /** "Ciudad, Provincia" se guarda en la dirección principal del usuario. */
    private void guardarUbicacion(long usuarioId, String ubicacion) {
        int coma = ubicacion.indexOf(',');
        String ciudad = coma < 0 ? ubicacion : ubicacion.substring(0, coma).trim();
        String provincia = coma < 0 ? "" : ubicacion.substring(coma + 1).trim();
        List<Long> principal = jdbc.queryForList(
                "SELECT id FROM direcciones WHERE usuario_id = ? ORDER BY es_principal DESC, id LIMIT 1", Long.class, usuarioId);
        if (principal.isEmpty()) {
            if (ubicacion.isEmpty()) return;
            jdbc.update("INSERT INTO direcciones (usuario_id, ciudad, provincia, es_principal, fecha_creacion) VALUES (?, ?, ?, 1, NOW())",
                    usuarioId, ciudad, provincia);
        } else {
            jdbc.update("UPDATE direcciones SET ciudad = ?, provincia = ? WHERE id = ?", ciudad, provincia, principal.get(0));
        }
    }
}
