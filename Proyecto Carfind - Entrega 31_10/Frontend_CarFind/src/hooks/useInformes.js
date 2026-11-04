import { useState, useEffect, useCallback } from 'react';
import { getInformes, generarInforme, descargarInforme } from '../services/adminService';
import { descargarBlob } from '../utils/descargarArchivo';

/**
 * Hook de "Generar informes": lista de informes, generación y descarga/vista previa.
 * @returns {{
 *   informes: import('../types/admin').Informe[],
 *   loading: boolean,
 *   error: string|null,
 *   reload: () => Promise<void>,
 *   generar: (datos: { tipo: string, desde: string, hasta: string, formato: string }) => Promise<boolean>,
 *   generando: boolean,
 *   errorGeneracion: string|null,
 *   ver: (id: number|string) => Promise<void>,
 *   descargar: (id: number|string) => Promise<void>,
 *   accionandoId: number|string|null,
 *   errorArchivo: string|null,
 * }}
 */
export default function useInformes() {
  const [informes, setInformes] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [generando, setGenerando] = useState(false);
  const [errorGeneracion, setErrorGeneracion] = useState(null);
  const [accionandoId, setAccionandoId] = useState(null);
  const [errorArchivo, setErrorArchivo] = useState(null);

  const reload = useCallback(async () => {
    setLoading(true);
    setError(null);
    try {
      const data = await getInformes();
      setInformes(data.informes);
    } catch (e) {
      setError(e.message || 'No pudimos cargar los informes.');
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    reload();
  }, [reload]);

  const generar = useCallback(async (datos) => {
    setGenerando(true);
    setErrorGeneracion(null);
    try {
      const res = await generarInforme(datos);
      setInformes((prev) => [res.informe, ...prev]);
      return true;
    } catch (e) {
      setErrorGeneracion(e.message || 'No pudimos generar el informe. Intentá de nuevo.');
      return false;
    } finally {
      setGenerando(false);
    }
  }, []);

  const descargar = useCallback(async (id) => {
    setAccionandoId(id);
    setErrorArchivo(null);
    try {
      const { blob, nombreArchivo } = await descargarInforme(id);
      descargarBlob(blob, nombreArchivo);
    } catch (e) {
      setErrorArchivo(e.message || 'No pudimos descargar el archivo. Intentá de nuevo.');
    } finally {
      setAccionandoId(null);
    }
  }, []);

  /** Abre el archivo en una pestaña nueva. La pestaña se abre antes de pedir el archivo para que el navegador no la bloquee. */
  const ver = useCallback(async (id) => {
    const ventana = window.open('', '_blank');
    setAccionandoId(id);
    setErrorArchivo(null);
    try {
      const { blob, nombreArchivo } = await descargarInforme(id);
      if (ventana) {
        const url = URL.createObjectURL(blob);
        ventana.location.href = url;
        setTimeout(() => URL.revokeObjectURL(url), 60000);
      } else {
        descargarBlob(blob, nombreArchivo); // el navegador bloqueó la pestaña: se descarga igual
      }
    } catch (e) {
      ventana?.close();
      setErrorArchivo(e.message || 'No pudimos abrir el archivo. Intentá de nuevo.');
    } finally {
      setAccionandoId(null);
    }
  }, []);

  return { informes, loading, error, reload, generar, generando, errorGeneracion, ver, descargar, accionandoId, errorArchivo };
}
