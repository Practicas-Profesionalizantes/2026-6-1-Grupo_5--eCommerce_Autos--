/**
 * Punto de entrada del servicio de Reportes.
 * Según VITE_USE_MOCK usa reporteService.mock.js (por defecto) o reporteService.api.js.
 * Para conectar el backend: VITE_USE_MOCK=false y definir VITE_API_URL.
 */
import * as mock from './reporteService.mock';
import * as api from './reporteService.api';

const usarMock = import.meta.env.VITE_USE_MOCK !== 'false';

export const { enviarReporteGeneral, enviarReportePublicacion } = usarMock ? mock : api;
