/**
 * Servicio de Wishlist — IMPLEMENTACIÓN REAL (backend). Formas en src/types/wishlist.js.
 *
 *   GET    /wishlist       → WishlistResponse
 *   POST   /wishlist       → WishlistMutationResponse   JSON { itemId }
 *   DELETE /wishlist/:id   → WishlistMutationResponse
 *
 * Si el backend usa otros nombres de campo o rutas, se ajusta SOLO en este archivo.
 */
import { request, haySesion } from './api';

const WISHLIST_VACIA = { items: [], resumen: { items: [], total: 0, moneda: 'ARS' } };

/**
 * Sin sesión la wishlist está vacía (así el detalle de una publicación no muestra un error a los invitados).
 * @returns {Promise<import('../types/wishlist').WishlistResponse>}
 */
export const getWishlistItems = () => (haySesion() ? request('/wishlist') : Promise.resolve(WISHLIST_VACIA));

/**
 * @param {number|string} itemId
 * @returns {Promise<import('../types/wishlist').WishlistMutationResponse>}
 */
export const addToWishlist = (itemId) => request('/wishlist', { method: 'POST', json: { itemId } });

/**
 * @param {number|string} itemId
 * @returns {Promise<import('../types/wishlist').WishlistMutationResponse>}
 */
export const removeFromWishlist = (itemId) => request(`/wishlist/${itemId}`, { method: 'DELETE' });
