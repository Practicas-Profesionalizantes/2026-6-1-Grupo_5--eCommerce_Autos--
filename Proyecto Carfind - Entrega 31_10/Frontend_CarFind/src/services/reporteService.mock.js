/**
 * Servicio de Reportes — IMPLEMENTACIÓN MOCK.
 * No guarda nada: valida lo básico y responde que el reporte se recibió.
 * Debe devolver la misma forma que reporteService.api.js (ver src/types/reportes.js).
 */
const MOCK_DELAY_MS = 500;

/** Simula la latencia de red. */
const delay = (ms = MOCK_DELAY_MS) => new Promise((resolve) => setTimeout(resolve, ms));

/**
 * @param {import('../types/reportes').ReporteGeneral} reporte
 * @returns {Promise<{ ok: boolean }>}
 */
export async function enviarReporteGeneral(reporte) {
  await delay();
  if (!reporte.mensaje?.trim()) throw new Error('Escribí tu problema, sugerencia o duda antes de reportar.');
  return { ok: true };
}

/**
 * @param {import('../types/reportes').ReportePublicacion} reporte
 * @returns {Promise<{ ok: boolean }>}
 */
export async function enviarReportePublicacion(reporte) {
  await delay();
  if (!reporte.motivo) throw new Error('Elegí el motivo del reporte.');
  return { ok: true };
}
