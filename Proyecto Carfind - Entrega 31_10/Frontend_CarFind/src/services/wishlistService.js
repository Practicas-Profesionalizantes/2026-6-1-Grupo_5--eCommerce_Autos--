/**
 * Punto de entrada del servicio de Wishlist.
 * Según VITE_USE_MOCK usa wishlistService.mock.js o wishlistService.api.js.
 */
import * as mock from './wishlistService.mock';
import * as api from './wishlistService.api';

const usarMock = import.meta.env.VITE_USE_MOCK !== 'false';

export const { getWishlistItems, addToWishlist, removeFromWishlist } = usarMock ? mock : api;
