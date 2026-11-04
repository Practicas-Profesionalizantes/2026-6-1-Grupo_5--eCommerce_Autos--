/**
 * Punto de entrada del servicio de Mensajería.
 *
 * Los hooks y componentes solo importan de acá. Según VITE_USE_MOCK se usa:
 *  - mensajeriaService.mock.js → conversaciones de prueba en memoria (por defecto)
 *  - mensajeriaService.api.js  → llamadas reales al backend
 *
 * Para conectar el backend: VITE_USE_MOCK=false y definir VITE_API_URL.
 * Si el backend entrega los mensajes en tiempo real (WebSocket / SSE), solo hay que
 * cambiar suscribirNuevosMensajes en mensajeriaService.api.js.
 */
import * as mock from './mensajeriaService.mock';
import * as api from './mensajeriaService.api';

const usarMock = import.meta.env.VITE_USE_MOCK !== 'false';

export const {
  getConversaciones,
  getMensajes,
  enviarMensaje,
  iniciarConversacion,
  marcarComoLeida,
  suscribirNuevosMensajes,
} = usarMock ? mock : api;
