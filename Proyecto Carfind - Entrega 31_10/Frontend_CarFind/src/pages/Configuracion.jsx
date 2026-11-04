import React from 'react';
import { NavLink, Outlet } from 'react-router-dom';
import useConfiguracion from '../hooks/useConfiguracion';
import '../assets/styles/App.css';

/** Menú lateral. Las secciones sin vista todavía (chats, almacenamiento, ayuda) muestran "próximamente". */
const SECCIONES = [
  { ruta: 'datos-personales', nombre: 'Editar datos personales' },
  { ruta: 'apariencia', nombre: 'Apariencia' },
  { ruta: 'accesibilidad', nombre: 'Accesibilidad' },
  { ruta: 'chats', nombre: 'Chats' },
  { ruta: 'notificaciones', nombre: 'Notificaciones' },
  { ruta: 'idioma', nombre: 'Idioma de la aplicación' },
  { ruta: 'ayuda', nombre: 'Ayuda y comentarios' },
];

/**
 * Contenedor de todas las vistas de Configuración (menú lateral + panel).
 * Carga los datos una sola vez y se los pasa a cada vista por el contexto del <Outlet />
 * (se leen con useOutletContext).
 */
function Configuracion() {
  const configuracion = useConfiguracion();
  const { config, loading, error, errorGuardado, reload } = configuracion;
  const datos = config?.datosPersonales;

  return (
    <main className="config-container">
      <aside className="config-sidebar">
        <div className="config-usuario">
          <span className="config-avatar" aria-hidden="true">
            {datos?.fotoUrl ? <img src={datos.fotoUrl} alt="" /> : '👤'}
          </span>
          <span className="config-usuario-nombre">
            {datos ? `${datos.nombre} ${datos.apellido}` : 'Cargando...'}
          </span>
        </div>

        <nav aria-label="Secciones de configuración">
          <ul className="config-menu">
            {SECCIONES.map(({ ruta, nombre }) => (
              <li key={ruta}>
                <NavLink to={ruta} className={({ isActive }) => `config-menu-item${isActive ? ' config-menu-item--activo' : ''}`}>
                  {nombre}
                </NavLink>
              </li>
            ))}
          </ul>
        </nav>
      </aside>

      <section className="config-panel">
        {errorGuardado && (
          <p className="perfil-error config-aviso" role="alert">
            {errorGuardado}
          </p>
        )}

        {loading && !config ? (
          <p className="perfil-status">Cargando configuración...</p>
        ) : error ? (
          <div className="perfil-error" role="alert">
            {error} <button type="button" className="btn-support" onClick={reload}>Reintentar</button>
          </div>
        ) : config ? (
          <Outlet context={configuracion} />
        ) : null}
      </section>
    </main>
  );
}

export default Configuracion;
