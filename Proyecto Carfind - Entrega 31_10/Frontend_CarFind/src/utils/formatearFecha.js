/** Fecha sola ("2025-03-14"); se fuerza UTC para que no se corra un día. */
export const formatearFechaCorta = (iso) =>
  new Date(iso).toLocaleDateString('es-AR', { day: '2-digit', month: '2-digit', year: 'numeric', timeZone: 'UTC' });

const mismoDia = (a, b) =>
  a.getFullYear() === b.getFullYear() && a.getMonth() === b.getMonth() && a.getDate() === b.getDate();

/**
 * Fecha para la lista de chats: la hora si es de hoy, "Ayer", o la fecha corta.
 * @param {string} iso
 * @param {Date} [ahora]
 */
export function formatearFechaLista(iso, ahora = new Date()) {
  const fecha = new Date(iso);
  if (mismoDia(fecha, ahora)) {
    return fecha.toLocaleTimeString('es-AR', { hour: '2-digit', minute: '2-digit', hour12: false });
  }
  const ayer = new Date(ahora);
  ayer.setDate(ayer.getDate() - 1);
  if (mismoDia(fecha, ayer)) return 'Ayer';
  return fecha.toLocaleDateString('es-AR', { day: '2-digit', month: '2-digit', year: '2-digit' });
}

/** Fecha y hora de envío de un mensaje (ej: "14/03/2026 18:05"). */
export const formatearFechaEnvio = (iso) =>
  new Date(iso).toLocaleString('es-AR', { day: '2-digit', month: '2-digit', year: 'numeric', hour: '2-digit', minute: '2-digit', hour12: false });

/** Fecha sola en hora local (para fechas que traen hora, como "2026-10-25T14:30:00Z"). */
export const formatearDia = (iso) =>
  new Date(iso).toLocaleDateString('es-AR', { day: '2-digit', month: '2-digit', year: 'numeric' });
