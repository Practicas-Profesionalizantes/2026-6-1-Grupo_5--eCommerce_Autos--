-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-09-2026 a las 14:49:04
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `compraventa`
--
CREATE DATABASE IF NOT EXISTS `compraventa` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `compraventa`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actividad_sospechosa`
--

CREATE TABLE `actividad_sospechosa` (
  `id` int(11) NOT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `tipo` varchar(50) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Disparadores `actividad_sospechosa`
--
DELIMITER $$
CREATE TRIGGER `trg_riesgo_fraude` AFTER INSERT ON `actividad_sospechosa` FOR EACH ROW BEGIN
    UPDATE usuarios SET score_riesgo = score_riesgo + 10 WHERE id = NEW.usuario_id;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actividad_usuario`
--

CREATE TABLE `actividad_usuario` (
  `id` int(11) NOT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `accion` varchar(50) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `auditoria`
--

CREATE TABLE `auditoria` (
  `id` int(11) NOT NULL,
  `tabla_afectada` varchar(100) NOT NULL,
  `accion` enum('INSERT','UPDATE','DELETE') NOT NULL,
  `registro_id` int(11) NOT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `datos_anteriores` text DEFAULT NULL,
  `datos_nuevos` text DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `autos`
--

CREATE TABLE `autos` (
  `id` int(11) NOT NULL,
  `modelo_id` int(11) NOT NULL,
  `versionn` varchar(50) DEFAULT NULL,
  `anio` int(11) DEFAULT NULL,
  `kms` int(11) DEFAULT NULL,
  `vtv` tinyint(1) DEFAULT 0,
  `grabado` tinyint(1) DEFAULT 0,
  `combustible_id` int(11) DEFAULT NULL,
  `transmision_id` int(11) DEFAULT NULL,
  `color` varchar(30) DEFAULT NULL,
  `puertas` int(11) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp(),
  `eliminado` tinyint(1) DEFAULT 0,
  `fecha_eliminacion` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `banco`
--

CREATE TABLE `banco` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `codigo` varchar(20) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT 1,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `billeteras`
--

CREATE TABLE `billeteras` (
  `usuario_id` int(11) NOT NULL,
  `saldo` decimal(12,2) DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bloqueos`
--

CREATE TABLE `bloqueos` (
  `usuario_id` int(11) NOT NULL,
  `usuario_bloqueado_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `busquedas_guardadas`
--

CREATE TABLE `busquedas_guardadas` (
  `id` int(11) NOT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `query` text DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `chats`
--

CREATE TABLE `chats` (
  `id` int(11) NOT NULL,
  `usuario1_id` int(11) NOT NULL,
  `usuario2_id` int(11) NOT NULL,
  `publicacion_id` int(11) DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comisiones`
--

CREATE TABLE `comisiones` (
  `id` int(11) NOT NULL,
  `compra_id` int(11) DEFAULT NULL,
  `porcentaje` decimal(5,2) DEFAULT NULL,
  `monto` decimal(12,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compras`
--

CREATE TABLE `compras` (
  `id` int(11) NOT NULL,
  `comprador_id` int(11) NOT NULL,
  `publicacion_id` int(11) NOT NULL,
  `precio_final` decimal(12,2) NOT NULL,
  `estado` enum('PENDIENTE','PAGADO','CANCELADO','COMPLETADO') DEFAULT 'PENDIENTE',
  `fecha` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Disparadores `compras`
--
DELIMITER $$
CREATE TRIGGER `trg_auditoria_compras_update` AFTER UPDATE ON `compras` FOR EACH ROW BEGIN
    INSERT INTO auditoria(tabla_afectada, accion, registro_id, usuario_id, datos_anteriores, datos_nuevos)
    VALUES('compras', 'UPDATE', OLD.id, OLD.comprador_id,
    CONCAT('estado=', OLD.estado, ', precio_final=', OLD.precio_final),
    CONCAT('estado=', NEW.estado, ', precio_final=', NEW.precio_final));
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_compra_completada` AFTER UPDATE ON `compras` FOR EACH ROW BEGIN
    IF NEW.estado = 'COMPLETADO' AND OLD.estado <> 'COMPLETADO' THEN
        UPDATE publicaciones SET estado = 'VENDIDA' WHERE id = NEW.publicacion_id;
        UPDATE usuarios SET total_compras = total_compras + 1 WHERE id = NEW.comprador_id;
        UPDATE usuarios SET total_ventas = total_ventas + 1 
        WHERE id = (SELECT usuario_id FROM publicaciones WHERE id = NEW.publicacion_id);
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuotas`
--

CREATE TABLE `cuotas` (
  `id` int(11) NOT NULL,
  `pago_cuota_id` int(11) DEFAULT NULL,
  `numero_cuota` int(11) DEFAULT NULL,
  `monto` decimal(12,2) DEFAULT NULL,
  `estado` enum('PENDIENTE','PAGADA','ATRASADA') DEFAULT NULL,
  `fecha_vencimiento` date DEFAULT NULL,
  `fecha_pago` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `direcciones`
--

CREATE TABLE `direcciones` (
  `id` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `pais` varchar(50) DEFAULT NULL,
  `provincia` varchar(50) DEFAULT NULL,
  `ciudad` varchar(50) DEFAULT NULL,
  `direccion` text DEFAULT NULL,
  `tipo` varchar(20) DEFAULT NULL,
  `es_principal` tinyint(1) DEFAULT 0,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dispositivos_usuario`
--

CREATE TABLE `dispositivos_usuario` (
  `id` int(11) NOT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `token_push` text DEFAULT NULL,
  `plataforma` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `encuentros`
--

CREATE TABLE `encuentros` (
  `id` int(11) NOT NULL,
  `compra_id` int(11) DEFAULT NULL,
  `vendedor_id` int(11) DEFAULT NULL,
  `comprador_id` int(11) DEFAULT NULL,
  `fecha_programada` datetime DEFAULT NULL,
  `lugar` varchar(255) DEFAULT NULL,
  `estado` enum('PENDIENTE','CONFIRMADO','REALIZADO','CANCELADO') DEFAULT NULL,
  `notas` text DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadisticas_publicacion`
--

CREATE TABLE `estadisticas_publicacion` (
  `publicacion_id` int(11) NOT NULL,
  `vistas` int(11) DEFAULT 0,
  `clicks` int(11) DEFAULT 0,
  `favoritos` int(11) DEFAULT 0,
  `contactos` int(11) DEFAULT 0,
  `compartidos` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturas`
--

CREATE TABLE `facturas` (
  `id` int(11) NOT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `compra_id` int(11) DEFAULT NULL,
  `total` decimal(12,2) DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `favoritos`
--

CREATE TABLE `favoritos` (
  `usuario_id` int(11) NOT NULL,
  `publicacion_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial_precios`
--

CREATE TABLE `historial_precios` (
  `id` int(11) NOT NULL,
  `publicacion_id` int(11) DEFAULT NULL,
  `precio` decimal(12,2) DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial_publicaciones`
--

CREATE TABLE `historial_publicaciones` (
  `id` int(11) NOT NULL,
  `publicacion_id` int(11) DEFAULT NULL,
  `estado` varchar(20) DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial_vehiculo`
--

CREATE TABLE `historial_vehiculo` (
  `id` int(11) NOT NULL,
  `auto_id` int(11) NOT NULL,
  `tipo` varchar(50) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `informe_dominio`
--

CREATE TABLE `informe_dominio` (
  `id` int(11) NOT NULL,
  `auto_id` int(11) NOT NULL,
  `dominio` varchar(10) NOT NULL,
  `numero_chasis` varchar(50) DEFAULT NULL,
  `numero_motor` varchar(50) DEFAULT NULL,
  `titular_actual` varchar(150) DEFAULT NULL,
  `cantidad_titulares` int(11) DEFAULT NULL,
  `fecha_inscripcion` date DEFAULT NULL,
  `tiene_embargo` tinyint(1) DEFAULT 0,
  `tiene_prenda` tinyint(1) DEFAULT 0,
  `es_robado` tinyint(1) DEFAULT 0,
  `titular_inhibido` tinyint(1) DEFAULT 0,
  `deuda_patentes` decimal(12,2) DEFAULT NULL,
  `deuda_multas` decimal(12,2) DEFAULT NULL,
  `observaciones` text DEFAULT NULL,
  `fecha_emision` timestamp NOT NULL DEFAULT current_timestamp(),
  `fecha_vencimiento` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `item_carrito`
--

CREATE TABLE `item_carrito` (
  `usuario_id` int(11) NOT NULL,
  `publicacion_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Disparadores `item_carrito`
--
DELIMITER $$
CREATE TRIGGER `trg_item_carrito_evitar_autocompra` BEFORE INSERT ON `item_carrito` FOR EACH ROW BEGIN
    DECLARE v_vendedor_id INT;
    SELECT usuario_id INTO v_vendedor_id FROM publicaciones WHERE id = NEW.publicacion_id;
    IF NEW.usuario_id = v_vendedor_id THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'No puedes agregar tu propia publicación al carrito.';
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `logs_transacciones`
--

CREATE TABLE `logs_transacciones` (
  `id` int(11) NOT NULL,
  `tipo` varchar(50) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `referencia_id` int(11) DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marcas`
--

CREATE TABLE `marcas` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensajes`
--

CREATE TABLE `mensajes` (
  `id` int(11) NOT NULL,
  `chat_id` int(11) NOT NULL,
  `emisor_id` int(11) NOT NULL,
  `contenido` text NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp(),
  `leido` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensajes_soporte`
--

CREATE TABLE `mensajes_soporte` (
  `id` int(11) NOT NULL,
  `ticket_id` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `mensaje` text NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `metodos_pago`
--

CREATE TABLE `metodos_pago` (
  `id` int(11) NOT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `datos_tokenizados` text DEFAULT NULL,
  `activo` tinyint(1) DEFAULT 1,
  `tipo_pago_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `modelos`
--

CREATE TABLE `modelos` (
  `id` int(11) NOT NULL,
  `marca_id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `moderacion_multimedia`
--

CREATE TABLE `moderacion_multimedia` (
  `id` int(11) NOT NULL,
  `multimedia_id` int(11) DEFAULT NULL,
  `estado` enum('PENDIENTE','APROBADO','RECHAZADO') DEFAULT NULL,
  `motivo` text DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movimientos`
--

CREATE TABLE `movimientos` (
  `id` int(11) NOT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `tipo` enum('INGRESO','EGRESO') DEFAULT NULL,
  `monto` decimal(12,2) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Disparadores `movimientos`
--
DELIMITER $$
CREATE TRIGGER `trg_actualizar_saldo_billetera` AFTER INSERT ON `movimientos` FOR EACH ROW BEGIN
    IF NEW.tipo = 'INGRESO' THEN
        UPDATE billeteras SET saldo = saldo + NEW.monto WHERE usuario_id = NEW.usuario_id;
    ELSEIF NEW.tipo = 'EGRESO' THEN
        UPDATE billeteras SET saldo = saldo - NEW.monto WHERE usuario_id = NEW.usuario_id;
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `multimedia`
--

CREATE TABLE `multimedia` (
  `id` int(11) NOT NULL,
  `publicacion_id` int(11) NOT NULL,
  `url` text NOT NULL,
  `tipo` varchar(10) NOT NULL CHECK (`tipo` in ('IMAGEN','VIDEO')),
  `orden` int(11) DEFAULT 0,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `notificaciones`
--

CREATE TABLE `notificaciones` (
  `id` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `tipo` varchar(30) DEFAULT NULL,
  `mensaje` text DEFAULT NULL,
  `leido` tinyint(1) DEFAULT 0,
  `entidad_id` int(11) DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ofertas`
--

CREATE TABLE `ofertas` (
  `id` int(11) NOT NULL,
  `publicacion_id` int(11) NOT NULL,
  `comprador_id` int(11) NOT NULL,
  `monto` decimal(12,2) NOT NULL,
  `estado` enum('PENDIENTE','ACEPTADA','RECHAZADA','EXPIRADA') DEFAULT 'PENDIENTE',
  `fecha` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagos_cuotas`
--

CREATE TABLE `pagos_cuotas` (
  `id` int(11) NOT NULL,
  `transaccion_id` int(11) NOT NULL,
  `plan_id` int(11) NOT NULL,
  `cantidad_cuotas` int(11) NOT NULL,
  `interes_aplicado` decimal(5,2) DEFAULT 0.00,
  `monto_original` decimal(12,2) NOT NULL,
  `monto_total` decimal(12,2) NOT NULL,
  `monto_cuota` decimal(12,2) NOT NULL,
  `moneda` varchar(10) DEFAULT 'ARS',
  `fecha_inicio` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagos_promocion`
--

CREATE TABLE `pagos_promocion` (
  `id` int(11) NOT NULL,
  `promocion_id` int(11) DEFAULT NULL,
  `monto` decimal(10,2) DEFAULT NULL,
  `estado` enum('PENDIENTE','APROBADO','RECHAZADO') DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `planes_cuotas`
--

CREATE TABLE `planes_cuotas` (
  `id` int(11) NOT NULL,
  `cantidad_cuotas` int(11) DEFAULT NULL,
  `interes` decimal(5,2) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `preferencias_usuario`
--

CREATE TABLE `preferencias_usuario` (
  `usuario_id` int(11) NOT NULL,
  `tema` enum('CLARO','OSCURO','SISTEMA') DEFAULT 'SISTEMA',
  `idioma` varchar(10) DEFAULT 'es',
  `tamanio_fuente` enum('PEQUENA','MEDIANA','GRANDE') DEFAULT 'MEDIANA',
  `accesibilidad` tinyint(1) DEFAULT 0,
  `notificaciones_email` tinyint(1) DEFAULT 1,
  `notificaciones_push` tinyint(1) DEFAULT 1,
  `notificaciones_sms` tinyint(1) DEFAULT 0,
  `descargas_automaticas` tinyint(1) DEFAULT 0,
  `fecha_actualizacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `preguntas`
--

CREATE TABLE `preguntas` (
  `id` int(11) NOT NULL,
  `publicacion_id` int(11) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `pregunta` text DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `promociones`
--

CREATE TABLE `promociones` (
  `id` int(11) NOT NULL,
  `publicacion_id` int(11) NOT NULL,
  `tipo_promocion_id` int(11) NOT NULL,
  `fecha_inicio` timestamp NOT NULL DEFAULT current_timestamp(),
  `fecha_fin` timestamp NOT NULL DEFAULT current_timestamp(),
  `estado` enum('ACTIVA','FINALIZADA','CANCELADA') DEFAULT 'ACTIVA'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `promociones_bancarias`
--

CREATE TABLE `promociones_bancarias` (
  `id` int(11) NOT NULL,
  `id_banco` int(11) DEFAULT NULL,
  `tarjeta` varchar(20) DEFAULT NULL,
  `cuotas` int(11) DEFAULT NULL,
  `interes` decimal(5,2) DEFAULT NULL,
  `activa` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `publicaciones`
--

CREATE TABLE `publicaciones` (
  `id` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `auto_id` int(11) NOT NULL,
  `precio` decimal(12,2) NOT NULL,
  `moneda` varchar(10) DEFAULT 'USD',
  `estado` enum('ACTIVA','PAUSADA','VENDIDA','CANCELADA') DEFAULT 'ACTIVA',
  `score` decimal(10,2) DEFAULT 0.00,
  `destacado` tinyint(1) DEFAULT 0,
  `visitas` int(11) DEFAULT 0,
  `descripcion` text DEFAULT NULL,
  `fecha_publicacion` timestamp NOT NULL DEFAULT current_timestamp(),
  `fecha_expiracion` datetime DEFAULT NULL,
  `eliminado` tinyint(1) DEFAULT 0,
  `fecha_eliminacion` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Disparadores `publicaciones`
--
DELIMITER $$
CREATE TRIGGER `trg_auditoria_delete_publicacion` BEFORE DELETE ON `publicaciones` FOR EACH ROW BEGIN
    INSERT INTO auditoria(tabla_afectada, accion, registro_id, usuario_id, datos_anteriores)
    VALUES('publicaciones', 'DELETE', OLD.id, OLD.usuario_id, CONCAT('precio=', OLD.precio, ', estado=', OLD.estado));
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_auditoria_publicaciones_update` AFTER UPDATE ON `publicaciones` FOR EACH ROW BEGIN
    INSERT INTO auditoria(tabla_afectada, accion, registro_id, usuario_id, datos_anteriores, datos_nuevos)
    VALUES('publicaciones', 'UPDATE', OLD.id, OLD.usuario_id,
    CONCAT('precio=', OLD.precio, ', estado=', OLD.estado, ', score=', OLD.score),
    CONCAT('precio=', NEW.precio, ', estado=', NEW.estado, ', score=', NEW.score));
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_limpiar_carrito_publicacion_inactiva` AFTER UPDATE ON `publicaciones` FOR EACH ROW BEGIN
    IF NEW.estado <> 'ACTIVA' OR NEW.eliminado = TRUE THEN
        DELETE FROM item_carrito WHERE publicacion_id = NEW.id;
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_log_venta` AFTER UPDATE ON `publicaciones` FOR EACH ROW BEGIN
    IF NEW.estado = 'VENDIDA' AND OLD.estado <> 'VENDIDA' THEN
        INSERT INTO logs_transacciones (tipo, descripcion, referencia_id)
        VALUES ('VENTA', 'Publicación vendida', NEW.id);
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_soft_delete_publicacion` AFTER UPDATE ON `publicaciones` FOR EACH ROW BEGIN
    IF NEW.eliminado = TRUE AND OLD.eliminado = FALSE THEN
        INSERT INTO logs_transacciones(tipo, descripcion, referencia_id)
        VALUES('SOFT_DELETE', CONCAT('Publicacion eliminada logicamente ID: ', NEW.id), NEW.id);
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reembolsos`
--

CREATE TABLE `reembolsos` (
  `id` int(11) NOT NULL,
  `transaccion_id` int(11) DEFAULT NULL,
  `monto` decimal(12,2) DEFAULT NULL,
  `motivo` text DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reportes`
--

CREATE TABLE `reportes` (
  `id` int(11) NOT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `publicacion_id` int(11) DEFAULT NULL,
  `motivo` varchar(100) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `estado` enum('PENDIENTE','REVISADO','RESUELTO') DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `resenias`
--

CREATE TABLE `resenias` (
  `id` int(11) NOT NULL,
  `comprador_id` int(11) NOT NULL,
  `vendedor_id` int(11) NOT NULL,
  `puntuacion` decimal(2,1) DEFAULT NULL CHECK (`puntuacion` between 1 and 5),
  `comentario` text DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Disparadores `resenias`
--
DELIMITER $$
CREATE TRIGGER `trg_nueva_resenia` AFTER INSERT ON `resenias` FOR EACH ROW BEGIN
    UPDATE usuarios
    SET reputacion = (SELECT AVG(puntuacion) FROM resenias WHERE vendedor_id = NEW.vendedor_id)
    WHERE id = NEW.vendedor_id;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `respuestas`
--

CREATE TABLE `respuestas` (
  `id` int(11) NOT NULL,
  `pregunta_id` int(11) DEFAULT NULL,
  `vendedor_id` int(11) DEFAULT NULL,
  `respuesta` text DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `activo` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sesiones`
--

CREATE TABLE `sesiones` (
  `id` int(11) NOT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `token` text DEFAULT NULL,
  `fecha_expiracion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarjetas_usuario`
--

CREATE TABLE `tarjetas_usuario` (
  `id` int(11) NOT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `marca` varchar(20) DEFAULT NULL,
  `ultimos4` varchar(4) DEFAULT NULL,
  `token` text DEFAULT NULL,
  `vencimiento_mes` int(11) DEFAULT NULL,
  `vencimiento_anio` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tickets_soporte`
--

CREATE TABLE `tickets_soporte` (
  `id` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `categoria` enum('PAGO','CUENTA','ACCESO','CONFIGURACION','PUBLICACION','SEGURIDAD','OTRO') DEFAULT 'OTRO',
  `prioridad` enum('BAJA','MEDIA','ALTA','URGENTE') DEFAULT 'MEDIA',
  `asunto` varchar(150) NOT NULL,
  `descripcion` text NOT NULL,
  `estado` enum('ABIERTO','EN_PROCESO','RESUELTO','CERRADO') DEFAULT 'ABIERTO',
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp(),
  `fecha_actualizacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_combustible`
--

CREATE TABLE `tipos_combustible` (
  `id` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_pago`
--

CREATE TABLE `tipos_pago` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_promocion`
--

CREATE TABLE `tipos_promocion` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `prioridad` int(11) DEFAULT NULL,
  `duracion_dias` int(11) DEFAULT NULL,
  `precio` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transacciones`
--

CREATE TABLE `transacciones` (
  `id` int(11) NOT NULL,
  `compra_id` int(11) NOT NULL,
  `tipo_pago_id` int(11) DEFAULT NULL,
  `estado` enum('PENDIENTE','APROBADO','RECHAZADO') DEFAULT 'PENDIENTE',
  `id_externo` varchar(100) DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Disparadores `transacciones`
--
DELIMITER $$
CREATE TRIGGER `trg_auditoria_pagos_update` AFTER UPDATE ON `transacciones` FOR EACH ROW BEGIN
    INSERT INTO auditoria(tabla_afectada, accion, registro_id, datos_anteriores, datos_nuevos)
    VALUES('transacciones', 'UPDATE', OLD.id, CONCAT('estado=', OLD.estado), CONCAT('estado=', NEW.estado));
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_log_pago` AFTER INSERT ON `transacciones` FOR EACH ROW BEGIN
    INSERT INTO logs_transacciones (tipo, descripcion, referencia_id)
    VALUES ('PAGO', 'Pago registrado', NEW.id);
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_pago_aprobado` AFTER UPDATE ON `transacciones` FOR EACH ROW BEGIN
    IF NEW.estado = 'APROBADO' AND OLD.estado <> 'APROBADO' THEN
        INSERT INTO movimientos (usuario_id, tipo, monto, descripcion)
        SELECT c.comprador_id, 'EGRESO', c.precio_final, 'Compra realizada'
        FROM compras c
        WHERE c.id = NEW.compra_id;
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transmisiones`
--

CREATE TABLE `transmisiones` (
  `id` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `email` varchar(120) NOT NULL,
  `dni` varchar(8) NOT NULL,
  `password_hash` varchar(150) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `reputacion` decimal(2,1) DEFAULT 0.0,
  `score_riesgo` decimal(5,2) DEFAULT 0.00,
  `verificacion` enum('1','2','3','4','5') DEFAULT '1',
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp(),
  `total_compras` int(11) DEFAULT 0,
  `total_ventas` int(11) DEFAULT 0,
  `eliminado` tinyint(1) DEFAULT 0,
  `fecha_eliminacion` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Disparadores `usuarios`
--
DELIMITER $$
CREATE TRIGGER `trg_auditoria_usuarios_update` AFTER UPDATE ON `usuarios` FOR EACH ROW BEGIN
    INSERT INTO auditoria(tabla_afectada, accion, registro_id, usuario_id, datos_anteriores, datos_nuevos)
    VALUES('usuarios', 'UPDATE', OLD.id, OLD.id,
    CONCAT('email=', OLD.email, ', telefono=', OLD.telefono),
    CONCAT('email=', NEW.email, ', telefono=', NEW.telefono));
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_roles`
--

CREATE TABLE `usuario_roles` (
  `usuario_id` int(11) NOT NULL,
  `rol_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `variantes`
--

CREATE TABLE `variantes` (
  `id` int(11) NOT NULL,
  `publicacion_id` int(11) DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `variante_opciones`
--

CREATE TABLE `variante_opciones` (
  `id` int(11) NOT NULL,
  `variante_id` int(11) DEFAULT NULL,
  `valor` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `visitas_auto`
--

CREATE TABLE `visitas_auto` (
  `id` int(11) NOT NULL,
  `auto_id` int(11) DEFAULT NULL,
  `comprador_id` int(11) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `resultado` enum('INTERESADO','NO_INTERESADO') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vistas_publicacion`
--

CREATE TABLE `vistas_publicacion` (
  `usuario_id` int(11) NOT NULL,
  `publicacion_id` int(11) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vista_compradores`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `vista_compradores` (
`id` int(11)
,`nombre` varchar(100)
,`total_compras` bigint(21)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vista_historial_compras`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `vista_historial_compras` (
`id` int(11)
,`comprador` varchar(100)
,`publicacion` int(11)
,`precio_final` decimal(12,2)
,`estado` enum('PENDIENTE','PAGADO','CANCELADO','COMPLETADO')
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vista_publicaciones_activas`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `vista_publicaciones_activas` (
`id` int(11)
,`usuario_id` int(11)
,`auto_id` int(11)
,`precio` decimal(12,2)
,`moneda` varchar(10)
,`estado` enum('ACTIVA','PAUSADA','VENDIDA','CANCELADA')
,`score` decimal(10,2)
,`destacado` tinyint(1)
,`visitas` int(11)
,`descripcion` text
,`fecha_publicacion` timestamp
,`fecha_expiracion` datetime
,`eliminado` tinyint(1)
,`fecha_eliminacion` timestamp
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vista_publicaciones_detalle`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `vista_publicaciones_detalle` (
`id` int(11)
,`precio` decimal(12,2)
,`estado` enum('ACTIVA','PAUSADA','VENDIDA','CANCELADA')
,`vendedor` varchar(100)
,`anio` int(11)
,`marca` varchar(50)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vista_rendimiento_publicaciones`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `vista_rendimiento_publicaciones` (
`publicacion_id` int(11)
,`vendedor_id` int(11)
,`marca` varchar(50)
,`modelo` varchar(50)
,`precio` decimal(12,2)
,`moneda` varchar(10)
,`visitas` int(11)
,`total_favoritos` bigint(21)
,`total_preguntas` bigint(21)
,`total_ofertas_recibidas` bigint(21)
,`oferta_mas_alta` decimal(12,2)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vista_top_publicaciones`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `vista_top_publicaciones` (
`id` int(11)
,`visitas` int(11)
,`score` decimal(10,2)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vista_usuarios_activos`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `vista_usuarios_activos` (
`id` int(11)
,`nombre` varchar(100)
,`email` varchar(120)
,`dni` varchar(8)
,`password_hash` varchar(150)
,`telefono` varchar(20)
,`reputacion` decimal(2,1)
,`score_riesgo` decimal(5,2)
,`verificacion` enum('1','2','3','4','5')
,`fecha_creacion` timestamp
,`total_compras` int(11)
,`total_ventas` int(11)
,`eliminado` tinyint(1)
,`fecha_eliminacion` timestamp
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vista_vendedores`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `vista_vendedores` (
`id` int(11)
,`nombre` varchar(100)
,`total_ventas` bigint(21)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vista_vendedores_activos`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `vista_vendedores_activos` (
`id` int(11)
,`nombre` varchar(100)
,`total_ventas` int(11)
,`reputacion` decimal(2,1)
);

-- --------------------------------------------------------

--
-- Estructura para la vista `vista_compradores`
--
DROP TABLE IF EXISTS `vista_compradores`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vista_compradores`  AS SELECT `u`.`id` AS `id`, `u`.`nombre` AS `nombre`, count(`c`.`id`) AS `total_compras` FROM (`usuarios` `u` join `compras` `c` on(`u`.`id` = `c`.`comprador_id`)) GROUP BY `u`.`id`, `u`.`nombre` ;

-- --------------------------------------------------------

--
-- Estructura para la vista `vista_historial_compras`
--
DROP TABLE IF EXISTS `vista_historial_compras`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vista_historial_compras`  AS SELECT `c`.`id` AS `id`, `u`.`nombre` AS `comprador`, `p`.`id` AS `publicacion`, `c`.`precio_final` AS `precio_final`, `c`.`estado` AS `estado` FROM ((`compras` `c` join `usuarios` `u` on(`c`.`comprador_id` = `u`.`id`)) join `publicaciones` `p` on(`c`.`publicacion_id` = `p`.`id`)) ;

-- --------------------------------------------------------

--
-- Estructura para la vista `vista_publicaciones_activas`
--
DROP TABLE IF EXISTS `vista_publicaciones_activas`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vista_publicaciones_activas`  AS SELECT `publicaciones`.`id` AS `id`, `publicaciones`.`usuario_id` AS `usuario_id`, `publicaciones`.`auto_id` AS `auto_id`, `publicaciones`.`precio` AS `precio`, `publicaciones`.`moneda` AS `moneda`, `publicaciones`.`estado` AS `estado`, `publicaciones`.`score` AS `score`, `publicaciones`.`destacado` AS `destacado`, `publicaciones`.`visitas` AS `visitas`, `publicaciones`.`descripcion` AS `descripcion`, `publicaciones`.`fecha_publicacion` AS `fecha_publicacion`, `publicaciones`.`fecha_expiracion` AS `fecha_expiracion`, `publicaciones`.`eliminado` AS `eliminado`, `publicaciones`.`fecha_eliminacion` AS `fecha_eliminacion` FROM `publicaciones` WHERE `publicaciones`.`eliminado` = 0 ;

-- --------------------------------------------------------

--
-- Estructura para la vista `vista_publicaciones_detalle`
--
DROP TABLE IF EXISTS `vista_publicaciones_detalle`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vista_publicaciones_detalle`  AS SELECT `p`.`id` AS `id`, `p`.`precio` AS `precio`, `p`.`estado` AS `estado`, `u`.`nombre` AS `vendedor`, `a`.`anio` AS `anio`, `m`.`nombre` AS `marca` FROM ((((`publicaciones` `p` join `usuarios` `u` on(`p`.`usuario_id` = `u`.`id`)) join `autos` `a` on(`p`.`auto_id` = `a`.`id`)) join `modelos` `mo` on(`a`.`modelo_id` = `mo`.`id`)) join `marcas` `m` on(`mo`.`marca_id` = `m`.`id`)) ;

-- --------------------------------------------------------

--
-- Estructura para la vista `vista_rendimiento_publicaciones`
--
DROP TABLE IF EXISTS `vista_rendimiento_publicaciones`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vista_rendimiento_publicaciones`  AS SELECT `p`.`id` AS `publicacion_id`, `p`.`usuario_id` AS `vendedor_id`, `m`.`nombre` AS `marca`, `mo`.`nombre` AS `modelo`, `p`.`precio` AS `precio`, `p`.`moneda` AS `moneda`, `p`.`visitas` AS `visitas`, (select count(0) from `favoritos` `f` where `f`.`publicacion_id` = `p`.`id`) AS `total_favoritos`, (select count(0) from `preguntas` `pr` where `pr`.`publicacion_id` = `p`.`id`) AS `total_preguntas`, (select count(0) from `ofertas` `o` where `o`.`publicacion_id` = `p`.`id`) AS `total_ofertas_recibidas`, (select max(`o`.`monto`) from `ofertas` `o` where `o`.`publicacion_id` = `p`.`id` and `o`.`estado` = 'PENDIENTE') AS `oferta_mas_alta` FROM (((`publicaciones` `p` join `autos` `a` on(`p`.`auto_id` = `a`.`id`)) join `modelos` `mo` on(`a`.`modelo_id` = `mo`.`id`)) join `marcas` `m` on(`mo`.`marca_id` = `m`.`id`)) WHERE `p`.`eliminado` = 0 AND `p`.`estado` = 'ACTIVA' ;

-- --------------------------------------------------------

--
-- Estructura para la vista `vista_top_publicaciones`
--
DROP TABLE IF EXISTS `vista_top_publicaciones`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vista_top_publicaciones`  AS SELECT `p`.`id` AS `id`, `p`.`visitas` AS `visitas`, `p`.`score` AS `score` FROM `publicaciones` AS `p` ORDER BY `p`.`score` DESC ;

-- --------------------------------------------------------

--
-- Estructura para la vista `vista_usuarios_activos`
--
DROP TABLE IF EXISTS `vista_usuarios_activos`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vista_usuarios_activos`  AS SELECT `usuarios`.`id` AS `id`, `usuarios`.`nombre` AS `nombre`, `usuarios`.`email` AS `email`, `usuarios`.`dni` AS `dni`, `usuarios`.`password_hash` AS `password_hash`, `usuarios`.`telefono` AS `telefono`, `usuarios`.`reputacion` AS `reputacion`, `usuarios`.`score_riesgo` AS `score_riesgo`, `usuarios`.`verificacion` AS `verificacion`, `usuarios`.`fecha_creacion` AS `fecha_creacion`, `usuarios`.`total_compras` AS `total_compras`, `usuarios`.`total_ventas` AS `total_ventas`, `usuarios`.`eliminado` AS `eliminado`, `usuarios`.`fecha_eliminacion` AS `fecha_eliminacion` FROM `usuarios` WHERE `usuarios`.`eliminado` = 0 ;

-- --------------------------------------------------------

--
-- Estructura para la vista `vista_vendedores`
--
DROP TABLE IF EXISTS `vista_vendedores`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vista_vendedores`  AS SELECT `u`.`id` AS `id`, `u`.`nombre` AS `nombre`, count(`p`.`id`) AS `total_ventas` FROM (`usuarios` `u` join `publicaciones` `p` on(`u`.`id` = `p`.`usuario_id`)) GROUP BY `u`.`id`, `u`.`nombre` ;

-- --------------------------------------------------------

--
-- Estructura para la vista `vista_vendedores_activos`
--
DROP TABLE IF EXISTS `vista_vendedores_activos`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vista_vendedores_activos`  AS SELECT `u`.`id` AS `id`, `u`.`nombre` AS `nombre`, `u`.`total_ventas` AS `total_ventas`, `u`.`reputacion` AS `reputacion` FROM `usuarios` AS `u` WHERE `u`.`total_ventas` > 0 ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `actividad_sospechosa`
--
ALTER TABLE `actividad_sospechosa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Indices de la tabla `actividad_usuario`
--
ALTER TABLE `actividad_usuario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Indices de la tabla `auditoria`
--
ALTER TABLE `auditoria`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `autos`
--
ALTER TABLE `autos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `modelo_id` (`modelo_id`),
  ADD KEY `combustible_id` (`combustible_id`),
  ADD KEY `transmision_id` (`transmision_id`);

--
-- Indices de la tabla `banco`
--
ALTER TABLE `banco`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `billeteras`
--
ALTER TABLE `billeteras`
  ADD PRIMARY KEY (`usuario_id`);

--
-- Indices de la tabla `bloqueos`
--
ALTER TABLE `bloqueos`
  ADD PRIMARY KEY (`usuario_id`,`usuario_bloqueado_id`),
  ADD KEY `usuario_bloqueado_id` (`usuario_bloqueado_id`);

--
-- Indices de la tabla `busquedas_guardadas`
--
ALTER TABLE `busquedas_guardadas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Indices de la tabla `chats`
--
ALTER TABLE `chats`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `usuario1_id` (`usuario1_id`,`usuario2_id`),
  ADD KEY `usuario2_id` (`usuario2_id`),
  ADD KEY `idx_chats_publicacion` (`publicacion_id`);

--
-- Indices de la tabla `comisiones`
--
ALTER TABLE `comisiones`
  ADD PRIMARY KEY (`id`),
  ADD KEY `compra_id` (`compra_id`);

--
-- Indices de la tabla `compras`
--
ALTER TABLE `compras`
  ADD PRIMARY KEY (`id`),
  ADD KEY `publicacion_id` (`publicacion_id`),
  ADD KEY `idx_compras_usuario` (`comprador_id`);

--
-- Indices de la tabla `cuotas`
--
ALTER TABLE `cuotas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `pago_cuota_id` (`pago_cuota_id`);

--
-- Indices de la tabla `direcciones`
--
ALTER TABLE `direcciones`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Indices de la tabla `dispositivos_usuario`
--
ALTER TABLE `dispositivos_usuario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Indices de la tabla `encuentros`
--
ALTER TABLE `encuentros`
  ADD PRIMARY KEY (`id`),
  ADD KEY `compra_id` (`compra_id`),
  ADD KEY `vendedor_id` (`vendedor_id`),
  ADD KEY `comprador_id` (`comprador_id`);

--
-- Indices de la tabla `estadisticas_publicacion`
--
ALTER TABLE `estadisticas_publicacion`
  ADD PRIMARY KEY (`publicacion_id`);

--
-- Indices de la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`),
  ADD KEY `compra_id` (`compra_id`);

--
-- Indices de la tabla `favoritos`
--
ALTER TABLE `favoritos`
  ADD PRIMARY KEY (`usuario_id`,`publicacion_id`),
  ADD KEY `publicacion_id` (`publicacion_id`);

--
-- Indices de la tabla `historial_precios`
--
ALTER TABLE `historial_precios`
  ADD PRIMARY KEY (`id`),
  ADD KEY `publicacion_id` (`publicacion_id`);

--
-- Indices de la tabla `historial_publicaciones`
--
ALTER TABLE `historial_publicaciones`
  ADD PRIMARY KEY (`id`),
  ADD KEY `publicacion_id` (`publicacion_id`);

--
-- Indices de la tabla `historial_vehiculo`
--
ALTER TABLE `historial_vehiculo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `auto_id` (`auto_id`);

--
-- Indices de la tabla `informe_dominio`
--
ALTER TABLE `informe_dominio`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `auto_id` (`auto_id`),
  ADD UNIQUE KEY `dominio` (`dominio`);

--
-- Indices de la tabla `item_carrito`
--
ALTER TABLE `item_carrito`
  ADD PRIMARY KEY (`usuario_id`,`publicacion_id`),
  ADD KEY `publicacion_id` (`publicacion_id`);

--
-- Indices de la tabla `logs_transacciones`
--
ALTER TABLE `logs_transacciones`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `marcas`
--
ALTER TABLE `marcas`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `mensajes`
--
ALTER TABLE `mensajes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `emisor_id` (`emisor_id`),
  ADD KEY `idx_mensajes_chat` (`chat_id`);

--
-- Indices de la tabla `mensajes_soporte`
--
ALTER TABLE `mensajes_soporte`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ticket_id` (`ticket_id`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Indices de la tabla `metodos_pago`
--
ALTER TABLE `metodos_pago`
  ADD PRIMARY KEY (`id`),
  ADD KEY `tipo_pago_id` (`tipo_pago_id`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Indices de la tabla `modelos`
--
ALTER TABLE `modelos`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `marca_id` (`marca_id`,`nombre`);

--
-- Indices de la tabla `moderacion_multimedia`
--
ALTER TABLE `moderacion_multimedia`
  ADD PRIMARY KEY (`id`),
  ADD KEY `multimedia_id` (`multimedia_id`);

--
-- Indices de la tabla `movimientos`
--
ALTER TABLE `movimientos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Indices de la tabla `multimedia`
--
ALTER TABLE `multimedia`
  ADD PRIMARY KEY (`id`),
  ADD KEY `publicacion_id` (`publicacion_id`);

--
-- Indices de la tabla `notificaciones`
--
ALTER TABLE `notificaciones`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Indices de la tabla `ofertas`
--
ALTER TABLE `ofertas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `publicacion_id` (`publicacion_id`),
  ADD KEY `comprador_id` (`comprador_id`);

--
-- Indices de la tabla `pagos_cuotas`
--
ALTER TABLE `pagos_cuotas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `transaccion_id` (`transaccion_id`),
  ADD KEY `plan_id` (`plan_id`);

--
-- Indices de la tabla `pagos_promocion`
--
ALTER TABLE `pagos_promocion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `promocion_id` (`promocion_id`);

--
-- Indices de la tabla `planes_cuotas`
--
ALTER TABLE `planes_cuotas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `preferencias_usuario`
--
ALTER TABLE `preferencias_usuario`
  ADD PRIMARY KEY (`usuario_id`);

--
-- Indices de la tabla `preguntas`
--
ALTER TABLE `preguntas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `publicacion_id` (`publicacion_id`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Indices de la tabla `promociones`
--
ALTER TABLE `promociones`
  ADD PRIMARY KEY (`id`),
  ADD KEY `publicacion_id` (`publicacion_id`),
  ADD KEY `tipo_promocion_id` (`tipo_promocion_id`);

--
-- Indices de la tabla `promociones_bancarias`
--
ALTER TABLE `promociones_bancarias`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_promociones_banco` (`id_banco`);

--
-- Indices de la tabla `publicaciones`
--
ALTER TABLE `publicaciones`
  ADD PRIMARY KEY (`id`),
  ADD KEY `auto_id` (`auto_id`),
  ADD KEY `idx_publicaciones_usuario` (`usuario_id`),
  ADD KEY `idx_publicaciones_estado` (`estado`);

--
-- Indices de la tabla `reembolsos`
--
ALTER TABLE `reembolsos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `transaccion_id` (`transaccion_id`);

--
-- Indices de la tabla `reportes`
--
ALTER TABLE `reportes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`),
  ADD KEY `publicacion_id` (`publicacion_id`);

--
-- Indices de la tabla `resenias`
--
ALTER TABLE `resenias`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `comprador_id` (`comprador_id`,`vendedor_id`),
  ADD KEY `vendedor_id` (`vendedor_id`);

--
-- Indices de la tabla `respuestas`
--
ALTER TABLE `respuestas`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `pregunta_id` (`pregunta_id`),
  ADD KEY `vendedor_id` (`vendedor_id`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `sesiones`
--
ALTER TABLE `sesiones`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Indices de la tabla `tarjetas_usuario`
--
ALTER TABLE `tarjetas_usuario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Indices de la tabla `tickets_soporte`
--
ALTER TABLE `tickets_soporte`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_tickets_usuario` (`usuario_id`),
  ADD KEY `idx_tickets_estado` (`estado`);

--
-- Indices de la tabla `tipos_combustible`
--
ALTER TABLE `tipos_combustible`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `tipos_pago`
--
ALTER TABLE `tipos_pago`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `tipos_promocion`
--
ALTER TABLE `tipos_promocion`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `transacciones`
--
ALTER TABLE `transacciones`
  ADD PRIMARY KEY (`id`),
  ADD KEY `tipo_pago_id` (`tipo_pago_id`),
  ADD KEY `idx_transacciones_compra` (`compra_id`);

--
-- Indices de la tabla `transmisiones`
--
ALTER TABLE `transmisiones`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `dni` (`dni`);

--
-- Indices de la tabla `usuario_roles`
--
ALTER TABLE `usuario_roles`
  ADD PRIMARY KEY (`usuario_id`,`rol_id`),
  ADD KEY `rol_id` (`rol_id`);

--
-- Indices de la tabla `variantes`
--
ALTER TABLE `variantes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `publicacion_id` (`publicacion_id`);

--
-- Indices de la tabla `variante_opciones`
--
ALTER TABLE `variante_opciones`
  ADD PRIMARY KEY (`id`),
  ADD KEY `variante_id` (`variante_id`);

--
-- Indices de la tabla `visitas_auto`
--
ALTER TABLE `visitas_auto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `auto_id` (`auto_id`),
  ADD KEY `comprador_id` (`comprador_id`);

--
-- Indices de la tabla `vistas_publicacion`
--
ALTER TABLE `vistas_publicacion`
  ADD PRIMARY KEY (`usuario_id`,`publicacion_id`,`fecha`),
  ADD KEY `publicacion_id` (`publicacion_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `actividad_sospechosa`
--
ALTER TABLE `actividad_sospechosa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `actividad_usuario`
--
ALTER TABLE `actividad_usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `auditoria`
--
ALTER TABLE `auditoria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `autos`
--
ALTER TABLE `autos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `banco`
--
ALTER TABLE `banco`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `busquedas_guardadas`
--
ALTER TABLE `busquedas_guardadas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `chats`
--
ALTER TABLE `chats`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `comisiones`
--
ALTER TABLE `comisiones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `compras`
--
ALTER TABLE `compras`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cuotas`
--
ALTER TABLE `cuotas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `direcciones`
--
ALTER TABLE `direcciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `dispositivos_usuario`
--
ALTER TABLE `dispositivos_usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `encuentros`
--
ALTER TABLE `encuentros`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `facturas`
--
ALTER TABLE `facturas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `historial_precios`
--
ALTER TABLE `historial_precios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `historial_publicaciones`
--
ALTER TABLE `historial_publicaciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `historial_vehiculo`
--
ALTER TABLE `historial_vehiculo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `informe_dominio`
--
ALTER TABLE `informe_dominio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `logs_transacciones`
--
ALTER TABLE `logs_transacciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `marcas`
--
ALTER TABLE `marcas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `mensajes`
--
ALTER TABLE `mensajes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `mensajes_soporte`
--
ALTER TABLE `mensajes_soporte`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `metodos_pago`
--
ALTER TABLE `metodos_pago`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `modelos`
--
ALTER TABLE `modelos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `moderacion_multimedia`
--
ALTER TABLE `moderacion_multimedia`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `movimientos`
--
ALTER TABLE `movimientos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `multimedia`
--
ALTER TABLE `multimedia`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `notificaciones`
--
ALTER TABLE `notificaciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `ofertas`
--
ALTER TABLE `ofertas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pagos_cuotas`
--
ALTER TABLE `pagos_cuotas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pagos_promocion`
--
ALTER TABLE `pagos_promocion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `planes_cuotas`
--
ALTER TABLE `planes_cuotas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `preguntas`
--
ALTER TABLE `preguntas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `promociones`
--
ALTER TABLE `promociones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `promociones_bancarias`
--
ALTER TABLE `promociones_bancarias`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `publicaciones`
--
ALTER TABLE `publicaciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `reembolsos`
--
ALTER TABLE `reembolsos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `reportes`
--
ALTER TABLE `reportes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `resenias`
--
ALTER TABLE `resenias`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `respuestas`
--
ALTER TABLE `respuestas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `sesiones`
--
ALTER TABLE `sesiones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tarjetas_usuario`
--
ALTER TABLE `tarjetas_usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tickets_soporte`
--
ALTER TABLE `tickets_soporte`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipos_combustible`
--
ALTER TABLE `tipos_combustible`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipos_pago`
--
ALTER TABLE `tipos_pago`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipos_promocion`
--
ALTER TABLE `tipos_promocion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `transacciones`
--
ALTER TABLE `transacciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `transmisiones`
--
ALTER TABLE `transmisiones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `variantes`
--
ALTER TABLE `variantes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `variante_opciones`
--
ALTER TABLE `variante_opciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `visitas_auto`
--
ALTER TABLE `visitas_auto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `actividad_sospechosa`
--
ALTER TABLE `actividad_sospechosa`
  ADD CONSTRAINT `actividad_sospechosa_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `actividad_usuario`
--
ALTER TABLE `actividad_usuario`
  ADD CONSTRAINT `actividad_usuario_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `autos`
--
ALTER TABLE `autos`
  ADD CONSTRAINT `autos_ibfk_1` FOREIGN KEY (`modelo_id`) REFERENCES `modelos` (`id`),
  ADD CONSTRAINT `autos_ibfk_2` FOREIGN KEY (`combustible_id`) REFERENCES `tipos_combustible` (`id`),
  ADD CONSTRAINT `autos_ibfk_3` FOREIGN KEY (`transmision_id`) REFERENCES `transmisiones` (`id`);

--
-- Filtros para la tabla `billeteras`
--
ALTER TABLE `billeteras`
  ADD CONSTRAINT `billeteras_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `bloqueos`
--
ALTER TABLE `bloqueos`
  ADD CONSTRAINT `bloqueos_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`),
  ADD CONSTRAINT `bloqueos_ibfk_2` FOREIGN KEY (`usuario_bloqueado_id`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `busquedas_guardadas`
--
ALTER TABLE `busquedas_guardadas`
  ADD CONSTRAINT `busquedas_guardadas_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `chats`
--
ALTER TABLE `chats`
  ADD CONSTRAINT `chats_ibfk_1` FOREIGN KEY (`usuario1_id`) REFERENCES `usuarios` (`id`),
  ADD CONSTRAINT `chats_ibfk_2` FOREIGN KEY (`usuario2_id`) REFERENCES `usuarios` (`id`),
  ADD CONSTRAINT `fk_chat_publicacion` FOREIGN KEY (`publicacion_id`) REFERENCES `publicaciones` (`id`);

--
-- Filtros para la tabla `comisiones`
--
ALTER TABLE `comisiones`
  ADD CONSTRAINT `comisiones_ibfk_1` FOREIGN KEY (`compra_id`) REFERENCES `compras` (`id`);

--
-- Filtros para la tabla `compras`
--
ALTER TABLE `compras`
  ADD CONSTRAINT `compras_ibfk_1` FOREIGN KEY (`comprador_id`) REFERENCES `usuarios` (`id`),
  ADD CONSTRAINT `compras_ibfk_2` FOREIGN KEY (`publicacion_id`) REFERENCES `publicaciones` (`id`);

--
-- Filtros para la tabla `cuotas`
--
ALTER TABLE `cuotas`
  ADD CONSTRAINT `cuotas_ibfk_1` FOREIGN KEY (`pago_cuota_id`) REFERENCES `pagos_cuotas` (`id`);

--
-- Filtros para la tabla `direcciones`
--
ALTER TABLE `direcciones`
  ADD CONSTRAINT `direcciones_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `dispositivos_usuario`
--
ALTER TABLE `dispositivos_usuario`
  ADD CONSTRAINT `dispositivos_usuario_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `encuentros`
--
ALTER TABLE `encuentros`
  ADD CONSTRAINT `encuentros_ibfk_1` FOREIGN KEY (`compra_id`) REFERENCES `compras` (`id`),
  ADD CONSTRAINT `encuentros_ibfk_2` FOREIGN KEY (`vendedor_id`) REFERENCES `usuarios` (`id`),
  ADD CONSTRAINT `encuentros_ibfk_3` FOREIGN KEY (`comprador_id`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `estadisticas_publicacion`
--
ALTER TABLE `estadisticas_publicacion`
  ADD CONSTRAINT `estadisticas_publicacion_ibfk_1` FOREIGN KEY (`publicacion_id`) REFERENCES `publicaciones` (`id`);

--
-- Filtros para la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD CONSTRAINT `facturas_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`),
  ADD CONSTRAINT `facturas_ibfk_2` FOREIGN KEY (`compra_id`) REFERENCES `compras` (`id`);

--
-- Filtros para la tabla `favoritos`
--
ALTER TABLE `favoritos`
  ADD CONSTRAINT `favoritos_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`),
  ADD CONSTRAINT `favoritos_ibfk_2` FOREIGN KEY (`publicacion_id`) REFERENCES `publicaciones` (`id`);

--
-- Filtros para la tabla `historial_precios`
--
ALTER TABLE `historial_precios`
  ADD CONSTRAINT `historial_precios_ibfk_1` FOREIGN KEY (`publicacion_id`) REFERENCES `publicaciones` (`id`);

--
-- Filtros para la tabla `historial_publicaciones`
--
ALTER TABLE `historial_publicaciones`
  ADD CONSTRAINT `historial_publicaciones_ibfk_1` FOREIGN KEY (`publicacion_id`) REFERENCES `publicaciones` (`id`);

--
-- Filtros para la tabla `historial_vehiculo`
--
ALTER TABLE `historial_vehiculo`
  ADD CONSTRAINT `historial_vehiculo_ibfk_1` FOREIGN KEY (`auto_id`) REFERENCES `autos` (`id`);

--
-- Filtros para la tabla `informe_dominio`
--
ALTER TABLE `informe_dominio`
  ADD CONSTRAINT `informe_dominio_ibfk_1` FOREIGN KEY (`auto_id`) REFERENCES `autos` (`id`);

--
-- Filtros para la tabla `item_carrito`
--
ALTER TABLE `item_carrito`
  ADD CONSTRAINT `item_carrito_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`),
  ADD CONSTRAINT `item_carrito_ibfk_2` FOREIGN KEY (`publicacion_id`) REFERENCES `publicaciones` (`id`);

--
-- Filtros para la tabla `mensajes`
--
ALTER TABLE `mensajes`
  ADD CONSTRAINT `mensajes_ibfk_1` FOREIGN KEY (`chat_id`) REFERENCES `chats` (`id`),
  ADD CONSTRAINT `mensajes_ibfk_2` FOREIGN KEY (`emisor_id`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `mensajes_soporte`
--
ALTER TABLE `mensajes_soporte`
  ADD CONSTRAINT `mensajes_soporte_ibfk_1` FOREIGN KEY (`ticket_id`) REFERENCES `tickets_soporte` (`id`),
  ADD CONSTRAINT `mensajes_soporte_ibfk_2` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `metodos_pago`
--
ALTER TABLE `metodos_pago`
  ADD CONSTRAINT `metodos_pago_ibfk_1` FOREIGN KEY (`tipo_pago_id`) REFERENCES `tipos_pago` (`id`),
  ADD CONSTRAINT `metodos_pago_ibfk_2` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `modelos`
--
ALTER TABLE `modelos`
  ADD CONSTRAINT `modelos_ibfk_1` FOREIGN KEY (`marca_id`) REFERENCES `marcas` (`id`);

--
-- Filtros para la tabla `moderacion_multimedia`
--
ALTER TABLE `moderacion_multimedia`
  ADD CONSTRAINT `moderacion_multimedia_ibfk_1` FOREIGN KEY (`multimedia_id`) REFERENCES `multimedia` (`id`);

--
-- Filtros para la tabla `movimientos`
--
ALTER TABLE `movimientos`
  ADD CONSTRAINT `movimientos_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `multimedia`
--
ALTER TABLE `multimedia`
  ADD CONSTRAINT `multimedia_ibfk_1` FOREIGN KEY (`publicacion_id`) REFERENCES `publicaciones` (`id`);

--
-- Filtros para la tabla `notificaciones`
--
ALTER TABLE `notificaciones`
  ADD CONSTRAINT `notificaciones_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `ofertas`
--
ALTER TABLE `ofertas`
  ADD CONSTRAINT `ofertas_ibfk_1` FOREIGN KEY (`publicacion_id`) REFERENCES `publicaciones` (`id`),
  ADD CONSTRAINT `ofertas_ibfk_2` FOREIGN KEY (`comprador_id`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `pagos_cuotas`
--
ALTER TABLE `pagos_cuotas`
  ADD CONSTRAINT `pagos_cuotas_ibfk_1` FOREIGN KEY (`transaccion_id`) REFERENCES `transacciones` (`id`),
  ADD CONSTRAINT `pagos_cuotas_ibfk_2` FOREIGN KEY (`plan_id`) REFERENCES `planes_cuotas` (`id`);

--
-- Filtros para la tabla `pagos_promocion`
--
ALTER TABLE `pagos_promocion`
  ADD CONSTRAINT `pagos_promocion_ibfk_1` FOREIGN KEY (`promocion_id`) REFERENCES `promociones` (`id`);

--
-- Filtros para la tabla `preferencias_usuario`
--
ALTER TABLE `preferencias_usuario`
  ADD CONSTRAINT `preferencias_usuario_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `preguntas`
--
ALTER TABLE `preguntas`
  ADD CONSTRAINT `preguntas_ibfk_1` FOREIGN KEY (`publicacion_id`) REFERENCES `publicaciones` (`id`),
  ADD CONSTRAINT `preguntas_ibfk_2` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `promociones`
--
ALTER TABLE `promociones`
  ADD CONSTRAINT `promociones_ibfk_1` FOREIGN KEY (`publicacion_id`) REFERENCES `publicaciones` (`id`),
  ADD CONSTRAINT `promociones_ibfk_2` FOREIGN KEY (`tipo_promocion_id`) REFERENCES `tipos_promocion` (`id`);

--
-- Filtros para la tabla `promociones_bancarias`
--
ALTER TABLE `promociones_bancarias`
  ADD CONSTRAINT `promociones_bancarias_ibfk_1` FOREIGN KEY (`id_banco`) REFERENCES `banco` (`id`);

--
-- Filtros para la tabla `publicaciones`
--
ALTER TABLE `publicaciones`
  ADD CONSTRAINT `publicaciones_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`),
  ADD CONSTRAINT `publicaciones_ibfk_2` FOREIGN KEY (`auto_id`) REFERENCES `autos` (`id`);

--
-- Filtros para la tabla `reembolsos`
--
ALTER TABLE `reembolsos`
  ADD CONSTRAINT `reembolsos_ibfk_1` FOREIGN KEY (`transaccion_id`) REFERENCES `transacciones` (`id`);

--
-- Filtros para la tabla `reportes`
--
ALTER TABLE `reportes`
  ADD CONSTRAINT `reportes_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`),
  ADD CONSTRAINT `reportes_ibfk_2` FOREIGN KEY (`publicacion_id`) REFERENCES `publicaciones` (`id`);

--
-- Filtros para la tabla `resenias`
--
ALTER TABLE `resenias`
  ADD CONSTRAINT `resenias_ibfk_1` FOREIGN KEY (`comprador_id`) REFERENCES `usuarios` (`id`),
  ADD CONSTRAINT `resenias_ibfk_2` FOREIGN KEY (`vendedor_id`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `respuestas`
--
ALTER TABLE `respuestas`
  ADD CONSTRAINT `respuestas_ibfk_1` FOREIGN KEY (`pregunta_id`) REFERENCES `preguntas` (`id`),
  ADD CONSTRAINT `respuestas_ibfk_2` FOREIGN KEY (`vendedor_id`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `sesiones`
--
ALTER TABLE `sesiones`
  ADD CONSTRAINT `sesiones_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `tarjetas_usuario`
--
ALTER TABLE `tarjetas_usuario`
  ADD CONSTRAINT `tarjetas_usuario_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `tickets_soporte`
--
ALTER TABLE `tickets_soporte`
  ADD CONSTRAINT `tickets_soporte_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `transacciones`
--
ALTER TABLE `transacciones`
  ADD CONSTRAINT `transacciones_ibfk_1` FOREIGN KEY (`compra_id`) REFERENCES `compras` (`id`),
  ADD CONSTRAINT `transacciones_ibfk_2` FOREIGN KEY (`tipo_pago_id`) REFERENCES `tipos_pago` (`id`);

--
-- Filtros para la tabla `usuario_roles`
--
ALTER TABLE `usuario_roles`
  ADD CONSTRAINT `usuario_roles_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`),
  ADD CONSTRAINT `usuario_roles_ibfk_2` FOREIGN KEY (`rol_id`) REFERENCES `roles` (`id`);

--
-- Filtros para la tabla `variantes`
--
ALTER TABLE `variantes`
  ADD CONSTRAINT `variantes_ibfk_1` FOREIGN KEY (`publicacion_id`) REFERENCES `publicaciones` (`id`);

--
-- Filtros para la tabla `variante_opciones`
--
ALTER TABLE `variante_opciones`
  ADD CONSTRAINT `variante_opciones_ibfk_1` FOREIGN KEY (`variante_id`) REFERENCES `variantes` (`id`);

--
-- Filtros para la tabla `visitas_auto`
--
ALTER TABLE `visitas_auto`
  ADD CONSTRAINT `visitas_auto_ibfk_1` FOREIGN KEY (`auto_id`) REFERENCES `autos` (`id`),
  ADD CONSTRAINT `visitas_auto_ibfk_2` FOREIGN KEY (`comprador_id`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `vistas_publicacion`
--
ALTER TABLE `vistas_publicacion`
  ADD CONSTRAINT `vistas_publicacion_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`),
  ADD CONSTRAINT `vistas_publicacion_ibfk_2` FOREIGN KEY (`publicacion_id`) REFERENCES `publicaciones` (`id`);
--
-- Base de datos: `phpmyadmin`
--
CREATE DATABASE IF NOT EXISTS `phpmyadmin` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `phpmyadmin`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__bookmark`
--

CREATE TABLE `pma__bookmark` (
  `id` int(10) UNSIGNED NOT NULL,
  `dbase` varchar(255) NOT NULL DEFAULT '',
  `user` varchar(255) NOT NULL DEFAULT '',
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `query` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Bookmarks';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__central_columns`
--

CREATE TABLE `pma__central_columns` (
  `db_name` varchar(64) NOT NULL,
  `col_name` varchar(64) NOT NULL,
  `col_type` varchar(64) NOT NULL,
  `col_length` text DEFAULT NULL,
  `col_collation` varchar(64) NOT NULL,
  `col_isNull` tinyint(1) NOT NULL,
  `col_extra` varchar(255) DEFAULT '',
  `col_default` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Central list of columns';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__column_info`
--

CREATE TABLE `pma__column_info` (
  `id` int(5) UNSIGNED NOT NULL,
  `db_name` varchar(64) NOT NULL DEFAULT '',
  `table_name` varchar(64) NOT NULL DEFAULT '',
  `column_name` varchar(64) NOT NULL DEFAULT '',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `mimetype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `transformation` varchar(255) NOT NULL DEFAULT '',
  `transformation_options` varchar(255) NOT NULL DEFAULT '',
  `input_transformation` varchar(255) NOT NULL DEFAULT '',
  `input_transformation_options` varchar(255) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Column information for phpMyAdmin';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__designer_settings`
--

CREATE TABLE `pma__designer_settings` (
  `username` varchar(64) NOT NULL,
  `settings_data` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Settings related to Designer';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__export_templates`
--

CREATE TABLE `pma__export_templates` (
  `id` int(5) UNSIGNED NOT NULL,
  `username` varchar(64) NOT NULL,
  `export_type` varchar(10) NOT NULL,
  `template_name` varchar(64) NOT NULL,
  `template_data` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Saved export templates';

--
-- Volcado de datos para la tabla `pma__export_templates`
--

INSERT INTO `pma__export_templates` (`id`, `username`, `export_type`, `template_name`, `template_data`) VALUES
(1, 'root', 'server', 'databasecars', '{\"quick_or_custom\":\"quick\",\"what\":\"sql\",\"db_select[]\":[\"compraventa\",\"phpmyadmin\",\"test\"],\"aliases_new\":\"\",\"output_format\":\"sendit\",\"filename_template\":\"@SERVER@\",\"remember_template\":\"on\",\"charset\":\"utf-8\",\"compression\":\"none\",\"maxsize\":\"\",\"codegen_structure_or_data\":\"data\",\"codegen_format\":\"0\",\"csv_separator\":\",\",\"csv_enclosed\":\"\\\"\",\"csv_escaped\":\"\\\"\",\"csv_terminated\":\"AUTO\",\"csv_null\":\"NULL\",\"csv_columns\":\"something\",\"csv_structure_or_data\":\"data\",\"excel_null\":\"NULL\",\"excel_columns\":\"something\",\"excel_edition\":\"win\",\"excel_structure_or_data\":\"data\",\"json_structure_or_data\":\"data\",\"json_unicode\":\"something\",\"latex_caption\":\"something\",\"latex_structure_or_data\":\"structure_and_data\",\"latex_structure_caption\":\"Estructura de la tabla @TABLE@\",\"latex_structure_continued_caption\":\"Estructura de la tabla @TABLE@ (continúa)\",\"latex_structure_label\":\"tab:@TABLE@-structure\",\"latex_relation\":\"something\",\"latex_comments\":\"something\",\"latex_mime\":\"something\",\"latex_columns\":\"something\",\"latex_data_caption\":\"Contenido de la tabla @TABLE@\",\"latex_data_continued_caption\":\"Contenido de la tabla @TABLE@ (continúa)\",\"latex_data_label\":\"tab:@TABLE@-data\",\"latex_null\":\"\\\\textit{NULL}\",\"mediawiki_structure_or_data\":\"data\",\"mediawiki_caption\":\"something\",\"mediawiki_headers\":\"something\",\"htmlword_structure_or_data\":\"structure_and_data\",\"htmlword_null\":\"NULL\",\"ods_null\":\"NULL\",\"ods_structure_or_data\":\"data\",\"odt_structure_or_data\":\"structure_and_data\",\"odt_relation\":\"something\",\"odt_comments\":\"something\",\"odt_mime\":\"something\",\"odt_columns\":\"something\",\"odt_null\":\"NULL\",\"pdf_report_title\":\"\",\"pdf_structure_or_data\":\"data\",\"phparray_structure_or_data\":\"data\",\"sql_include_comments\":\"something\",\"sql_header_comment\":\"\",\"sql_use_transaction\":\"something\",\"sql_compatibility\":\"NONE\",\"sql_structure_or_data\":\"structure_and_data\",\"sql_create_table\":\"something\",\"sql_auto_increment\":\"something\",\"sql_create_view\":\"something\",\"sql_create_trigger\":\"something\",\"sql_backquotes\":\"something\",\"sql_type\":\"INSERT\",\"sql_insert_syntax\":\"both\",\"sql_max_query_size\":\"50000\",\"sql_hex_for_binary\":\"something\",\"sql_utc_time\":\"something\",\"texytext_structure_or_data\":\"structure_and_data\",\"texytext_null\":\"NULL\",\"yaml_structure_or_data\":\"data\",\"\":null,\"as_separate_files\":null,\"csv_removeCRLF\":null,\"excel_removeCRLF\":null,\"json_pretty_print\":null,\"htmlword_columns\":null,\"ods_columns\":null,\"sql_dates\":null,\"sql_relation\":null,\"sql_mime\":null,\"sql_disable_fk\":null,\"sql_views_as_tables\":null,\"sql_metadata\":null,\"sql_drop_database\":null,\"sql_drop_table\":null,\"sql_if_not_exists\":null,\"sql_simple_view_export\":null,\"sql_view_current_user\":null,\"sql_or_replace_view\":null,\"sql_procedure_function\":null,\"sql_truncate\":null,\"sql_delayed\":null,\"sql_ignore\":null,\"texytext_columns\":null}');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__favorite`
--

CREATE TABLE `pma__favorite` (
  `username` varchar(64) NOT NULL,
  `tables` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Favorite tables';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__history`
--

CREATE TABLE `pma__history` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `username` varchar(64) NOT NULL DEFAULT '',
  `db` varchar(64) NOT NULL DEFAULT '',
  `table` varchar(64) NOT NULL DEFAULT '',
  `timevalue` timestamp NOT NULL DEFAULT current_timestamp(),
  `sqlquery` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='SQL history for phpMyAdmin';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__navigationhiding`
--

CREATE TABLE `pma__navigationhiding` (
  `username` varchar(64) NOT NULL,
  `item_name` varchar(64) NOT NULL,
  `item_type` varchar(64) NOT NULL,
  `db_name` varchar(64) NOT NULL,
  `table_name` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Hidden items of navigation tree';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__pdf_pages`
--

CREATE TABLE `pma__pdf_pages` (
  `db_name` varchar(64) NOT NULL DEFAULT '',
  `page_nr` int(10) UNSIGNED NOT NULL,
  `page_descr` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='PDF relation pages for phpMyAdmin';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__recent`
--

CREATE TABLE `pma__recent` (
  `username` varchar(64) NOT NULL,
  `tables` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Recently accessed tables';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__relation`
--

CREATE TABLE `pma__relation` (
  `master_db` varchar(64) NOT NULL DEFAULT '',
  `master_table` varchar(64) NOT NULL DEFAULT '',
  `master_field` varchar(64) NOT NULL DEFAULT '',
  `foreign_db` varchar(64) NOT NULL DEFAULT '',
  `foreign_table` varchar(64) NOT NULL DEFAULT '',
  `foreign_field` varchar(64) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Relation table';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__savedsearches`
--

CREATE TABLE `pma__savedsearches` (
  `id` int(5) UNSIGNED NOT NULL,
  `username` varchar(64) NOT NULL DEFAULT '',
  `db_name` varchar(64) NOT NULL DEFAULT '',
  `search_name` varchar(64) NOT NULL DEFAULT '',
  `search_data` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Saved searches';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__table_coords`
--

CREATE TABLE `pma__table_coords` (
  `db_name` varchar(64) NOT NULL DEFAULT '',
  `table_name` varchar(64) NOT NULL DEFAULT '',
  `pdf_page_number` int(11) NOT NULL DEFAULT 0,
  `x` float UNSIGNED NOT NULL DEFAULT 0,
  `y` float UNSIGNED NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table coordinates for phpMyAdmin PDF output';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__table_info`
--

CREATE TABLE `pma__table_info` (
  `db_name` varchar(64) NOT NULL DEFAULT '',
  `table_name` varchar(64) NOT NULL DEFAULT '',
  `display_field` varchar(64) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table information for phpMyAdmin';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__table_uiprefs`
--

CREATE TABLE `pma__table_uiprefs` (
  `username` varchar(64) NOT NULL,
  `db_name` varchar(64) NOT NULL,
  `table_name` varchar(64) NOT NULL,
  `prefs` text NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Tables'' UI preferences';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__tracking`
--

CREATE TABLE `pma__tracking` (
  `db_name` varchar(64) NOT NULL,
  `table_name` varchar(64) NOT NULL,
  `version` int(10) UNSIGNED NOT NULL,
  `date_created` datetime NOT NULL,
  `date_updated` datetime NOT NULL,
  `schema_snapshot` text NOT NULL,
  `schema_sql` text DEFAULT NULL,
  `data_sql` longtext DEFAULT NULL,
  `tracking` set('UPDATE','REPLACE','INSERT','DELETE','TRUNCATE','CREATE DATABASE','ALTER DATABASE','DROP DATABASE','CREATE TABLE','ALTER TABLE','RENAME TABLE','DROP TABLE','CREATE INDEX','DROP INDEX','CREATE VIEW','ALTER VIEW','DROP VIEW') DEFAULT NULL,
  `tracking_active` int(1) UNSIGNED NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Database changes tracking for phpMyAdmin';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__userconfig`
--

CREATE TABLE `pma__userconfig` (
  `username` varchar(64) NOT NULL,
  `timevalue` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `config_data` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User preferences storage for phpMyAdmin';

--
-- Volcado de datos para la tabla `pma__userconfig`
--

INSERT INTO `pma__userconfig` (`username`, `timevalue`, `config_data`) VALUES
('root', '2026-09-04 12:47:56', '{\"Console\\/Mode\":\"collapse\",\"lang\":\"es\"}');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__usergroups`
--

CREATE TABLE `pma__usergroups` (
  `usergroup` varchar(64) NOT NULL,
  `tab` varchar(64) NOT NULL,
  `allowed` enum('Y','N') NOT NULL DEFAULT 'N'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User groups with configured menu items';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__users`
--

CREATE TABLE `pma__users` (
  `username` varchar(64) NOT NULL,
  `usergroup` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Users and their assignments to user groups';

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `pma__bookmark`
--
ALTER TABLE `pma__bookmark`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `pma__central_columns`
--
ALTER TABLE `pma__central_columns`
  ADD PRIMARY KEY (`db_name`,`col_name`);

--
-- Indices de la tabla `pma__column_info`
--
ALTER TABLE `pma__column_info`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `db_name` (`db_name`,`table_name`,`column_name`);

--
-- Indices de la tabla `pma__designer_settings`
--
ALTER TABLE `pma__designer_settings`
  ADD PRIMARY KEY (`username`);

--
-- Indices de la tabla `pma__export_templates`
--
ALTER TABLE `pma__export_templates`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `u_user_type_template` (`username`,`export_type`,`template_name`);

--
-- Indices de la tabla `pma__favorite`
--
ALTER TABLE `pma__favorite`
  ADD PRIMARY KEY (`username`);

--
-- Indices de la tabla `pma__history`
--
ALTER TABLE `pma__history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `username` (`username`,`db`,`table`,`timevalue`);

--
-- Indices de la tabla `pma__navigationhiding`
--
ALTER TABLE `pma__navigationhiding`
  ADD PRIMARY KEY (`username`,`item_name`,`item_type`,`db_name`,`table_name`);

--
-- Indices de la tabla `pma__pdf_pages`
--
ALTER TABLE `pma__pdf_pages`
  ADD PRIMARY KEY (`page_nr`),
  ADD KEY `db_name` (`db_name`);

--
-- Indices de la tabla `pma__recent`
--
ALTER TABLE `pma__recent`
  ADD PRIMARY KEY (`username`);

--
-- Indices de la tabla `pma__relation`
--
ALTER TABLE `pma__relation`
  ADD PRIMARY KEY (`master_db`,`master_table`,`master_field`),
  ADD KEY `foreign_field` (`foreign_db`,`foreign_table`);

--
-- Indices de la tabla `pma__savedsearches`
--
ALTER TABLE `pma__savedsearches`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `u_savedsearches_username_dbname` (`username`,`db_name`,`search_name`);

--
-- Indices de la tabla `pma__table_coords`
--
ALTER TABLE `pma__table_coords`
  ADD PRIMARY KEY (`db_name`,`table_name`,`pdf_page_number`);

--
-- Indices de la tabla `pma__table_info`
--
ALTER TABLE `pma__table_info`
  ADD PRIMARY KEY (`db_name`,`table_name`);

--
-- Indices de la tabla `pma__table_uiprefs`
--
ALTER TABLE `pma__table_uiprefs`
  ADD PRIMARY KEY (`username`,`db_name`,`table_name`);

--
-- Indices de la tabla `pma__tracking`
--
ALTER TABLE `pma__tracking`
  ADD PRIMARY KEY (`db_name`,`table_name`,`version`);

--
-- Indices de la tabla `pma__userconfig`
--
ALTER TABLE `pma__userconfig`
  ADD PRIMARY KEY (`username`);

--
-- Indices de la tabla `pma__usergroups`
--
ALTER TABLE `pma__usergroups`
  ADD PRIMARY KEY (`usergroup`,`tab`,`allowed`);

--
-- Indices de la tabla `pma__users`
--
ALTER TABLE `pma__users`
  ADD PRIMARY KEY (`username`,`usergroup`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `pma__bookmark`
--
ALTER TABLE `pma__bookmark`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pma__column_info`
--
ALTER TABLE `pma__column_info`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pma__export_templates`
--
ALTER TABLE `pma__export_templates`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `pma__history`
--
ALTER TABLE `pma__history`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pma__pdf_pages`
--
ALTER TABLE `pma__pdf_pages`
  MODIFY `page_nr` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pma__savedsearches`
--
ALTER TABLE `pma__savedsearches`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- Base de datos: `test`
--
CREATE DATABASE IF NOT EXISTS `test` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `test`;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
