/**
 * Contrato de datos del panel de administración (Estadísticas y Reportes).
 * Igual que en el resto del proyecto, los tipos se definen con JSDoc.
 *
 * Importante: CarFind no permite la compra directa de vehículos, así que NO hay datos de ventas.
 * Las estadísticas se calculan con lo que la plataforma sí conoce: publicaciones, usuarios,
 * wishlist y conversaciones.
 */

/**
 * Administrador logueado (GET /admin/sesion; el backend responde 401/403 si no es administrador).
 * @typedef {Object} Administrador
 * @property {number|string} id
 * @property {string}        nombre
 * @property {string}        apellido
 * @property {string|null}   fotoUrl
 */

/** @typedef {{ nombre: string, cantidad: number }} ItemCantidad */

/**
 * Respuesta de GET /admin/estadisticas?meses=6
 * @typedef {Object} EstadisticasResponse
 * @property {{ publicaciones: number, usuarios: number, precioPromedio: number, conversaciones: number }} resumen
 *           precioPromedio en ARS; conversaciones = chats iniciados desde una publicación
 * @property {{ titulo: string, cantidad: number }[]} masGuardados      Publicaciones más guardadas en wishlists
 * @property {{ etiquetas: string[], publicaciones: number[], usuarios: number[] }} evolucion
 *           Un valor por mes (mismo largo que `etiquetas`): publicaciones nuevas y usuarios nuevos
 * @property {{ marca: ItemCantidad[], combustible: ItemCantidad[], transmision: ItemCantidad[] }} distribuciones
 *           Cantidad de publicaciones por cada valor de la dimensión
 * @property {ItemCantidad[]} porUbicacion                               Publicaciones por provincia (las más frecuentes)
 */

/**
 * Reporte enviado por un usuario (desde Reportes General, Reportar publicación o Reportar usuario).
 * @typedef {Object} ReporteRecibido
 * @property {number|string} id
 * @property {'general'|'publicacion'|'usuario'} tipo
 * @property {string}   fecha              Fecha y hora ISO
 * @property {'pendiente'|'en_revision'|'resuelto'} estado
 * @property {{ id: number|string, nombre: string, apellido: string }} reportante
 * @property {string}   descripcion        Texto que escribió el usuario (puede ser '')
 * @property {string}   [motivo]           Solo en tipo "publicacion": id de MOTIVOS_REPORTE_PUBLICACION
 * @property {{ id: number|string, titulo: string }} [publicacion]            Solo en tipo "publicacion"
 * @property {{ id: number|string, nombre: string, apellido: string }} [usuarioReportado]   Solo en tipo "usuario"
 * @property {{ id: number|string, texto: string, fecha: string, autor: string }[]} respuestas   Respuestas de la administración
 */

/**
 * Filtros de GET /admin/reportes (todos opcionales; '' = sin filtrar).
 * @typedef {Object} FiltrosReportes
 * @property {string} tipo
 * @property {string} estado
 * @property {string} desde   "YYYY-MM-DD"
 * @property {string} hasta   "YYYY-MM-DD"
 */

/**
 * Informe descargable generado por la administración.
 * @typedef {Object} Informe
 * @property {number|string} id
 * @property {string} nombre
 * @property {'reportes'|'publicaciones'|'usuarios'} tipo
 * @property {'pdf'|'csv'} formato
 * @property {string} desde   "YYYY-MM-DD"
 * @property {string} hasta   "YYYY-MM-DD"
 * @property {string} fecha   Fecha y hora ISO en que se generó
 * @property {number} cantidad  Cantidad de registros incluidos
 */

export const TIPOS_REPORTE_RECIBIDO = [
  { id: 'general', nombre: 'Consulta general' },
  { id: 'publicacion', nombre: 'Publicación' },
  { id: 'usuario', nombre: 'Usuario' },
];

export const ESTADOS_REPORTE = [
  { id: 'pendiente', nombre: 'Pendiente' },
  { id: 'en_revision', nombre: 'En revisión' },
  { id: 'resuelto', nombre: 'Resuelto' },
];

export const TIPOS_INFORME = [
  { id: 'reportes', nombre: 'Reportes recibidos' },
  { id: 'publicaciones', nombre: 'Publicaciones' },
  { id: 'usuarios', nombre: 'Usuarios registrados' },
];

export const FORMATOS_INFORME = [
  { id: 'pdf', nombre: 'PDF' },
  { id: 'csv', nombre: 'CSV (Excel)' },
];

/** Largo máximo de una respuesta del administrador. Valor sugerido: a confirmar con el equipo de backend. */
export const RESPUESTA_MAX_CARACTERES = 1000;

/** Períodos que se pueden ver en el gráfico de evolución. */
export const OPCIONES_MESES_EVOLUCION = [6, 12];

/** Formas de repartir las publicaciones en el gráfico circular. */
export const DIMENSIONES_DISTRIBUCION = [
  { id: 'marca', nombre: 'Marca', titulo: 'Publicaciones por marca' },
  { id: 'combustible', nombre: 'Combustible', titulo: 'Publicaciones por combustible' },
  { id: 'transmision', nombre: 'Transmisión', titulo: 'Publicaciones por transmisión' },
];
