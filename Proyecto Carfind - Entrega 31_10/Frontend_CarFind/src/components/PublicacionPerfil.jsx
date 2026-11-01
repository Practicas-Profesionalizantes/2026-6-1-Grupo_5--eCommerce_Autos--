import React from 'react';
import { Link } from 'react-router-dom';
import { imagenAuto, usarImagenGenerica } from '../utils/imagenAuto';

/**
 * Tarjeta de una publicación del usuario en su perfil.
 * Se renderiza una vez por cada publicación asociada al usuario.
 * @param {{ publicacion: import('../types/perfil').PublicacionPerfilItem, acciones?: React.ReactNode }} props
 *   acciones: botones extra (en "Mis publicaciones": Editar, Pausar, Eliminar)
 */
function PublicacionPerfil({ publicacion, acciones = null }) {
  const { id, titulo, imagenUrl, disponible, estado, comentarios, compartidos } = publicacion;
  const pausada = estado === 'PAUSADA';

  return (
    <article className={`perfil-card${pausada ? ' perfil-card--pausada' : ''}`}>
      <Link to={`/publicacion/${id}`} className="perfil-card-image">
        <img src={imagenAuto(imagenUrl)} alt={titulo} onError={usarImagenGenerica} />
      </Link>

      <div className="perfil-card-body">
        <h3 className="perfil-card-title">{titulo}</h3>

        {pausada ? (
          <span className="perfil-badge perfil-badge--pausa">Pausada: no la ven los demás</span>
        ) : (
          <span className={`perfil-badge ${disponible ? 'perfil-badge--si' : 'perfil-badge--no'}`}>
            Disponible: {disponible ? 'Sí' : 'No'}
          </span>
        )}

        <ul className="perfil-interacciones">
          <li title="Comentarios" aria-label={`${comentarios} comentarios`}>💬 {comentarios}</li>
          <li title="Compartidos" aria-label={`${compartidos} compartidos`}>🔗 {compartidos}</li>
        </ul>

        {acciones}
      </div>
    </article>
  );
}

export default PublicacionPerfil;
