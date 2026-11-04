/**
 * Servicio de Configuración — IMPLEMENTACIÓN REAL (backend).
 *
 * El usuario logueado lo identifica el backend (token o sesión), por eso ninguna ruta lleva su id.
 * Contrato esperado (formas en src/types/configuracion.js):
 *
 *   GET /configuracion                    → ConfiguracionResponse
 *   PUT /configuracion/datos-personales   → { ok, datosPersonales }   JSON: nombre, apellido, genero, correo,
 *                                           telefonos[], fechaNacimiento, direccion, otrasDirecciones[]
 *   PUT /configuracion/contrasena         → { ok }                    JSON: { actual, nueva }
 *   PUT /configuracion/apariencia         → { ok, apariencia }
 *   PUT /configuracion/accesibilidad      → { ok, accesibilidad }
 *   PUT /configuracion/notificaciones     → { ok, notificaciones }
 *   PUT /configuracion/idioma             → { ok, idioma }
 *
 * La foto de perfil se sigue gestionando con perfilService (POST/DELETE /perfil/foto).
 * Si el backend usa otros nombres de campo o rutas, se ajusta SOLO en este archivo.
 */
import { request } from './api';

/** @returns {Promise<import('../types/configuracion').ConfiguracionResponse>} */
export const getConfiguracion = () => request('/configuracion');

/** @param {Omit<import('../types/configuracion').DatosPersonales, 'id'|'fotoUrl'>} datos */
export const actualizarDatosPersonales = (datos) =>
  request('/configuracion/datos-personales', { method: 'PUT', json: datos });

/** @param {{ actual: string, nueva: string }} datos */
export const cambiarContrasena = ({ actual, nueva }) =>
  request('/configuracion/contrasena', { method: 'PUT', json: { actual, nueva } });

/** @param {import('../types/configuracion').Apariencia} apariencia */
export const guardarApariencia = (apariencia) =>
  request('/configuracion/apariencia', { method: 'PUT', json: apariencia });

/** @param {import('../types/configuracion').Accesibilidad} accesibilidad */
export const guardarAccesibilidad = (accesibilidad) =>
  request('/configuracion/accesibilidad', { method: 'PUT', json: accesibilidad });

/** @param {import('../types/configuracion').Notificaciones} notificaciones */
export const guardarNotificaciones = (notificaciones) =>
  request('/configuracion/notificaciones', { method: 'PUT', json: notificaciones });

/** @param {import('../types/configuracion').Idioma} idioma */
export const guardarIdioma = (idioma) =>
  request('/configuracion/idioma', { method: 'PUT', json: idioma });
