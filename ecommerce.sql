DROP DATABASE IF EXISTS COMPRAVENTA;


CREATE DATABASE COMPRAVENTA;


USE COMPRAVENTA;


CREATE TABLE roles (


    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) UNIQUE NOT NULL,
    activo BOOLEAN DEFAULT TRUE


);

CREATE TABLE logs_transacciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(50),
    descripcion TEXT,
    referencia_id INT,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Usuarios (


    id INT AUTO_INCREMENT PRIMARY KEY,


    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(120) UNIQUE NOT NULL,
    password_hash VARCHAR(150) NOT NULL, -- contraseña encriptada
    telefono VARCHAR(20) NOT NULL,


    reputacion DECIMAL(2,1) DEFAULT 0,
    score_riesgo DECIMAL(5,2) DEFAULT 0,
    verificado BOOLEAN DEFAULT FALSE,
   
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    total_compras INT DEFAULT 0,
    total_ventas INT DEFAULT 0

);

CREATE TABLE usuario_roles (
    usuario_id INT,
    rol_id INT,
    PRIMARY KEY (usuario_id, rol_id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (rol_id) REFERENCES roles(id)
);

CREATE TABLE actividad_usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT,
    accion VARCHAR(50),
    descripcion TEXT,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);


CREATE TABLE sesiones (


    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT,
    token TEXT,
    fecha_expiracion TIMESTAMP,


    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)


);


CREATE TABLE direcciones (


    id INT AUTO_INCREMENT PRIMARY KEY,


    usuario_id INT NOT NULL,


    pais VARCHAR(50),
    provincia VARCHAR(50),
    ciudad VARCHAR(50),
    direccion TEXT,


    tipo VARCHAR(20), -- domicilio, trabajo.
    es_principal BOOLEAN DEFAULT FALSE, -- Direccion usuario


    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,


    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE


);


CREATE TABLE notificaciones (


    id INT AUTO_INCREMENT PRIMARY KEY,


    usuario_id INT NOT NULL,


    tipo VARCHAR(30), -- mensaje, compra, venta, etc.
    mensaje TEXT,


    leido BOOLEAN DEFAULT FALSE,


    entidad_id INT, -- opcional (id de compra, publicación)
   
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Pone por default el horario que esta puesto


    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE -- Borra clase padre e hijo


);


CREATE TABLE tipos_combustible (


    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) UNIQUE NOT NULL


);


CREATE TABLE marcas (


    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) UNIQUE NOT NULL


);


CREATE TABLE modelos (


    id INT AUTO_INCREMENT PRIMARY KEY,


    marca_id INT NOT NULL,
    nombre VARCHAR(50) NOT NULL,


    UNIQUE (marca_id, nombre),


    FOREIGN KEY (marca_id) REFERENCES marcas(id) ON DELETE CASCADE


);


CREATE TABLE transmisiones (


    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) UNIQUE NOT NULL


);


CREATE TABLE autos (


    id INT AUTO_INCREMENT PRIMARY KEY,


    modelo_id INT NOT NULL,


    versionn VARCHAR(50),
    anio INT,
    kms INT,


    vtv BOOLEAN DEFAULT FALSE,
    grabado BOOLEAN DEFAULT FALSE,


    combustible_id INT,
    transmision_id INT,


    color VARCHAR(30),
    puertas INT,
    descripcion TEXT,


    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,


    FOREIGN KEY (modelo_id) REFERENCES modelos(id),
    FOREIGN KEY (combustible_id) REFERENCES tipos_combustible(id),
    FOREIGN KEY (transmision_id) REFERENCES transmisiones(id)


);


CREATE TABLE historial_vehiculo (


    id INT AUTO_INCREMENT PRIMARY KEY,


    auto_id INT NOT NULL,


    tipo VARCHAR(50), -- service, reparación.
    descripcion TEXT,


    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,


    FOREIGN KEY (auto_id) REFERENCES autos(id) ON DELETE CASCADE


);


