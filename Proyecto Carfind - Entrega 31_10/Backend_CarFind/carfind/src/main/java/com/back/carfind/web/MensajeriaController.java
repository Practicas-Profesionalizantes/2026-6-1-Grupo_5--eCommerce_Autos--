package com.back.carfind.web;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Mensajería entre comprador y publicante (tablas chats y mensajes). Formas en src/types/mensajeria.js.
 *
 *   GET  /api/web/conversaciones                             → { conversaciones }
 *   GET  /api/web/conversaciones/:id/mensajes[?desde=<id>]   → { conversacion, mensajes }  (con desde: solo { mensajes })
 *   POST /api/web/conversaciones/:id/mensajes                → { mensaje }   multipart: "texto" y "imagen" (opcional)
 *   POST /api/web/conversaciones                             → { conversacionId }   JSON { publicacionId }
 *   PUT  /api/web/conversaciones/:id/leida                   → { ok }
 */
@RestController
@RequestMapping("/api/web/conversaciones")
public class MensajeriaController {

    private static final int MENSAJE_MAX_CARACTERES = 2000;

    private final JdbcTemplate jdbc;
    private final SesionService sesiones;
    private final PublicacionWebService publicaciones;
    private final ArchivoService archivos;

    public MensajeriaController(JdbcTemplate jdbc, SesionService sesiones, PublicacionWebService publicaciones,
            ArchivoService archivos) {
        this.jdbc = jdbc;
        this.sesiones = sesiones;
        this.publicaciones = publicaciones;
        this.archivos = archivos;
    }

    public record NuevaConversacion(Long publicacionId) {
    }

    @GetMapping
    public Map<String, Object> listar(HttpServletRequest request) {
        long yo = sesiones.usuarioId(request);
        List<Map<String, Object>> conversaciones = jdbc
                .queryForList("SELECT * FROM chats WHERE usuario1_id = ? OR usuario2_id = ?", yo, yo)
                .stream()
                .map(chat -> conversacion(chat, yo))
                .sorted(Comparator.comparing((Map<String, Object> c) -> fechaOrden(c)).reversed())
                .toList();
        return Json.obj("conversaciones", conversaciones);
    }

    @GetMapping("/{id}/mensajes")
    public Map<String, Object> mensajes(HttpServletRequest request, @PathVariable long id,
            @RequestParam(required = false) Long desde) {
        long yo = sesiones.usuarioId(request);
        Map<String, Object> chat = chat(id, yo);
        List<Map<String, Object>> mensajes = jdbc.queryForList(
                "SELECT * FROM mensajes WHERE chat_id = ? AND id > ? ORDER BY fecha, id", id, desde == null ? 0 : desde)
                .stream().map(m -> mensaje(m, yo)).toList();
        return desde != null ? Json.obj("mensajes", mensajes)
                : Json.obj("conversacion", conversacion(chat, yo), "mensajes", mensajes);
    }

    @PostMapping("/{id}/mensajes")
    public Map<String, Object> enviar(HttpServletRequest request, @PathVariable long id,
            @RequestParam(defaultValue = "") String texto,
            @RequestParam(required = false) MultipartFile imagen) {
        long yo = sesiones.usuarioId(request);
        chat(id, yo);
        String contenido = texto.trim();
        boolean conImagen = imagen != null && !imagen.isEmpty();
        if (contenido.isEmpty() && !conImagen) throw Json.error(HttpStatus.BAD_REQUEST, "El mensaje está vacío.");
        if (contenido.length() > MENSAJE_MAX_CARACTERES) {
            throw Json.error(HttpStatus.BAD_REQUEST, "El mensaje no puede superar los " + MENSAJE_MAX_CARACTERES + " caracteres.");
        }
        String imagenUrl = conImagen ? archivos.guardarImagen(imagen) : null;
        long mensajeId = Consultas.insertar(jdbc,
                "INSERT INTO mensajes (chat_id, emisor_id, contenido, imagen_url, fecha, leido) VALUES (?, ?, ?, ?, NOW(), 0)",
                id, yo, contenido, imagenUrl);
        return Json.obj("mensaje", mensaje(jdbc.queryForMap("SELECT * FROM mensajes WHERE id = ?", mensajeId), yo));
    }

    @PostMapping
    public Map<String, Object> iniciar(HttpServletRequest request, @RequestBody NuevaConversacion body) {
        long yo = sesiones.usuarioId(request);
        Map<String, Object> publicacion = publicaciones.buscar(body.publicacionId());
        long publicante = Json.entero(publicacion.get("usuario_id"));
        if (publicante == yo) throw Json.error(HttpStatus.BAD_REQUEST, "Esta publicación es tuya: no podés preguntarte a vos mismo.");

        List<Long> existente = jdbc.queryForList(
                "SELECT id FROM chats WHERE publicacion_id = ? AND ((usuario1_id = ? AND usuario2_id = ?) OR (usuario1_id = ? AND usuario2_id = ?))",
                Long.class, body.publicacionId(), yo, publicante, publicante, yo);
        long chatId = !existente.isEmpty() ? existente.get(0) : Consultas.insertar(jdbc,
                "INSERT INTO chats (usuario1_id, usuario2_id, publicacion_id, fecha_creacion) VALUES (?, ?, ?, NOW())",
                yo, publicante, body.publicacionId());
        if (existente.isEmpty()) {
            jdbc.update("UPDATE estadisticas_publicacion SET contactos = COALESCE(contactos, 0) + 1 WHERE publicacion_id = ?",
                    body.publicacionId());
        }
        return Json.obj("conversacionId", chatId);
    }

