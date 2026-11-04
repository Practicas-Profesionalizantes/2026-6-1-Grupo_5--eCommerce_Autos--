import { useState, useEffect, useCallback } from 'react';
import { getPerfilPublico } from '../services/usuarioService';

/**
 * Hook de la vista Perfil Ajeno. Usar con `key={id}` en el componente que lo llama.
 * @param {number|string} id
 * @returns {{
 *   usuario: import('../types/perfilPublico').PerfilPublico|null,
 *   publicaciones: import('../types/perfil').PublicacionPerfilItem[],
 *   totalPublicaciones: number,
 *   loading: boolean,
 *   error: string|null,
 *   reload: () => Promise<void>,
 * }}
 */
export default function usePerfilPublico(id) {
  const [usuario, setUsuario] = useState(null);
  const [publicaciones, setPublicaciones] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const reload = useCallback(async () => {
    setLoading(true);
    setError(null);
    try {
      const data = await getPerfilPublico(id);
      setUsuario(data.usuario);
      setPublicaciones(data.publicaciones);
    } catch (e) {
      setError(e.message || 'No pudimos cargar el perfil.');
    } finally {
      setLoading(false);
    }
  }, [id]);

  useEffect(() => {
    reload();
  }, [reload]);

  return { usuario, publicaciones, totalPublicaciones: publicaciones.length, loading, error, reload };
}
