import React from 'react';
import { Link, NavLink, Outlet } from 'react-router-dom';
import Logo from '../../components/Logo';
import useAdminSesion from '../../hooks/useAdminSesion';
import '../../assets/styles/App.css';

/**
 * Marco del panel de administración: menú lateral + panel.
 * Usa los mismos estilos de estructura que Configuración (config-container, config-sidebar, etc.).
 * Antes de mostrar nada verifica con el backend que el usuario sea administrador.
 */
function AdminLayout() {
  const { admin, loading, sinPermiso, error, reload } = useAdminSesion();

  if (loading) {
    return <main className="admin-estado"><p className="perfil-status">Verificando acceso...</p></main>;
  }

  if (sinPermiso) {
    return (
      <main className="admin-estado">
        <h1>Acceso restringido</h1>
        <p>Esta sección es solo para administradores.</p>
        <Link to="/" className="btn-perfil">Volver al inicio</Link>
      </main>
    );
  }

  if (error || !admin) {
    return (
      <main className="admin-estado">
        <div className="perfil-error" role="alert">
          {error} <button type="button" className="btn-support" onClick={reload}>Reintentar</button>
        </div>
      </main>
    );
  }

  return (
    <main className="config-container">
      <aside className="config-sidebar">
        <Link to="/" className="reporte-volver">← Volver al sitio</Link>

        <div className="config-usuario">
          <span className="config-avatar" aria-hidden="true">
            {admin.fotoUrl ? <img src={admin.fotoUrl} alt="" /> : '👤'}
          </span>
          <span className="config-usuario-nombre">
            {admin.nombre} {admin.apellido}
            <small className="admin-rol">Administrador</small>
          </span>
        </div>

        <nav aria-label="Panel de administración">
          <ul className="config-menu">
            <li>
              <NavLink to="/admin/estadisticas" className={({ isActive }) => `config-menu-item${isActive ? ' config-menu-item--activo' : ''}`}>
                Estadísticas
              </NavLink>
            </li>
            <li>
              <NavLink to="/admin/reportes" className={({ isActive }) => `config-menu-item${isActive ? ' config-menu-item--activo' : ''}`}>
                Reportes
              </NavLink>
            </li>
            <li>
              <NavLink to="/configuracion" className="config-menu-item">Configuración</NavLink>
            </li>
          </ul>
        </nav>

        <Logo className="perfil-logo" />
      </aside>

      <section className="config-panel">
        <Outlet context={{ admin }} />
      </section>
    </main>
  );
}

export default AdminLayout;
