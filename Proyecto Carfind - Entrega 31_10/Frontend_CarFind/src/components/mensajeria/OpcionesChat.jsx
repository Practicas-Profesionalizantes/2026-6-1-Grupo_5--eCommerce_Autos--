import React, { useRef, useState } from 'react';
import { Link } from 'react-router-dom';
import useCerrarAlClickFuera from '../../hooks/useCerrarAlClickFuera';

/**
 * Botón "OPCIONES" de la cabecera del chat.
 * @param {{ conversacion: import('../../types/mensajeria').Conversacion }} props
 */
function OpcionesChat({ conversacion }) {
  const [abierto, setAbierto] = useState(false);
  const contenedorRef = useRef(null);
  useCerrarAlClickFuera(contenedorRef, abierto, () => setAbierto(false));

  const { otroUsuario, publicacion } = conversacion;
  const cerrar = () => setAbierto(false);

  return (
    <div className="chat-opciones" ref={contenedorRef}>
      <button type="button" className="chat-opciones-btn" aria-haspopup="menu" aria-expanded={abierto} onClick={() => setAbierto((v) => !v)}>
        Opciones
      </button>
      {abierto && (
        <div className="chat-opciones-menu" role="menu">
          <Link role="menuitem" to={`/usuario/${otroUsuario.id}`} onClick={cerrar}>Ver perfil de {otroUsuario.nombre}</Link>
          <Link role="menuitem" to={`/publicacion/${publicacion.id}`} onClick={cerrar}>Ver publicación</Link>
          <Link role="menuitem" to={`/publicacion/${publicacion.id}/reportar`} onClick={cerrar}>Reportar publicación</Link>
          <Link
            role="menuitem"
            to="/reportes"
            state={{ usuarioReportado: { id: otroUsuario.id, nombre: `${otroUsuario.nombre} ${otroUsuario.apellido}` } }}
            onClick={cerrar}
          >
            Reportar usuario
          </Link>
        </div>
      )}
    </div>
  );
}

export default OpcionesChat;
