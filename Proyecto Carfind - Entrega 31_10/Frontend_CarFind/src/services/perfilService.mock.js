/**
 * Servicio de Perfil — IMPLEMENTACIÓN MOCK.
 *
 * Simula al usuario logueado con un usuario fijo de prueba. Se usa mientras el
 * backend no esté listo (ver el interruptor VITE_USE_MOCK en perfilService.js).
 * Debe devolver la misma forma que perfilService.api.js (ver src/types/perfil.js).
 */
import corollaImg from '../assets/images/CorollaEjemplo.jpg';
import autoImg1 from '../assets/images/auto.jpg';
import autoImg2 from '../assets/images/Auto2.jpg';

const MOCK_DELAY_MS = 400;

/** Simula la latencia de red. */
const delay = (ms = MOCK_DELAY_MS) => new Promise((resolve) => setTimeout(resolve, ms));

/** @type {import('../types/perfil').Perfil} Usuario logueado "en la DB" simulada. */
let usuarioMock = {
  id: 1,
  nombre: 'Martina',
  apellido: 'Gómez',
  ubicacion: 'Buenos Aires, Argentina',
  reputacion: 4.8,
  fechaCreacion: '2025-03-14',
  telefono: '+54 9 11 5555-1234',
  fotoUrl: null,
};

/** @type {import('../types/perfil').PublicacionPerfilItem[]} */
const publicacionesMock = [
  { id: 2, titulo: 'Toyota Corolla Negro - 2023', imagenUrl: corollaImg, disponible: true, likes: 24, comentarios: 6, compartidos: 3 },
  { id: 3, titulo: 'Volkswagen Polo Highline - 2024', imagenUrl: autoImg1, disponible: true, likes: 11, comentarios: 2, compartidos: 1 },
  { id: 4, titulo: 'Toyota Hilux SRV - 2021', imagenUrl: autoImg2, disponible: false, likes: 40, comentarios: 14, compartidos: 9 },
];

/** Libera la URL temporal de una foto subida en esta sesión (solo existe en el mock). */
const liberarFotoTemporal = (url) => {
  if (url && url.startsWith('blob:')) URL.revokeObjectURL(url);
};

/**
 * Devuelve el usuario logueado y sus publicaciones.
 * @returns {Promise<import('../types/perfil').PerfilResponse>}
 */
export async function getPerfil() {
  await delay();
  return {
    usuario: { ...usuarioMock },
    publicaciones: publicacionesMock.map((p) => ({ ...p })),
  };
}

/**
 * Sube la foto de perfil.
 * @param {File} archivo
 * @returns {Promise<{ ok: boolean, usuario: import('../types/perfil').Perfil }>}
 */
export async function subirFotoPerfil(archivo) {
  await delay();
  liberarFotoTemporal(usuarioMock.fotoUrl);
  usuarioMock = { ...usuarioMock, fotoUrl: URL.createObjectURL(archivo) };
  return { ok: true, usuario: { ...usuarioMock } };
}

/**
 * Elimina la foto de perfil.
 * @returns {Promise<{ ok: boolean, usuario: import('../types/perfil').Perfil }>}
 */
export async function eliminarFotoPerfil() {
  await delay();
  liberarFotoTemporal(usuarioMock.fotoUrl);
  usuarioMock = { ...usuarioMock, fotoUrl: null };
  return { ok: true, usuario: { ...usuarioMock } };
}

/**
 * Actualiza los datos editables del perfil ("Editar perfil").
 * @param {import('../types/perfil').PerfilEditable} datos
 * @returns {Promise<{ ok: boolean, usuario: import('../types/perfil').Perfil }>}
 */
export async function actualizarPerfil(datos) {
  await delay();
  usuarioMock = { ...usuarioMock, ...datos };
  return { ok: true, usuario: { ...usuarioMock } };
}
