import { useState, useEffect, useCallback, useRef } from 'react';
import {
  getConfiguracion,
  actualizarDatosPersonales,
  cambiarContrasena as cambiarContrasenaServicio,
  guardarApariencia,
  guardarAccesibilidad,
  guardarNotificaciones,
  guardarIdioma,
} from '../services/configuracionService';
import { subirFotoPerfil, eliminarFotoPerfil } from '../services/perfilService';
import { aplicarModoTema } from '../utils/tema';
import { guardarPreferenciasUI } from '../utils/preferenciasUI';
import { validarImagen } from '../utils/validarImagen';

const MENSAJE_ERROR = 'No pudimos guardar los cambios. Intentá de nuevo.';
const ESPERA_GUARDADO_APARIENCIA_MS = 400;

/** Pinta en la app lo elegido en Apariencia / Accesibilidad. */
const aplicarApariencia = (a) => {
  aplicarModoTema(a.tema);
  guardarPreferenciasUI({ escalaFuente: a.escalaFuente, fuente: a.fuente });
};
const aplicarAccesibilidad = (a) =>
  guardarPreferenciasUI({
    atajosTeclado: a.atajosTeclado,
    animaciones: a.animaciones,
    subrayadoEnlaces: a.subrayadoEnlaces,
  });

/**
 * Hook que encapsula el estado de todas las vistas de Configuración.
 * Las vistas solo consumen el resultado; no conocen el servicio.
 *
 * - apariencia: se aplica al instante y se guarda en el servidor con una pequeña espera
 *   (así el slider de tamaño no dispara una llamada por cada movimiento).
 * - accesibilidad: se guarda cuando el usuario toca el botón de cada bloque.
 * - notificaciones e idioma: se guardan solos en cada cambio (con vuelta atrás si falla).
 *
 * @returns {{
 *   config: import('../types/configuracion').ConfiguracionResponse|null,
 *   loading: boolean,
 *   error: string|null,
 *   errorGuardado: string|null,
 *   reload: () => Promise<void>,
 *   guardarDatosPersonales: (datos: object) => Promise<{ ok: boolean, error?: string }>,
 *   cambiarContrasena: (datos: { actual: string, nueva: string }) => Promise<{ ok: boolean, error?: string }>,
 *   subirFoto: (archivo: File) => Promise<boolean>,
 *   eliminarFoto: () => Promise<boolean>,
 *   fotoError: string|null,
 *   savingFoto: boolean,
 *   actualizarApariencia: (parcial: Partial<import('../types/configuracion').Apariencia>) => void,
 *   guardarAccesibilidadCambios: (a: import('../types/configuracion').Accesibilidad) => Promise<{ ok: boolean, error?: string }>,
 *   actualizarNotificaciones: (n: import('../types/configuracion').Notificaciones) => Promise<boolean>,
 *   actualizarIdioma: (i: import('../types/configuracion').Idioma) => Promise<boolean>,
 * }}
 */
