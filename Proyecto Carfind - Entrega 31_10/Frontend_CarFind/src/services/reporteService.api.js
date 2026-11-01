/**
 * Servicio de Reportes — IMPLEMENTACIÓN REAL (backend).
 * El usuario que reporta lo identifica el backend (token o sesión).
 *
 *   POST /reportes                           → { ok }   JSON: { mensaje, usuarioReportadoId? }
 *   POST /publicaciones/:id/reportes         → { ok }   JSON: { motivo, descripcion }
 *
 * Si el backend usa otros nombres de campo o rutas, se ajusta SOLO en este archivo.
 */
import { request } from './api';

/** @param {import('../types/reportes').ReporteGeneral} reporte */
export const enviarReporteGeneral = (reporte) => request('/reportes', { method: 'POST', json: reporte });

/** @param {import('../types/reportes').ReportePublicacion} reporte */
export const enviarReportePublicacion = ({ publicacionId, motivo, descripcion }) =>
  request(`/publicaciones/${publicacionId}/reportes`, { method: 'POST', json: { motivo, descripcion } });
