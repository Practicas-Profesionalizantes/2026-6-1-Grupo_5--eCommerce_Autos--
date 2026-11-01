import React from 'react';
import { Link, useParams } from 'react-router-dom';
import PublicacionPerfil from '../components/PublicacionPerfil';
import Estrellas from '../components/Estrellas';
import SoporteBox from '../components/SoporteBox';
import Logo from '../components/Logo';
import usePerfilPublico from '../hooks/usePerfilPublico';
import { formatearFechaCorta } from '../utils/formatearFecha';
import '../assets/styles/App.css';

function PerfilAjenoContenido({ id }) {
  const { usuario, publicaciones, totalPublicaciones, loading, error, reload } = usePerfilPublico(id);

  return (
    <main className="perfil-container">
      {/* Panel lateral izquierdo: datos públicos del usuario */}
      <aside className="perfil-sidebar">
        <Link to="/" className="reporte-volver perfil-ajeno-volver">← Inicio</Link>

        {loading ? (
          <p className="perfil-status">Cargando perfil...</p>
        ) : error || !usuario ? (
          <div className="perfil-error" role="alert">
            {error} <button type="button" className="btn-support" onClick={reload}>Reintentar</button>
          </div>
        ) : (
          <>
            <div className="perfil-foto-btn perfil-foto--estatica">
              {usuario.fotoUrl ? <img src={usuario.fotoUrl} alt={`Foto de ${usuario.nombre}`} /> : <span aria-hidden="true">👤</span>}
            </div>

            <h1 className="perfil-nombre">{usuario.nombre} {usuario.apellido}</h1>

            <dl className="perfil-datos">
              <div className="perfil-dato"><dt>Nombre</dt><dd>{usuario.nombre}</dd></div>
              <div className="perfil-dato"><dt>Apellido</dt><dd>{usuario.apellido}</dd></div>
              <div className="perfil-dato"><dt>Ubicación</dt><dd>{usuario.ubicacion}</dd></div>
              <div className="perfil-dato"><dt>Fecha de creación</dt><dd>{formatearFechaCorta(usuario.fechaCreacion)}</dd></div>
              <div className="perfil-dato"><dt>Teléfono</dt><dd>{usuario.telefono}</dd></div>
              <div className="perfil-dato"><dt>Publicaciones</dt><dd>{totalPublicaciones}</dd></div>
              <div className="perfil-dato"><dt>Valoración</dt><dd><Estrellas valor={usuario.reputacion} /></dd></div>
            </dl>

            <p className="perfil-ajeno-reportar">
              ¿Te parece sospechoso?{' '}
              <Link
                to="/reportes"
                state={{ usuarioReportado: { id: usuario.id, nombre: `${usuario.nombre} ${usuario.apellido}` } }}
              >
                <strong>Reportar</strong>
              </Link>
            </p>
          </>
        )}

        <Logo className="perfil-logo" />
      </aside>

      {/* Área principal */}
      <section className="perfil-publicaciones">
        <h2 className="perfil-heading">Publicaciones</h2>

        {loading ? (
          <p className="perfil-status">Cargando publicaciones...</p>
        ) : usuario && publicaciones.length === 0 ? (
          <p className="perfil-status">Este usuario todavía no tiene publicaciones.</p>
        ) : (
          <div className="perfil-publicaciones-grilla">
            {publicaciones.map((pub) => <PublicacionPerfil key={pub.id} publicacion={pub} />)}
          </div>
        )}
      </section>

      {/* Columna derecha */}
      <div className="perfil-side">
        <Link to="/perfil" className="btn-perfil perfil-btn-mi-perfil">Perfil</Link>
        <SoporteBox />
      </div>
    </main>
  );
}

/** El `key` por id hace que al pasar de un perfil a otro la pantalla arranque limpia. */
function PerfilAjeno() {
  const { id } = useParams();
  return <PerfilAjenoContenido key={id} id={id} />;
}

export default PerfilAjeno;
