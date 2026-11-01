import { useState, useEffect, useCallback } from 'react';
import { getReportesRecibidos, responderReporte, cambiarEstadoReporte } from '../services/adminService';

const FILTROS_INICIALES = { tipo: '', estado: '', desde: '', hasta: '' };

/**
 * Hook de "Reportes recibidos": lista con filtros, respuesta y cambio de estado.
 * @returns {{
 *   reportes: import('../types/admin').ReporteRecibido[],
 *   filtros: import('../types/admin').FiltrosReportes,
 *   cambiarFiltro: (campo: string, valor: string) => void,
 *   limpiarFiltros: () => void,
 *   loading: boolean,
 *   error: string|null,
 *   reload: () => Promise<void>,
 *   responder: (id: number|string, datos: { texto: string, estado?: string }) => Promise<{ ok: boolean, error?: string }>,
 *   cambiarEstado: (id: number|string, estado: string) => Promise<{ ok: boolean, error?: string }>,
 * }}
 */
export default function useReportesRecibidos() {
  const [filtros, setFiltros] = useState(FILTROS_INICIALES);
  const [reportes, setReportes] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const reload = useCallback(async () => {
    setLoading(true);
    setError(null);
    try {
      const data = await getReportesRecibidos(filtros);
      setReportes(data.reportes);
    } catch (e) {
      setError(e.message || 'No pudimos cargar los reportes.');
    } finally {
      setLoading(false);
    }
  }, [filtros]);

  useEffect(() => {
    reload();
  }, [reload]);

  const cambiarFiltro = useCallback((campo, valor) => setFiltros((prev) => ({ ...prev, [campo]: valor })), []);
  const limpiarFiltros = useCallback(() => setFiltros(FILTROS_INICIALES), []);

  /** Actualiza un reporte en la lista (queda visible aunque ya no cumpla el filtro, hasta recargar). */
  const reemplazar = (reporte) => setReportes((prev) => prev.map((r) => (r.id === reporte.id ? reporte : r)));

  const responder = useCallback(async (id, datos) => {
    try {
      const res = await responderReporte(id, datos);
      reemplazar(res.reporte);
      return { ok: true };
    } catch (e) {
      return { ok: false, error: e.message || 'No pudimos enviar la respuesta. Intentá de nuevo.' };
    }
  }, []);

  const cambiarEstado = useCallback(async (id, estado) => {
    try {
      const res = await cambiarEstadoReporte(id, estado);
      reemplazar(res.reporte);
      return { ok: true };
    } catch (e) {
      return { ok: false, error: e.message || 'No pudimos cambiar el estado. Intentá de nuevo.' };
    }
  }, []);

  return { reportes, filtros, cambiarFiltro, limpiarFiltros, loading, error, reload, responder, cambiarEstado };
}
