import { useState, useEffect, useCallback } from 'react';
import { getEstadisticas } from '../services/adminService';

/**
 * Hook de la vista Estadísticas. Al cambiar el período mantiene los datos anteriores en pantalla
 * mientras llegan los nuevos (`actualizando`), así la página no parpadea.
 * @param {number} meses  Meses que abarca el gráfico de evolución
 * @returns {{
 *   datos: import('../types/admin').EstadisticasResponse|null,
 *   loading: boolean,
 *   actualizando: boolean,
 *   error: string|null,
 *   reload: () => Promise<void>,
 * }}
 */
export default function useEstadisticasAdmin(meses) {
  const [datos, setDatos] = useState(null);
  const [loading, setLoading] = useState(true);
  const [actualizando, setActualizando] = useState(false);
  const [error, setError] = useState(null);

  const reload = useCallback(async () => {
    setActualizando(true);
    setError(null);
    try {
      setDatos(await getEstadisticas({ meses }));
    } catch (e) {
      setError(e.message || 'No pudimos cargar las estadísticas.');
    } finally {
      setActualizando(false);
      setLoading(false);
    }
  }, [meses]);

  useEffect(() => {
    reload();
  }, [reload]);

  return { datos, loading, actualizando, error, reload };
}
