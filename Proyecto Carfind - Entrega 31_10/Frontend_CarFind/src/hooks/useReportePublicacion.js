import { useState, useEffect, useCallback } from 'react';
import { getResumenPublicacion } from '../services/publicacionService';
import { enviarReportePublicacion } from '../services/reporteService';

/**
 * Hook de "Reportar publicación": carga la tarjeta de la publicación y envía el reporte.
 * Usar con `key={id}` en el componente que lo llama.
 * @param {number|string} id  Id de la publicación que se reporta
 * @returns {{
 *   publicacion: import('../types/perfil').PublicacionPerfilItem|null,
 *   loading: boolean,
 *   error: string|null,
 *   reload: () => Promise<void>,
 *   enviar: (datos: { motivo: string, descripcion: string }) => Promise<boolean>,
 *   enviando: boolean,
 *   enviado: boolean,
 *   errorEnvio: string|null,
 * }}
 */
export default function useReportePublicacion(id) {
  const [publicacion, setPublicacion] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [enviando, setEnviando] = useState(false);
  const [enviado, setEnviado] = useState(false);
  const [errorEnvio, setErrorEnvio] = useState(null);

  const reload = useCallback(async () => {
    setLoading(true);
    setError(null);
    try {
      setPublicacion(await getResumenPublicacion(id));
    } catch (e) {
      setError(e.message || 'No pudimos cargar la publicación.');
    } finally {
      setLoading(false);
    }
  }, [id]);

  useEffect(() => {
    reload();
  }, [reload]);

  const enviar = useCallback(
    async ({ motivo, descripcion }) => {
      setEnviando(true);
      setErrorEnvio(null);
      try {
        await enviarReportePublicacion({ publicacionId: id, motivo, descripcion });
        setEnviado(true);
        return true;
      } catch (e) {
        setErrorEnvio(e.message || 'No pudimos enviar tu reporte. Intentá de nuevo.');
        return false;
      } finally {
        setEnviando(false);
      }
    },
    [id]
  );

  return { publicacion, loading, error, reload, enviar, enviando, enviado, errorEnvio };
}
