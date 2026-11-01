/**
 * Punto de entrada del servicio de Perfil.
 *
 * La vista y el hook solo importan de acá. Según VITE_USE_MOCK se usa:
 *  - perfilService.mock.js → usuario fijo de prueba (por defecto, mientras no haya backend)
 *  - perfilService.api.js  → llamadas reales al backend
 *
 * Para conectar el backend: poner VITE_USE_MOCK=false en el .env y definir VITE_API_URL.
 * No hay que tocar ningún componente ni el hook.
 */
import * as mock from './perfilService.mock';
import * as api from './perfilService.api';

const usarMock = import.meta.env.VITE_USE_MOCK !== 'false';

export const { getPerfil, subirFotoPerfil, eliminarFotoPerfil, actualizarPerfil } = usarMock ? mock : api;
