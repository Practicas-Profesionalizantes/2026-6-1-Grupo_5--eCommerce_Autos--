package com.back.carfind.web;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

/**
 * Configuración del usuario logueado. Formas en src/types/configuracion.js.
 *
 *   GET /api/web/configuracion                    → ConfiguracionResponse
 *   PUT /api/web/configuracion/datos-personales   → { ok, datosPersonales }
 *   PUT /api/web/configuracion/contrasena         → { ok }   JSON { actual, nueva }
 *   PUT /api/web/configuracion/apariencia         → { ok, apariencia }
 *   PUT /api/web/configuracion/accesibilidad      → { ok, accesibilidad }
 *   PUT /api/web/configuracion/notificaciones     → { ok, notificaciones }
 *   PUT /api/web/configuracion/idioma             → { ok, idioma }
 *
 * Lo que tiene columna en preferencias_usuario (tema, idioma, tamaño de fuente, canales de aviso)
 * se guarda ahí; el resto, en preferencias_usuario.config_json.
 */
@RestController
@RequestMapping("/api/web/configuracion")
public class ConfiguracionController {

    private static final int CONTRASENA_MIN_LARGO = 8;
    private static final List<String> TIPOS_AVISO = List.of("mensajes", "preguntas", "bajaPrecioWishlist",
            "publicacionNoDisponible", "estadoPublicaciones", "novedades", "seguridadCuenta");

    private final JdbcTemplate jdbc;
    private final SesionService sesiones;
    private final PasswordEncoder encoder;
    private final ObjectMapper mapper;

    public ConfiguracionController(JdbcTemplate jdbc, SesionService sesiones, PasswordEncoder encoder, ObjectMapper mapper) {
        this.jdbc = jdbc;
        this.sesiones = sesiones;
        this.encoder = encoder;
        this.mapper = mapper;
    }

    public record DatosPersonales(String nombre, String apellido, String genero, String correo, List<String> telefonos,
            String fechaNacimiento, String direccion, List<String> otrasDirecciones) {
    }

    public record CambioContrasena(String actual, String nueva) {
    }

    @GetMapping
    public Map<String, Object> obtener(HttpServletRequest request) {
        long id = sesiones.usuarioId(request);
        Map<String, Object> prefs = preferencias(id);
        Map<String, Object> extra = extra(prefs);
        return Json.obj(
                "datosPersonales", datosPersonales(id, extra),
                "apariencia", apariencia(prefs, extra),
                "accesibilidad", seccion(extra, "accesibilidad",
                        Json.obj("atajosTeclado", false, "animaciones", "sistema", "subrayadoEnlaces", "mostrar")),
                "notificaciones", notificaciones(prefs, extra),
                "idioma", idioma(prefs, extra));
    }

    @PutMapping("/datos-personales")
    @Transactional
    public Map<String, Object> guardarDatosPersonales(HttpServletRequest request, @RequestBody DatosPersonales d) {
        long id = sesiones.usuarioId(request);
        String correo = Json.texto(d.correo()).trim().toLowerCase();
        List<String> telefonos = limpiar(d.telefonos());
        if (Json.texto(d.nombre()).isBlank()) throw Json.error(HttpStatus.BAD_REQUEST, "El nombre es obligatorio.");
        if (!correo.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) throw Json.error(HttpStatus.BAD_REQUEST, "El correo no es válido.");
        if (telefonos.isEmpty()) throw Json.error(HttpStatus.BAD_REQUEST, "Ingresá al menos un teléfono.");
        Integer otro = jdbc.queryForObject("SELECT COUNT(*) FROM usuarios WHERE LOWER(email) = ? AND id <> ?", Integer.class, correo, id);
        if (otro != null && otro > 0) throw Json.error(HttpStatus.CONFLICT, "Ese correo ya lo usa otra cuenta.");

        LocalDate nacimiento = null;
        if (!Json.texto(d.fechaNacimiento()).isBlank()) {
            try {
                nacimiento = LocalDate.parse(d.fechaNacimiento());
            } catch (DateTimeParseException e) {
                throw Json.error(HttpStatus.BAD_REQUEST, "La fecha de nacimiento no es válida.");
            }
        }

        jdbc.update("UPDATE usuarios SET nombre = ?, email = ?, telefono = ?, genero = ?, fecha_nacimiento = ? WHERE id = ?",
                Json.unirNombre(d.nombre(), d.apellido()), correo, telefonos.get(0), Json.texto(d.genero()), nacimiento, id);

        // Dirección principal + otras direcciones (tabla direcciones). La ciudad/provincia de la principal se conservan.
        List<Long> principal = jdbc.queryForList(
                "SELECT id FROM direcciones WHERE usuario_id = ? ORDER BY es_principal DESC, id LIMIT 1", Long.class, id);
        if (principal.isEmpty()) {
            jdbc.update("INSERT INTO direcciones (usuario_id, direccion, es_principal, fecha_creacion) VALUES (?, ?, 1, NOW())",
                    id, Json.texto(d.direccion()).trim());
        } else {
            jdbc.update("UPDATE direcciones SET direccion = ?, es_principal = 1 WHERE id = ?", Json.texto(d.direccion()).trim(), principal.get(0));
            jdbc.update("DELETE FROM direcciones WHERE usuario_id = ? AND id <> ?", id, principal.get(0));
        }
        for (String direccion : limpiar(d.otrasDirecciones())) {
            jdbc.update("INSERT INTO direcciones (usuario_id, direccion, es_principal, fecha_creacion) VALUES (?, ?, 0, NOW())", id, direccion);
        }

        guardarExtra(id, "telefonosAdicionales", telefonos.subList(1, telefonos.size()));
        return Json.obj("ok", true, "datosPersonales", datosPersonales(id, extra(preferencias(id))));
    }

