/**
 * Servicio de Publicaciones — IMPLEMENTACIÓN MOCK.
 * Debe devolver la misma forma que publicacionService.api.js.
 */
import { PUBLICACION_DETALLE_MOCK, PUBLICACIONES_HOME_MOCK } from '../utils/mockPublicaciones';

const MOCK_DELAY_MS = 300;

/** Simula la latencia de red. */
const delay = (ms = MOCK_DELAY_MS) => new Promise((resolve) => setTimeout(resolve, ms));

/**
 * Devuelve el detalle de una publicación.
 * El mock devuelve siempre el mismo vehículo de ejemplo, pero con el id pedido.
 * @param {number|string} id
 * @returns {Promise<typeof PUBLICACION_DETALLE_MOCK>}
 */
export async function getPublicacion(id) {
  await delay();
  const numero = Number(id);
  if (!Number.isInteger(numero) || numero <= 0) {
    throw new Error('La publicación no existe.');
  }
  return { ...PUBLICACION_DETALLE_MOCK, id: numero };
}

/**
 * Resumen de una publicación (foto, título, disponibilidad e interacciones).
 * Lo usa la vista "Reportar publicación" para mostrar la tarjeta de lo que se reporta.
 * Contrato esperado: GET /publicaciones/:id/resumen → PublicacionPerfilItem (ver types/perfil.js)
 * @param {number|string} id
 * @returns {Promise<import('../types/perfil').PublicacionPerfilItem>}
 */
export async function getResumenPublicacion(id) {
  await delay();
  const numero = Number(id);
  if (!Number.isInteger(numero) || numero <= 0) {
    throw new Error('La publicación no existe.');
  }
  return {
    id: numero,
    titulo: PUBLICACION_DETALLE_MOCK.titulo,
    imagenUrl: PUBLICACION_DETALLE_MOCK.imagenes[0],
    disponible: true,
    likes: 24,
    comentarios: 6,
    compartidos: 3,
  };
}

/**
 * Tarjetas del inicio. El mock ignora los filtros.
 * @returns {Promise<{ id: number, titulo: string, precio: number, moneda?: string, imagen?: string|null, vendedor: string }[]>}
 */
export async function getPublicaciones() {
  await delay();
  return PUBLICACIONES_HOME_MOCK.map((p) => ({ ...p }));
}

/** @returns {Promise<{ marca: string[], modelo: string[], anio: number[] }>} */
export async function getOpcionesFiltros() {
  await delay();
  return { marca: ['Toyota'], modelo: ['Corolla'], anio: [2017] };
}

/** En el mock la publicación no se guarda: solo se simula el alta. */
export async function crearPublicacion() {
  await delay(600);
  return { id: PUBLICACION_DETALLE_MOCK.id };
}

/** Datos de ejemplo para el formulario de edición. */
export async function getPublicacionParaEditar(id) {
  await delay();
  const c = PUBLICACION_DETALLE_MOCK.caracteristicas;
  return {
    id: Number(id), estadoPublicacion: 'ACTIVA', precio: String(PUBLICACION_DETALLE_MOCK.precio),
    marca: c.marca, modelo: c.modelo, version: c.versionn, anio: String(c.anio), estado: 'Sin detalles',
    descripcionDetalles: '', color: c.color, papelesAlDia: 'Si', papelesFaltantes: '', kilometraje: String(c.kilometraje),
    transmision: c.transmision, combustible: c.combustible, caracteristicasAdicionales: '', descripcionLibre: '',
    fotos: [...PUBLICACION_DETALLE_MOCK.imagenes],
  };
}

/** En el mock los cambios no se guardan: solo se simulan. */
export async function actualizarPublicacion() {
  await delay(600);
  return { ok: true };
}

export async function cambiarEstadoPublicacion(id, estado) {
  await delay();
  return { ok: true, estado };
}

export async function eliminarPublicacion() {
  await delay();
  return { ok: true };
}

