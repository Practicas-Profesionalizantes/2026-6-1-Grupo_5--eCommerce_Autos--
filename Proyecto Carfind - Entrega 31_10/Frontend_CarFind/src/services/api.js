/**
 * Cliente HTTP mínimo compartido por todos los servicios "api" (*.api.js).
 *
 * Variables de entorno: VITE_API_URL (URL base del backend; en desarrollo "/api/web",
 * que Vite redirige al backend de Spring Boot, ver vite.config.js).
 */
const BASE_URL = import.meta.env.VITE_API_URL ?? '';

/** Clave de localStorage donde vive el token de la sesión (lo entrega el backend al iniciar sesión). */
const CLAVE_TOKEN = 'token';

const leerToken = () => {
  try {
    return localStorage.getItem(CLAVE_TOKEN);
  } catch {
    return null;
  }
};

/** @param {string|null} token  null borra la sesión guardada */
export function guardarToken(token) {
  try {
    if (token) localStorage.setItem(CLAVE_TOKEN, token);
    else localStorage.removeItem(CLAVE_TOKEN);
  } catch {
    // almacenamiento bloqueado: la sesión dura lo que dure la pestaña
  }
}

/** true si hay un token guardado (no garantiza que siga vigente: eso lo decide el backend). */
export const haySesion = () => Boolean(leerToken());

/** Cabecera de autenticación. Ajustar acá si la sesión se maneja distinto (cookie, otro nombre de token). */
const headersAuth = () => {
  const token = leerToken();
  return token ? { Authorization: `Bearer ${token}` } : {};
};

/**
 * @param {string} ruta                      Ej: "/configuracion"
 * @param {{ method?: string, json?: unknown, formData?: FormData }} [opciones]
 * @returns {Promise<any>} El JSON de la respuesta (null si es 204).
 */
export async function request(ruta, { method = 'GET', json, formData } = {}) {
  const headers = { ...headersAuth() };
  if (json) headers['Content-Type'] = 'application/json';

  const res = await fetchSeguro(`${BASE_URL}${ruta}`, {
    method,
    credentials: 'include',
    headers,
    body: formData ?? (json ? JSON.stringify(json) : undefined),
  });

  if (!res.ok) throw await errorDeRespuesta(res);
  return res.status === 204 ? null : res.json();
}

/**
 * fetch que, si no hay conexión con el servidor, lanza un error entendible en lugar de "Failed to fetch".
 * @param {string} url
 * @param {RequestInit} [opciones]
 */
async function fetchSeguro(url, opciones) {
  try {
    return await fetch(url, opciones);
  } catch {
    const error = new Error('No pudimos conectar con el servidor. Revisá que el backend esté funcionando e intentá de nuevo.');
    error.status = 0;
    throw error;
  }
}

/** Arma el Error de una respuesta fallida. Lleva el código HTTP en `error.status` (ej: 401, 403). */
async function errorDeRespuesta(res) {
  let mensaje;
  if (res.status === 401) {
    mensaje = 'Tenés que iniciar sesión para continuar.';
  } else if (res.status === 502 || res.status === 503 || res.status === 504) {
    mensaje = 'El servidor no responde. Revisá que el backend esté funcionando e intentá de nuevo.';
  } else {
    try {
      mensaje = (await res.json()).message;
    } catch {
      // la respuesta de error no era JSON
    }
  }
  const error = new Error(mensaje || `Error ${res.status}. Intentá de nuevo.`);
  error.status = res.status;
  return error;
}

/**
 * Descarga un archivo del backend (informes en PDF o CSV).
 * @param {string} ruta
 * @returns {Promise<{ blob: Blob, nombreArchivo: string }>}  El nombre sale de la cabecera Content-Disposition.
 */
export async function requestBlob(ruta) {
  const res = await fetchSeguro(`${BASE_URL}${ruta}`, { credentials: 'include', headers: headersAuth() });
  if (!res.ok) throw await errorDeRespuesta(res);
  const disposicion = res.headers.get('Content-Disposition') ?? '';
  const nombreArchivo = /filename\*?=(?:UTF-8'')?"?([^";]+)"?/i.exec(disposicion)?.[1] ?? 'informe';
  return { blob: await res.blob(), nombreArchivo: decodeURIComponent(nombreArchivo) };
}
