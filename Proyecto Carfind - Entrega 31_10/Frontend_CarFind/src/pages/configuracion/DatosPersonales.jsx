import React, { useRef, useState } from 'react';
import { useOutletContext } from 'react-router-dom';
import SeccionConfig from '../../components/configuracion/SeccionConfig';
import ListaEditable from '../../components/configuracion/ListaEditable';
import CambiarContrasenaModal from '../../components/configuracion/CambiarContrasenaModal';
import FotoPerfilModal from '../../components/FotoPerfilModal';
import { GENEROS } from '../../types/configuracion';

const AYUDA = [
  { titulo: 'Foto de perfil', texto: 'Tocá la foto para subir una nueva o eliminarla. Se ve en tu perfil y en tus publicaciones.' },
  { titulo: 'Nombre y apellido', texto: 'Cómo te ven otros usuarios en tu perfil.' },
  { titulo: 'Género', texto: 'Dato opcional. Podés elegir "Prefiero no decirlo".' },
  { titulo: 'Correo electrónico', texto: 'Es el correo donde recibís los avisos y el que usás para iniciar sesión.' },
  { titulo: 'Teléfono(s)', texto: 'El primero es tu teléfono principal. Podés agregar más.' },
  { titulo: 'Fecha de nacimiento', texto: 'Se usa para verificar tu edad. No se muestra en tu perfil.' },
  { titulo: 'Dirección y otras direcciones', texto: 'Tu dirección principal y otras que quieras tener guardadas.' },
  { titulo: 'Contraseña', texto: 'Cambiala cuando quieras. Te pedimos la actual para confirmar que sos vos.' },
  { titulo: 'Guardar cambios', texto: 'Aplica todos los datos de esta pantalla de una sola vez.' },
];

