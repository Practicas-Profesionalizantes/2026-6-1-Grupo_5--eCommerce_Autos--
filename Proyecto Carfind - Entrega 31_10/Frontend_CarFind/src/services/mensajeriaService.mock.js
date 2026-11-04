/**
 * Servicio de Mensajería — IMPLEMENTACIÓN MOCK.
 *
 * Tres conversaciones de prueba en memoria (se reinician al recargar la página).
 * Para poder ver la pantalla "en vivo", cada mensaje que se envía recibe una respuesta
 * automática a los pocos segundos. Eso NO existe en el backend real.
 * Debe devolver la misma forma que mensajeriaService.api.js (ver src/types/mensajeria.js).
 */
import corollaImg from '../assets/images/CorollaEjemplo.jpg';
import autoImg1 from '../assets/images/auto.jpg';
import { PUBLICACION_DETALLE_MOCK } from '../utils/mockPublicaciones';

const MOCK_DELAY_MS = 350;
const RESPUESTA_AUTOMATICA_MS = 2500;

/** Simula la latencia de red. */
const delay = (ms = MOCK_DELAY_MS) => new Promise((resolve) => setTimeout(resolve, ms));

/** Fecha ISO de hace N minutos. */
const hace = (minutos) => new Date(Date.now() - minutos * 60000).toISOString();

let siguienteId = 1000;

/** @type {Omit<import('../types/mensajeria').Conversacion, 'ultimoMensaje'>[]} */
let conversaciones = [
  {
    id: 101,
    otroUsuario: { id: 7, nombre: 'Carlos', apellido: 'Rodríguez', fotoUrl: null, telefono: '+54 9 351 555-0199' },
    rolOtroUsuario: 'Vendedor',
    publicacion: {
      id: PUBLICACION_DETALLE_MOCK.id,
      titulo: PUBLICACION_DETALLE_MOCK.titulo,
      precio: PUBLICACION_DETALLE_MOCK.precio,
      moneda: PUBLICACION_DETALLE_MOCK.moneda,
      imagenUrl: PUBLICACION_DETALLE_MOCK.imagenes[0],
    },
    noLeidos: 1,
  },
  {
    id: 102,
    otroUsuario: { id: 8, nombre: 'Lucía', apellido: 'Fernández', fotoUrl: null, telefono: '+54 9 341 555-0142' },
    rolOtroUsuario: 'Vendedor',
    publicacion: { id: 2, titulo: 'Toyota Corolla Negro - 2023', precio: 32500000, moneda: 'ARS', imagenUrl: corollaImg },
    noLeidos: 0,
  },
  {
    id: 103,
    otroUsuario: { id: 9, nombre: 'Julián', apellido: 'Pérez', fotoUrl: null, telefono: '+54 9 261 555-0177' },
    rolOtroUsuario: 'Comprador',
    publicacion: { id: 3, titulo: 'Volkswagen Polo Highline - 2024', precio: 28900000, moneda: 'ARS', imagenUrl: autoImg1 },
    noLeidos: 0,
  },
];

const msg = (id, conversacionId, texto, minutos, propio) => ({
  id, conversacionId, texto, imagenUrl: null, fecha: hace(minutos), propio, estado: 'leido',
});

/** @type {Record<number, import('../types/mensajeria').Mensaje[]>} */
const mensajesPorConversacion = {
  101: [
    msg(1, 101, 'Hola, ¿sigue disponible la Kangoo?', 1500, true),
    msg(2, 101, 'Hola! Sí, sigue disponible.', 1490, false),
    msg(3, 101, '¿Tiene los papeles al día y la VTV vigente?', 1480, true),
    msg(4, 101, 'Sí, todo al día. Si querés podés venir a verla este fin de semana.', 20, false),
  ],
  102: [
    msg(5, 102, 'Buenas tardes, me interesa el Corolla. ¿Aceptan permuta?', 3000, true),
    msg(6, 102, 'Buenas! Por ahora solo vendo, no acepto permutas.', 2990, false),
    msg(7, 102, 'Perfecto, gracias por avisar.', 2985, true),
  ],
  103: [msg(8, 103, 'Hola, vi tu consulta por el Polo. ¿Cuándo podrías pasar a verlo?', 60, true)],
};

/** @type {Map<number, Set<(m: import('../types/mensajeria').Mensaje) => void>>} */
const oyentes = new Map();

const buscar = (id) => conversaciones.find((c) => String(c.id) === String(id));

/** Arma la Conversacion completa (con su último mensaje) a partir del estado mock. */
function resumen(c) {
  const lista = mensajesPorConversacion[c.id] ?? [];
  const ultimo = lista[lista.length - 1];
  return {
    ...c,
    otroUsuario: { ...c.otroUsuario },
    publicacion: { ...c.publicacion },
    ultimoMensaje: ultimo ? { texto: ultimo.texto || '📷 Foto', fecha: ultimo.fecha, propio: ultimo.propio } : null,
  };
}

