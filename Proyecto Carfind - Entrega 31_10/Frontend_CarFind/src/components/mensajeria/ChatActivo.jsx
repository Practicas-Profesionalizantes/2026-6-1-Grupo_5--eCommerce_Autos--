import React, { useEffect, useRef } from 'react';
import { useNavigate } from 'react-router-dom';
import ChatCabecera from './ChatCabecera';
import BurbujaMensaje from './BurbujaMensaje';
import ComposerMensaje from './ComposerMensaje';
import useChat from '../../hooks/useChat';

/**
 * Conversación abierta. Usar con `key={conversacionId}` para que arranque limpia al cambiar de chat.
 * @param {{ conversacionId: string, alHaberActividad?: () => void }} props
 */
function ChatActivo({ conversacionId, alHaberActividad }) {
  const navigate = useNavigate();
  const { conversacion, mensajes, loading, error, enviando, errorEnvio, enviar, reload } = useChat(conversacionId, alHaberActividad);
  const listaRef = useRef(null);

  // Siempre mostrar el mensaje más nuevo.
  useEffect(() => {
    const lista = listaRef.current;
    if (lista) lista.scrollTop = lista.scrollHeight;
  }, [mensajes.length]);

  if (loading) {
    return <p className="perfil-status chat-estado">Cargando conversación...</p>;
  }
  if (error || !conversacion) {
    return (
      <div className="perfil-error chat-estado" role="alert">
        {error} <button type="button" className="btn-support" onClick={reload}>Reintentar</button>
      </div>
    );
  }

  return (
    <div className="chat-activo">
      <ChatCabecera conversacion={conversacion} onVolver={() => navigate('/mensajes')} />

      <div className="chat-mensajes" ref={listaRef} role="log" aria-live="polite" aria-label="Mensajes">
        {mensajes.length === 0 ? (
          <p className="perfil-status chat-vacio-mensajes">Todavía no hay mensajes. Escribí el primero.</p>
        ) : (
          mensajes.map((m) => <BurbujaMensaje key={m.id} mensaje={m} />)
        )}
      </div>

      <ComposerMensaje onEnviar={enviar} enviando={enviando} errorEnvio={errorEnvio} />
    </div>
  );
}

export default ChatActivo;
