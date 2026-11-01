/**
 * Servicio del panel de administración — IMPLEMENTACIÓN MOCK.
 * Datos de prueba en memoria (se reinician al recargar la página).
 * Debe devolver la misma forma que adminService.api.js (ver src/types/admin.js).
 */
import { TIPOS_INFORME } from '../types/admin';
import { generarCsv, generarPdfSimple } from '../utils/generarArchivosMock';
import { nombreCompleto, nombreEstadoReporte, nombreTipoReporte, tituloReporte } from '../utils/reportesAdmin';

const MOCK_DELAY_MS = 400;

/** Simula la latencia de red. */
const delay = (ms = MOCK_DELAY_MS) => new Promise((resolve) => setTimeout(resolve, ms));

/** Fecha ISO de hace N días. */
const hace = (dias) => new Date(Date.now() - dias * 86400000).toISOString();

const copiar = (valor) => structuredClone(valor);

let siguienteId = 100;

// ---------- Sesión ----------

/** @returns {Promise<{ admin: import('../types/admin').Administrador }>} */
export async function getSesionAdmin() {
  await delay(200);
  return { admin: { id: 1, nombre: 'Ana', apellido: 'Morales', fotoUrl: null } };
}

// ---------- Estadísticas ----------

const SERIE_PUBLICACIONES = [62, 70, 78, 74, 91, 104, 98, 112, 120, 118, 131, 142];
const SERIE_USUARIOS = [180, 210, 244, 260, 301, 340, 322, 368, 395, 410, 452, 487];

/** Nombres de los últimos N meses, terminando en el actual (ej: "may", "jun"). */
const etiquetasMeses = (n) =>
  Array.from({ length: n }, (_, i) => {
    const d = new Date();
    d.setDate(1);
    d.setMonth(d.getMonth() - (n - 1 - i));
    return d.toLocaleDateString('es-AR', { month: 'short' }).replace('.', '');
  });

/**
 * @param {{ meses?: number }} [opciones]
 * @returns {Promise<import('../types/admin').EstadisticasResponse>}
 */
export async function getEstadisticas({ meses = 6 } = {}) {
  await delay();
  const n = Math.min(12, Math.max(1, meses));
  return {
    resumen: { publicaciones: 1248, usuarios: 3560, precioPromedio: 24850000, conversaciones: 912 },
    masGuardados: [
      { titulo: 'Toyota Corolla 2023', cantidad: 48 },
      { titulo: 'Volkswagen Polo 2024', cantidad: 41 },
      { titulo: 'Fiat Cronos 2022', cantidad: 35 },
      { titulo: 'Renault Kangoo 2018', cantidad: 29 },
      { titulo: 'Ford Ranger 2019', cantidad: 22 },
    ],
    evolucion: {
      etiquetas: etiquetasMeses(n),
      publicaciones: SERIE_PUBLICACIONES.slice(-n),
      usuarios: SERIE_USUARIOS.slice(-n),
    },
    distribuciones: {
      marca: [
        { nombre: 'Toyota', cantidad: 312 },
        { nombre: 'Fiat', cantidad: 262 },
        { nombre: 'Volkswagen', cantidad: 237 },
        { nombre: 'Ford', cantidad: 187 },
        { nombre: 'Renault', cantidad: 150 },
        { nombre: 'Otras', cantidad: 100 },
      ],
      combustible: [
        { nombre: 'Nafta', cantidad: 702 },
        { nombre: 'Diésel', cantidad: 318 },
        { nombre: 'GNC', cantidad: 148 },
        { nombre: 'Híbrido', cantidad: 56 },
        { nombre: 'Eléctrico', cantidad: 24 },
      ],
      transmision: [
        { nombre: 'Manual', cantidad: 689 },
        { nombre: 'Automática', cantidad: 559 },
      ],
    },
    porUbicacion: [
      { nombre: 'Buenos Aires', cantidad: 420 },
      { nombre: 'Córdoba', cantidad: 215 },
      { nombre: 'Santa Fe', cantidad: 160 },
      { nombre: 'Mendoza', cantidad: 98 },
      { nombre: 'Tucumán', cantidad: 60 },
    ],
  };
}

// ---------- Reportes recibidos ----------

