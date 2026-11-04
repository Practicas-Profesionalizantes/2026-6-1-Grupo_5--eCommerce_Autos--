import React from 'react';
import WishlistItemCard from '../components/WishlistItemCard';
import ResumenCompra from '../components/ResumenCompra';
import SoporteBox from '../components/SoporteBox';
import useWishlist from '../hooks/useWishlist';
import '../assets/styles/App.css';

function Wishlist() {
  const { items, resumen, loading, error, pendingId, remove, reload } = useWishlist();

  return (
    <>
      <main className="wishlist-container">
        <section className="wishlist-list">
          <h1 className="wishlist-heading">Mi lista de deseos</h1>

          {error && (
            <div className="wishlist-error" role="alert">
              {error} <button type="button" className="btn-support" onClick={reload}>Reintentar</button>
            </div>
          )}

          {loading ? (
            <p className="wishlist-status">Cargando publicaciones guardadas...</p>
          ) : items.length === 0 ? (
            <p className="wishlist-status">Aún no guardaste ninguna publicación.</p>
          ) : (
            items.map((item) => (
              <WishlistItemCard
                key={item.id}
                item={item}
                onRemove={remove}
                removing={pendingId === item.id}
              />
            ))
          )}
        </section>

        <div className="wishlist-side">
          <ResumenCompra items={resumen.items} total={resumen.total} />
          <SoporteBox />
        </div>
      </main>
    </>
  );
}

export default Wishlist;
