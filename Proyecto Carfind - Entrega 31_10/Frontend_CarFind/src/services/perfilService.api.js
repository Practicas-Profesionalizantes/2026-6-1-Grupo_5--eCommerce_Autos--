/**
 * Servicio de Perfil — IMPLEMENTACIÓN REAL (backend).
 *
 * El usuario logueado lo identifica el backend (token o sesión), por eso ninguna
 * ruta lleva el id del usuario. Contrato esperado (formas en src/types/perfil.js):
 *
 *   GET    /perfil        → PerfilResponse            { usuario, publicaciones }
 *   POST   /perfil/foto   → { ok, usuario }           multipart/form-data, campo "foto"
 *   DELETE /perfil/foto   → { ok, usuario }
 *   PUT    /perfil        → { ok, usuario }           JSON con nombre, apellido, ubicacion, telefono
 *
 * Si el backend usa otros nombres de campo o rutas, se ajusta SOLO en este archivo.
 */
import { request } from './api';

/** @returns {Promise<import('../types/perfil').PerfilResponse>} */
export const getPerfil = () => request('/perfil');

/**
 * @param {File} archivo
 * @returns {Promise<{ ok: boolean, usuario: import('../types/perfil').Perfil }>}
 */
export const subirFotoPerfil = (archivo) => {
  const formData = new FormData();
  formData.append('foto', archivo);
  return request('/perfil/foto', { method: 'POST', formData });
};

/** @returns {Promise<{ ok: boolean, usuario: import('../types/perfil').Perfil }>} */
export const eliminarFotoPerfil = () => request('/perfil/foto', { method: 'DELETE' });

/**
 * @param {import('../types/perfil').PerfilEditable} datos
 * @returns {Promise<{ ok: boolean, usuario: import('../types/perfil').Perfil }>}
 */
export const actualizarPerfil = (datos) => request('/perfil', { method: 'PUT', json: datos });
