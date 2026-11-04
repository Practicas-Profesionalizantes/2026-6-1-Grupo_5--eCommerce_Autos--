import { useState, useEffect, useCallback } from 'react';
import { getPublicacion } from '../services/publicacionService';

/**
 * Hook que carga el detalle de la publicación indicada por el id de la ruta.
 * @param {number|string} id
 * @returns {{
 *   publicacion: Awaited<ReturnType<typeof getPublicacion>>|null,
 *   loading: boolean,
 *   error: string|null,
 *   reload: () => Promise<void>,
 * }}
 */
export default function usePublicacion(id) {
  const [publicacion, setPublicacion] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const reload = useCallback(async () => {
    setLoading(true);
    setError(null);
    try {
      setPublicacion(await getPublicacion(id));
    } catch (e) {
      setError(e.message || 'No pudimos cargar la publicación.');
    } finally {
      setLoading(false);
    }
  }, [id]);

  useEffect(() => {
    reload();
  }, [reload]);

  return { publicacion, loading, error, reload };
}
