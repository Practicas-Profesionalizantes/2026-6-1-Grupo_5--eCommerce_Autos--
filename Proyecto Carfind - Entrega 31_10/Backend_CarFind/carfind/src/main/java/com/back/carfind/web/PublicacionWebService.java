package com.back.carfind.web;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/** Publicaciones: listado del inicio, detalle, resumen y alta desde "Crear publicación". */
@Service
public class PublicacionWebService {

    private static final int MAX_FOTOS = 10;
    private static final String VISIBLE = " WHERE COALESCE(p.eliminado, 0) = 0";
    private static final String ESTADO_ACTIVA = "ACTIVA";
    private static final String ESTADO_PAUSADA = "PAUSADA";
    private static final String PREFIJO_DETALLES = "Detalles: ";
    private static final String PREFIJO_PAPELES = "Documentación faltante: ";

    private final JdbcTemplate jdbc;
    private final ArchivoService archivos;

    public PublicacionWebService(JdbcTemplate jdbc, ArchivoService archivos) {
        this.jdbc = jdbc;
        this.archivos = archivos;
    }

    /** Filtros del inicio. Todos opcionales. */
    public record Filtros(String q, String marca, String modelo, Integer anio, Integer kmsMax, Double precioMax,
            Long excluir, Integer limite) {
    }

    /** Tarjetas del inicio (ProductCard): solo publicaciones activas. */
    public List<Map<String, Object>> listar(Filtros f) {
        StringBuilder sql = new StringBuilder(Consultas.PUBLICACIONES).append(VISIBLE).append(" AND p.estado = 'ACTIVA'");
        List<Object> args = new ArrayList<>();
        if (tiene(f.q())) {
            sql.append(" AND CONCAT_WS(' ', ma.nombre, mo.nombre, a.versionn, a.anio, a.color) LIKE ?");
            args.add("%" + f.q().trim() + "%");
        }
        if (tiene(f.marca())) {
            sql.append(" AND ma.nombre = ?");
            args.add(f.marca());
        }
        if (tiene(f.modelo())) {
            sql.append(" AND mo.nombre = ?");
            args.add(f.modelo());
        }
        if (f.anio() != null) {
            sql.append(" AND a.anio = ?");
            args.add(f.anio());
        }
        if (f.kmsMax() != null) {
            sql.append(" AND a.kms <= ?");
            args.add(f.kmsMax());
        }
        if (f.precioMax() != null) {
            sql.append(" AND p.precio <= ?");
            args.add(f.precioMax());
        }
        if (f.excluir() != null) {
            sql.append(" AND p.id <> ?");
            args.add(f.excluir());
        }
        sql.append(" ORDER BY p.destacado DESC, p.fecha_publicacion DESC");
        if (f.limite() != null && f.limite() > 0) sql.append(" LIMIT ").append(Math.min(f.limite(), 100));

        return jdbc.queryForList(sql.toString(), args.toArray()).stream()
                .map(p -> Json.obj(
                        "id", Json.entero(p.get("id")),
                        "titulo", tituloConAnio(p),
                        "precio", Json.decimal(p.get("precio")),
                        "moneda", Json.texto(p.get("moneda")),
                        "imagen", p.get("imagen_url"),
                        "vendedor", Json.texto(p.get("vendedor"))))
                .toList();
    }

    /** Valores posibles de los desplegables del inicio (FiltrosBar), según lo publicado. */
    public Map<String, Object> opcionesFiltros() {
        String base = "FROM publicaciones p JOIN autos a ON a.id = p.auto_id "
                + "LEFT JOIN modelos mo ON mo.id = a.modelo_id LEFT JOIN marcas ma ON ma.id = mo.marca_id"
                + VISIBLE + " AND p.estado = 'ACTIVA'";
        return Json.obj(
                "marca", jdbc.queryForList("SELECT DISTINCT ma.nombre " + base + " AND ma.nombre IS NOT NULL ORDER BY 1", String.class),
                "modelo", jdbc.queryForList("SELECT DISTINCT mo.nombre " + base + " AND mo.nombre IS NOT NULL ORDER BY 1", String.class),
                "anio", jdbc.queryForList("SELECT DISTINCT a.anio " + base + " AND a.anio IS NOT NULL ORDER BY 1 DESC", Integer.class));
    }