CREATE TABLE informe_dominio (


    id INT AUTO_INCREMENT PRIMARY KEY,


    auto_id INT UNIQUE NOT NULL,


    dominio VARCHAR(10) UNIQUE NOT NULL,
    numero_chasis VARCHAR(50),
    numero_motor VARCHAR(50),


    titular_actual VARCHAR(150),
    cantidad_titulares INT,
    fecha_inscripcion DATE,


    tiene_embargo BOOLEAN DEFAULT FALSE,
    tiene_prenda BOOLEAN DEFAULT FALSE,
    es_robado BOOLEAN DEFAULT FALSE,
    titular_inhibido BOOLEAN DEFAULT FALSE,


    deuda_patentes DECIMAL(12,2),
    deuda_multas DECIMAL(12,2),


    observaciones TEXT,


    fecha_emision TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_vencimiento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,


    FOREIGN KEY (auto_id) REFERENCES autos(id) ON DELETE CASCADE


);


CREATE TABLE publicaciones (


    id INT AUTO_INCREMENT PRIMARY KEY,


    usuario_id INT NOT NULL,
    auto_id INT NOT NULL,


    precio DECIMAL(12,2) NOT NULL,
    moneda VARCHAR(10) DEFAULT 'USD',


    estado ENUM('ACTIVA','PAUSADA','VENDIDA','CANCELADA') DEFAULT 'ACTIVA',

    score DECIMAL(10,2) DEFAULT 0,
    destacado BOOLEAN DEFAULT FALSE,
    visitas INT DEFAULT 0,
    descripcion text,


    fecha_publicacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_expiracion DATETIME DEFAULT NULL,


    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,


    FOREIGN KEY (auto_id) REFERENCES autos(id) ON DELETE CASCADE


);


CREATE TABLE preguntas (

    id INT AUTO_INCREMENT PRIMARY KEY,
    publicacion_id INT,
    usuario_id INT,
    pregunta TEXT,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (publicacion_id) REFERENCES publicaciones(id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)

);


CREATE TABLE respuestas (

    id INT AUTO_INCREMENT PRIMARY KEY,
    pregunta_id INT UNIQUE,
    vendedor_id INT,
    respuesta TEXT,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (pregunta_id) REFERENCES preguntas(id),
    FOREIGN KEY (vendedor_id) REFERENCES usuarios(id)

);


CREATE TABLE multimedia (

    id INT AUTO_INCREMENT PRIMARY KEY,

    publicacion_id INT NOT NULL,

    url TEXT NOT NULL,
    tipo VARCHAR(10) NOT NULL CHECK (tipo IN ('IMAGEN', 'VIDEO')),

    orden INT DEFAULT 0,

    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (publicacion_id) REFERENCES publicaciones(id)ON DELETE CASCADE

);


CREATE TABLE favoritos (

    usuario_id INT NOT NULL,
    publicacion_id INT NOT NULL,

    PRIMARY KEY (usuario_id, publicacion_id),

    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,

    FOREIGN KEY (publicacion_id) REFERENCES publicaciones(id) ON DELETE CASCADE

);


CREATE TABLE chats (

    id INT AUTO_INCREMENT PRIMARY KEY,

    usuario1_id INT NOT NULL,
    usuario2_id INT NOT NULL,

    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    chk_orden_usuarios CHECK (usuario1_id < usuario2_id),

    UNIQUE (usuario1_id, usuario2_id),

    FOREIGN KEY (usuario1_id) REFERENCES usuarios(id) ON DELETE CASCADE,

    FOREIGN KEY (usuario2_id) REFERENCES usuarios(id) ON DELETE CASCADE

);


CREATE TABLE mensajes (

    id INT AUTO_INCREMENT PRIMARY KEY,

    chat_id INT NOT NULL,
    emisor_id INT NOT NULL,

    contenido TEXT NOT NULL,

    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    leido BOOLEAN DEFAULT FALSE,

    FOREIGN KEY (chat_id) REFERENCES chats(id) ON DELETE CASCADE,

    FOREIGN KEY (emisor_id) REFERENCES usuarios(id) ON DELETE CASCADE

);


