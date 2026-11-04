/**
 * Punto de entrada del servicio de Publicaciones.
 * Según VITE_USE_MOCK usa publicacionService.mock.js o publicacionService.api.js.
 */
import * as mock from './publicacionService.mock';
import * as api from './publicacionService.api';

const usarMock = import.meta.env.VITE_USE_MOCK !== 'false';

export const {
  getPublicaciones,
  getOpcionesFiltros,
  getPublicacion,
  getResumenPublicacion,
  crearPublicacion,
  getPublicacionParaEditar,
  actualizarPublicacion,
  cambiarEstadoPublicacion,
  eliminarPublicacion,
} = usarMock ? mock : api;