    /**
     * Detalle (pantalla /publicacion/:id). Forma: PUBLICACION_DETALLE_MOCK en src/utils/mockPublicaciones.js
     * Una publicación pausada solo la ve su dueño.
     * @param usuarioActual id del usuario logueado, o null si es un invitado
     */
    public Map<String, Object> detalle(long id, Long usuarioActual) {
        Map<String, Object> p = buscar(id);
        long usuarioId = Json.entero(p.get("usuario_id"));
        boolean esPropia = usuarioActual != null && usuarioActual == usuarioId;
        if (ESTADO_PAUSADA.equals(p.get("estado")) && !esPropia) {
            throw Json.error(HttpStatus.NOT_FOUND, "Esta publicación está pausada por su dueño.");
        }
        Map<String, Object> usuario = jdbc.queryForMap("SELECT id, nombre, telefono, foto_url FROM usuarios WHERE id = ?", usuarioId);
        String[] nombre = Json.separarNombre((String) usuario.get("nombre"));

        if (!esPropia) jdbc.update("UPDATE publicaciones SET visitas = COALESCE(visitas, 0) + 1 WHERE id = ?", id);

        return Json.obj(
                "id", id,
                "titulo", titulo(p),
                "precio", Json.decimal(p.get("precio")),
                "moneda", Json.texto(p.get("moneda")),
                "estado", p.get("estado"),
                "esPropia", esPropia,
                "publicante", Json.obj("id", usuarioId, "nombre", nombre[0], "apellido", nombre[1], "fotoUrl", usuario.get("foto_url"),
                        "telefono", Json.texto(usuario.get("telefono"))),
                "ubicacion", Consultas.ubicacion(jdbc, usuarioId),
                "anio", p.get("anio"),
                "kms", Json.entero(p.get("kms")),
                "publicadoHace", publicadoHace(p.get("fecha_publicacion")),
                "descripcion", Json.texto(p.get("descripcion")),
                "caracteristicas", Json.obj(
                        "marca", Json.texto(p.get("marca")),
                        "modelo", Json.texto(p.get("modelo")),
                        "versionn", Json.texto(p.get("versionn")),
                        "anio", p.get("anio"),
                        "transmision", Json.texto(p.get("transmision")),
                        "combustible", Json.texto(p.get("combustible")),
                        "kilometraje", Json.entero(p.get("kms")),
                        "color", Json.texto(p.get("color")),
                        "vtv", Json.bool(p.get("vtv")),
                        "grabado", Json.bool(p.get("grabado")),
                        "papelesAlDia", p.get("papeles_al_dia") == null || Json.bool(p.get("papeles_al_dia")) ? "Sí" : "No"),
                "imagenes", jdbc.queryForList(
                        "SELECT url FROM multimedia WHERE publicacion_id = ? AND tipo = 'IMAGEN' ORDER BY orden, id",
                        String.class, id));
    }

    /** Tarjeta de una publicación (PublicacionPerfilItem en src/types/perfil.js). */
    public Map<String, Object> resumen(long id) {
        return itemPerfil(buscar(id));
    }

    /**
     * Publicaciones de un usuario con la forma PublicacionPerfilItem.
     * @param incluirPausadas true en el perfil propio; en el perfil público las pausadas no se muestran
     */
    public List<Map<String, Object>> deUsuario(long usuarioId, boolean incluirPausadas) {
        String filtro = incluirPausadas ? "" : " AND p.estado <> '" + ESTADO_PAUSADA + "'";
        return jdbc.queryForList(Consultas.PUBLICACIONES + VISIBLE + " AND p.usuario_id = ?" + filtro
                + " ORDER BY p.fecha_publicacion DESC", usuarioId)
                .stream().map(this::itemPerfil).toList();
    }

    Map<String, Object> buscar(long id) {
        List<Map<String, Object>> filas = jdbc.queryForList(Consultas.PUBLICACIONES + VISIBLE + " AND p.id = ?", id);
        if (filas.isEmpty()) throw Json.error(HttpStatus.NOT_FOUND, "La publicación no existe.");
        return filas.get(0);
    }

    private Map<String, Object> itemPerfil(Map<String, Object> p) {
        long id = Json.entero(p.get("id"));
        Map<String, Object> cuentas = jdbc.queryForMap("""
                SELECT (SELECT COUNT(*) FROM favoritos f WHERE f.publicacion_id = ?) AS likes,
                       (SELECT COUNT(*) FROM preguntas q WHERE q.publicacion_id = ?) AS comentarios,
                       (SELECT COALESCE(MAX(e.compartidos), 0) FROM estadisticas_publicacion e WHERE e.publicacion_id = ?) AS compartidos
                """, id, id, id);
        return Json.obj(
                "id", id,
                "titulo", tituloConAnio(p),
                "imagenUrl", p.get("imagen_url"),
                "disponible", ESTADO_ACTIVA.equals(p.get("estado")),
                "estado", p.get("estado"),
                "likes", Json.entero(cuentas.get("likes")),
                "comentarios", Json.entero(cuentas.get("comentarios")),
                "compartidos", Json.entero(cuentas.get("compartidos")));
    }

