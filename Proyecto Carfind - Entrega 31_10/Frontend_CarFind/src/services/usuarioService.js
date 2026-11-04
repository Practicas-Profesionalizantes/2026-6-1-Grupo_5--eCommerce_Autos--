/**
 * Punto de entrada del servicio de Usuarios (perfil público de otros usuarios).
 * Según VITE_USE_MOCK usa usuarioService.mock.js (por defecto) o usuarioService.api.js.
 */
import * as mock from './usuarioService.mock';
import * as api from './usuarioService.api';

const usarMock = import.meta.env.VITE_USE_MOCK !== 'false';

export const { getPerfilPublico } = usarMock ? mock : api;