CREATE TABLE compras (

    id INT AUTO_INCREMENT PRIMARY KEY,

    comprador_id INT NOT NULL,
    publicacion_id INT NOT NULL,

    precio_final DECIMAL(12,2) NOT NULL,

    estado ENUM('PENDIENTE','PAGADO','CANCELADO','COMPLETADO') DEFAULT 'PENDIENTE',
    n_transaccion int, 

    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (comprador_id) REFERENCES usuarios(id),

    FOREIGN KEY (publicacion_id) REFERENCES publicaciones(id)

);

CREATE TABLE tipos_pago (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) UNIQUE NOT NULL
);


CREATE TABLE pagos (

    id INT AUTO_INCREMENT PRIMARY KEY,

    compra_id INT NOT NULL,
    tipo_pago_id INT,  

    estado ENUM('PENDIENTE','APROBADO','RECHAZADO') DEFAULT 'PENDIENTE',
    n_transaccion INT, 

    id_externo VARCHAR(100),

    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (compra_id) REFERENCES compras(id),
    FOREIGN KEY (tipo_pago_id) REFERENCES tipos_pago(id);
   
);


CREATE TABLE resenias (

    id INT AUTO_INCREMENT PRIMARY KEY,

    comprador_id INT NOT NULL,
    vendedor_id INT NOT NULL,

    puntuacion DECIMAL(2,1) CHECK (puntuacion BETWEEN 1 AND 5),

    comentario TEXT,

    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    UNIQUE (comprador_id, vendedor_id),

    FOREIGN KEY (comprador_id) REFERENCES usuarios(id),

    FOREIGN KEY (vendedor_id) REFERENCES usuarios(id)

);


CREATE TABLE carrito(

    usuario_id INT,
    publicacion_id INT,
    cantidad INT DEFAULT 1,
    PRIMARY KEY (usuario_id, publicacion_id),

    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (publicacion_id) REFERENCES publicaciones(id);

);


CREATE TABLE vistas_publicacion (

    usuario_id INT,
    publicacion_id INT,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (usuario_id, publicacion_id, fecha),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    FOREIGN KEY (publicacion_id) REFERENCES publicaciones(id) ON DELETE CASCADE

);


CREATE TABLE historial_precios (

    id INT AUTO_INCREMENT PRIMARY KEY,
    publicacion_id INT,
    precio DECIMAL(12,2),
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (publicacion_id) REFERENCES publicaciones(id)

);


CREATE TABLE verificaciones (

    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT,
    tipo VARCHAR(50), -- DNI, selfie, etc
    estado ENUM('PENDIENTE','APROBADO','RECHAZADO'),
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)

);


CREATE TABLE reportes (

    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT,
    publicacion_id INT,
    motivo VARCHAR(100),
    descripcion TEXT,
    estado ENUM('PENDIENTE','REVISADO','RESUELTO'),
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (publicacion_id) REFERENCES publicaciones(id)

);


CREATE TABLE comisiones (

    id INT AUTO_INCREMENT PRIMARY KEY,
    compra_id INT,
    porcentaje DECIMAL(5,2),
    monto DECIMAL(12,2),
    FOREIGN KEY (compra_id) REFERENCES compras(id)

);


CREATE TABLE ofertas (

    id INT AUTO_INCREMENT PRIMARY KEY,
    publicacion_id INT NOT NULL,
    comprador_id INT NOT NULL,

    monto DECIMAL(12,2) NOT NULL,
    estado ENUM('PENDIENTE','ACEPTADA','RECHAZADA','EXPIRADA') DEFAULT 'PENDIENTE',
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (publicacion_id) REFERENCES publicaciones(id),
    FOREIGN KEY (comprador_id) REFERENCES usuarios(id)

);


CREATE TABLE dispositivos_usuario (

    id INT AUTO_INCREMENT PRIMARY KEY,

    usuario_id INT,
    token_push TEXT,
    plataforma VARCHAR(20), -- android, ios, web

    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)

);


CREATE TABLE actividad_sospechosa (

    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT,
    tipo VARCHAR(50), -- spam, fraude, etc
    descripcion TEXT,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)

);