    /** Datos del formulario "Crear publicación" (src/pages/CrearPublicacion.jsx). */
    public record NuevaPublicacion(Double precio, String marca, String modelo, String version, Integer anio,
            String estado, String descripcionDetalles, String color, String papelesAlDia, String papelesFaltantes,
            Integer kilometraje, String transmision, String combustible, String caracteristicasAdicionales,
            String descripcionLibre) {
    }

    @Transactional
    public long crear(long usuarioId, NuevaPublicacion d, List<MultipartFile> fotos) {
        List<MultipartFile> imagenes = validar(d, fotos, 0);
        DatosAuto auto = datosAuto(d);

        long autoId = Consultas.insertar(jdbc, """
                INSERT INTO autos (modelo_id, versionn, anio, kms, combustible_id, transmision_id, color,
                                   descripcion, papeles_al_dia, fecha_creacion, eliminado)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), 0)
                """, auto.modeloId(), d.version(), d.anio(), d.kilometraje(), auto.combustibleId(), auto.transmisionId(),
                d.color(), auto.detalles(), auto.papelesAlDia());

        long publicacionId = Consultas.insertar(jdbc, """
                INSERT INTO publicaciones (usuario_id, auto_id, precio, moneda, estado, descripcion, fecha_publicacion, eliminado)
                VALUES (?, ?, ?, 'ARS', 'ACTIVA', ?, NOW(), 0)
                """, usuarioId, autoId, d.precio(), descripcion(d, auto.detalles()));
        jdbc.update("INSERT INTO estadisticas_publicacion (publicacion_id) VALUES (?)", publicacionId);

        agregarFotos(publicacionId, imagenes, 0);
        return publicacionId;
    }

    // ---------- Gestión de las publicaciones propias (perfil "Mis publicaciones") ----------

    /** Datos actuales de una publicación propia, con la forma del formulario de CrearPublicacion.jsx. */
    public Map<String, Object> paraEditar(long usuarioId, long id) {
        Map<String, Object> p = propia(usuarioId, id);
        String detallesAuto = Json.texto(jdbc.queryForObject(
                "SELECT a.descripcion FROM autos a JOIN publicaciones p ON p.auto_id = a.id WHERE p.id = ?", String.class, id));
        String detalles = "";
        String papelesFaltantes = "";
        for (String linea : detallesAuto.split("\n")) {
            if (linea.startsWith(PREFIJO_DETALLES)) detalles = linea.substring(PREFIJO_DETALLES.length());
            if (linea.startsWith(PREFIJO_PAPELES)) papelesFaltantes = linea.substring(PREFIJO_PAPELES.length());
        }
        // La descripción guardada termina con los detalles del auto: se quitan para no duplicarlos al volver a guardar
        String descripcion = Json.texto(p.get("descripcion"));
        if (!detallesAuto.isBlank() && descripcion.endsWith(detallesAuto)) {
            descripcion = descripcion.substring(0, descripcion.length() - detallesAuto.length()).strip();
        }
        boolean papelesAlDia = p.get("papeles_al_dia") == null || Json.bool(p.get("papeles_al_dia"));

        return Json.obj(
                "id", id,
                "estadoPublicacion", p.get("estado"),
                "precio", Json.texto(p.get("precio")).replaceAll("\\.00$", ""),
                "marca", Json.texto(p.get("marca")),
                "modelo", Json.texto(p.get("modelo")),
                "version", Json.texto(p.get("versionn")),
                "anio", p.get("anio") == null ? "" : p.get("anio").toString(),
                "estado", detalles.isEmpty() ? "Sin detalles" : "Con detalles",
                "descripcionDetalles", detalles,
                "color", Json.texto(p.get("color")),
                "papelesAlDia", papelesAlDia ? "Si" : "No",
                "papelesFaltantes", papelesFaltantes,
                "kilometraje", p.get("kms") == null ? "" : p.get("kms").toString(),
                "transmision", Json.texto(p.get("transmision")),
                "combustible", Json.texto(p.get("combustible")),
                "caracteristicasAdicionales", "",
                "descripcionLibre", descripcion,
                "fotos", jdbc.queryForList(
                        "SELECT url FROM multimedia WHERE publicacion_id = ? AND tipo = 'IMAGEN' ORDER BY orden, id", String.class, id));
    }

