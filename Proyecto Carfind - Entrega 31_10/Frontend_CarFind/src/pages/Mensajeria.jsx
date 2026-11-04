import React from 'react';
import { useParams } from 'react-router-dom';
import ListaChats from '../components/mensajeria/ListaChats';
import ChatActivo from '../components/mensajeria/ChatActivo';
import useConversaciones from '../hooks/useConversaciones';
import '../assets/styles/App.css';

/**
 * Mensajería: lista "CHATS" a la izquierda y la conversación abierta a la derecha.
 * Rutas: /mensajes (sin chat elegido) y /mensajes/:conversacionId.
 * En pantallas angostas se muestra una sola columna a la vez.
 */
function Mensajeria() {
  const { conversacionId } = useParams();
  const { conversaciones, loading, error, reload, refrescar } = useConversaciones();

  return (
    <main className={`mensajeria-container${conversacionId ? ' mensajeria--chat-abierto' : ''}`}>
      <ListaChats
        conversaciones={conversaciones}
        loading={loading}
        error={error}
        onReintentar={reload}
        activaId={conversacionId}
      />

      <section className="chat-panel" aria-label="Conversación">
        {conversacionId ? (
          // El key reinicia el estado del chat al pasar de una conversación a otra.
          <ChatActivo key={conversacionId} conversacionId={conversacionId} alHaberActividad={refrescar} />
        ) : (
          <p className="perfil-status chat-estado">Elegí un chat para ver la conversación.</p>
        )}
      </section>
    </main>
  );
}

export default Mensajeria;
