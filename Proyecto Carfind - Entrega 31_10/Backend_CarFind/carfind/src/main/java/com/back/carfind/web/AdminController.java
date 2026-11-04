package com.back.carfind.web;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Panel de administración. Todas las rutas responden 401 sin sesión y 403 si el usuario no tiene el rol ADMIN.
 * Formas en src/types/admin.js.
 *
 *   GET  /api/web/admin/sesion                       → { admin }
 *   GET  /api/web/admin/estadisticas?meses=6         → EstadisticasResponse
 *   GET  /api/web/admin/reportes?tipo=&estado=&desde=&hasta=   → { reportes }
 *   POST /api/web/admin/reportes/:id/respuestas      → { reporte }   JSON { texto, estado? }
 *   PUT  /api/web/admin/reportes/:id/estado          → { reporte }   JSON { estado }
 *   GET  /api/web/admin/informes                     → { informes }
 *   POST /api/web/admin/informes                     → { informe }   JSON { tipo, desde, hasta, formato }
 *   GET  /api/web/admin/informes/:id/archivo         → PDF o CSV
 */
@RestController
@RequestMapping("/api/web/admin")
public class AdminController {

    private static final Locale ES_AR = Locale.forLanguageTag("es-AR");
    private static final int RESPUESTA_MAX_CARACTERES = 1000;
    private static final Map<String, String> NOMBRES_INFORME = Map.of(
            "reportes", "Reportes recibidos", "publicaciones", "Publicaciones", "usuarios", "Usuarios registrados");
    private static final Map<String, String> NOMBRES_ESTADO = Map.of(
            "pendiente", "Pendiente", "en_revision", "En revisión", "resuelto", "Resuelto");

    private final JdbcTemplate jdbc;
    private final SesionService sesiones;

    public AdminController(JdbcTemplate jdbc, SesionService sesiones) {
        this.jdbc = jdbc;
        this.sesiones = sesiones;
    }

    public record Respuesta(String texto, String estado) {
    }

    public record CambioEstado(String estado) {
    }

    public record NuevoInforme(String tipo, String desde, String hasta, String formato) {
    }

    // ---------- Sesión ----------

    @GetMapping("/sesion")
    public Map<String, Object> sesion(HttpServletRequest request) {
        long id = sesiones.adminId(request);
        Map<String, Object> u = jdbc.queryForMap("SELECT nombre, foto_url FROM usuarios WHERE id = ?", id);
        String[] nombre = Json.separarNombre((String) u.get("nombre"));
        return Json.obj("admin", Json.obj("id", id, "nombre", nombre[0], "apellido", nombre[1], "fotoUrl", u.get("foto_url")));
    }

    // ---------- Estadísticas ----------

