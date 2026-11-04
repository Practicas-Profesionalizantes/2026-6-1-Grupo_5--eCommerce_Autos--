package com.back.carfind.web;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Guarda las imágenes que sube el frontend (publicaciones, perfil y chat) en la tabla archivos
 * y devuelve su URL pública (/uploads/<nombre>, la sirve ArchivosController).
 *
 * Se guardan en la base y no en disco porque en hostings gratuitos (Render) el disco se borra
 * cada vez que el servidor se reinicia o se duerme.
 */
@Service
public class ArchivoService {

    private static final Map<String, String> TIPOS = Map.of(
            "jpg", "image/jpeg", "jpeg", "image/jpeg", "png", "image/png", "gif", "image/gif",
            "webp", "image/webp", "heic", "image/heic", "heif", "image/heif");

    private final JdbcTemplate jdbc;

    public ArchivoService(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /** Una imagen guardada: su tipo MIME y su contenido. */
    public record Archivo(String tipo, byte[] datos) {
    }

    public String guardarImagen(MultipartFile archivo) {
        String extension = extension(archivo);
        String tipo = archivo.getContentType();
        boolean esImagen = (tipo != null && tipo.startsWith("image/")) || TIPOS.containsKey(extension);
        if (archivo.isEmpty() || !esImagen) {
            throw Json.error(HttpStatus.BAD_REQUEST, "El archivo tiene que ser una imagen.");
        }
        if (tipo == null || !tipo.startsWith("image/")) tipo = TIPOS.get(extension);

        String nombre = UUID.randomUUID() + "." + (TIPOS.containsKey(extension) ? extension : "jpg");
        try {
            jdbc.update("INSERT INTO archivos (nombre, tipo, datos, fecha) VALUES (?, ?, ?, NOW())",
                    nombre, tipo, archivo.getBytes());
        } catch (IOException e) {
            throw Json.error(HttpStatus.INTERNAL_SERVER_ERROR, "No pudimos guardar la imagen. Intentá de nuevo.");
        }
        return "/uploads/" + nombre;
    }

    public Optional<Archivo> buscar(String nombre) {
        List<Archivo> filas = jdbc.query("SELECT tipo, datos FROM archivos WHERE nombre = ?",
                (rs, i) -> new Archivo(rs.getString("tipo"), rs.getBytes("datos")), nombre);
        return filas.stream().findFirst();
    }

    /** Borra una imagen subida antes (solo las que viven en /uploads). */
    public void eliminar(String url) {
        if (url == null || !url.startsWith("/uploads/")) return;
        jdbc.update("DELETE FROM archivos WHERE nombre = ?", url.substring("/uploads/".length()));
    }

    private static String extension(MultipartFile archivo) {
        String nombre = archivo.getOriginalFilename();
        if (nombre == null || !nombre.contains(".")) return "";
        return nombre.substring(nombre.lastIndexOf('.') + 1).toLowerCase(Locale.ROOT);
    }
}
