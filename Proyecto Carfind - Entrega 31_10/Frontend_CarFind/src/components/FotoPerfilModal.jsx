import React from 'react';
import Modal from './Modal';

/**
 * Cuadro "¿Qué deseas hacer?" que aparece al hacer clic en la foto de perfil.
 * "Eliminar foto de perfil" solo se ofrece si el usuario ya tiene una.
 * @param {{ tieneFoto: boolean, onSubir: () => void, onEliminar: () => void, onCerrar: () => void }} props
 */
function FotoPerfilModal({ tieneFoto, onSubir, onEliminar, onCerrar }) {
  return (
    <Modal titulo="¿Qué deseas hacer?" onCerrar={onCerrar}>
      <button type="button" className="btn-perfil" onClick={onSubir}>
        Subir foto de perfil
      </button>
      {tieneFoto && (
        <button type="button" className="btn-perfil btn-perfil--danger" onClick={onEliminar}>
          Eliminar foto de perfil
        </button>
      )}
      <button type="button" className="btn-perfil btn-perfil--ghost" onClick={onCerrar}>
        Cancelar
      </button>
    </Modal>
  );
}

export default FotoPerfilModal;