    /**
     * Guarda los cambios de una publicación propia.
     * @param fotosConservadas URLs de las fotos que ya tenía y siguen (en el orden elegido); las demás se borran
     * @param fotosNuevas      fotos agregadas en la edición (van después de las conservadas)
     */
    @Transactional
    public void actualizar(long usuarioId, long id, NuevaPublicacion d, List<String> fotosConservadas, List<MultipartFile> fotosNuevas) {
        propia(usuarioId, id);
        List<String> actuales = jdbc.queryForList(
                "SELECT url FROM multimedia WHERE publicacion_id = ? AND tipo = 'IMAGEN' ORDER BY orden, id", String.class, id);
        List<String> conservadas = fotosConservadas == null ? List.of()
                : fotosConservadas.stream().filter(actuales::contains).distinct().toList();
        List<MultipartFile> imagenes = validar(d, fotosNuevas, conservadas.size());
        DatosAuto auto = datosAuto(d);

        jdbc.update("""
                UPDATE autos a JOIN publicaciones p ON p.auto_id = a.id
                SET a.modelo_id = ?, a.versionn = ?, a.anio = ?, a.kms = ?, a.combustible_id = ?, a.transmision_id = ?,
                    a.color = ?, a.descripcion = ?, a.papeles_al_dia = ?
                WHERE p.id = ?
                """, auto.modeloId(), d.version(), d.anio(), d.kilometraje(), auto.combustibleId(), auto.transmisionId(),
                d.color(), auto.detalles(), auto.papelesAlDia(), id);
        jdbc.update("UPDATE publicaciones SET precio = ?, descripcion = ? WHERE id = ?", d.precio(), descripcion(d, auto.detalles()), id);

        for (String url : actuales) {
            if (conservadas.contains(url)) continue;
            jdbc.update("DELETE FROM moderacion_multimedia WHERE multimedia_id IN (SELECT id FROM multimedia WHERE publicacion_id = ? AND url = ?)", id, url);
            jdbc.update("DELETE FROM multimedia WHERE publicacion_id = ? AND url = ?", id, url);
            archivos.eliminar(url);
        }
        for (int i = 0; i < conservadas.size(); i++) {
            jdbc.update("UPDATE multimedia SET orden = ? WHERE publicacion_id = ? AND url = ?", i + 1, id, conservadas.get(i));
        }
        agregarFotos(id, imagenes, conservadas.size());
    }

    /** Pausa (deja de mostrarse) o reactiva una publicación propia. */
    public void cambiarEstado(long usuarioId, long id, String estado) {
        Map<String, Object> p = propia(usuarioId, id);
        if (!ESTADO_ACTIVA.equals(estado) && !ESTADO_PAUSADA.equals(estado)) {
            throw Json.error(HttpStatus.BAD_REQUEST, "Estado inválido: solo se puede pausar o reactivar.");
        }
        if (!ESTADO_ACTIVA.equals(p.get("estado")) && !ESTADO_PAUSADA.equals(p.get("estado"))) {
            throw Json.error(HttpStatus.BAD_REQUEST, "Esta publicación ya no se puede pausar ni reactivar.");
        }
        jdbc.update("UPDATE publicaciones SET estado = ? WHERE id = ?", estado, id);
        jdbc.update("INSERT INTO historial_publicaciones (publicacion_id, estado, fecha) VALUES (?, ?, NOW())", id, estado);
    }

    /** Elimina una publicación propia (borrado lógico: queda en la base con eliminado = 1). */
    public void eliminar(long usuarioId, long id) {
        propia(usuarioId, id);
        jdbc.update("UPDATE publicaciones SET eliminado = 1, fecha_eliminacion = NOW() WHERE id = ?", id);
    }

    /** La publicación, solo si es del usuario. 404 si no existe y 403 si es de otra persona. */
    private Map<String, Object> propia(long usuarioId, long id) {
        Map<String, Object> p = buscar(id);
        if (Json.entero(p.get("usuario_id")) != usuarioId) {
            throw Json.error(HttpStatus.FORBIDDEN, "Solo el dueño puede modificar esta publicación.");
        }
        return p;
    }

    // ---------- Partes compartidas entre crear y actualizar ----------

    /** Ids de marca, modelo, combustible y transmisión (los crea si no existen) y los detalles del auto. */
    private record DatosAuto(long modeloId, Long combustibleId, Long transmisionId, String detalles, boolean papelesAlDia) {
    }