    @GetMapping("/estadisticas")
    public Map<String, Object> estadisticas(HttpServletRequest request, @RequestParam(defaultValue = "6") int meses) {
        sesiones.adminId(request);
        int n = Math.min(12, Math.max(1, meses));
        String activas = "FROM publicaciones p WHERE COALESCE(p.eliminado, 0) = 0";

        Double precioPromedio = jdbc.queryForObject(
                "SELECT COALESCE(AVG(CASE WHEN p.moneda = 'ARS' THEN p.precio END), AVG(p.precio), 0) " + activas, Double.class);
        Map<String, Object> resumen = Json.obj(
                "publicaciones", contar("SELECT COUNT(*) " + activas),
                "usuarios", contar("SELECT COUNT(*) FROM usuarios WHERE COALESCE(eliminado, 0) = 0"),
                "precioPromedio", precioPromedio == null ? 0 : Math.round(precioPromedio),
                "conversaciones", contar("SELECT COUNT(*) FROM chats WHERE publicacion_id IS NOT NULL"));

        List<Map<String, Object>> masGuardados = jdbc.queryForList(Consultas.PUBLICACIONES
                + " JOIN (SELECT publicacion_id, COUNT(*) AS cantidad FROM favoritos GROUP BY publicacion_id) f ON f.publicacion_id = p.id"
                + " WHERE COALESCE(p.eliminado, 0) = 0 ORDER BY f.cantidad DESC LIMIT 5")
                .stream()
                .map(p -> Json.obj("titulo", PublicacionWebService.tituloConAnio(p),
                        "cantidad", contar("SELECT COUNT(*) FROM favoritos WHERE publicacion_id = ?", p.get("id"))))
                .toList();

        YearMonth actual = YearMonth.now();
        List<String> etiquetas = new ArrayList<>();
        List<Long> publicacionesPorMes = new ArrayList<>();
        List<Long> usuariosPorMes = new ArrayList<>();
        for (int i = n - 1; i >= 0; i--) {
            YearMonth mes = actual.minusMonths(i);
            etiquetas.add(mes.getMonth().getDisplayName(TextStyle.SHORT, ES_AR).replace(".", ""));
            publicacionesPorMes.add(contar("SELECT COUNT(*) FROM publicaciones WHERE YEAR(fecha_publicacion) = ? AND MONTH(fecha_publicacion) = ?",
                    mes.getYear(), mes.getMonthValue()));
            usuariosPorMes.add(contar("SELECT COUNT(*) FROM usuarios WHERE YEAR(fecha_creacion) = ? AND MONTH(fecha_creacion) = ?",
                    mes.getYear(), mes.getMonthValue()));
        }

        String porAuto = "FROM publicaciones p JOIN autos a ON a.id = p.auto_id "
                + "LEFT JOIN modelos mo ON mo.id = a.modelo_id LEFT JOIN marcas ma ON ma.id = mo.marca_id "
                + "LEFT JOIN tipos_combustible tc ON tc.id = a.combustible_id LEFT JOIN transmisiones tr ON tr.id = a.transmision_id "
                + "WHERE COALESCE(p.eliminado, 0) = 0";
        return Json.obj(
                "resumen", resumen,
                "masGuardados", masGuardados,
                "evolucion", Json.obj("etiquetas", etiquetas, "publicaciones", publicacionesPorMes, "usuarios", usuariosPorMes),
                "distribuciones", Json.obj(
                        "marca", agrupar("ma.nombre", porAuto, 6),
                        "combustible", agrupar("tc.nombre", porAuto, 6),
                        "transmision", agrupar("tr.nombre", porAuto, 6)),
                "porUbicacion", agrupar("d.provincia", "FROM publicaciones p JOIN direcciones d ON d.usuario_id = p.usuario_id "
                        + "AND d.es_principal = 1 WHERE COALESCE(p.eliminado, 0) = 0 AND COALESCE(d.provincia, '') <> ''", 6));
    }

    /** Cantidad de publicaciones por valor de la columna, de mayor a menor. */
    private List<Map<String, Object>> agrupar(String columna, String desde, int limite) {
        return jdbc.queryForList("SELECT COALESCE(" + columna + ", 'Sin dato') AS nombre, COUNT(*) AS cantidad "
                + desde + " GROUP BY 1 ORDER BY 2 DESC LIMIT " + limite)
                .stream().map(f -> Json.obj("nombre", f.get("nombre"), "cantidad", Json.entero(f.get("cantidad")))).toList();
    }

    // ---------- Reportes ----------

    private static final String REPORTES = """
            SELECT r.*, u.nombre AS reportante, ur.nombre AS reportado
            FROM reportes r
            LEFT JOIN usuarios u ON u.id = r.usuario_id
            LEFT JOIN usuarios ur ON ur.id = r.usuario_reportado_id
            """;

    @GetMapping("/reportes")
    public Map<String, Object> reportes(HttpServletRequest request, @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String estado, @RequestParam(required = false) String desde,
            @RequestParam(required = false) String hasta) {
        sesiones.adminId(request);
        return Json.obj("reportes", buscarReportes(tipo, estado, fecha(desde), fecha(hasta)).stream().map(this::reporte).toList());
    }

    @PostMapping("/reportes/{id}/respuestas")
    public Map<String, Object> responder(HttpServletRequest request, @PathVariable long id, @RequestBody Respuesta r) {
        long adminId = sesiones.adminId(request);
        reporteFila(id);
        String texto = Json.texto(r.texto()).trim();
        if (texto.isEmpty()) throw Json.error(HttpStatus.BAD_REQUEST, "Escribí una respuesta.");
        if (texto.length() > RESPUESTA_MAX_CARACTERES) {
            throw Json.error(HttpStatus.BAD_REQUEST, "La respuesta no puede superar los " + RESPUESTA_MAX_CARACTERES + " caracteres.");
        }
        jdbc.update("INSERT INTO respuestas_reporte (reporte_id, admin_id, texto, fecha) VALUES (?, ?, ?, NOW())", id, adminId, texto);

        // La respuesta le llega a quien reportó como notificación.
        Object reportante = reporteFila(id).get("usuario_id");
        if (reportante != null) {
            jdbc.update("INSERT INTO notificaciones (usuario_id, tipo, mensaje, leido, entidad_id, fecha) VALUES (?, 'REPORTE', ?, 0, ?, NOW())",
                    reportante, "La administración respondió tu reporte: " + texto, id);
        }
        if (r.estado() != null && !r.estado().isBlank()) actualizarEstado(id, r.estado());
        return Json.obj("reporte", reporte(reporteFila(id)));
    }

