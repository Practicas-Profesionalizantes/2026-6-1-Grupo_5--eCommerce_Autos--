/**
 * Servicio de Configuración — IMPLEMENTACIÓN MOCK.
 *
 * Se usa mientras el backend no esté listo (ver VITE_USE_MOCK en configuracionService.js).
 * Debe devolver la misma forma que configuracionService.api.js (ver src/types/configuracion.js).
 *
 * - Nombre, apellido, teléfono principal y foto salen de perfilService.mock, para que
 *   Configuración y Perfil muestren siempre lo mismo.
 * - Apariencia y accesibilidad se leen de lo que ya está aplicado en el navegador (tema.js y
 *   preferenciasUI.js, que guardan en localStorage).
 * - El resto vive en memoria: se reinicia al recargar la página.
 */
import { getPerfil, actualizarPerfil } from './perfilService.mock';
import { obtenerModoTema } from '../utils/tema';
import { obtenerPreferenciasUI } from '../utils/preferenciasUI';

const MOCK_DELAY_MS = 400;

/** Simula la latencia de red. */
const delay = (ms = MOCK_DELAY_MS) => new Promise((resolve) => setTimeout(resolve, ms));

/** Datos que el perfil no tiene. Teléfonos adicionales = todos menos el principal. */
let extra = {
  genero: 'Femenino',
  correo: 'martina.gomez@example.com',
  fechaNacimiento: '1995-06-21',
  direccion: 'Av. Corrientes 1234, Buenos Aires',
  otrasDirecciones: ['Libertad 456, Buenos Aires'],
  telefonosAdicionales: [],
};

/** Contraseña de prueba para validar el flujo "Cambiar contraseña" (el backend real la verifica). */
let contrasena = 'Mock1234';

/** @type {import('../types/configuracion').Notificaciones} */
let notificaciones = {
  canales: { app: true, correo: true },
  avisos: {
    mensajes: { app: true, correo: true },
    preguntas: { app: true, correo: true },
    bajaPrecioWishlist: { app: true, correo: true },
    publicacionNoDisponible: { app: true, correo: false },
    estadoPublicaciones: { app: true, correo: true },
    novedades: { app: false, correo: false },
    seguridadCuenta: { app: true, correo: true },
  },
  frecuenciaCorreo: 'inmediato',
  noMolestar: { activo: true, desde: '22:00', hasta: '08:00' },
};

/** @type {import('../types/configuracion').Idioma} */
let idioma = { preferido: 'es-AR', otros: [], agregarAutomaticamente: true };

/** Une el usuario del perfil con los datos extra. @returns {import('../types/configuracion').DatosPersonales} */
const combinar = (usuario) => ({
  id: usuario.id,
  nombre: usuario.nombre,
  apellido: usuario.apellido,
  genero: extra.genero,
  correo: extra.correo,
  telefonos: [usuario.telefono, ...extra.telefonosAdicionales].filter(Boolean),
  fechaNacimiento: extra.fechaNacimiento,
  direccion: extra.direccion,
  otrasDirecciones: [...extra.otrasDirecciones],
  fotoUrl: usuario.fotoUrl,
});

/** @returns {Promise<import('../types/configuracion').ConfiguracionResponse>} */
export async function getConfiguracion() {
  const { usuario } = await getPerfil(); // ya incluye su propia latencia simulada
  const prefs = obtenerPreferenciasUI();
  return {
    datosPersonales: combinar(usuario),
    apariencia: { tema: obtenerModoTema(), escalaFuente: prefs.escalaFuente, fuente: prefs.fuente },
    accesibilidad: {
      atajosTeclado: prefs.atajosTeclado,
      animaciones: prefs.animaciones,
      subrayadoEnlaces: prefs.subrayadoEnlaces,
    },
    notificaciones: structuredClone(notificaciones),
    idioma: { ...idioma, otros: [...idioma.otros] },
  };
}

/**
 * @param {Omit<import('../types/configuracion').DatosPersonales, 'id'|'fotoUrl'>} datos
 * @returns {Promise<{ ok: boolean, datosPersonales: import('../types/configuracion').DatosPersonales }>}
 */
export async function actualizarDatosPersonales(datos) {
  await delay();
  const [principal = '', ...adicionales] = datos.telefonos;
  const { usuario } = await actualizarPerfil({ nombre: datos.nombre, apellido: datos.apellido, telefono: principal });
  extra = {
    genero: datos.genero,
    correo: datos.correo,
    fechaNacimiento: datos.fechaNacimiento,
    direccion: datos.direccion,
    otrasDirecciones: [...datos.otrasDirecciones],
    telefonosAdicionales: adicionales,
  };
  return { ok: true, datosPersonales: combinar(usuario) };
}

/** @param {{ actual: string, nueva: string }} datos */
export async function cambiarContrasena({ actual, nueva }) {
  await delay();
  if (actual !== contrasena) throw new Error('La contraseña actual no es correcta.');
  contrasena = nueva;
  return { ok: true };
}

/** @param {import('../types/configuracion').Apariencia} apariencia */
export async function guardarApariencia(apariencia) {
  await delay();
  return { ok: true, apariencia: { ...apariencia } }; // ya quedó guardado localmente al aplicarlo
}

/** @param {import('../types/configuracion').Accesibilidad} accesibilidad */
export async function guardarAccesibilidad(accesibilidad) {
  await delay();
  return { ok: true, accesibilidad: { ...accesibilidad } };
}

/** @param {import('../types/configuracion').Notificaciones} nuevas */
export async function guardarNotificaciones(nuevas) {
  await delay(250);
  notificaciones = structuredClone(nuevas);
  return { ok: true, notificaciones: structuredClone(notificaciones) };
}

/** @param {import('../types/configuracion').Idioma} nuevo */
export async function guardarIdioma(nuevo) {
  await delay(250);
  idioma = { ...nuevo, otros: [...nuevo.otros] };
  return { ok: true, idioma: { ...idioma, otros: [...idioma.otros] } };
}
