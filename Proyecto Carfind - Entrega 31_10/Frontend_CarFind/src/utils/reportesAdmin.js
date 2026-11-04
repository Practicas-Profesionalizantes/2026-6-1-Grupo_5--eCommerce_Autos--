import { TIPOS_REPORTE_RECIBIDO, ESTADOS_REPORTE } from '../types/admin';
import { MOTIVOS_REPORTE_PUBLICACION } from '../types/reportes';

const buscarNombre = (lista, id, porDefecto = '—') => lista.find((x) => x.id === id)?.nombre ?? porDefecto;

export const nombreTipoReporte = (id) => buscarNombre(TIPOS_REPORTE_RECIBIDO, id);
export const nombreEstadoReporte = (id) => buscarNombre(ESTADOS_REPORTE, id);
export const nombreMotivo = (id) => buscarNombre(MOTIVOS_REPORTE_PUBLICACION, id);
export const nombreCompleto = (u) => (u ? `${u.nombre} ${u.apellido}` : '—');

const recortar = (texto, max) => (texto.length > max ? `${texto.slice(0, max).trimEnd()}…` : texto);

/** Título corto para la tabla: qué se reportó. @param {import('../types/admin').ReporteRecibido} r */
export function tituloReporte(r) {
  if (r.tipo === 'publicacion') return `Publicación: ${r.publicacion?.titulo ?? '—'}`;
  if (r.tipo === 'usuario') return `Usuario: ${nombreCompleto(r.usuarioReportado)}`;
  return `Consulta: ${recortar(r.descripcion || 'sin texto', 50)}`;
}