CREATE TABLE facturas (

    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT,
    compra_id INT,

    total DECIMAL(12,2),
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (compra_id) REFERENCES compras(id)

);


CREATE TABLE metodos_pago (

    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT,
    datos_tokenizados TEXT,
    activo BOOLEAN DEFAULT TRUE,
    tipo_pago_id INT,

    FOREIGN KEY (tipo_pago_id) REFERENCES tipos_pago(id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)

);


CREATE TABLE bloqueos (

    usuario_id INT,

    usuario_bloqueado_id INT,

    PRIMARY KEY (usuario_id, usuario_bloqueado_id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (usuario_bloqueado_id) REFERENCES usuarios(id)

);


CREATE TABLE historial_publicaciones (

    id INT AUTO_INCREMENT PRIMARY KEY,
    publicacion_id INT,
    estado VARCHAR(20),
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (publicacion_id) REFERENCES publicaciones(id)

);


CREATE TABLE moderacion_multimedia (

    id INT AUTO_INCREMENT PRIMARY KEY,
    multimedia_id INT,
    estado ENUM('PENDIENTE','APROBADO','RECHAZADO'),
    motivo TEXT,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (multimedia_id) REFERENCES multimedia(id)

);


CREATE TABLE busquedas_guardadas (

    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT,
    query TEXT,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)

);


CREATE TABLE inventario (

    publicacion_id INT PRIMARY KEY,

    stock INT DEFAULT 1,

    FOREIGN KEY (publicacion_id) REFERENCES publicaciones(id)

);


CREATE TABLE variantes (

    id INT AUTO_INCREMENT PRIMARY KEY,

    publicacion_id INT,
    nombre VARCHAR(50), -- color, modelo, etc

    FOREIGN KEY (publicacion_id) REFERENCES publicaciones(id)

);


CREATE TABLE variante_opciones (

    id INT AUTO_INCREMENT PRIMARY KEY,

    variante_id INT,
    valor VARCHAR(50),

    FOREIGN KEY (variante_id) REFERENCES variantes(id)

);


CREATE TABLE estadisticas_publicacion (


    publicacion_id INT PRIMARY KEY,
    vistas INT DEFAULT 0,
    clicks INT DEFAULT 0,
    favoritos INT DEFAULT 0,
    contactos INT DEFAULT 0,
    compartidos INT DEFAULT 0,

    FOREIGN KEY (publicacion_id) REFERENCES publicaciones(id)

);


CREATE TABLE tipos_promocion (

    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50), -- destacado, premium, urgente
    prioridad INT, -- para ordenar resultados
    duracion_dias INT,
    precio DECIMAL(10,2)

);


CREATE TABLE promociones (

    id INT AUTO_INCREMENT PRIMARY KEY,

    publicacion_id INT NOT NULL,
    tipo_promocion_id INT NOT NULL,

    fecha_inicio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_fin TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    estado ENUM('ACTIVA','FINALIZADA','CANCELADA') DEFAULT 'ACTIVA',

    FOREIGN KEY (publicacion_id) REFERENCES publicaciones(id),
    FOREIGN KEY (tipo_promocion_id) REFERENCES tipos_promocion(id)

);


CREATE TABLE pagos_promocion (

    id INT AUTO_INCREMENT PRIMARY KEY,
    promocion_id INT,
    monto DECIMAL(10,2),
    estado ENUM('PENDIENTE','APROBADO','RECHAZADO'),
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (promocion_id) REFERENCES promociones(id)

);


CREATE TABLE tarjetas_usuario (

    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT,
    marca VARCHAR(20), -- visa, mastercard
    ultimos4 VARCHAR(4),
    token TEXT, -- lo da la pasarela de pago
    vencimiento_mes INT,
    vencimiento_anio INT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)

);


CREATE TABLE planes_cuotas (

    id INT AUTO_INCREMENT PRIMARY KEY,
    cantidad_cuotas INT, -- 3, 6, 12
    interes DECIMAL(5,2), -- % de interés
    descripcion VARCHAR(100)

);


