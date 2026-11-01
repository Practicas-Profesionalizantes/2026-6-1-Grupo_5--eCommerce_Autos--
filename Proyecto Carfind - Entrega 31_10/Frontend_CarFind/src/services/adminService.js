/**
 * Punto de entrada del servicio del panel de administración.
 * Según VITE_USE_MOCK usa adminService.mock.js (por defecto) o adminService.api.js.
 * Para conectar el backend: VITE_USE_MOCK=false y definir VITE_API_URL.
 */
import * as mock from './adminService.mock';
import * as api from './adminService.api';

const usarMock = import.meta.env.VITE_USE_MOCK !== 'false';

export const {
  getSesionAdmin,
  getEstadisticas,
  getReportesRecibidos,
  responderReporte,
  cambiarEstadoReporte,
  getInformes,
  generarInforme,
  descargarInforme,
} = usarMock ? mock : api;
