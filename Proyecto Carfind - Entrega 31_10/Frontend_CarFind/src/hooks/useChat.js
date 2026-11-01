import { useState, useEffect, useCallback } from 'react';
import { getMensajes, enviarMensaje, marcarComoLeida, suscribirNuevosMensajes } from '../services/mensajeriaService';

/**
 * Hook de una conversación abierta. Usar con `key={conversacionId}` en el componente que lo llama,
 * así el estado arranca limpio al pasar de un chat a otro.
 *
 * @param {number|string} conversacionId
 * @param {() => void} [alHaberActividad]  Se llama al cargar, enviar o recibir (para refrescar la lista de chats)
 * @returns {{
 *   conversacion: import('../types/mensajeria').Conversacion|null,
 *   mensajes: import('../types/mensajeria').Mensaje[],
 *   loading: boolean,
 *   error: string|null,
 *   enviando: boolean,
 *   errorEnvio: string|null,
 *   enviar: (contenido: { texto?: string, imagen?: File|null }) => Promise<boolean>,
 *   reload: () => Promise<void>,
 * }}
 */
export default function useChat(conversacionId, alHaberActividad) {
  const [conversacion, setConversacion] = useState(null);
  const [mensajes, setMensajes] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [enviando, setEnviando] = useState(false);
  const [errorEnvio, setErrorEnvio] = useState(null);

  const reload = useCallback(async () => {
    setLoading(true);
    setError(null);
    try {
      const data = await getMensajes(conversacionId);
      setConversacion(data.conversacion);
      setMensajes(data.mensajes);
      await marcarComoLeida(conversacionId);
      alHaberActividad?.();
    } catch (e) {
      setError(e.message || 'No pudimos cargar la conversación.');
    } finally {
      setLoading(false);
    }
  }, [conversacionId, alHaberActividad]);

  useEffect(() => {
    reload();
  }, [reload]);

  // Mensajes que llegan mientras el chat está abierto.
  useEffect(() => {
    const dejarDeEscuchar = suscribirNuevosMensajes(conversacionId, (nuevo) => {
      setMensajes((prev) => (prev.some((m) => m.id === nuevo.id) ? prev : [...prev, nuevo]));
      marcarComoLeida(conversacionId).catch(() => {});
      alHaberActividad?.();
    });
    return dejarDeEscuchar;
  }, [conversacionId, alHaberActividad]);

  const enviar = useCallback(
    async (contenido) => {
      setEnviando(true);
      setErrorEnvio(null);
      try {
        const res = await enviarMensaje(conversacionId, contenido);
        setMensajes((prev) => (prev.some((m) => m.id === res.mensaje.id) ? prev : [...prev, res.mensaje]));
        alHaberActividad?.();
        return true;
      } catch (e) {
        setErrorEnvio(e.message || 'No pudimos enviar el mensaje. Intentá de nuevo.');
        return false;
      } finally {
        setEnviando(false);
      }
    },
    [conversacionId, alHaberActividad]
  );

  return { conversacion, mensajes, loading, error, enviando, errorEnvio, enviar, reload };
}
