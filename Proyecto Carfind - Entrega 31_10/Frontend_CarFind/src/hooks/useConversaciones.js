import { useState, useEffect, useCallback } from 'react';
import { getConversaciones } from '../services/mensajeriaService';

/**
 * Hook de la lista "CHATS".
 * @returns {{
 *   conversaciones: import('../types/mensajeria').Conversacion[],
 *   loading: boolean,
 *   error: string|null,
 *   reload: () => Promise<void>,
 *   refrescar: () => Promise<void>,
 * }}  refrescar vuelve a pedir la lista sin mostrar "Cargando" (para actualizar el último mensaje).
 */
export default function useConversaciones() {
  const [conversaciones, setConversaciones] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const cargar = useCallback(async (silencioso = false) => {
    if (!silencioso) setLoading(true);
    setError(null);
    try {
      const data = await getConversaciones();
      setConversaciones(data.conversaciones);
    } catch (e) {
      setError(e.message || 'No pudimos cargar tus chats.');
    } finally {
      if (!silencioso) setLoading(false);
    }
  }, []);

  useEffect(() => {
    cargar();
  }, [cargar]);

  const reload = useCallback(() => cargar(false), [cargar]);
  const refrescar = useCallback(() => cargar(true), [cargar]);

  return { conversaciones, loading, error, reload, refrescar };
}
