/**
 * Punto de entrada del servicio de Configuración.
 *
 * Los hooks y componentes solo importan de acá. Igual que con Perfil, según
 * VITE_USE_MOCK se usa:
 *  - configuracionService.mock.js → datos de prueba (por defecto, mientras no haya backend)
 *  - configuracionService.api.js  → llamadas reales al backend
 *
 * Para conectar el backend: VITE_USE_MOCK=false en el .env y definir VITE_API_URL.
 * No hay que tocar ningún componente ni el hook.
 */
import * as mock from './configuracionService.mock';
import * as api from './configuracionService.api';

const usarMock = import.meta.env.VITE_USE_MOCK !== 'false';

export const {
  getConfiguracion,
  actualizarDatosPersonales,
  cambiarContrasena,
  guardarApariencia,
  guardarAccesibilidad,
  guardarNotificaciones,
  guardarIdioma,
} = usarMock ? mock : api;
