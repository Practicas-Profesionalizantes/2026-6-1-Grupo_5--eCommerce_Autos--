/**
 * Contrato de datos de las vistas de Configuración.
 *
 * Igual que en Wishlist y Perfil, los tipos se definen con JSDoc. Este es el
 * contrato que el backend debe respetar en sus respuestas (ver
 * services/configuracionService.api.js para las rutas).
 */

/**
 * Datos personales (vista "Editar datos personales").
 * Nombre, apellido, teléfono principal y foto se comparten con la vista Perfil.
 * @typedef {Object} DatosPersonales
 * @property {number|string} id
 * @property {string}   nombre
 * @property {string}   apellido
 * @property {string}   genero             Uno de GENEROS, o '' si no lo indicó
 * @property {string}   correo
 * @property {string[]} telefonos          El primero es el teléfono principal
 * @property {string}   fechaNacimiento    Fecha ISO "YYYY-MM-DD", o ''
 * @property {string}   direccion
 * @property {string[]} otrasDirecciones
 * @property {string|null} fotoUrl         null si no tiene foto de perfil
 */

/**
 * @typedef {'sistema'|'oscuro'|'claro'} TemaPreferido
 * @typedef {Object} Apariencia
 * @property {TemaPreferido} tema
 * @property {number} escalaFuente   Porcentaje del tamaño base (ver ESCALA_FUENTE)
 * @property {string} fuente         id de FUENTES
 */

/**
 * @typedef {Object} Accesibilidad
 * @property {boolean} atajosTeclado                   Atajos de una sola tecla ("claves de caracteres")
 * @property {'sistema'|'activado'|'desactivado'} animaciones
 * @property {'mostrar'|'ocultar'} subrayadoEnlaces
 */

/**
 * Un tipo de aviso y los canales por los que el usuario lo quiere recibir.
 * @typedef {Object} PreferenciaAviso
 * @property {boolean} app
 * @property {boolean} correo
 */

/**
 * @typedef {Object} Notificaciones
 * @property {{ app: boolean, correo: boolean }} canales    Interruptores generales por canal
 * @property {Object<string, PreferenciaAviso>} avisos      Una entrada por cada id de TIPOS_AVISO
 * @property {'inmediato'|'diario'|'semanal'} frecuenciaCorreo
 * @property {{ activo: boolean, desde: string, hasta: string }} noMolestar   Horas "HH:MM"
 */

/**
 * @typedef {Object} Idioma
 * @property {string}   preferido                Código de IDIOMAS_DISPONIBLES (ej: "es-AR")
 * @property {string[]} otros                    Otros códigos de idioma
 * @property {boolean}  agregarAutomaticamente
 */

/**
 * Respuesta de GET /configuracion.
 * @typedef {Object} ConfiguracionResponse
 * @property {DatosPersonales} datosPersonales
 * @property {Apariencia} apariencia
 * @property {Accesibilidad} accesibilidad
 * @property {Notificaciones} notificaciones
 * @property {Idioma} idioma
 */

export const GENEROS = ['Femenino', 'Masculino', 'No binario', 'Prefiero no decirlo'];

export const TEMAS = [
  { id: 'sistema', nombre: 'Sistema (Predeterminado)' },
  { id: 'oscuro', nombre: 'Oscuro' },
  { id: 'claro', nombre: 'Claro' },
];

/** Tamaño de fuente como porcentaje del tamaño base del navegador. */
export const ESCALA_FUENTE = { min: 85, max: 130, paso: 5, defecto: 100 };

/** "predeterminada" respeta la fuente original de la app. El resto cae a una genérica si no está instalada. */
export const FUENTES = [
  { id: 'predeterminada', nombre: 'Predeterminada', stack: "'Segoe UI', Tahoma, Geneva, Verdana, sans-serif" },
  { id: 'calibri', nombre: 'Calibri', stack: "Calibri, Carlito, 'Segoe UI', sans-serif" },
  { id: 'arial', nombre: 'Arial', stack: 'Arial, Helvetica, sans-serif' },
  { id: 'verdana', nombre: 'Verdana', stack: 'Verdana, Geneva, sans-serif' },
  { id: 'trebuchet', nombre: 'Trebuchet MS', stack: "'Trebuchet MS', Tahoma, sans-serif" },
  { id: 'georgia', nombre: 'Georgia', stack: "Georgia, 'Times New Roman', serif" },
];

export const ANIMACIONES = [
  { id: 'sistema', nombre: 'Sincronizar con el sistema' },
  { id: 'activado', nombre: 'Activado' },
  { id: 'desactivado', nombre: 'Desactivado' },
];

export const SUBRAYADO_ENLACES = [
  { id: 'ocultar', nombre: 'Ocultar subrayados de enlaces' },
  { id: 'mostrar', nombre: 'Mostrar subrayados de enlaces' },
];

/** Filas de la tabla "Qué querés que te avisemos". El id es la clave dentro de Notificaciones.avisos. */
export const TIPOS_AVISO = [
  { id: 'mensajes', nombre: 'Mensajes nuevos en mis chats' },
  { id: 'preguntas', nombre: 'Preguntas en mis publicaciones' },
  { id: 'bajaPrecioWishlist', nombre: 'Baja de precio en mi Wishlist' },
  { id: 'publicacionNoDisponible', nombre: 'Publicación guardada vendida o pausada' },
  { id: 'estadoPublicaciones', nombre: 'Estado de mis publicaciones (aprobada / reportada)' },
  { id: 'novedades', nombre: 'Novedades y promociones' },
  { id: 'seguridadCuenta', nombre: 'Seguridad de la cuenta (inicio de sesión nuevo)' },
];

export const FRECUENCIAS_CORREO = [
  { id: 'inmediato', nombre: 'Inmediato' },
  { id: 'diario', nombre: 'Resumen diario' },
  { id: 'semanal', nombre: 'Resumen semanal' },
];

export const IDIOMAS_DISPONIBLES = [
  { codigo: 'es-AR', idioma: 'Español', region: 'Argentina' },
  { codigo: 'es-ES', idioma: 'Español', region: 'España' },
  { codigo: 'es-MX', idioma: 'Español', region: 'México' },
  { codigo: 'en-US', idioma: 'English', region: 'United States' },
  { codigo: 'en-GB', idioma: 'English', region: 'United Kingdom' },
  { codigo: 'pt-BR', idioma: 'Português', region: 'Brasil' },
  { codigo: 'fr-FR', idioma: 'Français', region: 'France' },
  { codigo: 'it-IT', idioma: 'Italiano', region: 'Italia' },
  { codigo: 'de-DE', idioma: 'Deutsch', region: 'Deutschland' },
];

/** Largo mínimo de la contraseña nueva. Valor sugerido: a confirmar con el equipo de backend. */
export const CONTRASENA_MIN_LARGO = 8;
