import React from 'react';

/**
 * Foto redonda del usuario (o el ícono por defecto si no tiene).
 * @param {{ usuario: { fotoUrl: string|null }, grande?: boolean }} props
 */
function AvatarUsuario({ usuario, grande = false }) {
  return (
    <span className={`chat-avatar${grande ? ' chat-avatar--grande' : ''}`} aria-hidden="true">
      {usuario.fotoUrl ? <img src={usuario.fotoUrl} alt="" /> : '👤'}
    </span>
  );
}

export default AvatarUsuario;
