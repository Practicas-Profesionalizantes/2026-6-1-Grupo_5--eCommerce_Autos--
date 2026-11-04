import { useState, useEffect, useCallback, useMemo } from 'react';
import { getWishlistItems, addToWishlist, removeFromWishlist } from '../services/wishlistService';

/**
 * Hook que encapsula el estado de la wishlist.
 * La vista solo consume { items, total, loading, error, add, remove, isInWishlist }.
 *
 * @returns {{
 *   items: import('../types/wishlist').WishlistItem[],
 *   resumen: import('../types/wishlist').WishlistResumen,
 *   total: number,
 *   loading: boolean,
 *   error: string|null,
 *   pendingId: number|string|null,
 *   add: (id: number|string) => Promise<boolean>,
 *   remove: (id: number|string) => Promise<boolean>,
 *   isInWishlist: (id: number|string) => boolean,
 *   reload: () => Promise<void>,
 * }}
 */
export default function useWishlist() {
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [pendingId, setPendingId] = useState(null); // ítem con una operación en curso

  const reload = useCallback(async () => {
    setLoading(true);
    setError(null);
    try {
      const data = await getWishlistItems();
      setItems(data.items);
    } catch (e) {
      setError(e.message || 'No pudimos cargar tu lista de deseos.');
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    reload();
  }, [reload]);

  const mutate = useCallback(async (fn, id) => {
    setPendingId(id);
    setError(null);
    try {
      const res = await fn(id);
      setItems(res.wishlist.items);
      return true;
    } catch (e) {
      setError(e.message || 'Ocurrió un error. Intentá de nuevo.');
      return false;
    } finally {
      setPendingId(null);
    }
  }, []);

  const add = useCallback((id) => mutate(addToWishlist, id), [mutate]);
  const remove = useCallback((id) => mutate(removeFromWishlist, id), [mutate]);

  const isInWishlist = useCallback(
    (id) => items.some((it) => String(it.id) === String(id)),
    [items]
  );

  // Total calculado en el front: suma de todos los precios listados.
  const resumen = useMemo(
    () => ({
      items: items.map(({ id, titulo, precio }) => ({ id, titulo, precio })),
      total: items.reduce((acc, it) => acc + it.precio, 0),
      moneda: 'ARS',
    }),
    [items]
  );

  return { items, resumen, total: resumen.total, loading, error, pendingId, add, remove, isInWishlist, reload };
}
