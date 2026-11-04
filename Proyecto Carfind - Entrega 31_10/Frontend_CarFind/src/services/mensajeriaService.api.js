/**
 * Servicio de Mensajería — IMPLEMENTACIÓN REAL (backend).
 *
 * El usuario logueado lo identifica el backend (token o sesión). Contrato esperado
 * (formas en src/types/mensajeria.js):
 *
 *   GET  /conversaciones                              → { conversaciones: Conversacion[] }
 *   GET  /conversaciones/:id/mensajes                 → ChatResponse { conversacion, mensajes }
 *   GET  /conversaciones/:id/mensajes?desde=<idMsj>   → { mensajes: Mensaje[] }   solo los posteriores a ese id
 *   POST /conversaciones/:id/mensajes                 → { mensaje: Mensaje }
 *        multipart/form-data con los campos "texto" y (opcional) "imagen"
 *   POST /conversaciones                              → { conversacionId }
 *        JSON { publicacionId }: crea la conversación o devuelve la que ya existe para esa publicación
 *   PUT  /conversaciones/:id/leida                    → { ok }
 *
 * Si el backend usa otros nombres de campo o rutas, se ajusta SOLO en este archivo.
 */
import { request } from './api';

/** Cada cuántos milisegundos se consultan mensajes nuevos mientras no haya tiempo real. */
const INTERVALO_CONSULTA_MS = 5000;

/** @returns {Promise<{ conversaciones: import('../types/mensajeria').Conversacion[] }>} */
export const getConversaciones = () => request('/conversaciones');

/**
 * @param {number|string} conversacionId
 * @returns {Promise<import('../types/mensajeria').ChatResponse>}
 */
export const getMensajes = (conversacionId) => request(`/conversaciones/${conversacionId}/mensajes`);

/**
 * @param {number|string} conversacionId
 * @param {{ texto?: string, imagen?: File|null }} contenido
 * @returns {Promise<{ mensaje: import('../types/mensajeria').Mensaje }>}
 */
export function enviarMensaje(conversacionId, { texto = '', imagen = null }) {
  const formData = new FormData();
  formData.append('texto', texto);
  if (imagen) formData.append('imagen', imagen);
  return request(`/conversaciones/${conversacionId}/mensajes`, { method: 'POST', formData });
}

/**
 * Inicia (o recupera) la conversación de la publicación indicada.
 * @param {number|string} publicacionId
 * @returns {Promise<{ conversacionId: number|string }>}
 */
export const iniciarConversacion = (publicacionId) =>
  request('/conversaciones', { method: 'POST', json: { publicacionId } });

/** @param {number|string} conversacionId */
export const marcarComoLeida = (conversacionId) => request(`/conversaciones/${conversacionId}/leida`, { method: 'PUT' });

/**
 * Avisa cada vez que llega un mensaje nuevo a la conversación. Devuelve la función para dejar de escuchar.
 *
 * Esta versión consulta al servidor cada pocos segundos. Si el backend ofrece tiempo real
 * (WebSocket o SSE), reemplazar el cuerpo por la conexión, manteniendo la firma.
 *
 * @param {number|string} conversacionId
 * @param {(mensaje: import('../types/mensajeria').Mensaje) => void} alRecibir
 * @returns {() => void}
 */
export function suscribirNuevosMensajes(conversacionId, alRecibir) {
  let ultimoId = null;
  let activo = true;

  const consultar = async () => {
    try {
      const ruta = `/conversaciones/${conversacionId}/mensajes${ultimoId !== null ? `?desde=${ultimoId}` : ''}`;
      const { mensajes } = await request(ruta);
      if (!activo) return;
      mensajes.forEach((m) => {
        ultimoId = m.id;
        if (!m.propio) alRecibir(m); // los propios ya los agregó el envío
      });
    } catch {
      // Un fallo de red pasajero no debe cortar la escucha: se reintenta en el próximo ciclo.
    }
  };

  const temporizador = setInterval(consultar, INTERVALO_CONSULTA_MS);
  return () => {
    activo = false;
    clearInterval(temporizador);
  };
}
