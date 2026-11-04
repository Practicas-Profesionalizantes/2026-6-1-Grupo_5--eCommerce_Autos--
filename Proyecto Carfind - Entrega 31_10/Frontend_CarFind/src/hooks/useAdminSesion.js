import { useState, useEffect, useCallback } from 'react';
import { getSesionAdmin } from '../services/adminService';

/**
 * Verifica que el usuario logueado sea administrador.
 * @returns {{
 *   admin: import('../types/admin').Administrador|null,
 *   loading: boolean,
 *   sinPermiso: boolean,
 *   error: string|null,
 *   reload: () => Promise<void>,
 * }}  sinPermiso = el backend respondió 401/403
 */
export default function useAdminSesion() {
  const [admin, setAdmin] = useState(null);
  const [loading, setLoading] = useState(true);
  const [sinPermiso, setSinPermiso] = useState(false);
  const [error, setError] = useState(null);

  const reload = useCallback(async () => {
    setLoading(true);
    setError(null);
    setSinPermiso(false);
    try {
      const data = await getSesionAdmin();
      setAdmin(data.admin);
    } catch (e) {
      if (e.status === 401 || e.status === 403) setSinPermiso(true);
      else setError(e.message || 'No pudimos verificar tu acceso.');
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    reload();
  }, [reload]);

  return { admin, loading, sinPermiso, error, reload };
}