    @PutMapping("/contrasena")
    public Map<String, Object> cambiarContrasena(HttpServletRequest request, @RequestBody CambioContrasena c) {
        long id = sesiones.usuarioId(request);
        String hash = jdbc.queryForObject("SELECT password_hash FROM usuarios WHERE id = ?", String.class, id);
        boolean coincide;
        try {
            coincide = encoder.matches(Json.texto(c.actual()), hash);
        } catch (IllegalArgumentException e) {
            coincide = false;
        }
        if (!coincide) throw Json.error(HttpStatus.BAD_REQUEST, "La contraseña actual no es correcta.");
        if (Json.texto(c.nueva()).length() < CONTRASENA_MIN_LARGO) {
            throw Json.error(HttpStatus.BAD_REQUEST, "La contraseña nueva debe tener al menos " + CONTRASENA_MIN_LARGO + " caracteres.");
        }
        jdbc.update("UPDATE usuarios SET password_hash = ? WHERE id = ?", encoder.encode(c.nueva()), id);
        return Json.obj("ok", true);
    }

    @PutMapping("/apariencia")
    public Map<String, Object> guardarApariencia(HttpServletRequest request, @RequestBody Map<String, Object> apariencia) {
        long id = sesiones.usuarioId(request);
        String tema = switch (Json.texto(apariencia.get("tema"))) {
            case "oscuro" -> "OSCURO";
            case "claro" -> "CLARO";
            default -> "SISTEMA";
        };
        double escala = Json.decimal(apariencia.getOrDefault("escalaFuente", 100));
        String tamanio = escala < 95 ? "PEQUENA" : escala > 110 ? "GRANDE" : "MEDIANA";
        jdbc.update("UPDATE preferencias_usuario SET tema = ?, tamanio_fuente = ? WHERE usuario_id = ?", tema, tamanio, id);
        guardarExtra(id, "apariencia", apariencia);
        return Json.obj("ok", true, "apariencia", apariencia);
    }

    @PutMapping("/accesibilidad")
    public Map<String, Object> guardarAccesibilidad(HttpServletRequest request, @RequestBody Map<String, Object> accesibilidad) {
        long id = sesiones.usuarioId(request);
        boolean personalizada = Json.bool(accesibilidad.get("atajosTeclado"))
                || !"sistema".equals(accesibilidad.get("animaciones"))
                || !"mostrar".equals(accesibilidad.get("subrayadoEnlaces"));
        jdbc.update("UPDATE preferencias_usuario SET accesibilidad = ? WHERE usuario_id = ?", personalizada, id);
        guardarExtra(id, "accesibilidad", accesibilidad);
        return Json.obj("ok", true, "accesibilidad", accesibilidad);
    }

    @PutMapping("/notificaciones")
    public Map<String, Object> guardarNotificaciones(HttpServletRequest request, @RequestBody Map<String, Object> notificaciones) {
        long id = sesiones.usuarioId(request);
        Map<?, ?> canales = notificaciones.get("canales") instanceof Map<?, ?> m ? m : Map.of();
        jdbc.update("UPDATE preferencias_usuario SET notificaciones_push = ?, notificaciones_email = ? WHERE usuario_id = ?",
                Json.bool(canales.get("app")), Json.bool(canales.get("correo")), id);
        guardarExtra(id, "notificaciones", notificaciones);
        return Json.obj("ok", true, "notificaciones", notificaciones);
    }

    @PutMapping("/idioma")
    public Map<String, Object> guardarIdioma(HttpServletRequest request, @RequestBody Map<String, Object> idioma) {
        long id = sesiones.usuarioId(request);
        String preferido = Json.texto(idioma.get("preferido"));
        if (preferido.isEmpty() || preferido.length() > 10) throw Json.error(HttpStatus.BAD_REQUEST, "Elegí un idioma válido.");
        jdbc.update("UPDATE preferencias_usuario SET idioma = ? WHERE usuario_id = ?", preferido, id);
        guardarExtra(id, "idioma", idioma);
        return Json.obj("ok", true, "idioma", idioma);
    }

