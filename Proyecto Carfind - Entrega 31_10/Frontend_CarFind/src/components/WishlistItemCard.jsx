import React from 'react';
import { Link } from 'react-router-dom';
import { imagenAuto, usarImagenGenerica } from '../utils/imagenAuto';

/**
 * Tarjeta de una publicación guardada en la wishlist.
 * @param {{ item: import('../types/wishlist').WishlistItem, onRemove: (id: number|string) => void, removing?: boolean }} props
 */
function WishlistItemCard({ item, onRemove, removing = false }) {
  const { id, titulo, estado, kilometraje, combustible, imagenUrl } = item;

  return (
    <article className="wishlist-card">
      <Link to={`/publicacion/${id}`} className="wishlist-card-image">
        <img src={imagenAuto(imagenUrl)} alt={titulo} onError={usarImagenGenerica} />
      </Link>

      <div className="wishlist-card-body">
        <h3 className="wishlist-card-title">{titulo}</h3>
        <div className="wishlist-badges">
          <span className="wishlist-badge">{estado}</span>
          <span className="wishlist-badge">{kilometraje.toLocaleString('es-AR')} KM</span>
          <span className="wishlist-badge">{combustible}</span>
        </div>
      </div>

      <button
        type="button"
        className="btn-wishlist-remove"
        onClick={() => onRemove(id)}
        disabled={removing}
        title="Quitar de la lista"
        aria-label={`Eliminar ${titulo} de la lista de deseos`}
      >
        {removing ? '...' : 'eliminar'}
      </button>
    </article>
  );
}

export default WishlistItemCard;