    @PutMapping("/{id}/leida")
    public Map<String, Object> marcarLeida(HttpServletRequest request, @PathVariable long id) {
        long yo = sesiones.usuarioId(request);
        chat(id, yo);
        jdbc.update("UPDATE mensajes SET leido = 1 WHERE chat_id = ? AND emisor_id <> ?", id, yo);
        return Json.obj("ok", true);
    }

    /** El chat, solo si el usuario logueado participa. */
    private Map<String, Object> chat(long id, long yo) {
        List<Map<String, Object>> filas = jdbc.queryForList(
                "SELECT * FROM chats WHERE id = ? AND (usuario1_id = ? OR usuario2_id = ?)", id, yo, yo);
        if (filas.isEmpty()) throw Json.error(HttpStatus.NOT_FOUND, "La conversación no existe.");
        return filas.get(0);
    }

    private Map<String, Object> conversacion(Map<String, Object> chat, long yo) {
        long chatId = Json.entero(chat.get("id"));
        long u1 = Json.entero(chat.get("usuario1_id"));
        long otroId = u1 == yo ? Json.entero(chat.get("usuario2_id")) : u1;

        Map<String, Object> otro = jdbc.queryForMap("SELECT nombre, telefono, foto_url FROM usuarios WHERE id = ?", otroId);
        String[] nombre = Json.separarNombre((String) otro.get("nombre"));

        Map<String, Object> publicacion = null;
        String rol = "Comprador";
        if (chat.get("publicacion_id") != null) {
            List<Map<String, Object>> filas = jdbc.queryForList(Consultas.PUBLICACIONES + " WHERE p.id = ?", chat.get("publicacion_id"));
            if (!filas.isEmpty()) {
                Map<String, Object> p = filas.get(0);
                if (Json.entero(p.get("usuario_id")) == otroId) rol = "Vendedor";
                publicacion = Json.obj("id", Json.entero(p.get("id")), "titulo", PublicacionWebService.tituloConAnio(p),
                        "precio", Json.decimal(p.get("precio")), "moneda", Json.texto(p.get("moneda")), "imagenUrl", p.get("imagen_url"));
            }
        }

        List<Map<String, Object>> ultimo = jdbc.queryForList(
                "SELECT contenido, imagen_url, fecha, emisor_id FROM mensajes WHERE chat_id = ? ORDER BY fecha DESC, id DESC LIMIT 1", chatId);
        Map<String, Object> ultimoMensaje = ultimo.isEmpty() ? null : Json.obj(
                "texto", textoVisible(ultimo.get(0)),
                "fecha", Json.fechaIso(ultimo.get(0).get("fecha")),
                "propio", Json.entero(ultimo.get(0).get("emisor_id")) == yo);
        Integer noLeidos = jdbc.queryForObject(
                "SELECT COUNT(*) FROM mensajes WHERE chat_id = ? AND emisor_id <> ? AND COALESCE(leido, 0) = 0", Integer.class, chatId, yo);

        return Json.obj(
                "id", chatId,
                "otroUsuario", Json.obj("id", otroId, "nombre", nombre[0], "apellido", nombre[1],
                        "fotoUrl", otro.get("foto_url"), "telefono", Json.texto(otro.get("telefono"))),
                "rolOtroUsuario", rol,
                "publicacion", publicacion,
                "ultimoMensaje", ultimoMensaje,
                "noLeidos", noLeidos == null ? 0 : noLeidos,
                "fechaCreacion", Json.fechaIso(chat.get("fecha_creacion")));
    }

    private static Map<String, Object> mensaje(Map<String, Object> m, long yo) {
        return Json.obj(
                "id", Json.entero(m.get("id")),
                "conversacionId", Json.entero(m.get("chat_id")),
                "texto", Json.texto(m.get("contenido")),
                "imagenUrl", m.get("imagen_url"),
                "fecha", Json.fechaIso(m.get("fecha")),
                "propio", Json.entero(m.get("emisor_id")) == yo,
                "estado", Json.bool(m.get("leido")) ? "leido" : "entregado");
    }

    private static String textoVisible(Map<String, Object> m) {
        String texto = Json.texto(m.get("contenido"));
        return texto.isEmpty() && m.get("imagen_url") != null ? "📷 Foto" : texto;
    }

    @SuppressWarnings("unchecked")
    private static String fechaOrden(Map<String, Object> conversacion) {
        Map<String, Object> ultimo = (Map<String, Object>) conversacion.get("ultimoMensaje");
        Object fecha = ultimo != null ? ultimo.get("fecha") : conversacion.get("fechaCreacion");
        return fecha == null ? "" : fecha.toString();
    }
}