function DatosPersonales() {
  const { config, guardarDatosPersonales, cambiarContrasena, subirFoto, eliminarFoto, fotoError, savingFoto } = useOutletContext();
  const datos = config.datosPersonales;

  const [form, setForm] = useState({
    nombre: datos.nombre,
    apellido: datos.apellido,
    genero: datos.genero,
    correo: datos.correo,
    telefonos: datos.telefonos,
    fechaNacimiento: datos.fechaNacimiento,
    direccion: datos.direccion,
    otrasDirecciones: datos.otrasDirecciones,
  });
  const [hoy] = useState(() => new Date().toISOString().slice(0, 10));
  const [guardando, setGuardando] = useState(false);
  const [mensaje, setMensaje] = useState(null); // { tipo: 'ok' | 'error', texto }
  const [fotoModalAbierto, setFotoModalAbierto] = useState(false);
  const [contrasenaAbierta, setContrasenaAbierta] = useState(false);
  const inputFotoRef = useRef(null);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setGuardando(true);
    setMensaje(null);
    const limpiar = (lista) => lista.map((v) => v.trim()).filter(Boolean);
    const res = await guardarDatosPersonales({
      ...form,
      telefonos: limpiar(form.telefonos),
      otrasDirecciones: limpiar(form.otrasDirecciones),
    });
    setGuardando(false);
    setMensaje(res.ok ? { tipo: 'ok', texto: 'Cambios guardados.' } : { tipo: 'error', texto: res.error });
  };

  // Abre el explorador de archivos (el clic viene de un gesto del usuario, así que el navegador lo permite).
  const handleSubir = () => {
    setFotoModalAbierto(false);
    inputFotoRef.current?.click();
  };

  const handleArchivoElegido = (e) => {
    const archivo = e.target.files?.[0];
    e.target.value = ''; // permite volver a elegir el mismo archivo
    if (archivo) subirFoto(archivo);
  };

  const handleEliminar = () => {
    setFotoModalAbierto(false);
    eliminarFoto();
  };

  return (
    <SeccionConfig titulo="Datos personales" ayuda={AYUDA}>
      <form className="config-form" onSubmit={handleSubmit}>
        <div className="config-campo config-foto-fila">
          <span className="config-campo-etiqueta">Foto de perfil</span>
          <button
            type="button"
            className="perfil-foto-btn config-foto"
            onClick={() => setFotoModalAbierto(true)}
            disabled={savingFoto}
            aria-label="Cambiar foto de perfil"
            title="Cambiar foto de perfil"
          >
            {datos.fotoUrl ? <img src={datos.fotoUrl} alt="Foto de perfil" /> : <span aria-hidden="true">👤</span>}
          </button>
          <input
            ref={inputFotoRef}
            type="file"
            accept="image/*,.heic,.heif"
            className="perfil-input-oculto"
            onChange={handleArchivoElegido}
            tabIndex={-1}
            aria-hidden="true"
          />
          {savingFoto && <p className="perfil-status">Actualizando foto...</p>}
          {fotoError && <p className="perfil-error" role="alert">{fotoError}</p>}
        </div>

        <label className="perfil-form-campo">
          Nombre
          <input className="perfil-form-input config-input" name="nombre" value={form.nombre} onChange={handleChange} autoComplete="given-name" required />
        </label>
        <label className="perfil-form-campo">
          Apellido
          <input className="perfil-form-input config-input" name="apellido" value={form.apellido} onChange={handleChange} autoComplete="family-name" required />
        </label>
        <label className="perfil-form-campo">
          Género
          <select className="perfil-form-input config-input" name="genero" value={form.genero} onChange={handleChange}>
            <option value="">Sin especificar</option>
            {GENEROS.map((g) => (
              <option key={g} value={g}>{g}</option>
            ))}
          </select>
        </label>
        <label className="perfil-form-campo">
          Correo electrónico
          <input className="perfil-form-input config-input" type="email" name="correo" value={form.correo} onChange={handleChange} autoComplete="email" required />
        </label>

        <ListaEditable
          etiqueta="Teléfono"
          valores={form.telefonos}
          onChange={(telefonos) => setForm((prev) => ({ ...prev, telefonos }))}
          tipo="tel"
          textoAgregar="Agregar teléfono"
          textoVacio="Todavía no cargaste ningún teléfono."
        />

        <label className="perfil-form-campo">
          Fecha de nacimiento
          <input className="perfil-form-input config-input" type="date" name="fechaNacimiento" value={form.fechaNacimiento} onChange={handleChange} max={hoy} autoComplete="bday" />
        </label>
        <label className="perfil-form-campo">
          Dirección
          <input className="perfil-form-input config-input" name="direccion" value={form.direccion} onChange={handleChange} autoComplete="street-address" />
        </label>

        <ListaEditable
          etiqueta="Otras direcciones"
          valores={form.otrasDirecciones}
          onChange={(otrasDirecciones) => setForm((prev) => ({ ...prev, otrasDirecciones }))}
          textoAgregar="Agregar dirección"
          textoVacio="No tenés otras direcciones guardadas."
        />

        <div className="config-campo">
          <span className="config-campo-etiqueta">Contraseña</span>
          <div className="config-contrasena-fila">
            <span className="config-contrasena-valor" aria-label="Contraseña oculta">••••••••</span>
            <button type="button" className="btn-perfil btn-perfil--ghost" onClick={() => setContrasenaAbierta(true)}>
              Cambiar contraseña
            </button>
          </div>
        </div>

        <div className="config-acciones">
          <button type="submit" className="btn-perfil" disabled={guardando}>
            {guardando ? 'Guardando...' : 'Guardar cambios'}
          </button>
          {mensaje && (
            <p className={mensaje.tipo === 'ok' ? 'config-ok' : 'perfil-error'} role={mensaje.tipo === 'ok' ? 'status' : 'alert'}>
              {mensaje.texto}
            </p>
          )}
        </div>
      </form>

      {fotoModalAbierto && (
        <FotoPerfilModal
          tieneFoto={Boolean(datos.fotoUrl)}
          onSubir={handleSubir}
          onEliminar={handleEliminar}
          onCerrar={() => setFotoModalAbierto(false)}
        />
      )}
      {contrasenaAbierta && (
        <CambiarContrasenaModal onGuardar={cambiarContrasena} onCerrar={() => setContrasenaAbierta(false)} />
      )}
    </SeccionConfig>
  );
}

export default DatosPersonales;