/** @type {import('../types/admin').ReporteRecibido[]} */
let reportes = [
  {
    id: 1, tipo: 'publicacion', fecha: hace(1), estado: 'pendiente',
    reportante: { id: 8, nombre: 'Lucía', apellido: 'Fernández' },
    motivo: 'precio_enganoso', publicacion: { id: 1, titulo: 'Renault Kangoo 1.6 Authentique' },
    descripcion: 'El precio publicado no es el real: cuando pregunté me pidieron bastante más.', respuestas: [],
  },
  {
    id: 2, tipo: 'general', fecha: hace(2), estado: 'pendiente',
    reportante: { id: 9, nombre: 'Julián', apellido: 'Pérez' },
    descripcion: 'No me llegan las notificaciones por correo desde ayer.', respuestas: [],
  },
  {
    id: 3, tipo: 'usuario', fecha: hace(3), estado: 'en_revision',
    reportante: { id: 8, nombre: 'Lucía', apellido: 'Fernández' },
    usuarioReportado: { id: 7, nombre: 'Carlos', apellido: 'Rodríguez' },
    descripcion: 'Me pidió una seña por transferencia antes de que yo pudiera ver el auto.', respuestas: [],
  },
  {
    id: 4, tipo: 'publicacion', fecha: hace(6), estado: 'resuelto',
    reportante: { id: 7, nombre: 'Carlos', apellido: 'Rodríguez' },
    motivo: 'duplicada', publicacion: { id: 2, titulo: 'Toyota Corolla Negro - 2023' },
    descripcion: 'La misma publicación aparece dos veces.',
    respuestas: [{ id: 1, texto: 'Revisamos y eliminamos la publicación duplicada. ¡Gracias por avisarnos!', fecha: hace(5), autor: 'Administración' }],
  },
  {
    id: 5, tipo: 'general', fecha: hace(8), estado: 'resuelto',
    reportante: { id: 9, nombre: 'Julián', apellido: 'Pérez' },
    descripcion: 'Estaría bueno poder filtrar los autos por provincia.',
    respuestas: [{ id: 2, texto: 'Gracias por la sugerencia, la pasamos al equipo de desarrollo.', fecha: hace(7), autor: 'Administración' }],
  },
  {
    id: 6, tipo: 'publicacion', fecha: hace(9), estado: 'pendiente',
    reportante: { id: 7, nombre: 'Carlos', apellido: 'Rodríguez' },
    motivo: 'fraude', publicacion: { id: 3, titulo: 'Volkswagen Polo Highline - 2024' },
    descripcion: 'Las fotos parecen sacadas de otra publicación.', respuestas: [],
  },
  {
    id: 7, tipo: 'general', fecha: hace(12), estado: 'pendiente',
    reportante: { id: 8, nombre: 'Lucía', apellido: 'Fernández' },
    descripcion: 'No puedo cambiar mi foto de perfil desde el celular.', respuestas: [],
  },
  {
    id: 8, tipo: 'publicacion', fecha: hace(14), estado: 'en_revision',
    reportante: { id: 9, nombre: 'Julián', apellido: 'Pérez' },
    motivo: 'informacion_incorrecta', publicacion: { id: 5, titulo: 'Fiat Cronos 1.3 Drive - 2022' },
    descripcion: 'El kilometraje publicado no coincide con el que muestra el auto.', respuestas: [],
  },
];

const buscarReporte = (id) => {
  const reporte = reportes.find((r) => String(r.id) === String(id));
  if (!reporte) throw new Error('El reporte no existe.');
  return reporte;
};

/** Filtra por tipo, estado y rango de fechas ("YYYY-MM-DD", ambos extremos incluidos). */
function filtrarReportes({ tipo, estado, desde, hasta }) {
  return reportes.filter((r) => {
    const dia = r.fecha.slice(0, 10);
    return (!tipo || r.tipo === tipo) && (!estado || r.estado === estado) && (!desde || dia >= desde) && (!hasta || dia <= hasta);
  });
}

/**
 * @param {import('../types/admin').FiltrosReportes} filtros
 * @returns {Promise<{ reportes: import('../types/admin').ReporteRecibido[] }>}
 */
export async function getReportesRecibidos(filtros) {
  await delay();
  const lista = filtrarReportes(filtros).sort((a, b) => new Date(b.fecha) - new Date(a.fecha));
  return { reportes: copiar(lista) };
}

/**
 * @param {number|string} id
 * @param {{ texto: string, estado?: string }} respuesta
 */
export async function responderReporte(id, { texto, estado }) {
  await delay();
  const reporte = buscarReporte(id);
  if (!texto?.trim()) throw new Error('Escribí la respuesta antes de enviarla.');
  reporte.respuestas = [
    ...reporte.respuestas,
    { id: siguienteId++, texto: texto.trim(), fecha: new Date().toISOString(), autor: 'Administración' },
  ];
  if (estado) reporte.estado = estado;
  return { reporte: copiar(reporte) };
}

