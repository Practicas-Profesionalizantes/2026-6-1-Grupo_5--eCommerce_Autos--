import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import Modal from './Modal';
import Icono from './Icono';
import { cambiarEstadoPublicacion, eliminarPublicacion } from '../services/publicacionService';

/**
 * Botones para gestionar una publicación propia: Editar, Pausar / Reactivar y Eliminar (con confirmación).
 * Se usa en "Mis publicaciones" (perfil) y en el detalle de la publicación cuando es del usuario.
 * @param {{
 *   publicacion: { id: number|string, estado?: string },
 *   onCambio: (cambio: { tipo: 'estado', estado: string } | { tipo: 'eliminada' }) => void,
 * }} props
 */
function AccionesPublicacion({ publicacion, onCambio }) {
  const { id, estado } = publicacion;
  const [trabajando, setTrabajando] = useState(false);
  const [confirmarEliminar, setConfirmarEliminar] = useState(false);
  const [error, setError] = useState(null);

  const pausada = estado === 'PAUSADA';
  // Una publicación vendida o cancelada ya no se puede pausar ni reactivar
  const sePuedePausar = estado === 'ACTIVA' || pausada;

  const alternarPausa = async () => {
    setTrabajando(true);
    setError(null);
    try {
      const nuevoEstado = pausada ? 'ACTIVA' : 'PAUSADA';
      await cambiarEstadoPublicacion(id, nuevoEstado);
      onCambio({ tipo: 'estado', estado: nuevoEstado });
    } catch (e) {
      setError(e.message || 'No pudimos cambiar el estado. Intentá de nuevo.');
    } finally {
      setTrabajando(false);
    }
  };

  const eliminar = async () => {
    setTrabajando(true);
    setError(null);
    try {
      await eliminarPublicacion(id);
      setConfirmarEliminar(false);
      onCambio({ tipo: 'eliminada' });
    } catch (e) {
      setError(e.message || 'No pudimos eliminar la publicación. Intentá de nuevo.');
      setTrabajando(false);
    }
  };

  return (
    <div className="acciones-publicacion">
      <Link to={`/publicacion/${id}/editar`} className="navButton navButton-secundario">
        <Icono nombre="editar" tamano={16} /> Editar
      </Link>
      {sePuedePausar && (
        <button type="button" className="navButton navButton-fantasma" onClick={alternarPausa} disabled={trabajando}>
          <Icono nombre={pausada ? 'reanudar' : 'pausa'} tamano={16} /> {pausada ? 'Reactivar' : 'Pausar'}
        </button>
      )}
      <button
        type="button"
        className="navButton navButton-peligro"
        onClick={() => setConfirmarEliminar(true)}
        disabled={trabajando}
      >
        <Icono nombre="eliminar" tamano={16} /> Eliminar
      </button>
      {error && !confirmarEliminar && <small className="perfil-error" role="alert">{error}</small>}

      {confirmarEliminar && (
        <Modal titulo="¿Eliminar esta publicación?" onCerrar={() => !trabajando && setConfirmarEliminar(false)}>
          <p className="acciones-publicacion-aviso">
            Deja de mostrarse en CarFind y no se puede deshacer. Si solo querés ocultarla por un tiempo, usá <strong>Pausar</strong>.
          </p>
          {error && <p className="perfil-error" role="alert">{error}</p>}
          <div className="acciones-publicacion-modal">
            <button type="button" className="navButton navButton-fantasma" onClick={() => setConfirmarEliminar(false)} disabled={trabajando}>
              Cancelar
            </button>
            <button type="button" className="navButton navButton-peligro-lleno" onClick={eliminar} disabled={trabajando}>
              {trabajando ? 'Eliminando...' : 'Sí, eliminar'}
            </button>
          </div>
        </Modal>
      )}
    </div>
  );
}

export default AccionesPublicacion;
