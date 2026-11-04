import React from 'react';
import { Outlet } from 'react-router-dom';
import Header from './Header';
import useAtajosTeclado from '../hooks/useAtajosTeclado';

/**
 * Estructura común de todas las páginas: el Header se monta una sola vez
 * y se mantiene al navegar (así conserva, por ejemplo, el estado del tema).
 * Cada página se dibuja en el <Outlet />.
 */
function Layout() {
  useAtajosTeclado(); // atajos de una tecla, si el usuario los activó en Configuración > Accesibilidad

  return (
    <div className="app-wrapper">
      <Header />
      <Outlet />
    </div>
  );
}

export default Layout;
