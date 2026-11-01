/**
 * Servicio de Publicaciones — IMPLEMENTACIÓN REAL (backend).
 *
 *   GET  /publicaciones?q=&marca=&modelo=&anio=&kmsMax=&precioMax=&excluir=&limite=
 *        → [{ id, titulo, precio, moneda, imagen, vendedor }]   (tarjetas del inicio)
 *   GET  /publicaciones/filtros       → { marca[], modelo[], anio[] }
 *   GET  /publicaciones/:id           → detalle (forma de PUBLICACION_DETALLE_MOCK)
 *   GET  /publicaciones/:id/resumen   → PublicacionPerfilItem (ver types/perfil.js)
 *   POST /publicaciones               → { id }   multipart: campos del formulario + "fotos"
 *
 * Gestión de las publicaciones propias (solo el dueño; el backend responde 403 si no lo es):
 *   GET    /publicaciones/:id/edicion → datos para el formulario (mismos campos que crear + fotos[] con URLs)
 *   PUT    /publicaciones/:id         → { ok }   multipart: campos + "fotosConservadas" (URLs) + "fotos" nuevas
 *   PUT    /publicaciones/:id/estado  → { ok, estado }   JSON { estado: "ACTIVA" | "PAUSADA" }
 *   DELETE /publicaciones/:id         → { ok }
 *
 * Si el backend usa otros nombres de campo o rutas, se ajusta SOLO en este archivo.
 */
import { request } from './api';

/**
 * @param {Record<string, string|number|null|undefined>} [filtros]
 * @returns {Promise<{ id: number, titulo: string, precio: number, moneda: string, imagen: string|null, vendedor: string }[]>}
 */
export function getPublicaciones(filtros = {}) {
  const params = new URLSearchParams();
  Object.entries(filtros).forEach(([clave, valor]) => {
    if (valor !== '' && valor !== null && valor !== undefined) params.set(clave, valor);
  });
  const consulta = params.toString();
  return request(`/publicaciones${consulta ? `?${consulta}` : ''}`);
}

/** @returns {Promise<{ marca: string[], modelo: string[], anio: number[] }>} */
export const getOpcionesFiltros = () => request('/publicaciones/filtros');

/** @param {number|string} id */
export const getPublicacion = (id) => request(`/publicaciones/${id}`);

/**
 * @param {number|string} id
 * @returns {Promise<import('../types/perfil').PublicacionPerfilItem>}
 */
export const getResumenPublicacion = (id) => request(`/publicaciones/${id}/resumen`);

/**
 * @param {Record<string, string>} datos   Campos del formulario de CrearPublicacion (sin las fotos)
 * @param {File[]} fotos
 * @returns {Promise<{ id: number }>}
 */
export function crearPublicacion(datos, fotos) {
  const formData = new FormData();
  Object.entries(datos).forEach(([clave, valor]) => formData.append(clave, valor ?? ''));
  fotos.forEach((foto) => formData.append('fotos', foto));
  return request('/publicaciones', { method: 'POST', formData });
}

/**
 * @param {number|string} id
 * @returns {Promise<Record<string, string> & { fotos: string[], estadoPublicacion: string }>}
 */
export const getPublicacionParaEditar = (id) => request(`/publicaciones/${id}/edicion`);

/**
 * @param {number|string} id
 * @param {Record<string, string>} datos   Campos del formulario (sin las fotos)
 * @param {string[]} fotosConservadas      URLs de las fotos que ya tenía y quedan
 * @param {File[]} fotosNuevas
 */
export function actualizarPublicacion(id, datos, fotosConservadas, fotosNuevas) {
  const formData = new FormData();
  Object.entries(datos).forEach(([clave, valor]) => formData.append(clave, valor ?? ''));
  fotosConservadas.forEach((url) => formData.append('fotosConservadas', url));
  fotosNuevas.forEach((foto) => formData.append('fotos', foto));
  return request(`/publicaciones/${id}`, { method: 'PUT', formData });
}

/**
 * Pausa o reactiva una publicación propia.
 * @param {number|string} id
 * @param {'ACTIVA'|'PAUSADA'} estado
 */
export const cambiarEstadoPublicacion = (id, estado) =>
  request(`/publicaciones/${id}/estado`, { method: 'PUT', json: { estado } });

/** @param {number|string} id */
export const eliminarPublicacion = (id) => request(`/publicaciones/${id}`, { method: 'DELETE' });
