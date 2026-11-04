package com.back.carfind.web;

import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Reportes que envían los usuarios (tabla reportes). Formas en src/types/reportes.js.
 *
 *   POST /api/web/reportes                    → { ok }   JSON { mensaje, usuarioReportadoId? }
 *   POST /api/web/publicaciones/:id/reportes  → { ok }   JSON { motivo, descripcion }
 */
@RestController
@RequestMapping("/api/web")
public class ReportesWebController {

    private static final int REPORTE_GENERAL_MAX_CARACTERES = 1000;
    private static final int REPORTE_PUBLICACION_MAX_CARACTERES = 500;
    private static final Set<String> MOTIVOS = Set.of("fraude", "informacion_incorrecta", "precio_enganoso",
            "contenido_inapropiado", "duplicada", "otro");

    private final JdbcTemplate jdbc;
    private final SesionService sesiones;
    private final PublicacionWebService publicaciones;

    public ReportesWebController(JdbcTemplate jdbc, SesionService sesiones, PublicacionWebService publicaciones) {
        this.jdbc = jdbc;
        this.sesiones = sesiones;
        this.publicaciones = publicaciones;
    }

    public record ReporteGeneral(String mensaje, Long usuarioReportadoId) {
    }

    public record ReportePublicacion(String motivo, String descripcion) {
    }

    @PostMapping("/reportes")
    public Map<String, Object> reporteGeneral(HttpServletRequest request, @RequestBody ReporteGeneral r) {
        long usuarioId = sesiones.usuarioId(request);
        String mensaje = Json.texto(r.mensaje()).trim();
        if (mensaje.isEmpty()) throw Json.error(HttpStatus.BAD_REQUEST, "Contanos qué pasó.");
        if (mensaje.length() > REPORTE_GENERAL_MAX_CARACTERES) {
            throw Json.error(HttpStatus.BAD_REQUEST, "El mensaje no puede superar los " + REPORTE_GENERAL_MAX_CARACTERES + " caracteres.");
        }
        jdbc.update("INSERT INTO reportes (usuario_id, tipo, usuario_reportado_id, descripcion, estado, fecha) VALUES (?, ?, ?, ?, 'PENDIENTE', NOW())",
                usuarioId, r.usuarioReportadoId() != null ? "usuario" : "general", r.usuarioReportadoId(), mensaje);
        return Json.obj("ok", true);
    }

    @PostMapping("/publicaciones/{id}/reportes")
    public Map<String, Object> reportePublicacion(HttpServletRequest request, @PathVariable long id,
            @RequestBody ReportePublicacion r) {
        long usuarioId = sesiones.usuarioId(request);
        publicaciones.buscar(id); // 404 si no existe
        String descripcion = Json.texto(r.descripcion()).trim();
        if (!MOTIVOS.contains(r.motivo())) throw Json.error(HttpStatus.BAD_REQUEST, "Elegí un motivo.");
        if ("otro".equals(r.motivo()) && descripcion.isEmpty()) throw Json.error(HttpStatus.BAD_REQUEST, "Contanos el motivo del reporte.");
        if (descripcion.length() > REPORTE_PUBLICACION_MAX_CARACTERES) {
            throw Json.error(HttpStatus.BAD_REQUEST, "La descripción no puede superar los " + REPORTE_PUBLICACION_MAX_CARACTERES + " caracteres.");
        }
        jdbc.update("INSERT INTO reportes (usuario_id, publicacion_id, tipo, motivo, descripcion, estado, fecha) VALUES (?, ?, 'publicacion', ?, ?, 'PENDIENTE', NOW())",
                usuarioId, id, r.motivo(), descripcion);
        return Json.obj("ok", true);
    }
}
