import React from 'react';
import { Link } from 'react-router-dom';
import AvatarUsuario from './AvatarUsuario';
import OpcionesChat from './OpcionesChat';
import formatearPrecio from '../../utils/formatearPrecio';
import { imagenAuto, usarImagenGenerica } from '../../utils/imagenAuto';

/**
 * Cabecera del chat: usuario con el que se conversa, tarjeta de la publicación
 * ("Precio - Título", "Ver detalles" y primera imagen) y botón de opciones.
 * @param {{ conversacion: import('../../types/mensajeria').Conversacion, onVolver: () => void }} props
 */
function ChatCabecera({ conversacion, onVolver }) {
  const { otroUsuario, publicacion } = conversacion;

  return (
    <header className="chat-cabecera">
      <div className="chat-cabecera-usuario">
        <button type="button" className="chat-volver" onClick={onVolver} aria-label="Volver a la lista de chats">←</button>
        <AvatarUsuario usuario={otroUsuario} grande />
        <div className="chat-cabecera-datos">
          <strong>{otroUsuario.nombre} {otroUsuario.apellido}</strong>
          <span className="chat-cabecera-telefono">{otroUsuario.telefono}</span>
        </div>
      </div>

      <div className="chat-publicacion">
        <div className="chat-publicacion-texto">
          <span className="chat-publicacion-titulo">{formatearPrecio(publicacion.precio)} - {publicacion.titulo}</span>
          <Link to={`/publicacion/${publicacion.id}`} className="chat-publicacion-detalles">Ver detalles</Link>
        </div>
        <img className="chat-publicacion-imagen" src={imagenAuto(publicacion.imagenUrl)} alt={publicacion.titulo} onError={usarImagenGenerica} />
      </div>

      <OpcionesChat conversacion={conversacion} />
    </header>
  );
}

export default ChatCabecera;