    @PutMapping("/reportes/{id}/estado")
    public Map<String, Object> cambiarEstado(HttpServletRequest request, @PathVariable long id, @RequestBody CambioEstado c) {
        sesiones.adminId(request);
        reporteFila(id);
        actualizarEstado(id, c.estado());
        return Json.obj("reporte", reporte(reporteFila(id)));
    }

    private List<Map<String, Object>> buscarReportes(String tipo, String estado, LocalDate desde, LocalDate hasta) {
        StringBuilder sql = new StringBuilder(REPORTES).append(" WHERE 1 = 1");
        List<Object> args = new ArrayList<>();
        if (tipo != null && !tipo.isBlank()) {
            sql.append(" AND r.tipo = ?");
            args.add(tipo);
        }
        if (estado != null && !estado.isBlank()) {
            sql.append(" AND COALESCE(r.estado, 'PENDIENTE') = ?");
            args.add(estadoDb(estado));
        }
        if (desde != null) {
            sql.append(" AND r.fecha >= ?");
            args.add(desde.atStartOfDay());
        }
        if (hasta != null) {
            sql.append(" AND r.fecha < ?");
            args.add(hasta.plusDays(1).atStartOfDay());
        }
        sql.append(" ORDER BY r.fecha DESC, r.id DESC");
        return jdbc.queryForList(sql.toString(), args.toArray());
    }

    private Map<String, Object> reporteFila(long id) {
        List<Map<String, Object>> filas = jdbc.queryForList(REPORTES + " WHERE r.id = ?", id);
        if (filas.isEmpty()) throw Json.error(HttpStatus.NOT_FOUND, "El reporte no existe.");
        return filas.get(0);
    }

    /** ReporteRecibido de src/types/admin.js */
    private Map<String, Object> reporte(Map<String, Object> r) {
        long id = Json.entero(r.get("id"));
        String tipo = Json.texto(r.get("tipo")).isEmpty() ? "publicacion" : Json.texto(r.get("tipo"));
        String[] reportante = r.get("reportante") == null ? new String[] { "Usuario", "eliminado" }
                : Json.separarNombre((String) r.get("reportante"));

        Map<String, Object> reporte = Json.obj(
                "id", id,
                "tipo", tipo,
                "fecha", Json.fechaIso(r.get("fecha")),
                "estado", estadoFront((String) r.get("estado")),
                "reportante", Json.obj("id", r.get("usuario_id"), "nombre", reportante[0], "apellido", reportante[1]),
                "descripcion", Json.texto(r.get("descripcion")));
        if ("publicacion".equals(tipo) && r.get("publicacion_id") != null) {
            List<Map<String, Object>> p = jdbc.queryForList(Consultas.PUBLICACIONES + " WHERE p.id = ?", r.get("publicacion_id"));
            reporte.put("motivo", Json.texto(r.get("motivo")));
            reporte.put("publicacion", Json.obj("id", r.get("publicacion_id"),
                    "titulo", p.isEmpty() ? "Publicación eliminada" : PublicacionWebService.tituloConAnio(p.get(0))));
        }
        if ("usuario".equals(tipo) && r.get("usuario_reportado_id") != null) {
            String[] reportado = Json.separarNombre((String) r.get("reportado"));
            reporte.put("usuarioReportado", Json.obj("id", r.get("usuario_reportado_id"), "nombre", reportado[0], "apellido", reportado[1]));
        }
        reporte.put("respuestas", jdbc.queryForList("SELECT id, texto, fecha FROM respuestas_reporte WHERE reporte_id = ? ORDER BY fecha, id", id)
                .stream().map(x -> Json.obj("id", Json.entero(x.get("id")), "texto", x.get("texto"),
                        "fecha", Json.fechaIso(x.get("fecha")), "autor", "Administración"))
                .toList());
        return reporte;
    }

