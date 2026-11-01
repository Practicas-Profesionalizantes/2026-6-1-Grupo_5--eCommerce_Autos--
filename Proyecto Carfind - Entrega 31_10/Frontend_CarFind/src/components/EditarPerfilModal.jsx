import React, { useState } from 'react';
import Modal from './Modal';

const CAMPOS = [
  { name: 'nombre', label: 'Nombre', type: 'text' },
  { name: 'apellido', label: 'Apellido', type: 'text' },
  { name: 'ubicacion', label: 'Ubicación', type: 'text' },
  { name: 'telefono', label: 'Teléfono', type: 'tel' },
];

/**
 * Edición de los datos del perfil. El guardado lo resuelve el backend (vía onGuardar).
 * @param {{
 *   usuario: import('../types/perfil').Perfil,
 *   onGuardar: (datos: import('../types/perfil').PerfilEditable) => Promise<{ ok: boolean, error?: string }>,
 *   onCerrar: () => void,
 * }} props
 */
function EditarPerfilModal({ usuario, onGuardar, onCerrar }) {
  const [datos, setDatos] = useState({
    nombre: usuario.nombre,
    apellido: usuario.apellido,
    ubicacion: usuario.ubicacion,
    telefono: usuario.telefono,
  });
  const [guardando, setGuardando] = useState(false);
  const [error, setError] = useState(null);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setDatos((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setGuardando(true);
    setError(null);
    const res = await onGuardar(datos);
    setGuardando(false);
    if (res.ok) onCerrar();
    else setError(res.error);
  };

  return (
    <Modal titulo="Editar perfil" onCerrar={onCerrar}>
      <form className="perfil-form" onSubmit={handleSubmit}>
        {CAMPOS.map(({ name, label, type }) => (
          <label key={name} className="perfil-form-campo">
            {label}
            <input
              className="perfil-form-input"
              type={type}
              name={name}
              value={datos[name]}
              onChange={handleChange}
              required
            />
          </label>
        ))}

        {error && <p className="perfil-error" role="alert">{error}</p>}

        <button type="submit" className="btn-perfil" disabled={guardando}>
          {guardando ? 'Guardando...' : 'Guardar cambios'}
        </button>
        <button type="button" className="btn-perfil btn-perfil--ghost" onClick={onCerrar} disabled={guardando}>
          Cancelar
        </button>
      </form>
    </Modal>
  );
}

export default EditarPerfilModal;
