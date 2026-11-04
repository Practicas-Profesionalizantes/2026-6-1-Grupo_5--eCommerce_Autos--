package com.back.carfind.web;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Al iniciar, ejecuta db/integracion.sql: completa la base importada (compraventa.sql) con lo que
 * necesita el frontend. Las columnas que ya existen se saltean, así se puede correr siempre y funciona
 * tanto en MariaDB (XAMPP) como en MySQL 8 (que no admite "ADD COLUMN IF NOT EXISTS").
 */
@Component
public class IntegracionBaseDatos implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(IntegracionBaseDatos.class);

    /** Código de MySQL/MariaDB para "Duplicate column name". */
    private static final int COLUMNA_DUPLICADA = 1060;

    private final JdbcTemplate jdbc;

    public IntegracionBaseDatos(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void afterPropertiesSet() throws IOException {
        String script = new ClassPathResource("db/integracion.sql").getContentAsString(StandardCharsets.UTF_8);
        String sinComentarios = Arrays.stream(script.split("\\R"))
                .filter(linea -> !linea.trim().startsWith("--"))
                .collect(Collectors.joining("\n"));

        for (String sentencia : sinComentarios.split(";")) {
            if (sentencia.isBlank()) continue;
            try {
                jdbc.execute(sentencia.trim());
            } catch (DataAccessException e) {
                if (!(e.getMostSpecificCause() instanceof SQLException sql) || sql.getErrorCode() != COLUMNA_DUPLICADA) {
                    throw e;
                }
                log.debug("Columna ya existente, se saltea: {}", sentencia.trim());
            }
        }
        log.info("Base de datos lista para el frontend (db/integracion.sql aplicado).");
    }
}
