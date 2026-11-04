package com.back.carfind.web;

import java.sql.PreparedStatement;
import java.sql.Statement;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/** Consultas SQL compartidas por los servicios de la API web. */
final class Consultas {

    private Consultas() {
    }

    /**
     * Datos de una publicación con su auto, marca, modelo, combustible, transmisión y primera foto.
     * Se le agrega el WHERE / ORDER BY en cada uso.
     */
    static final String PUBLICACIONES = """
            SELECT p.id, p.usuario_id, p.precio, p.moneda, p.estado, p.descripcion, p.fecha_publicacion,
                   a.anio, a.kms, a.versionn, a.color, a.vtv, a.grabado, a.papeles_al_dia,
                   ma.nombre AS marca, mo.nombre AS modelo, tc.nombre AS combustible, tr.nombre AS transmision,
                   u.nombre AS vendedor,
                   (SELECT m.url FROM multimedia m WHERE m.publicacion_id = p.id AND m.tipo = 'IMAGEN'
                     ORDER BY m.orden, m.id LIMIT 1) AS imagen_url
            FROM publicaciones p
            JOIN autos a ON a.id = p.auto_id
            JOIN usuarios u ON u.id = p.usuario_id
            LEFT JOIN modelos mo ON mo.id = a.modelo_id
            LEFT JOIN marcas ma ON ma.id = mo.marca_id
            LEFT JOIN tipos_combustible tc ON tc.id = a.combustible_id
            LEFT JOIN transmisiones tr ON tr.id = a.transmision_id
            """;

    /** Ubicación del usuario: su dirección principal (ciudad, provincia). */
    static final String UBICACION = """
            SELECT CONCAT_WS(', ', NULLIF(d.ciudad, ''), NULLIF(d.provincia, ''))
            FROM direcciones d WHERE d.usuario_id = ? ORDER BY d.es_principal DESC, d.id LIMIT 1
            """;

    /** INSERT que devuelve el id generado. */
    static long insertar(JdbcTemplate jdbc, String sql, Object... argumentos) {
        KeyHolder clave = new GeneratedKeyHolder();
        jdbc.update(conexion -> {
            PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < argumentos.length; i++) {
                ps.setObject(i + 1, argumentos[i]);
            }
            return ps;
        }, clave);
        return clave.getKey().longValue();
    }

    static String ubicacion(JdbcTemplate jdbc, long usuarioId) {
        return jdbc.queryForList(UBICACION, String.class, usuarioId).stream().findFirst().orElse("");
    }
}
