/**
 * Servicio de Usuarios — IMPLEMENTACIÓN REAL (backend).
 *
 *   GET /usuarios/:id → PerfilPublicoResponse   { usuario, publicaciones }
 *
 * Formas en src/types/perfilPublico.js. Si el backend usa otra ruta o campos, se ajusta SOLO acá.
 */
import { request } from './api';

/**
 * @param {number|string} id
 * @returns {Promise<import('../types/perfilPublico').PerfilPublicoResponse>}
 */
export const getPerfilPublico = (id) => request(`/usuarios/${id}`);
