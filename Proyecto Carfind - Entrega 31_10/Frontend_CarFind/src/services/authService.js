/**
 * Servicio de autenticación (registro, inicio y cierre de sesión). Siempre usa el backend.
 *
 *   POST /auth/registro  → { token, usuario }   JSON { dni, email, nombre, telefono, password }
 *   POST /auth/login     → { token, usuario }   JSON { email, password }
 *   GET  /auth/sesion    → { usuario }
 *   POST /auth/logout    → 204
 *
 * usuario = { id, nombre, apellido, email, fotoUrl, esAdmin }
 * El token queda guardado (ver guardarToken en api.js) y se manda en cada pedido.
 */
import { request, guardarToken, haySesion } from './api';

/**
 * @typedef {Object} UsuarioSesion
 * @property {number} id
 * @property {string} nombre
 * @property {string} apellido
 * @property {string} email
 * @property {string|null} fotoUrl
 * @property {boolean} esAdmin
 */

/** @returns {Promise<UsuarioSesion>} */
export async function login({ email, password }) {
  const { token, usuario } = await request('/auth/login', { method: 'POST', json: { email, password } });
  guardarToken(token);
  return usuario;
}

/** @returns {Promise<UsuarioSesion>} */
export async function registrar(datos) {
  const { token, usuario } = await request('/auth/registro', { method: 'POST', json: datos });
  guardarToken(token);
  return usuario;
}

/** Usuario de la sesión guardada, o null si no hay sesión o ya venció. */
export async function getSesion() {
  if (!haySesion()) return null;
  try {
    const { usuario } = await request('/auth/sesion');
    return usuario;
  } catch (e) {
    if (e.status === 401) guardarToken(null);
    throw e;
  }
}

export async function logout() {
  try {
    await request('/auth/logout', { method: 'POST' });
  } finally {
    guardarToken(null);
  }
}