    /** Valida los datos y devuelve las fotos nuevas que no están vacías. */
    private List<MultipartFile> validar(NuevaPublicacion d, List<MultipartFile> fotos, int fotosExistentes) {
        if (d.precio() == null || d.precio() <= 0) throw Json.error(HttpStatus.BAD_REQUEST, "Ingresá un precio válido.");
        if (!tiene(d.marca()) || !tiene(d.modelo())) throw Json.error(HttpStatus.BAD_REQUEST, "Elegí la marca y el modelo.");
        if (d.kilometraje() == null || d.kilometraje() < 0) throw Json.error(HttpStatus.BAD_REQUEST, "Ingresá el kilometraje.");
        List<MultipartFile> imagenes = fotos == null ? List.of() : fotos.stream().filter(f -> !f.isEmpty()).toList();
        if (fotosExistentes + imagenes.size() > MAX_FOTOS) {
            throw Json.error(HttpStatus.BAD_REQUEST, "Podés tener hasta " + MAX_FOTOS + " fotos.");
        }
        return imagenes;
    }

    private DatosAuto datosAuto(NuevaPublicacion d) {
        long marcaId = buscarOCrear("marcas", "nombre = ?", "INSERT INTO marcas (nombre) VALUES (?)", d.marca().trim());
        long modeloId = buscarOCrear("modelos", "nombre = ? AND marca_id = ?",
                "INSERT INTO modelos (nombre, marca_id) VALUES (?, ?)", d.modelo().trim(), marcaId);
        Long combustibleId = tiene(d.combustible()) ? buscarOCrear("tipos_combustible", "nombre = ?",
                "INSERT INTO tipos_combustible (nombre) VALUES (?)", d.combustible().trim()) : null;
        Long transmisionId = tiene(d.transmision()) ? buscarOCrear("transmisiones", "nombre = ?",
                "INSERT INTO transmisiones (nombre) VALUES (?)", d.transmision().trim()) : null;

        boolean papelesAlDia = !"No".equalsIgnoreCase(d.papelesAlDia());
        String detalles = Stream.of(
                "Con detalles".equalsIgnoreCase(d.estado()) ? PREFIJO_DETALLES + Json.texto(d.descripcionDetalles()).trim() : "",
                papelesAlDia ? "" : PREFIJO_PAPELES + Json.texto(d.papelesFaltantes()).trim())
                .filter(t -> !t.isBlank()).collect(Collectors.joining("\n"));
        return new DatosAuto(modeloId, combustibleId, transmisionId, detalles, papelesAlDia);
    }

    /** Descripción que se muestra en el detalle: texto libre + características + detalles del auto. */
    private static String descripcion(NuevaPublicacion d, String detallesAuto) {
        return Stream.of(d.descripcionLibre(), d.caracteristicasAdicionales(), detallesAuto)
                .map(Json::texto).map(String::trim).filter(t -> !t.isEmpty()).collect(Collectors.joining("\n\n"));
    }

    private void agregarFotos(long publicacionId, List<MultipartFile> imagenes, int desde) {
        for (int i = 0; i < imagenes.size(); i++) {
            String url = archivos.guardarImagen(imagenes.get(i));
            jdbc.update("INSERT INTO multimedia (publicacion_id, url, tipo, orden, fecha_creacion) VALUES (?, ?, 'IMAGEN', ?, NOW())",
                    publicacionId, url, desde + i + 1);
        }
    }

    private long buscarOCrear(String tabla, String condicion, String insert, Object... valores) {
        List<Long> ids = jdbc.queryForList("SELECT id FROM " + tabla + " WHERE " + condicion + " LIMIT 1", Long.class, valores);
        return ids.isEmpty() ? Consultas.insertar(jdbc, insert, valores) : ids.get(0);
    }

    /** Ej: "Toyota Corolla XEI" */
    static String titulo(Map<String, Object> p) {
        return Stream.of(p.get("marca"), p.get("modelo"), p.get("versionn"))
                .map(Json::texto).map(String::trim).filter(s -> !s.isEmpty()).collect(Collectors.joining(" "));
    }

    /** Ej: "Toyota Corolla XEI - 2019" */
    static String tituloConAnio(Map<String, Object> p) {
        return p.get("anio") == null ? titulo(p) : titulo(p) + " - " + p.get("anio");
    }

    private static String publicadoHace(Object fecha) {
        if (fecha == null) return "";
        long dias = Duration.between(((java.sql.Timestamp) fecha).toLocalDateTime(), LocalDateTime.now()).toDays();
        if (dias <= 0) return "Publicado hoy";
        if (dias == 1) return "Publicado hace 1 día";
        if (dias < 30) return "Publicado hace " + dias + " días";
        long meses = dias / 30;
        return meses == 1 ? "Publicado hace 1 mes" : "Publicado hace " + meses + " meses";
    }

    private static boolean tiene(String valor) {
        return valor != null && !valor.isBlank();
    }
}
