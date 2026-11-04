import React, { useEffect, useRef, useState } from 'react';
import useCerrarAlClickFuera from '../../hooks/useCerrarAlClickFuera';
import { validarImagen } from '../../utils/validarImagen';
import { EMOJIS, MENSAJE_MAX_CARACTERES } from '../../types/mensajeria';

/**
 * Barra inferior del chat: emojis, cámara, texto y enviar.
 * @param {{
 *   onEnviar: (contenido: { texto: string, imagen: File|null }) => Promise<boolean>,
 *   enviando: boolean,
 *   errorEnvio: string|null,
 * }} props
 */
function ComposerMensaje({ onEnviar, enviando, errorEnvio }) {
  const [texto, setTexto] = useState('');
  const [adjunto, setAdjunto] = useState(null); // { archivo, url }
  const [errorFoto, setErrorFoto] = useState(null);
  const [emojisAbierto, setEmojisAbierto] = useState(false);
  const emojisRef = useRef(null);
  const inputFotoRef = useRef(null);
  const inputTextoRef = useRef(null);

  useCerrarAlClickFuera(emojisRef, emojisAbierto, () => setEmojisAbierto(false));

  // Libera la vista previa de la foto cuando se cambia o se descarta.
  useEffect(() => () => {
    if (adjunto) URL.revokeObjectURL(adjunto.url);
  }, [adjunto]);

  const elegirFoto = (e) => {
    const archivo = e.target.files?.[0];
    e.target.value = ''; // permite volver a elegir la misma foto
    if (!archivo) return;
    const mensaje = validarImagen(archivo);
    if (mensaje) {
      setErrorFoto(mensaje);
      return;
    }
    setErrorFoto(null);
    setAdjunto({ archivo, url: URL.createObjectURL(archivo) });
  };

  const agregarEmoji = (emoji) => {
    setTexto((t) => `${t}${emoji}`);
    inputTextoRef.current?.focus();
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (enviando || (!texto.trim() && !adjunto)) return;
    const ok = await onEnviar({ texto, imagen: adjunto?.archivo ?? null });
    if (ok) {
      setTexto('');
      setAdjunto(null);
    }
  };

  const puedeEnviar = !enviando && (texto.trim() !== '' || adjunto !== null);
  const error = errorFoto || errorEnvio;

  return (
    <form className="chat-composer" onSubmit={handleSubmit}>
      {adjunto && (
        <div className="chat-adjunto">
          <img src={adjunto.url} alt="Foto para enviar" />
          <button type="button" className="chat-adjunto-quitar" onClick={() => setAdjunto(null)} aria-label="Quitar la foto">✕</button>
        </div>
      )}
      {error && <p className="perfil-error" role="alert">{error}</p>}

      <div className="chat-composer-fila">
        <div className="chat-emojis" ref={emojisRef}>
          <button type="button" className="chat-composer-btn" aria-haspopup="true" aria-expanded={emojisAbierto} onClick={() => setEmojisAbierto((v) => !v)} aria-label="Emojis" title="Emojis">
            😊
          </button>
          {emojisAbierto && (
            <div className="chat-emojis-panel" role="group" aria-label="Elegí un emoji">
              {EMOJIS.map((emoji) => (
                <button key={emoji} type="button" className="chat-emoji" onClick={() => agregarEmoji(emoji)}>{emoji}</button>
              ))}
            </div>
          )}
        </div>

        <button type="button" className="chat-composer-btn" onClick={() => inputFotoRef.current?.click()} aria-label="Adjuntar foto" title="Adjuntar foto">
          📷
        </button>
        <input ref={inputFotoRef} type="file" accept="image/*,.heic,.heif" className="perfil-input-oculto" onChange={elegirFoto} tabIndex={-1} aria-hidden="true" />

        <input
          ref={inputTextoRef}
          className="chat-composer-texto"
          type="text"
          value={texto}
          maxLength={MENSAJE_MAX_CARACTERES}
          onChange={(e) => setTexto(e.target.value)}
          placeholder="Escribir tu mensaje"
          aria-label="Escribir tu mensaje"
          autoComplete="off"
        />

        <button type="submit" className="chat-enviar" disabled={!puedeEnviar} aria-label="Enviar mensaje" title="Enviar">
          ➤
        </button>
      </div>
    </form>
  );
}

export default ComposerMensaje;
