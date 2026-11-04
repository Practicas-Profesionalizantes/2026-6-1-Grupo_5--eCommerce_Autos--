package com.back.carfind.web;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/** Utilidades para armar las respuestas con la forma que espera el frontend (src/types/*.js). */
final class Json {

    private Json() {
    }

    /** Objeto JSON a partir de pares clave, valor. A diferencia de Map.of, acepta valores null. */
    static Map<String, Object> obj(Object... claveValor) {
        Map<String, Object> mapa = new LinkedHashMap<>();
        for (int i = 0; i < claveValor.length; i += 2) {
            mapa.put((String) claveValor[i], claveValor[i + 1]);
        }
        return mapa;
    }

    /** Fecha y hora ISO (ej: "2026-10-28T18:53:53"), o null. */
    static String fechaIso(Object valor) {
        if (valor instanceof Timestamp t) return t.toLocalDateTime().toString();
        if (valor instanceof Date d) return d.toLocalDate().toString();
        return valor == null ? null : valor.toString();
    }

    static long entero(Object valor) {
        return valor == null ? 0 : ((Number) valor).longValue();
    }

    static double decimal(Object valor) {
        if (valor == null) return 0;
        return valor instanceof BigDecimal b ? b.doubleValue() : ((Number) valor).doubleValue();
    }

    static boolean bool(Object valor) {
        if (valor instanceof Boolean b) return b;
        return valor != null && ((Number) valor).intValue() != 0;
    }

    static String texto(Object valor) {
        return valor == null ? "" : valor.toString();
    }

    static ResponseStatusException error(HttpStatus estado, String mensaje) {
        return new ResponseStatusException(estado, mensaje);
    }

    /** Separa "Nombre Apellido" (la tabla usuarios guarda el nombre completo en una sola columna). */
    static String[] separarNombre(String nombreCompleto) {
        String limpio = texto(nombreCompleto).trim();
        int espacio = limpio.indexOf(' ');
        return espacio < 0 ? new String[] { limpio, "" }
                : new String[] { limpio.substring(0, espacio), limpio.substring(espacio + 1).trim() };
    }

    static String unirNombre(String nombre, String apellido) {
        return (texto(nombre).trim() + " " + texto(apellido).trim()).trim();
    }
}
