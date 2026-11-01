-- Integración frontend <-> base de datos.
--
-- Lo ejecuta IntegracionBaseDatos.java al iniciar el backend, DESPUÉS de haber importado
-- Base_De_Datos_CarFind/compraventa.sql. Es idempotente: no rompe nada si se corre varias veces
-- (las columnas que ya existen se saltean). Funciona en MariaDB (XAMPP) y en MySQL 8.

-- Datos que muestran Perfil y Configuración y que la tabla usuarios no tenía
ALTER TABLE usuarios ADD COLUMN foto_url VARCHAR(255) NULL;
ALTER TABLE usuarios ADD COLUMN genero VARCHAR(30) NULL;
ALTER TABLE usuarios ADD COLUMN fecha_nacimiento DATE NULL;

-- "Papeles al día" del formulario de publicación
ALTER TABLE autos ADD COLUMN papeles_al_dia TINYINT(1) DEFAULT 1;

-- Foto adjunta en un mensaje del chat
ALTER TABLE mensajes ADD COLUMN imagen_url VARCHAR(255) NULL;

-- Reportes: además de los de publicaciones, los generales y los reportes a usuarios
ALTER TABLE reportes ADD COLUMN tipo VARCHAR(20) NOT NULL DEFAULT 'publicacion';
ALTER TABLE reportes ADD COLUMN usuario_reportado_id INT(11) NULL;

-- Preferencias de Configuración que no tienen columna propia (fuente, avisos, idiomas, etc.)
ALTER TABLE preferencias_usuario ADD COLUMN config_json LONGTEXT NULL;

-- Respuestas de la administración a los reportes
CREATE TABLE IF NOT EXISTS respuestas_reporte (
  id INT(11) NOT NULL AUTO_INCREMENT,
  reporte_id INT(11) NOT NULL,
  admin_id INT(11) NULL,
  texto TEXT NOT NULL,
  fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY idx_respuestas_reporte (reporte_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Informes generados desde el panel de administración (el archivo se arma al descargarlo)
CREATE TABLE IF NOT EXISTS informes_admin (
  id INT(11) NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(150) NOT NULL,
  tipo VARCHAR(20) NOT NULL,
  formato VARCHAR(5) NOT NULL,
  desde DATE NOT NULL,
  hasta DATE NOT NULL,
  cantidad INT(11) NOT NULL DEFAULT 0,
  fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Fotos subidas desde la página (publicaciones, perfil y chat). Van en la base y no en disco
-- porque en los hostings gratuitos el disco se borra al reiniciar el servidor.
CREATE TABLE IF NOT EXISTS archivos (
  nombre VARCHAR(64) NOT NULL,
  tipo VARCHAR(100) NOT NULL,
  datos LONGBLOB NOT NULL,
  fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (nombre)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Rol que habilita el panel /admin
INSERT INTO roles (nombre, activo)
SELECT 'ADMIN', 1 FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM roles WHERE nombre = 'ADMIN');

-- Usuarios de prueba: las contraseñas del dump no eran hashes reales.
-- Quedan con la contraseña "Carfind123" para poder iniciar sesión.
UPDATE usuarios SET password_hash = '$2a$10$xf3//NVoyIm5vg8cRHw/le5g/88MBR.m1F9vt/4bgQJuw6RoVOyPO'
WHERE password_hash LIKE '$2a$10$hashDePrueba%';

-- maria.gomez@mail.com es administradora de prueba
INSERT INTO usuario_roles (usuario_id, rol_id)
SELECT u.id, r.id FROM usuarios u JOIN roles r ON r.nombre = 'ADMIN'
WHERE u.email = 'maria.gomez@mail.com'
  AND NOT EXISTS (SELECT 1 FROM usuario_roles ur WHERE ur.usuario_id = u.id AND ur.rol_id = r.id);