/** @returns {Promise<{ conversaciones: import('../types/mensajeria').Conversacion[] }>} */
export async function getConversaciones() {
  await delay();
  const lista = conversaciones.map(resumen);
  // La más reciente primero; las que no tienen mensajes, al final.
  lista.sort((a, b) => new Date(b.ultimoMensaje?.fecha ?? 0) - new Date(a.ultimoMensaje?.fecha ?? 0));
  return { conversaciones: lista };
}

/**
 * @param {number|string} conversacionId
 * @returns {Promise<import('../types/mensajeria').ChatResponse>}
 */
export async function getMensajes(conversacionId) {
  await delay();
  const c = buscar(conversacionId);
  if (!c) throw new Error('La conversación no existe.');
  return { conversacion: resumen(c), mensajes: (mensajesPorConversacion[c.id] ?? []).map((m) => ({ ...m })) };
}

/**
 * @param {number|string} conversacionId
 * @param {{ texto?: string, imagen?: File|null }} contenido
 * @returns {Promise<{ mensaje: import('../types/mensajeria').Mensaje }>}
 */
export async function enviarMensaje(conversacionId, { texto = '', imagen = null }) {
  await delay(300);
  const c = buscar(conversacionId);
  if (!c) throw new Error('La conversación no existe.');
  if (!texto.trim() && !imagen) throw new Error('Escribí un mensaje o elegí una foto.');

  const mensaje = {
    id: siguienteId++,
    conversacionId: c.id,
    texto: texto.trim(),
    imagenUrl: imagen ? URL.createObjectURL(imagen) : null,
    fecha: new Date().toISOString(),
    propio: true,
    estado: 'entregado',
  };
  mensajesPorConversacion[c.id] = [...(mensajesPorConversacion[c.id] ?? []), mensaje];

  // Respuesta automática de prueba (solo existe en el mock).
  setTimeout(() => {
    const respuesta = {
      id: siguienteId++,
      conversacionId: c.id,
      texto: '¡Gracias por tu mensaje! Te respondo a la brevedad.',
      imagenUrl: null,
      fecha: new Date().toISOString(),
      propio: false,
      estado: 'entregado',
    };
    mensajesPorConversacion[c.id] = [...mensajesPorConversacion[c.id], respuesta];
    const escuchando = oyentes.get(c.id);
    if (escuchando?.size) escuchando.forEach((alRecibir) => alRecibir({ ...respuesta }));
    else c.noLeidos += 1; // nadie tiene el chat abierto: queda sin leer
  }, RESPUESTA_AUTOMATICA_MS);

  return { mensaje: { ...mensaje } };
}

/**
 * Crea la conversación de una publicación, o devuelve la que ya existe.
 * @param {number|string} publicacionId
 * @returns {Promise<{ conversacionId: number|string }>}
 */
export async function iniciarConversacion(publicacionId) {
  await delay();
  const numero = Number(publicacionId);
  if (!Number.isInteger(numero) || numero <= 0) throw new Error('La publicación no existe.');

  const existente = conversaciones.find((c) => c.publicacion.id === numero);
  if (existente) return { conversacionId: existente.id };

  // El mock siempre usa al publicante y los datos del vehículo de ejemplo.
  const { publicante } = PUBLICACION_DETALLE_MOCK;
  const nueva = {
    id: siguienteId++,
    otroUsuario: { id: publicante.id, nombre: publicante.nombre, apellido: publicante.apellido, fotoUrl: null, telefono: '+54 9 351 555-0199' },
    rolOtroUsuario: 'Vendedor',
    publicacion: {
      id: numero,
      titulo: PUBLICACION_DETALLE_MOCK.titulo,
      precio: PUBLICACION_DETALLE_MOCK.precio,
      moneda: PUBLICACION_DETALLE_MOCK.moneda,
      imagenUrl: PUBLICACION_DETALLE_MOCK.imagenes[0],
    },
    noLeidos: 0,
  };
  conversaciones = [...conversaciones, nueva];
  mensajesPorConversacion[nueva.id] = [];
  return { conversacionId: nueva.id };
}

/** @param {number|string} conversacionId */
export async function marcarComoLeida(conversacionId) {
  const c = buscar(conversacionId);
  if (c) c.noLeidos = 0;
  return { ok: true };
}

/**
 * @param {number|string} conversacionId
 * @param {(mensaje: import('../types/mensajeria').Mensaje) => void} alRecibir
 * @returns {() => void}
 */
export function suscribirNuevosMensajes(conversacionId, alRecibir) {
  const id = Number(conversacionId);
  if (!oyentes.has(id)) oyentes.set(id, new Set());
  oyentes.get(id).add(alRecibir);
  return () => oyentes.get(id)?.delete(alRecibir);
}
