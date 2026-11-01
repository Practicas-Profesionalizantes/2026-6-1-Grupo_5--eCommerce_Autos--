import { createContext } from 'react';

/**
 * Sesión del usuario. El valor lo provee <AuthProvider> (context/AuthContext.jsx) y se lee con useAuth().
 * @type {import('react').Context<null | {
 *   usuario: import('../services/authService').UsuarioSesion|null,
 *   cargando: boolean,
 *   login: (datos: { email: string, password: string }) => Promise<void>,
 *   registrar: (datos: { dni: string, email: string, nombre: string, telefono: string, password: string }) => Promise<void>,
 *   logout: () => Promise<void>,
 *   refrescar: () => Promise<void>,
 * }>}
 */
export const AuthContext = createContext(null);
