import React, { useState } from 'react';
import Modal from '../Modal';
import { CONTRASENA_MIN_LARGO } from '../../types/configuracion';

/**
 * Diálogo para cambiar la contraseña. La verificación real de la contraseña actual la hace el backend.
 * @param {{
 *   onGuardar: (datos: { actual: string, nueva: string }) => Promise<{ ok: boolean, error?: string }>,
 *   onCerrar: () => void,
 * }} props
 */
function CambiarContrasenaModal({ onGuardar, onCerrar }) {
  const [campos, setCampos] = useState({ actual: '', nueva: '', repetir: '' });
  const [guardando, setGuardando] = useState(false);
  const [error, setError] = useState(null);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setCampos((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (campos.nueva.length < CONTRASENA_MIN_LARGO) {
      setError(`La contraseña nueva debe tener al menos ${CONTRASENA_MIN_LARGO} caracteres.`);
      return;
    }
    if (campos.nueva !== campos.repetir) {
      setError('Las contraseñas nuevas no coinciden.');
      return;
    }
    setGuardando(true);
    setError(null);
    const res = await onGuardar({ actual: campos.actual, nueva: campos.nueva });
    setGuardando(false);
    if (res.ok) onCerrar();
    else setError(res.error);
  };

  return (
    <Modal titulo="Cambiar contraseña" onCerrar={onCerrar}>
      <form className="perfil-form" onSubmit={handleSubmit}>
        <label className="perfil-form-campo">
          Contraseña actual
          <input className="perfil-form-input" type="password" name="actual" value={campos.actual} onChange={handleChange} autoComplete="current-password" required />
        </label>
        <label className="perfil-form-campo">
          Contraseña nueva
          <input className="perfil-form-input" type="password" name="nueva" value={campos.nueva} onChange={handleChange} autoComplete="new-password" required />
        </label>
        <label className="perfil-form-campo">
          Repetir contraseña nueva
          <input className="perfil-form-input" type="password" name="repetir" value={campos.repetir} onChange={handleChange} autoComplete="new-password" required />
        </label>

        {error && <p className="perfil-error" role="alert">{error}</p>}

        <button type="submit" className="btn-perfil" disabled={guardando}>
          {guardando ? 'Guardando...' : 'Cambiar contraseña'}
        </button>
        <button type="button" className="btn-perfil btn-perfil--ghost" onClick={onCerrar} disabled={guardando}>
          Cancelar
        </button>
      </form>
    </Modal>
  );
}

export default CambiarContrasenaModal;
