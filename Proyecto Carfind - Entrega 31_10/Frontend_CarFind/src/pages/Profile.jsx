import React, { useRef, useState } from 'react';
import { Link } from 'react-router-dom';
import PublicacionPerfil from '../components/PublicacionPerfil';
import AccionesPublicacion from '../components/AccionesPublicacion';
import FotoPerfilModal from '../components/FotoPerfilModal';
import EditarPerfilModal from '../components/EditarPerfilModal';
import SoporteBox from '../components/SoporteBox';
import Logo from '../components/Logo';
import usePerfil from '../hooks/usePerfil';
import '../assets/styles/App.css';

/** Fecha en formato local; se fuerza UTC para que "2025-03-14" no se corra un día. */
const formatearFecha = (iso) =>
  new Date(iso).toLocaleDateString('es-AR', { day: '2-digit', month: '2-digit', year: 'numeric', timeZone: 'UTC' });

function Profile() {
  const {
    usuario, publicaciones, totalPublicaciones, loading, error,
    fotoError, savingFoto, subirFoto, eliminarFoto, guardarPerfil, reload,
  } = usePerfil();

  const [fotoModalAbierto, setFotoModalAbierto] = useState(false);
  const [editarAbierto, setEditarAbierto] = useState(false);
  const inputFotoRef = useRef(null);

  // Abre el explorador de archivos (el clic viene de un gesto del usuario, así que el navegador lo permite).
  const handleSubir = () => {
    setFotoModalAbierto(false);
    inputFotoRef.current?.click();
  };

  const handleArchivoElegido = (e) => {
    const archivo = e.target.files?.[0];
    e.target.value = ''; // permite volver a elegir el mismo archivo
    if (archivo) subirFoto(archivo);
  };

  const handleEliminar = () => {
    setFotoModalAbierto(false);
    eliminarFoto();
  };

  return (
    <>
      <main className="perfil-container">
        {/* Panel lateral izquierdo: info del usuario */}
        <aside className="perfil-sidebar">
          {loading && !usuario ? (
            <p className="perfil-status">Cargando perfil...</p>
          ) : usuario ? (
            <>
              <button
                type="button"
                className="perfil-foto-btn"
                onClick={() => setFotoModalAbierto(true)}
                disabled={savingFoto}
                aria-label="Cambiar foto de perfil"
                title="Cambiar foto de perfil"
              >
                {usuario.fotoUrl ? <img src={usuario.fotoUrl} alt="Foto de perfil" /> : <span aria-hidden="true">👤</span>}
              </button>
              {savingFoto && <p className="perfil-status">Actualizando foto...</p>}
              {fotoError && <p className="perfil-error" role="alert">{fotoError}</p>}

              <input
                ref={inputFotoRef}
                type="file"
                accept="image/*,.heic,.heif"
                className="perfil-input-oculto"
                onChange={handleArchivoElegido}
                tabIndex={-1}
                aria-hidden="true"
              />

              <h1 className="perfil-nombre">{usuario.nombre} {usuario.apellido}</h1>

              <dl className="perfil-datos">
                <div className="perfil-dato"><dt>Ubicación</dt><dd>{usuario.ubicacion}</dd></div>
                <div className="perfil-dato"><dt>Reputación</dt><dd>{usuario.reputacion}</dd></div>
                <div className="perfil-dato"><dt>Miembro desde</dt><dd>{formatearFecha(usuario.fechaCreacion)}</dd></div>
                <div className="perfil-dato"><dt>Teléfono</dt><dd>{usuario.telefono}</dd></div>
                <div className="perfil-dato"><dt>Publicaciones</dt><dd>{totalPublicaciones}</dd></div>
              </dl>

              <section className="perfil-config">
                <h2 className="perfil-config-titulo">Configuración</h2>
                <button type="button" className="btn-perfil" onClick={() => setEditarAbierto(true)}>
                  Editar perfil
                </button>
                <Link to="/configuracion" className="btn-perfil btn-perfil--ghost">
                  Ajustes de la cuenta
                </Link>
              </section>
            </>
          ) : null}

          <Logo className="perfil-logo" />
        </aside>

        {/* Área principal */}
        <section className="perfil-publicaciones">
          <h2 className="perfil-heading">Mis Publicaciones</h2>

          {error && (
            <div className="perfil-error" role="alert">
              {error} <button type="button" className="btn-support" onClick={reload}>Reintentar</button>
            </div>
          )}

          {loading ? (
            <p className="perfil-status">Cargando publicaciones...</p>
          ) : publicaciones.length === 0 && !error ? (
            <p className="perfil-status">Todavía no tenés publicaciones.</p>
          ) : (
            publicaciones.map((pub) => (
              <PublicacionPerfil
                key={pub.id}
                publicacion={pub}
                acciones={<AccionesPublicacion publicacion={pub} onCambio={reload} />}
              />
            ))
          )}
        </section>

        {/* Columna derecha (tercera sección) */}
        <div className="perfil-side">
          <Link to="/perfil" className="btn-perfil perfil-btn-mi-perfil">Mi Perfil</Link>
          <SoporteBox />
        </div>
      </main>

      {fotoModalAbierto && usuario && (
        <FotoPerfilModal
          tieneFoto={Boolean(usuario.fotoUrl)}
          onSubir={handleSubir}
          onEliminar={handleEliminar}
          onCerrar={() => setFotoModalAbierto(false)}
        />
      )}

      {editarAbierto && usuario && (
        <EditarPerfilModal
          usuario={usuario}
          onGuardar={guardarPerfil}
          onCerrar={() => setEditarAbierto(false)}
        />
      )}
    </>
  );
}

export default Profile;
