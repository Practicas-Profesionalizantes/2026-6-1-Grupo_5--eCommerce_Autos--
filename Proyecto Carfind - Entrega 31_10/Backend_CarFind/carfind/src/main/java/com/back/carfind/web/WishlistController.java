package com.back.carfind.web;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Wishlist del usuario logueado (tabla favoritos). Formas en src/types/wishlist.js.
 *
 *   GET    /api/web/wishlist        → WishlistResponse
 *   POST   /api/web/wishlist        → WishlistMutationResponse   JSON { itemId }
 *   DELETE /api/web/wishlist/:id    → WishlistMutationResponse
 */
@RestController
@RequestMapping("/api/web/wishlist")
public class WishlistController {

    private final JdbcTemplate jdbc;
    private final SesionService sesiones;
    private final PublicacionWebService publicaciones;

    public WishlistController(JdbcTemplate jdbc, SesionService sesiones, PublicacionWebService publicaciones) {
        this.jdbc = jdbc;
        this.sesiones = sesiones;
        this.publicaciones = publicaciones;
    }

    public record AgregarItem(Long itemId) {
    }

    @GetMapping
    public Map<String, Object> obtener(HttpServletRequest request) {
        return wishlist(sesiones.usuarioId(request));
    }

    @PostMapping
    public Map<String, Object> agregar(HttpServletRequest request, @RequestBody AgregarItem body) {
        long usuarioId = sesiones.usuarioId(request);
        publicaciones.buscar(body.itemId()); // 404 si no existe
        jdbc.update("INSERT IGNORE INTO favoritos (usuario_id, publicacion_id) VALUES (?, ?)", usuarioId, body.itemId());
        return Json.obj("ok", true, "itemId", body.itemId(), "wishlist", wishlist(usuarioId));
    }

    @DeleteMapping("/{itemId}")
    public Map<String, Object> quitar(HttpServletRequest request, @PathVariable long itemId) {
        long usuarioId = sesiones.usuarioId(request);
        jdbc.update("DELETE FROM favoritos WHERE usuario_id = ? AND publicacion_id = ?", usuarioId, itemId);
        return Json.obj("ok", true, "itemId", itemId, "wishlist", wishlist(usuarioId));
    }

    private Map<String, Object> wishlist(long usuarioId) {
        List<Map<String, Object>> items = jdbc.queryForList(Consultas.PUBLICACIONES
                + " JOIN favoritos f ON f.publicacion_id = p.id"
                + " WHERE f.usuario_id = ? AND COALESCE(p.eliminado, 0) = 0 ORDER BY p.fecha_publicacion DESC", usuarioId)
                .stream()
                .map(p -> Json.obj(
                        "id", Json.entero(p.get("id")),
                        "titulo", PublicacionWebService.tituloConAnio(p),
                        "estado", Json.entero(p.get("kms")) == 0 ? "Nuevo" : "Usado",
                        "kilometraje", Json.entero(p.get("kms")),
                        "combustible", combustible(p.get("combustible")),
                        "imagenUrl", p.get("imagen_url"),
                        "precio", Json.decimal(p.get("precio"))))
                .toList();
        double total = items.stream().mapToDouble(i -> (double) i.get("precio")).sum();
        return Json.obj(
                "items", items,
                "resumen", Json.obj(
                        "items", items.stream().map(i -> Json.obj("id", i.get("id"), "titulo", i.get("titulo"), "precio", i.get("precio"))).toList(),
                        "total", total,
                        "moneda", "ARS"));
    }

    /** Nombre de la tabla tipos_combustible → valor de Combustible en src/types/wishlist.js. */
    private static String combustible(Object nombre) {
        String n = Json.texto(nombre).toLowerCase(Locale.ROOT);
        if (n.contains("gasoil") || n.contains("diesel") || n.contains("diésel")) return "DIESEL";
        if (n.contains("eléctr") || n.contains("electr")) return "ELECTRICO";
        if (n.contains("híbr") || n.contains("hibr")) return "HIBRIDO";
        if (n.contains("gnc")) return "GNC";
        return n.isEmpty() ? "" : "NAFTA";
    }
}
