/**
 * Servicio de Usuarios — IMPLEMENTACIÓN MOCK.
 * Tres usuarios de prueba (ids 7, 8 y 9: los mismos que aparecen en los chats de prueba).
 * Debe devolver la misma forma que usuarioService.api.js (ver src/types/perfilPublico.js).
 */
import corollaImg from '../assets/images/CorollaEjemplo.jpg';
import autoImg1 from '../assets/images/auto.jpg';
import autoImg2 from '../assets/images/Auto2.jpg';

const MOCK_DELAY_MS = 400;

/** Simula la latencia de red. */
const delay = (ms = MOCK_DELAY_MS) => new Promise((resolve) => setTimeout(resolve, ms));

/** @type {Record<number, import('../types/perfilPublico').PerfilPublicoResponse>} */
const USUARIOS_MOCK = {
  7: {
    usuario: {
      id: 7, nombre: 'Carlos', apellido: 'Rodríguez', ubicacion: 'Córdoba, Argentina', reputacion: 4.6,
      fechaCreacion: '2024-08-02', telefono: '+54 9 351 555-0199', fotoUrl: null,
    },
    publicaciones: [
      { id: 1, titulo: 'Renault Kangoo 1.6 Authentique - 2018', imagenUrl: corollaImg, disponible: true, likes: 12, comentarios: 3, compartidos: 1 },
      { id: 5, titulo: 'Fiat Cronos 1.3 Drive - 2022', imagenUrl: autoImg1, disponible: true, likes: 31, comentarios: 8, compartidos: 4 },
      { id: 6, titulo: 'Peugeot 208 Allure - 2020', imagenUrl: autoImg2, disponible: false, likes: 19, comentarios: 5, compartidos: 2 },
      { id: 9, titulo: 'Ford Ranger XLT - 2019', imagenUrl: corollaImg, disponible: true, likes: 44, comentarios: 12, compartidos: 7 },
    ],
  },
  8: {
    usuario: {
      id: 8, nombre: 'Lucía', apellido: 'Fernández', ubicacion: 'Rosario, Argentina', reputacion: 4.9,
      fechaCreacion: '2023-11-19', telefono: '+54 9 341 555-0142', fotoUrl: null,
    },
    publicaciones: [
      { id: 2, titulo: 'Toyota Corolla Negro - 2023', imagenUrl: corollaImg, disponible: true, likes: 24, comentarios: 6, compartidos: 3 },
    ],
  },
  9: {
    usuario: {
      id: 9, nombre: 'Julián', apellido: 'Pérez', ubicacion: 'Mendoza, Argentina', reputacion: 3.8,
      fechaCreacion: '2025-06-30', telefono: '+54 9 261 555-0177', fotoUrl: null,
    },
    publicaciones: [],
  },
};

/**
 * @param {number|string} id
 * @returns {Promise<import('../types/perfilPublico').PerfilPublicoResponse>}
 */
export async function getPerfilPublico(id) {
  await delay();
  const datos = USUARIOS_MOCK[Number(id)];
  if (!datos) throw new Error('El usuario no existe.');
  return { usuario: { ...datos.usuario }, publicaciones: datos.publicaciones.map((p) => ({ ...p })) };
}