/**
 * @param {number|string} id
 * @param {string} estado
 */
export async function cambiarEstadoReporte(id, estado) {
  await delay(250);
  const reporte = buscarReporte(id);
  reporte.estado = estado;
  return { reporte: copiar(reporte) };
}

// ---------- Informes ----------

/** @type {import('../types/admin').Informe[]} */
let informes = [
  { id: 1, nombre: 'Reportes recibidos 2026-09-01 a 2026-09-30', tipo: 'reportes', formato: 'pdf', desde: '2026-09-01', hasta: '2026-09-30', fecha: hace(27), cantidad: 12 },
  { id: 2, nombre: 'Publicaciones 2026-09-01 a 2026-09-30', tipo: 'publicaciones', formato: 'csv', desde: '2026-09-01', hasta: '2026-09-30', fecha: hace(28), cantidad: 140 },
  { id: 3, nombre: 'Usuarios registrados 2026-08-01 a 2026-08-31', tipo: 'usuarios', formato: 'pdf', desde: '2026-08-01', hasta: '2026-08-31', fecha: hace(57), cantidad: 98 },
];

const nombreTipoInforme = (id) => TIPOS_INFORME.find((t) => t.id === id)?.nombre ?? id;
const diasEntre = (desde, hasta) => Math.round((new Date(hasta) - new Date(desde)) / 86400000) + 1;

/** @returns {Promise<{ informes: import('../types/admin').Informe[] }>} */
export async function getInformes() {
  await delay();
  return { informes: copiar([...informes].sort((a, b) => new Date(b.fecha) - new Date(a.fecha))) };
}

/**
 * @param {{ tipo: string, desde: string, hasta: string, formato: string }} datos
 * @returns {Promise<{ informe: import('../types/admin').Informe }>}
 */
export async function generarInforme({ tipo, desde, hasta, formato }) {
  await delay(700);
  if (!desde || !hasta) throw new Error('Elegí las fechas del informe.');
  if (desde > hasta) throw new Error('La fecha "Desde" no puede ser posterior a "Hasta".');

  // En el mock, solo los informes de reportes cuentan datos reales; el resto es un número de ejemplo.
  const dias = diasEntre(desde, hasta);
  const cantidad = tipo === 'reportes' ? filtrarReportes({ desde, hasta }).length : dias * (tipo === 'publicaciones' ? 4 : 3);

  const informe = {
    id: siguienteId++,
    nombre: `${nombreTipoInforme(tipo)} ${desde} a ${hasta}`,
    tipo, formato, desde, hasta, cantidad,
    fecha: new Date().toISOString(),
  };
  informes = [informe, ...informes];
  return { informe: copiar(informe) };
}

/**
 * Arma un archivo de prueba con el contenido del informe.
 * @param {number|string} id
 * @returns {Promise<{ blob: Blob, nombreArchivo: string }>}
 */
export async function descargarInforme(id) {
  await delay(300);
  const informe = informes.find((i) => String(i.id) === String(id));
  if (!informe) throw new Error('El informe no existe.');

  let columnas;
  let filas;
  if (informe.tipo === 'reportes') {
    columnas = ['ID', 'Tipo', 'Fecha', 'Estado', 'Reporte', 'Reportado por'];
    filas = filtrarReportes({ desde: informe.desde, hasta: informe.hasta }).map((r) => [
      r.id, nombreTipoReporte(r.tipo), r.fecha.slice(0, 10), nombreEstadoReporte(r.estado), tituloReporte(r), nombreCompleto(r.reportante),
    ]);
  } else {
    columnas = ['N°', 'Detalle'];
    filas = Array.from({ length: Math.min(informe.cantidad, 40) }, (_, i) => [i + 1, `Registro de ejemplo (datos de prueba)`]);
  }

  const nombreArchivo = `${informe.nombre.replace(/[^\w-]+/g, '_')}.${informe.formato}`;
  if (informe.formato === 'csv') return { blob: generarCsv(columnas, filas), nombreArchivo };

  const lineas = [
    `Periodo: ${informe.desde} a ${informe.hasta}`,
    `Registros: ${informe.cantidad}`,
    `Generado: ${informe.fecha.slice(0, 10)}`,
    '',
    columnas.join('  |  '),
    ...filas.map((f) => f.join('  |  ')),
  ];
  return { blob: generarPdfSimple(informe.nombre, lineas), nombreArchivo };
}
