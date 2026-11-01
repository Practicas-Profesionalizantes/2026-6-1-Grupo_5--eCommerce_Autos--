package com.back.carfind.web;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Publicaciones para el frontend.
 *
 *   GET  /api/web/publicaciones              → [{ id, titulo, precio, moneda, imagen, vendedor }]
 *        ?q=&marca=&modelo=&anio=&kmsMax=&precioMax=&excluir=&limite=
 *   GET  /api/web/publicaciones/filtros      → { marca[], modelo[], anio[] }
 *   GET  /api/web/publicaciones/:id          → detalle
 *   GET  /api/web/publicaciones/:id/resumen  → PublicacionPerfilItem
 *   POST /api/web/publicaciones              → { id }   multipart: campos del formulario + "fotos"
 *
 * Solo para el dueño de la publicación (403 si es de otra persona):
 *   GET    /api/web/publicaciones/:id/edicion → datos para el formulario de edición
 *   PUT    /api/web/publicaciones/:id         → { ok }   multipart: campos + "fotosConservadas" (URLs) + "fotos" nuevas
 *   PUT    /api/web/publicaciones/:id/estado  → { ok, estado }   JSON { estado: "ACTIVA" | "PAUSADA" }
 *   DELETE /api/web/publicaciones/:id         → { ok }
 */
@RestController
@RequestMapping("/api/web/publicaciones")
public class PublicacionWebController {

    private final PublicacionWebService service;
    private final SesionService sesiones;

    public PublicacionWebController(PublicacionWebService service, SesionService sesiones) {
        this.service = service;
        this.sesiones = sesiones;
    }

    @GetMapping
    public List<Map<String, Object>> listar(PublicacionWebService.Filtros filtros) {
        return service.listar(filtros);
    }

    @GetMapping("/filtros")
    public Map<String, Object> filtros() {
        return service.opcionesFiltros();
    }

    @GetMapping("/{id}")
    public Map<String, Object> detalle(HttpServletRequest request, @PathVariable long id) {
        return service.detalle(id, sesiones.usuarioIdOpcional(request));
    }

    @GetMapping("/{id}/resumen")
    public Map<String, Object> resumen(@PathVariable long id) {
        return service.resumen(id);
    }

    @PostMapping
    public Map<String, Object> crear(HttpServletRequest request, PublicacionWebService.NuevaPublicacion datos,
            @RequestParam(name = "fotos", required = false) List<MultipartFile> fotos) {
        long usuarioId = sesiones.usuarioId(request);
        return Json.obj("id", service.crear(usuarioId, datos, fotos));
    }

    public record CambioEstado(String estado) {
    }

    @GetMapping("/{id}/edicion")
    public Map<String, Object> paraEditar(HttpServletRequest request, @PathVariable long id) {
        return service.paraEditar(sesiones.usuarioId(request), id);
    }

    @PutMapping("/{id}")
    public Map<String, Object> actualizar(HttpServletRequest request, @PathVariable long id,
            PublicacionWebService.NuevaPublicacion datos,
            @RequestParam(name = "fotosConservadas", required = false) List<String> fotosConservadas,
            @RequestParam(name = "fotos", required = false) List<MultipartFile> fotos) {
        service.actualizar(sesiones.usuarioId(request), id, datos, fotosConservadas, fotos);
        return Json.obj("ok", true);
    }

    @PutMapping("/{id}/estado")
    public Map<String, Object> cambiarEstado(HttpServletRequest request, @PathVariable long id, @RequestBody CambioEstado body) {
        service.cambiarEstado(sesiones.usuarioId(request), id, body.estado());
        return Json.obj("ok", true, "estado", body.estado());
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> eliminar(HttpServletRequest request, @PathVariable long id) {
        service.eliminar(sesiones.usuarioId(request), id);
        return Json.obj("ok", true);
    }
}
