/**
 * Contrato de datos de las vistas de Reportes.
 * Igual que en el resto del proyecto, los tipos se definen con JSDoc.
 */

/**
 * Reporte general hacia la página (dudas, problemas, sugerencias).
 * @typedef {Object} ReporteGeneral
 * @property {string} mensaje
 * @property {number|string} [usuarioReportadoId]  Se envía solo si el reporte nace desde el perfil o el chat de otro usuario
 */

/**
 * Reporte a una publicación de otro usuario.
 * @typedef {Object} ReportePublicacion
 * @property {number|string} publicacionId
 * @property {string} motivo       id de MOTIVOS_REPORTE_PUBLICACION
 * @property {string} descripcion  Obligatoria si el motivo es "otro"; opcional en el resto
 */

/** Motivos de la vista "Reportar publicación". El id es el valor que recibe el backend. */
export const MOTIVOS_REPORTE_PUBLICACION = [
  { id: 'fraude', nombre: 'Sospecho de fraude o estafa' },
  { id: 'informacion_incorrecta', nombre: 'La información no coincide con el auto' },
  { id: 'precio_enganoso', nombre: 'Precio engañoso' },
  { id: 'contenido_inapropiado', nombre: 'Contenido inapropiado' },
  { id: 'duplicada', nombre: 'Publicación duplicada' },
  { id: 'otro', nombre: 'Otro motivo' },
];

/** Máximo del texto en "Reportar publicación" (el boceto muestra 0/500). */
export const REPORTE_PUBLICACION_MAX_CARACTERES = 500;

/** Máximo del texto en el reporte general. Valor sugerido: a confirmar con el equipo de backend. */
export const REPORTE_GENERAL_MAX_CARACTERES = 1000;
