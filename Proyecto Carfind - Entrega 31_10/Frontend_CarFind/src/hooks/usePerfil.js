import { useState, useEffect, useCallback } from 'react';
import { getPerfil, subirFotoPerfil, eliminarFotoPerfil, actualizarPerfil } from '../services/perfilService';
import { validarImagen } from '../utils/validarImagen';

/**
 * Hook que encapsula el estado de la vista Perfil.
 * La vista solo consume el resultado; no conoce el servicio.
 *
 * @returns {{
 *   usuario: import('../types/perfil').Perfil|null,
 *   publicaciones: import('../types/perfil').PublicacionPerfilItem[],
 *   totalPublicaciones: number,
 *   loading: boolean,
 *   error: string|null,
 *   fotoError: string|null,
 *   savingFoto: boolean,
 *   subirFoto: (archivo: File) => Promise<boolean>,
 *   eliminarFoto: () => Promise<boolean>,
 *   guardarPerfil: (datos: import('../types/perfil').PerfilEditable) => Promise<{ ok: boolean, error?: string }>,
 *   reload: () => Promise<void>,
 * }}
 */
export default function usePerfil() {
  const [usuario, setUsuario] = useState(null);
  const [publicaciones, setPublicaciones] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [fotoError, setFotoError] = useState(null);
  const [savingFoto, setSavingFoto] = useState(false);

  const reload = useCallback(async () => {
    setLoading(true);
    setError(null);
    try {
      const data = await getPerfil();
      setUsuario(data.usuario);
      setPublicaciones(data.publicaciones);
    } catch (e) {
      setError(e.message || 'No pudimos cargar tu perfil.');
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    reload();
  }, [reload]);

  /** Valida el archivo en el front y, si es una imagen válida, lo sube. */
  const subirFoto = useCallback(async (archivo) => {
    const mensaje = validarImagen(archivo);
    if (mensaje) {
      setFotoError(mensaje);
      return false;
    }
    setSavingFoto(true);
    setFotoError(null);
    try {
      const res = await subirFotoPerfil(archivo);
      setUsuario(res.usuario);
      return true;
    } catch (e) {
      setFotoError(e.message || 'No pudimos subir la foto. Intentá de nuevo.');
      return false;
    } finally {
      setSavingFoto(false);
    }
  }, []);

  const eliminarFoto = useCallback(async () => {
    setSavingFoto(true);
    setFotoError(null);
    try {
      const res = await eliminarFotoPerfil();
      setUsuario(res.usuario);
      return true;
    } catch (e) {
      setFotoError(e.message || 'No pudimos eliminar la foto. Intentá de nuevo.');
      return false;
    } finally {
      setSavingFoto(false);
    }
  }, []);

  const guardarPerfil = useCallback(async (datos) => {
    try {
      const res = await actualizarPerfil(datos);
      setUsuario(res.usuario);
      return { ok: true };
    } catch (e) {
      return { ok: false, error: e.message || 'No pudimos guardar los cambios. Intentá de nuevo.' };
    }
  }, []);

  return {
    usuario,
    publicaciones,
    totalPublicaciones: publicaciones.length,
    loading,
    error,
    fotoError,
    savingFoto,
    subirFoto,
    eliminarFoto,
    guardarPerfil,
    reload,
  };
}
