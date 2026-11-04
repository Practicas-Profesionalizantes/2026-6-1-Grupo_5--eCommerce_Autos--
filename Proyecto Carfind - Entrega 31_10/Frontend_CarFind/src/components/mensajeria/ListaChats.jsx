import React from 'react';
import { Link } from 'react-router-dom';
import AvatarUsuario from './AvatarUsuario';
import { formatearFechaLista } from '../../utils/formatearFecha';

/**
 * Panel "CHATS": una fila por conversación.
 * @param {{
 *   conversaciones: import('../../types/mensajeria').Conversacion[],
 *   loading: boolean,
 *   error: string|null,
 *   onReintentar: () => void,
 *   activaId?: string,
 * }} props
 */
function ListaChats({ conversaciones, loading, error, onReintentar, activaId }) {
  return (
    <aside className="chats-panel" aria-label="Lista de chats">
      <h1 className="chats-titulo">CHATS</h1>

      {loading ? (
        <p className="perfil-status chats-mensaje">Cargando chats...</p>
      ) : error ? (
        <div className="perfil-error chats-mensaje" role="alert">
          {error} <button type="button" className="btn-support" onClick={onReintentar}>Reintentar</button>
        </div>
      ) : conversaciones.length === 0 ? (
        <p className="perfil-status chats-mensaje">
          Todavía no tenés conversaciones. Iniciá una desde una publicación con el botón «Preguntar».
        </p>
      ) : (
        <ul className="chats-lista">
          {conversaciones.map((c) => {
            const activa = String(c.id) === String(activaId);
            return (
              <li key={c.id}>
                <Link
                  to={`/mensajes/${c.id}`}
                  className={`chat-item${activa ? ' chat-item--activo' : ''}`}
                  aria-current={activa ? 'true' : undefined}
                >
                  <AvatarUsuario usuario={c.otroUsuario} />
                  <span className="chat-item-texto">
                    <span className="chat-item-nombre">
                      {c.otroUsuario.nombre} {c.otroUsuario.apellido}
                      <small className="chat-item-rol">{c.rolOtroUsuario}</small>
                    </span>
                    <span className="chat-item-ultimo">
                      <span className="chat-item-mensaje">
                        {c.ultimoMensaje ? `${c.ultimoMensaje.propio ? 'Vos: ' : ''}${c.ultimoMensaje.texto}` : 'Sin mensajes todavía'}
                      </span>
                      {c.ultimoMensaje && <span className="chat-item-fecha">{formatearFechaLista(c.ultimoMensaje.fecha)}</span>}
                    </span>
                  </span>
                  {c.noLeidos > 0 && (
                    <span className="chat-item-noleidos" aria-label={`${c.noLeidos} mensajes sin leer`}>{c.noLeidos}</span>
                  )}
                </Link>
              </li>
            );
          })}
        </ul>
      )}
    </aside>
  );
}

export default ListaChats;
