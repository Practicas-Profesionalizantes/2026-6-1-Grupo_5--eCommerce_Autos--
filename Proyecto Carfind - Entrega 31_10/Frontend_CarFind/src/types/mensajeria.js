/**
 * Contrato de datos de la Mensajería.
 *
 * Una conversación nace SIEMPRE desde una publicación (botón "Preguntar" del detalle):
 * conecta a un comprador y al publicante alrededor de esa publicación.
 * La lógica (crear conversaciones, guardar y entregar mensajes) la resuelve el backend.
 */

/**
 * Usuario con el que se conversa (el "otro", nunca el usuario logueado).
 * @typedef {Object} UsuarioChat
 * @property {number|string} id
 * @property {string}        nombre
 * @property {string}        apellido
 * @property {string|null}   fotoUrl
 * @property {string}        telefono
 */

/**
 * Publicación sobre la que trata la conversación (tarjeta "Precio - Título").
 * @typedef {Object} PublicacionChat
 * @property {number|string} id
 * @property {string}        titulo
 * @property {number}        precio
 * @property {string}        moneda      Ej: "ARS"
 * @property {string}        imagenUrl   Primera imagen del carrusel de la publicación
 */

/**
 * Resumen de una conversación (lista "CHATS" y cabecera del chat).
 * @typedef {Object} Conversacion
 * @property {number|string} id
 * @property {UsuarioChat}   otroUsuario
 * @property {'Vendedor'|'Comprador'} rolOtroUsuario   Rol del otro usuario en ESTA publicación
 * @property {PublicacionChat} publicacion
 * @property {{ texto: string, fecha: string, propio: boolean }|null} ultimoMensaje   null si todavía no hay mensajes
 * @property {number}        noLeidos    Mensajes del otro usuario que el logueado todavía no leyó
 */

/**
 * @typedef {'enviado'|'entregado'|'leido'} EstadoMensaje
 */

/**
 * Mensaje de una conversación.
 * @typedef {Object} Mensaje
 * @property {number|string} id
 * @property {number|string} conversacionId
 * @property {string}        texto       Puede ser '' si el mensaje es solo una imagen
 * @property {string|null}   imagenUrl   Foto adjunta (botón de cámara), o null
 * @property {string}        fecha       Fecha y hora ISO de envío
 * @property {boolean}       propio      true si lo envió el usuario logueado (el backend lo calcula con la sesión)
 * @property {EstadoMensaje} estado      Solo se muestran las tildes en los mensajes propios
 */

/**
 * Respuesta de GET /conversaciones/:id/mensajes.
 * @typedef {Object} ChatResponse
 * @property {Conversacion} conversacion
 * @property {Mensaje[]}    mensajes      De más viejo a más nuevo
 */

/** Largo máximo de un mensaje. Valor sugerido: a confirmar con el equipo de backend. */
export const MENSAJE_MAX_CARACTERES = 2000;

/** Emojis del selector (botón EMOJIS). */
export const EMOJIS = [
  '😀', '😂', '😍', '😊', '😉', '😅', '🤔', '😢',
  '👍', '👏', '🙏', '🤝', '👋', '🎉', '🔥', '⭐',
  '🚗', '🔧', '💰', '📍', '📅', '✅', '❌', '❤️',
];