    private void actualizarEstado(long id, String estado) {
        jdbc.update("UPDATE reportes SET estado = ? WHERE id = ?", estadoDb(estado), id);
    }

    /** La tabla usa PENDIENTE / REVISADO / RESUELTO; el frontend, pendiente / en_revision / resuelto. */
    private static String estadoDb(String estado) {
        return switch (Json.texto(estado)) {
            case "pendiente" -> "PENDIENTE";
            case "en_revision" -> "REVISADO";
            case "resuelto" -> "RESUELTO";
            default -> throw Json.error(HttpStatus.BAD_REQUEST, "Estado de reporte inválido.");
        };
    }

    private static String estadoFront(String estado) {
        if ("REVISADO".equals(estado)) return "en_revision";
        if ("RESUELTO".equals(estado)) return "resuelto";
        return "pendiente";
    }

    // ---------- Informes ----------

    @GetMapping("/informes")
    public Map<String, Object> informes(HttpServletRequest request) {
        sesiones.adminId(request);
        return Json.obj("informes", jdbc.queryForList("SELECT * FROM informes_admin ORDER BY fecha DESC, id DESC")
                .stream().map(AdminController::informe).toList());
    }

    @PostMapping("/informes")
    public Map<String, Object> generarInforme(HttpServletRequest request, @RequestBody NuevoInforme datos) {
        sesiones.adminId(request);
        LocalDate desde = fecha(datos.desde());
        LocalDate hasta = fecha(datos.hasta());
        if (desde == null || hasta == null) throw Json.error(HttpStatus.BAD_REQUEST, "Elegí las fechas del informe.");
        if (desde.isAfter(hasta)) throw Json.error(HttpStatus.BAD_REQUEST, "La fecha \"Desde\" no puede ser posterior a \"Hasta\".");
        if (!NOMBRES_INFORME.containsKey(datos.tipo())) throw Json.error(HttpStatus.BAD_REQUEST, "Tipo de informe inválido.");
        if (!"pdf".equals(datos.formato()) && !"csv".equals(datos.formato())) throw Json.error(HttpStatus.BAD_REQUEST, "Formato inválido.");

        String nombre = NOMBRES_INFORME.get(datos.tipo()) + " " + desde + " a " + hasta;
        int cantidad = filasInforme(datos.tipo(), desde, hasta).size();
        long id = Consultas.insertar(jdbc,
                "INSERT INTO informes_admin (nombre, tipo, formato, desde, hasta, cantidad, fecha) VALUES (?, ?, ?, ?, ?, ?, NOW())",
                nombre, datos.tipo(), datos.formato(), desde, hasta, cantidad);
        return Json.obj("informe", informe(jdbc.queryForMap("SELECT * FROM informes_admin WHERE id = ?", id)));
    }

