import { useState, useCallback } from 'react';
import { enviarReporteGeneral } from '../services/reporteService';

/**
 * Hook del reporte general (dudas, problemas, sugerencias).
 * @returns {{
 *   enviar: (reporte: import('../types/reportes').ReporteGeneral) => Promise<boolean>,
 *   enviando: boolean,
 *   enviado: boolean,
 *   error: string|null,
 *   reiniciar: () => void,
 * }}
 */
export default function useReporteGeneral() {
  const [enviando, setEnviando] = useState(false);
  const [enviado, setEnviado] = useState(false);
  const [error, setError] = useState(null);

  const enviar = useCallback(async (reporte) => {
    setEnviando(true);
    setError(null);
    try {
      await enviarReporteGeneral(reporte);
      setEnviado(true);
      return true;
    } catch (e) {
      setError(e.message || 'No pudimos enviar tu reporte. Intentá de nuevo.');
      return false;
    } finally {
      setEnviando(false);
    }
  }, []);

  const reiniciar = useCallback(() => {
    setEnviado(false);
    setError(null);
  }, []);

  return { enviar, enviando, enviado, error, reiniciar };
}
