/**
 * Servicio del panel de administración — IMPLEMENTACIÓN REAL (backend).
 *
 * Todas las rutas son solo para administradores: el backend debe responder 401 (sin sesión)
 * o 403 (sin rol de administrador) y el panel muestra "Acceso restringido".
 * Formas en src/types/admin.js:
 *
 *   GET  /admin/sesion                          → { admin }
 *   GET  /admin/estadisticas?meses=6            → EstadisticasResponse
 *   GET  /admin/reportes?tipo=&estado=&desde=&hasta=   → { reportes: ReporteRecibido[] }  (más nuevos primero)
 *   POST /admin/reportes/:id/respuestas         → { reporte }   JSON { texto, estado? }: guarda la respuesta,
 *                                                  la hace llegar a quien reportó y, si viene `estado`, lo actualiza
 *   PUT  /admin/reportes/:id/estado             → { reporte }   JSON { estado }
 *   GET  /admin/informes                        → { informes: Informe[] }
 *   POST /admin/informes                        → { informe }   JSON { tipo, desde, hasta, formato }
 *   GET  /admin/informes/:id/archivo            → el archivo (PDF/CSV), con Content-Disposition: filename=...
 *
 * Si el backend usa otros nombres de campo o rutas, se ajusta SOLO en este archivo.
 */
import { request, requestBlob } from './api';

/** @returns {Promise<{ admin: import('../types/admin').Administrador }>} */
export const getSesionAdmin = () => request('/admin/sesion');

/**
 * @param {{ meses?: number }} [opciones]
 * @returns {Promise<import('../types/admin').EstadisticasResponse>}
 */
export const getEstadisticas = ({ meses = 6 } = {}) => request(`/admin/estadisticas?meses=${meses}`);

/**
 * @param {import('../types/admin').FiltrosReportes} filtros
 * @returns {Promise<{ reportes: import('../types/admin').ReporteRecibido[] }>}
 */
export function getReportesRecibidos(filtros) {
  const params = new URLSearchParams();
  Object.entries(filtros).forEach(([clave, valor]) => {
    if (valor) params.set(clave, valor);
  });
  const consulta = params.toString();
  return request(`/admin/reportes${consulta ? `?${consulta}` : ''}`);
}

/**
 * @param {number|string} id
 * @param {{ texto: string, estado?: string }} respuesta
 * @returns {Promise<{ reporte: import('../types/admin').ReporteRecibido }>}
 */
export const responderReporte = (id, respuesta) =>
  request(`/admin/reportes/${id}/respuestas`, { method: 'POST', json: respuesta });

/**
 * @param {number|string} id
 * @param {string} estado
 * @returns {Promise<{ reporte: import('../types/admin').ReporteRecibido }>}
 */
export const cambiarEstadoReporte = (id, estado) =>
  request(`/admin/reportes/${id}/estado`, { method: 'PUT', json: { estado } });

/** @returns {Promise<{ informes: import('../types/admin').Informe[] }>} */
export const getInformes = () => request('/admin/informes');

/**
 * @param {{ tipo: string, desde: string, hasta: string, formato: string }} datos
 * @returns {Promise<{ informe: import('../types/admin').Informe }>}
 */
export const generarInforme = (datos) => request('/admin/informes', { method: 'POST', json: datos });

/**
 * @param {number|string} id
 * @returns {Promise<{ blob: Blob, nombreArchivo: string }>}
 */
export const descargarInforme = (id) => requestBlob(`/admin/informes/${id}/archivo`);
