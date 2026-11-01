import { useContext } from 'react';
import { AuthContext } from '../context/sesionContext';

/** Sesión actual: { usuario, cargando, login, registrar, logout, refrescar }. Requiere <AuthProvider>. */
export default function useAuth() {
  const contexto = useContext(AuthContext);
  if (!contexto) throw new Error('useAuth debe usarse dentro de <AuthProvider>.');
  return contexto;
}