CREATE TABLE pagos_cuotas (

    id INT AUTO_INCREMENT PRIMARY KEY,

    pago_id INT NOT NULL,
    plan_id INT NOT NULL,

    cantidad_cuotas INT NOT NULL,
    interes_aplicado DECIMAL(5,2) DEFAULT 0,

    monto_original DECIMAL(12,2) NOT NULL, -- precio sin interés
    monto_total DECIMAL(12,2) NOT NULL, -- con interés
    monto_cuota DECIMAL(12,2) NOT NULL,

    moneda VARCHAR(10) DEFAULT 'ARS',

    fecha_inicio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (pago_id) REFERENCES pagos(id),
    FOREIGN KEY (plan_id) REFERENCES planes_cuotas(id)
    
);

CREATE TABLE cuotas (

    id INT AUTO_INCREMENT PRIMARY KEY,
    pago_cuota_id INT,
    numero_cuota INT,
    monto DECIMAL(12,2),
    estado ENUM('PENDIENTE','PAGADA','ATRASADA'),
    fecha_vencimiento DATE,
    fecha_pago DATE,

    FOREIGN KEY (pago_cuota_id) REFERENCES pagos_cuotas(id)
);


CREATE TABLE reembolsos (

    id INT AUTO_INCREMENT PRIMARY KEY,
    pago_id INT,
    monto DECIMAL(12,2),
    motivo TEXT,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (pago_id) REFERENCES pagos(id)

);

CREATE TABLE banco(

    ID INT AUTO_INCREMENT PRIMARY KEY, 
    NOMBRE VARCHAR(100) UNIQUE NOT NULL, 
    CODIGO VARCHAR(20),
    ACTIVO BOOLEAN DEFAULT TRUE, 
    FECHA_CREACION TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);

CREATE TABLE promociones_bancarias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_banco INT,
    tarjeta VARCHAR(20),
    cuotas INT,
    interes DECIMAL(5,2),
    activa BOOLEAN DEFAULT TRUE,

    FOREIGN KEY (id_banco) REFERENCES banco(id)
);