export default function useConfiguracion() {
  const [config, setConfig] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [errorGuardado, setErrorGuardado] = useState(null);
  const [fotoError, setFotoError] = useState(null);
  const [savingFoto, setSavingFoto] = useState(false);

  // Guardado pendiente de Apariencia (para no perderlo si el usuario sale de Configuración).
  const temporizadorApariencia = useRef(null);
  const aparienciaPendiente = useRef(null);

  const reload = useCallback(async () => {
    setLoading(true);
    setError(null);
    try {
      const data = await getConfiguracion();
      setConfig(data);
      aplicarApariencia(data.apariencia);
      aplicarAccesibilidad(data.accesibilidad);
    } catch (e) {
      setError(e.message || 'No pudimos cargar tu configuración.');
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    reload();
  }, [reload]);

  // Al salir de Configuración, si quedó un guardado de Apariencia pendiente, se envía igual.
  useEffect(
    () => () => {
      clearTimeout(temporizadorApariencia.current);
      if (aparienciaPendiente.current) {
        guardarApariencia(aparienciaPendiente.current).catch(() => {});
        aparienciaPendiente.current = null;
      }
    },
    []
  );

  const reemplazarSeccion = useCallback((clave, valor) => {
    setConfig((prev) => (prev ? { ...prev, [clave]: valor } : prev));
  }, []);

  /** Guarda una sección con cambio inmediato en pantalla; si el servidor falla, vuelve al valor anterior. */
  const guardarConVueltaAtras = useCallback(
    async (clave, nuevo, servicio) => {
      const anterior = config?.[clave];
      setErrorGuardado(null);
      reemplazarSeccion(clave, nuevo);
      try {
        const res = await servicio(nuevo);
        if (res?.[clave]) reemplazarSeccion(clave, res[clave]);
        return true;
      } catch (e) {
        reemplazarSeccion(clave, anterior);
        setErrorGuardado(e.message || MENSAJE_ERROR);
        return false;
      }
    },
    [config, reemplazarSeccion]
  );

  // ---------- Datos personales ----------
  const guardarDatosPersonales = useCallback(
    async (datos) => {
      try {
        const res = await actualizarDatosPersonales(datos);
        reemplazarSeccion('datosPersonales', res.datosPersonales);
        return { ok: true };
      } catch (e) {
        return { ok: false, error: e.message || MENSAJE_ERROR };
      }
    },
    [reemplazarSeccion]
  );

  const cambiarContrasena = useCallback(async (datos) => {
    try {
      await cambiarContrasenaServicio(datos);
      return { ok: true };
    } catch (e) {
      return { ok: false, error: e.message || 'No pudimos cambiar la contraseña. Intentá de nuevo.' };
    }
  }, []);

  const actualizarFoto = useCallback((fotoUrl) => {
    setConfig((prev) =>
      prev ? { ...prev, datosPersonales: { ...prev.datosPersonales, fotoUrl } } : prev
    );
  }, []);

  /** Valida el archivo en el front y, si es una imagen válida, lo sube. */
  const subirFoto = useCallback(
    async (archivo) => {
      const mensaje = validarImagen(archivo);
      if (mensaje) {
        setFotoError(mensaje);
        return false;
      }
      setSavingFoto(true);
      setFotoError(null);
      try {
        const res = await subirFotoPerfil(archivo);
        actualizarFoto(res.usuario.fotoUrl);
        return true;
      } catch (e) {
        setFotoError(e.message || 'No pudimos subir la foto. Intentá de nuevo.');
        return false;
      } finally {
        setSavingFoto(false);
      }
    },
    [actualizarFoto]
  );

  const eliminarFoto = useCallback(async () => {
    setSavingFoto(true);
    setFotoError(null);
    try {
      const res = await eliminarFotoPerfil();
      actualizarFoto(res.usuario.fotoUrl);
      return true;
    } catch (e) {
      setFotoError(e.message || 'No pudimos eliminar la foto. Intentá de nuevo.');
      return false;
    } finally {
      setSavingFoto(false);
    }
  }, [actualizarFoto]);

  // ---------- Apariencia ----------
  const actualizarApariencia = useCallback(
    (parcial) => {
      if (!config) return;
      const nueva = { ...config.apariencia, ...parcial };
      setErrorGuardado(null);
      reemplazarSeccion('apariencia', nueva);
      aplicarApariencia(nueva); // se ve al instante

      aparienciaPendiente.current = nueva;
      clearTimeout(temporizadorApariencia.current);
      temporizadorApariencia.current = setTimeout(async () => {
        const aGuardar = aparienciaPendiente.current;
        aparienciaPendiente.current = null;
        try {
          await guardarApariencia(aGuardar);
        } catch (e) {
          // El cambio ya está aplicado y guardado en este dispositivo; solo falló la sincronización.
          setErrorGuardado(e.message || 'No pudimos sincronizar la apariencia. Se mantiene en este dispositivo.');
        }
      }, ESPERA_GUARDADO_APARIENCIA_MS);
    },
    [config, reemplazarSeccion]
  );

  // ---------- Accesibilidad ----------
  const guardarAccesibilidadCambios = useCallback(
    async (nueva) => {
      const anterior = config?.accesibilidad;
      setErrorGuardado(null);
      reemplazarSeccion('accesibilidad', nueva);
      aplicarAccesibilidad(nueva);
      try {
        await guardarAccesibilidad(nueva);
        return { ok: true };
      } catch (e) {
        reemplazarSeccion('accesibilidad', anterior);
        if (anterior) aplicarAccesibilidad(anterior);
        return { ok: false, error: e.message || MENSAJE_ERROR };
      }
    },
    [config, reemplazarSeccion]
  );

  // ---------- Notificaciones e idioma ----------
  const actualizarNotificaciones = useCallback(
    (nuevas) => guardarConVueltaAtras('notificaciones', nuevas, guardarNotificaciones),
    [guardarConVueltaAtras]
  );

  const actualizarIdioma = useCallback(
    (nuevo) => guardarConVueltaAtras('idioma', nuevo, guardarIdioma),
    [guardarConVueltaAtras]
  );

  return {
    config,
    loading,
    error,
    errorGuardado,
    reload,
    guardarDatosPersonales,
    cambiarContrasena,
    subirFoto,
    eliminarFoto,
    fotoError,
    savingFoto,
    actualizarApariencia,
    guardarAccesibilidadCambios,
    actualizarNotificaciones,
    actualizarIdioma,
  };
}
