/**
 * Servicio de Wishlist — IMPLEMENTACIÓN MOCK.
 * Debe devolver la misma forma que wishlistService.api.js (ver src/types/wishlist.js).
 */
import corollaImg from '../assets/images/CorollaEjemplo.jpg';
import autoImg1 from '../assets/images/auto.jpg';
import autoImg2 from '../assets/images/Auto2.jpg';
import { PUBLICACION_DETALLE_MOCK } from '../utils/mockPublicaciones';
import { MONEDA_DEFAULT } from '../types/wishlist';

const MOCK_DELAY_MS = 400;

/** Simula la latencia de red. */
const delay = (ms = MOCK_DELAY_MS) => new Promise((resolve) => setTimeout(resolve, ms));

/** @type {import('../types/wishlist').WishlistItem[]} Publicaciones que "existen" en el backend simulado. */
const CATALOGO_MOCK = [
  {
    id: PUBLICACION_DETALLE_MOCK.id,
    titulo: `${PUBLICACION_DETALLE_MOCK.titulo} - ${PUBLICACION_DETALLE_MOCK.anio}`,
    estado: 'Usado',
    kilometraje: PUBLICACION_DETALLE_MOCK.kms,
    combustible: 'NAFTA',
    imagenUrl: PUBLICACION_DETALLE_MOCK.imagenes[0],
    precio: PUBLICACION_DETALLE_MOCK.precio,
  },
  {
    id: 2,
    titulo: 'Toyota Corolla Negro - 2023',
    estado: 'Usado',
    kilometraje: 10000,
    combustible: 'NAFTA',
    imagenUrl: corollaImg,
    precio: 32500000,
  },
  {
    id: 3,
    titulo: 'Volkswagen Polo Highline - 2024',
    estado: 'Nuevo',
    kilometraje: 0,
    combustible: 'NAFTA',
    imagenUrl: autoImg1,
    precio: 28900000,
  },
  {
    id: 4,
    titulo: 'Toyota Hilux SRV - 2021',
    estado: 'Usado',
    kilometraje: 54000,
    combustible: 'DIESEL',
    imagenUrl: autoImg2,
    precio: 41200000,
  },
];

/** IDs guardados al iniciar (simula lo que ya tiene el usuario en la DB). */
let idsGuardados = [2, 3, 4];

const buscarEnCatalogo = (id) => CATALOGO_MOCK.find((p) => String(p.id) === String(id));

/** Arma la respuesta completa (ítems + resumen) a partir del estado mock. */
const armarRespuesta = () => {
  const items = idsGuardados.map(buscarEnCatalogo).filter(Boolean).map((p) => ({ ...p }));
  return {
    items,
    resumen: {
      items: items.map(({ id, titulo, precio }) => ({ id, titulo, precio })),
      total: items.reduce((acc, it) => acc + it.precio, 0),
      moneda: MONEDA_DEFAULT,
    },
  };
};

/**
 * Devuelve la wishlist completa del usuario.
 * @returns {Promise<import('../types/wishlist').WishlistResponse>}
 */
export async function getWishlistItems() {
  await delay();
  return armarRespuesta();
}

/**
 * Agrega una publicación a la wishlist.
 * @param {number|string} itemId
 * @returns {Promise<import('../types/wishlist').WishlistMutationResponse>}
 */
export async function addToWishlist(itemId) {
  await delay();
  if (!buscarEnCatalogo(itemId)) {
    throw new Error('La publicación no existe.');
  }
  if (!idsGuardados.some((id) => String(id) === String(itemId))) {
    idsGuardados = [...idsGuardados, itemId];
  }
  return { ok: true, itemId, wishlist: armarRespuesta() };
}

/**
 * Elimina una publicación de la wishlist.
 * @param {number|string} itemId
 * @returns {Promise<import('../types/wishlist').WishlistMutationResponse>}
 */
export async function removeFromWishlist(itemId) {
  await delay();
  idsGuardados = idsGuardados.filter((id) => String(id) !== String(itemId));
  return { ok: true, itemId, wishlist: armarRespuesta() };
}