    @GetMapping("/informes/{id}/archivo")
    public ResponseEntity<byte[]> descargarInforme(HttpServletRequest request, @PathVariable long id) {
        sesiones.adminId(request);
        List<Map<String, Object>> filas = jdbc.queryForList("SELECT * FROM informes_admin WHERE id = ?", id);
        if (filas.isEmpty()) throw Json.error(HttpStatus.NOT_FOUND, "El informe no existe.");
        Map<String, Object> informe = filas.get(0);
        String tipo = (String) informe.get("tipo");
        String nombre = (String) informe.get("nombre");
        LocalDate desde = ((java.sql.Date) informe.get("desde")).toLocalDate();
        LocalDate hasta = ((java.sql.Date) informe.get("hasta")).toLocalDate();

        List<String> columnas = columnasInforme(tipo);
        List<List<Object>> datos = filasInforme(tipo, desde, hasta);
        boolean esCsv = "csv".equals(informe.get("formato"));
        byte[] archivo;
        if (esCsv) {
            archivo = InformeArchivos.csv(columnas, datos);
        } else {
            List<String> lineas = new ArrayList<>(List.of(
                    "Periodo: " + desde + " a " + hasta,
                    "Registros: " + datos.size(),
                    "Generado: " + Json.fechaIso(informe.get("fecha")).substring(0, 10),
                    "",
                    String.join("  |  ", columnas)));
            datos.forEach(f -> lineas.add(f.stream().map(Json::texto).collect(Collectors.joining("  |  "))));
            archivo = InformeArchivos.pdf(nombre, lineas);
        }
        String nombreArchivo = nombre.replaceAll("[^\\w-]+", "_") + (esCsv ? ".csv" : ".pdf");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + nombreArchivo + "\"")
                .contentType(esCsv ? MediaType.parseMediaType("text/csv; charset=UTF-8") : MediaType.APPLICATION_PDF)
                .body(archivo);
    }

    private static List<String> columnasInforme(String tipo) {
        return switch (tipo) {
            case "reportes" -> List.of("ID", "Tipo", "Fecha", "Estado", "Reporte", "Reportado por");
            case "publicaciones" -> List.of("ID", "Publicación", "Precio", "Estado", "Publicante", "Fecha");
            default -> List.of("ID", "Nombre", "Correo", "Teléfono", "Fecha de alta");
        };
    }

    private List<List<Object>> filasInforme(String tipo, LocalDate desde, LocalDate hasta) {
        Object inicio = desde.atStartOfDay();
        Object fin = hasta.plusDays(1).atStartOfDay();
        return switch (tipo) {
            case "reportes" -> buscarReportes(null, null, desde, hasta).stream().map(this::reporte)
                    .map(r -> List.<Object>of(r.get("id"), r.get("tipo"), Json.texto(r.get("fecha")).substring(0, 10),
                            NOMBRES_ESTADO.get(r.get("estado")), tituloReporte(r), nombreReportante(r)))
                    .toList();
            case "publicaciones" -> jdbc.queryForList(Consultas.PUBLICACIONES
                    + " WHERE p.fecha_publicacion >= ? AND p.fecha_publicacion < ? ORDER BY p.fecha_publicacion", inicio, fin)
                    .stream()
                    .map(p -> List.<Object>of(p.get("id"), PublicacionWebService.tituloConAnio(p),
                            Json.texto(p.get("moneda")) + " " + Json.texto(p.get("precio")), Json.texto(p.get("estado")),
                            Json.texto(p.get("vendedor")), Json.fechaIso(p.get("fecha_publicacion")).substring(0, 10)))
                    .toList();
            default -> jdbc.queryForList("SELECT id, nombre, email, telefono, fecha_creacion FROM usuarios "
                    + "WHERE fecha_creacion >= ? AND fecha_creacion < ? ORDER BY fecha_creacion", inicio, fin)
                    .stream()
                    .map(u -> List.<Object>of(u.get("id"), Json.texto(u.get("nombre")), Json.texto(u.get("email")),
                            Json.texto(u.get("telefono")), Json.fechaIso(u.get("fecha_creacion")).substring(0, 10)))
                    .toList();
        };
    }

    @SuppressWarnings("unchecked")
    private static String tituloReporte(Map<String, Object> r) {
        if (r.get("publicacion") instanceof Map<?, ?> p) return "Publicación: " + ((Map<String, Object>) p).get("titulo");
        if (r.get("usuarioReportado") instanceof Map<?, ?> u) {
            return "Usuario: " + Json.unirNombre((String) ((Map<String, Object>) u).get("nombre"), (String) ((Map<String, Object>) u).get("apellido"));
        }
        String descripcion = Json.texto(r.get("descripcion"));
        return descripcion.length() > 60 ? descripcion.substring(0, 57) + "..." : descripcion;
    }

    @SuppressWarnings("unchecked")
    private static String nombreReportante(Map<String, Object> r) {
        Map<String, Object> u = (Map<String, Object>) r.get("reportante");
        return Json.unirNombre((String) u.get("nombre"), (String) u.get("apellido"));
    }

    private static Map<String, Object> informe(Map<String, Object> i) {
        return Json.obj(
                "id", Json.entero(i.get("id")),
                "nombre", i.get("nombre"),
                "tipo", i.get("tipo"),
                "formato", i.get("formato"),
                "desde", Json.fechaIso(i.get("desde")),
                "hasta", Json.fechaIso(i.get("hasta")),
                "fecha", Json.fechaIso(i.get("fecha")),
                "cantidad", Json.entero(i.get("cantidad")));
    }

    // ---------- Utilidades ----------

    private long contar(String sql, Object... args) {
        Long cantidad = jdbc.queryForObject(sql, Long.class, args);
        return cantidad == null ? 0 : cantidad;
    }

    private static LocalDate fecha(String valor) {
        if (valor == null || valor.isBlank()) return null;
        try {
            return LocalDate.parse(valor);
        } catch (DateTimeParseException e) {
            throw Json.error(HttpStatus.BAD_REQUEST, "Fecha inválida: " + valor);
        }
    }
}