CREATE TABLE billeteras (
    usuario_id INT PRIMARY KEY,
    saldo DECIMAL(12,2) DEFAULT 0,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

CREATE TABLE movimientos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT,
    tipo ENUM('INGRESO','EGRESO'),
    monto DECIMAL(12,2),
    descripcion TEXT,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

CREATE TABLE visitas_auto (
    id INT AUTO_INCREMENT PRIMARY KEY,

    auto_id INT,
    comprador_id INT,

    fecha DATETIME,
    resultado ENUM('INTERESADO','NO_INTERESADO'),

    FOREIGN KEY (auto_id) REFERENCES autos(id),
    FOREIGN KEY (comprador_id) REFERENCES usuarios(id)
);

CREATE TABLE encuentros (
    id INT AUTO_INCREMENT PRIMARY KEY,

    compra_id INT,
    vendedor_id INT,
    comprador_id INT,

    fecha_programada DATETIME,
    lugar VARCHAR(255),

    estado ENUM('PENDIENTE','CONFIRMADO','REALIZADO','CANCELADO'),

    notas TEXT,

    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (compra_id) REFERENCES compras(id),
    FOREIGN KEY (vendedor_id) REFERENCES usuarios(id),
    FOREIGN KEY (comprador_id) REFERENCES usuarios(id)
);

CREATE INDEX idx_publicaciones_usuario ON publicaciones(usuario_id);
CREATE INDEX idx_publicaciones_estado ON publicaciones(estado);

CREATE INDEX idx_mensajes_chat ON mensajes(chat_id);

CREATE INDEX idx_pagos_compra ON pagos(compra_id);

CREATE INDEX idx_compras_usuario ON compras(comprador_id);

CREATE INDEX idx_promociones_banco ON promociones_bancarias(id_banco);

CREATE VIEW vista_compradores AS
SELECT 
    u.id,
    u.nombre,
    COUNT(c.id) AS total_compras
FROM usuarios u
JOIN compras c ON u.id = c.comprador_id
GROUP BY u.id;

CREATE VIEW vista_vendedores AS
SELECT 
    u.id,
    u.nombre,
    COUNT(p.id) AS total_ventas
FROM usuarios u
JOIN publicaciones p ON u.id = p.usuario_id
GROUP BY u.id;

CREATE VIEW vista_publicaciones_detalle AS
SELECT 
    p.id,
    p.precio,
    p.estado,
    u.nombre AS vendedor,
    a.anio,
    m.nombre AS marca
FROM publicaciones p
JOIN usuarios u ON p.usuario_id = u.id
JOIN autos a ON p.auto_id = a.id
JOIN modelos mo ON a.modelo_id = mo.id
JOIN marcas m ON mo.marca_id = m.id;

CREATE VIEW vista_top_publicaciones AS
SELECT 
    p.id,
    p.visitas,
    p.score
FROM publicaciones p
ORDER BY p.score DESC;

CREATE VIEW vista_vendedores_activos AS
SELECT 
    u.id,
    u.nombre,
    u.total_ventas,
    u.reputacion
FROM usuarios u
WHERE u.total_ventas > 0;

CREATE VIEW vista_historial_compras AS
SELECT 
    c.id,
    u.nombre AS comprador,
    p.id AS publicacion,
    c.precio_final,
    c.estado
FROM compras c
JOIN usuarios u ON c.comprador_id = u.id
JOIN publicaciones p ON c.publicacion_id = p.id;


DELIMITER $$

CREATE TRIGGER trg_compra_completada
AFTER UPDATE ON compras
FOR EACH ROW
BEGIN
    IF NEW.estado = 'COMPLETADO' THEN
        UPDATE publicaciones
        SET estado = 'VENDIDA'
        WHERE id = NEW.publicacion_id;
    END IF;
END$$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER trg_pago_aprobado
AFTER UPDATE ON pagos
FOR EACH ROW
BEGIN
    IF NEW.estado = 'APROBADO' THEN
        INSERT INTO movimientos (usuario_id, tipo, monto, descripcion)
        SELECT c.comprador_id, 'EGRESO', c.precio_final, 'Compra realizada'
        FROM compras c
        WHERE c.id = NEW.compra_id;
    END IF;
END$$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER trg_nueva_resenia
AFTER INSERT ON resenias
FOR EACH ROW
BEGIN
    UPDATE usuarios
    SET reputacion = (
        SELECT AVG(puntuacion)
        FROM resenias
        WHERE vendedor_id = NEW.vendedor_id
    )
    WHERE id = NEW.vendedor_id;
END$$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER trg_total_ventas
AFTER INSERT ON compras
FOR EACH ROW
BEGIN
    UPDATE usuarios
    SET total_ventas = total_ventas + 1
    WHERE id = (
        SELECT usuario_id FROM publicaciones WHERE id = NEW.publicacion_id
    );
END$$

DELIMITER ;


START TRANSACTION;

INSERT INTO compras (...);

INSERT INTO pagos (...);

UPDATE publicaciones SET estado = 'VENDIDA' WHERE id = ?;

COMMIT;

DELIMITER $$

CREATE FUNCTION fn_reputacion_usuario(uid INT)
RETURNS DECIMAL(2,1)
DETERMINISTIC
BEGIN
    DECLARE promedio DECIMAL(2,1);

    SELECT AVG(puntuacion)
    INTO promedio
    FROM resenias
    WHERE vendedor_id = uid;

    RETURN IFNULL(promedio, 0);
END$$

DELIMITER ;

SELECT fn_reputacion_usuario(5);

CREATE EVENT evt_expirar_publicaciones
ON SCHEDULE EVERY 1 DAY
DO
UPDATE publicaciones
SET estado = 'CANCELADA'
WHERE fecha_expiracion < NOW()
AND estado = 'ACTIVA';

DELIMITER $$

CREATE PROCEDURE sp_crear_chat(
    IN u1 INT,
    IN u2 INT
)
BEGIN
    DECLARE user1 INT;
    DECLARE user2 INT;

    SET user1 = LEAST(u1, u2);
    SET user2 = GREATEST(u1, u2);

    IF NOT EXISTS (
        SELECT 1 FROM chats 
        WHERE usuario1_id = user1 AND usuario2_id = user2
    ) THEN
        INSERT INTO chats (usuario1_id, usuario2_id)
        VALUES (user1, user2);
    END IF;
END$$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE sp_crear_compra(
    IN p_publicacion_id INT,
    IN p_comprador_id INT
)
BEGIN
    DECLARE estado_pub VARCHAR(20);

    SELECT estado INTO estado_pub
    FROM publicaciones
    WHERE id = p_publicacion_id;

    IF estado_pub = 'ACTIVA' THEN

        INSERT INTO compras (comprador_id, publicacion_id, precio_final)
        SELECT p_comprador_id, id, precio
        FROM publicaciones
        WHERE id = p_publicacion_id;

    ELSE
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La publicación no está disponible';
    END IF;
END$$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE sp_procesar_pago(
    IN p_compra_id INT,
    IN p_tipo_pago_id INT
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
    END;

    START TRANSACTION;

    INSERT INTO pagos (compra_id, tipo_pago_id, estado)
    VALUES (p_compra_id, p_tipo_pago_id, 'APROBADO');

    UPDATE compras
    SET estado = 'COMPLETADO'
    WHERE id = p_compra_id;

    UPDATE publicaciones
    SET estado = 'VENDIDA'
    WHERE id = (
        SELECT publicacion_id FROM compras WHERE id = p_compra_id
    );

    COMMIT;
END$$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE sp_hacer_oferta(
    IN p_publicacion_id INT,
    IN p_comprador_id INT,
    IN p_monto DECIMAL(12,2)
)
BEGIN
    INSERT INTO ofertas (publicacion_id, comprador_id, monto)
    VALUES (p_publicacion_id, p_comprador_id, p_monto);
END$$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE sp_aceptar_oferta(
    IN p_oferta_id INT
)
BEGIN
    DECLARE pub_id INT;
    DECLARE comp_id INT;
    DECLARE monto DECIMAL(12,2);

    SELECT publicacion_id, comprador_id, monto
    INTO pub_id, comp_id, monto
    FROM ofertas
    WHERE id = p_oferta_id;

    UPDATE ofertas
    SET estado = 'ACEPTADA'
    WHERE id = p_oferta_id;

    INSERT INTO compras (comprador_id, publicacion_id, precio_final)
    VALUES (comp_id, pub_id, monto);

    UPDATE publicaciones
    SET estado = 'PAUSADA'
    WHERE id = pub_id;
END$$

DELIMITER ;

CALL sp_crear_chat(2, 5);

CALL sp_crear_compra(10, 3);

CALL sp_procesar_pago(1, 2);

CALL sp_hacer_oferta(10, 3, 5000);

CALL sp_aceptar_oferta(1);

DELIMITER $$

CREATE PROCEDURE sp_comprar_auto_completo(
    IN p_publicacion_id INT,
    IN p_comprador_id INT,
    IN p_tipo_pago_id INT
)
BEGIN
    DECLARE v_estado VARCHAR(20);
    DECLARE v_vendedor_id INT;
    DECLARE v_precio DECIMAL(12,2);
    DECLARE v_compra_id INT;
    DECLARE v_user1 INT;
    DECLARE v_user2 INT;

    -- manejo de errores
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
    END;

    START TRANSACTION;

    SELECT estado, usuario_id, precio
    INTO v_estado, v_vendedor_id, v_precio
    FROM publicaciones
    WHERE id = p_publicacion_id
    FOR UPDATE;

    IF v_estado <> 'ACTIVA' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La publicación no está disponible';
    END IF;

    SET v_user1 = LEAST(p_comprador_id, v_vendedor_id);
    SET v_user2 = GREATEST(p_comprador_id, v_vendedor_id);

    IF NOT EXISTS (
        SELECT 1 FROM chats 
        WHERE usuario1_id = v_user1 AND usuario2_id = v_user2
    ) THEN
        INSERT INTO chats (usuario1_id, usuario2_id)
        VALUES (v_user1, v_user2);
    END IF;

    INSERT INTO compras (comprador_id, publicacion_id, precio_final, estado)
    VALUES (p_comprador_id, p_publicacion_id, v_precio, 'PENDIENTE');

    SET v_compra_id = LAST_INSERT_ID();

    INSERT INTO pagos (compra_id, tipo_pago_id, estado)
    VALUES (v_compra_id, p_tipo_pago_id, 'APROBADO');

    UPDATE compras
    SET estado = 'COMPLETADO'
    WHERE id = v_compra_id;

    UPDATE publicaciones
    SET estado = 'VENDIDA'
    WHERE id = p_publicacion_id;

    UPDATE usuarios
    SET total_compras = total_compras + 1
    WHERE id = p_comprador_id;

    UPDATE usuarios
    SET total_ventas = total_ventas + 1
    WHERE id = v_vendedor_id;

    COMMIT;

END$$

DELIMITER ;

CALL sp_comprar_auto_completo(10, 3, 1);

DELIMITER $$

CREATE TRIGGER trg_log_venta
AFTER UPDATE ON publicaciones
FOR EACH ROW
BEGIN
    IF NEW.estado = 'VENDIDA' AND OLD.estado <> 'VENDIDA' THEN
        INSERT INTO logs_transacciones (tipo, descripcion, referencia_id)
        VALUES ('VENTA', 'Publicación vendida', NEW.id);
    END IF;
END$$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER trg_log_pago
AFTER INSERT ON pagos
FOR EACH ROW
BEGIN
    INSERT INTO logs_transacciones (tipo, descripcion, referencia_id)
    VALUES ('PAGO', 'Pago registrado', NEW.id);
END$$

DELIMITER ;

DECLARE v_riesgo DECIMAL(5,2);

SELECT score_riesgo INTO v_riesgo
FROM usuarios
WHERE id = p_comprador_id;

IF v_riesgo > 80 THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Usuario bloqueado por riesgo alto';
END IF;

DELIMITER $$

CREATE TRIGGER trg_riesgo_fraude
AFTER INSERT ON actividad_sospechosa
FOR EACH ROW
BEGIN
    UPDATE usuarios
    SET score_riesgo = score_riesgo + 10
    WHERE id = NEW.usuario_id;
END$$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE sp_generar_cuotas(
    IN p_pago_id INT,
    IN p_plan_id INT
)
BEGIN
    DECLARE v_cuotas INT;
    DECLARE v_interes DECIMAL(5,2);
    DECLARE v_monto DECIMAL(12,2);
    DECLARE v_total DECIMAL(12,2);
    DECLARE v_monto_cuota DECIMAL(12,2);
    DECLARE i INT DEFAULT 1;

    SELECT cantidad_cuotas, interes
    INTO v_cuotas, v_interes
    FROM planes_cuotas
    WHERE id = p_plan_id;

    SELECT c.precio_final
    INTO v_monto
    FROM compras c
    JOIN pagos p ON p.compra_id = c.id
    WHERE p.id = p_pago_id;

    SET v_total = v_monto + (v_monto * v_interes / 100);
    SET v_monto_cuota = v_total / v_cuotas;

    INSERT INTO pagos_cuotas (
        pago_id, plan_id, cantidad_cuotas,
        interes_aplicado, monto_original,
        monto_total, monto_cuota
    )
    VALUES (
        p_pago_id, p_plan_id, v_cuotas,
        v_interes, v_monto,
        v_total, v_monto_cuota
    );

    WHILE i <= v_cuotas DO
        INSERT INTO cuotas (
            pago_cuota_id,
            numero_cuota,
            monto,
            estado,
            fecha_vencimiento
        )
        VALUES (
            LAST_INSERT_ID(),
            i,
            v_monto_cuota,
            'PENDIENTE',
            DATE_ADD(CURDATE(), INTERVAL i MONTH)
        );

        SET i = i + 1;
    END WHILE;

END$$

DELIMITER ;

CALL sp_generar_cuotas(v_compra_id, 1);