    private Map<String, Object> datosPersonales(long id, Map<String, Object> extra) {
        Map<String, Object> u = jdbc.queryForMap(
                "SELECT nombre, email, telefono, genero, fecha_nacimiento, foto_url FROM usuarios WHERE id = ?", id);
        String[] nombre = Json.separarNombre((String) u.get("nombre"));
        List<String> direcciones = jdbc.queryForList(
                "SELECT COALESCE(direccion, '') FROM direcciones WHERE usuario_id = ? ORDER BY es_principal DESC, id", String.class, id);
        List<String> telefonos = new ArrayList<>();
        telefonos.add(Json.texto(u.get("telefono")));
        telefonos.addAll(listaTextos(extra.get("telefonosAdicionales")));
        String nacimiento = Json.fechaIso(u.get("fecha_nacimiento"));
        return Json.obj(
                "id", id,
                "nombre", nombre[0],
                "apellido", nombre[1],
                "genero", Json.texto(u.get("genero")),
                "correo", Json.texto(u.get("email")),
                "telefonos", telefonos,
                "fechaNacimiento", nacimiento == null ? "" : nacimiento,
                "direccion", direcciones.isEmpty() ? "" : direcciones.get(0),
                "otrasDirecciones", direcciones.isEmpty() ? List.of() : direcciones.subList(1, direcciones.size()),
                "fotoUrl", u.get("foto_url"));
    }

    private Map<String, Object> apariencia(Map<String, Object> prefs, Map<String, Object> extra) {
        String tema = switch (Json.texto(prefs.get("tema"))) {
            case "OSCURO" -> "oscuro";
            case "CLARO" -> "claro";
            default -> "sistema";
        };
        int escala = switch (Json.texto(prefs.get("tamanio_fuente"))) {
            case "PEQUENA" -> 90;
            case "GRANDE" -> 115;
            default -> 100;
        };
        Map<String, Object> resultado = seccion(extra, "apariencia", Json.obj("escalaFuente", escala, "fuente", "predeterminada"));
        resultado.put("tema", tema); // el tema siempre sale de su columna
        return resultado;
    }

    private Map<String, Object> notificaciones(Map<String, Object> prefs, Map<String, Object> extra) {
        Map<String, Object> avisos = new LinkedHashMap<>();
        TIPOS_AVISO.forEach(t -> avisos.put(t, Json.obj("app", true, "correo", !"novedades".equals(t))));
        Map<String, Object> resultado = seccion(extra, "notificaciones", Json.obj(
                "avisos", avisos,
                "frecuenciaCorreo", "inmediato",
                "noMolestar", Json.obj("activo", false, "desde", "22:00", "hasta", "08:00")));
        resultado.put("canales", Json.obj("app", Json.bool(prefs.get("notificaciones_push")),
                "correo", Json.bool(prefs.get("notificaciones_email"))));
        return resultado;
    }

    private Map<String, Object> idioma(Map<String, Object> prefs, Map<String, Object> extra) {
        Map<String, Object> resultado = seccion(extra, "idioma", Json.obj("otros", List.of(), "agregarAutomaticamente", true));
        String preferido = Json.texto(prefs.get("idioma"));
        resultado.put("preferido", preferido.isEmpty() || "es".equals(preferido) ? "es-AR" : preferido);
        return resultado;
    }

    /** Fila de preferencias_usuario (la crea con los valores por defecto si el usuario no tenía). */
    private Map<String, Object> preferencias(long id) {
        jdbc.update("INSERT IGNORE INTO preferencias_usuario (usuario_id) VALUES (?)", id);
        return jdbc.queryForMap("SELECT * FROM preferencias_usuario WHERE usuario_id = ?", id);
    }

    private Map<String, Object> extra(Map<String, Object> prefs) {
        String json = (String) prefs.get("config_json");
        if (json == null || json.isBlank()) return new LinkedHashMap<>();
        return mapper.readValue(json, new TypeReference<LinkedHashMap<String, Object>>() {
        });
    }

    private void guardarExtra(long id, String clave, Object valor) {
        Map<String, Object> extra = extra(preferencias(id));
        extra.put(clave, valor);
        jdbc.update("UPDATE preferencias_usuario SET config_json = ? WHERE usuario_id = ?", mapper.writeValueAsString(extra), id);
    }

    /** Lo guardado en config_json para esa sección, completado con los valores por defecto. */
    private static Map<String, Object> seccion(Map<String, Object> extra, String clave, Map<String, Object> defecto) {
        Map<String, Object> resultado = new LinkedHashMap<>(defecto);
        if (extra.get(clave) instanceof Map<?, ?> guardado) {
            guardado.forEach((k, v) -> resultado.put(k.toString(), v));
        }
        return resultado;
    }

    private static List<String> listaTextos(Object valor) {
        if (!(valor instanceof List<?> lista)) return List.of();
        return lista.stream().map(Json::texto).toList();
    }

    private static List<String> limpiar(List<String> valores) {
        return valores == null ? new ArrayList<>()
                : new ArrayList<>(valores.stream().map(Json::texto).map(String::trim).filter(s -> !s.isEmpty()).toList());
    }
}
