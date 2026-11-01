import { useCallback, useEffect, useMemo, useState } from 'react';
import { AuthContext } from './sesionContext';
import * as authService from '../services/authService';
import { haySesion } from '../services/api';

/**
 * Mantiene el usuario logueado para toda la app (Header, Crear publicación, etc.).
 * Al cargar la página valida con el backend el token guardado.
 */
export function AuthProvider({ children }) {
  const [usuario, setUsuario] = useState(null);
  const [cargando, setCargando] = useState(haySesion);

  const refrescar = useCallback(
    () =>
      authService
        .getSesion()
        .then(setUsuario)
        .catch(() => setUsuario(null)) // token vencido o backend caído: se muestra como invitado
        .finally(() => setCargando(false)),
    []
  );

  useEffect(() => {
    if (haySesion()) refrescar();
  }, [refrescar]);

  const login = useCallback(async (datos) => setUsuario(await authService.login(datos)), []);
  const registrar = useCallback(async (datos) => setUsuario(await authService.registrar(datos)), []);
  const logout = useCallback(async () => {
    try {
      await authService.logout();
    } finally {
      setUsuario(null);
    }
  }, []);

  const valor = useMemo(
    () => ({ usuario, cargando, login, registrar, logout, refrescar }),
    [usuario, cargando, login, registrar, logout, refrescar]
  );

  return <AuthContext.Provider value={valor}>{children}</AuthContext.Provider>;
}

export default AuthProvider;
