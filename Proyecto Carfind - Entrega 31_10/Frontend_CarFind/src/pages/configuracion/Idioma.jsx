import React, { useState } from 'react';
import { useOutletContext } from 'react-router-dom';
import SeccionConfig from '../../components/configuracion/SeccionConfig';
import ToggleSwitch from '../../components/ToggleSwitch';
import { IDIOMAS_DISPONIBLES } from '../../types/configuracion';

const AYUDA = [
  { titulo: 'Idioma preferido', texto: 'Es el idioma principal de la aplicación. Tocá el lápiz para cambiarlo.' },
  { titulo: 'Otros idiomas', texto: 'Idiomas adicionales que entendés. Se usan cuando falta una traducción en tu idioma preferido.' },
  { titulo: 'Agregar idioma automáticamente', texto: 'Si está activado, sumamos a "Otros idiomas" los que detectemos en tu navegador o dispositivo.' },
  { titulo: 'Guardado', texto: 'Cada cambio se guarda solo; no hace falta ningún botón.' },
];

const buscarIdioma = (codigo) => IDIOMAS_DISPONIBLES.find((i) => i.codigo === codigo);

function Idioma() {
  const { config, actualizarIdioma } = useOutletContext();
  const idioma = config.idioma;
  const preferido = buscarIdioma(idioma.preferido);

  const [editandoPreferido, setEditandoPreferido] = useState(false);
  const [agregando, setAgregando] = useState(false);
  const [nuevo, setNuevo] = useState('');

  // Idiomas que todavía se pueden sumar a "Otros idiomas".
  const disponibles = IDIOMAS_DISPONIBLES.filter((i) => i.codigo !== idioma.preferido && !idioma.otros.includes(i.codigo));

  const cambiarPreferido = (codigo) => {
    setEditandoPreferido(false);
    // Si el nuevo preferido estaba en "Otros", se saca de ahí para no duplicarlo.
    actualizarIdioma({ ...idioma, preferido: codigo, otros: idioma.otros.filter((c) => c !== codigo) });
  };

  const agregarOtro = () => {
    if (!nuevo) return;
    setAgregando(false);
    setNuevo('');
    actualizarIdioma({ ...idioma, otros: [...idioma.otros, nuevo] });
  };

  const quitarOtro = (codigo) => actualizarIdioma({ ...idioma, otros: idioma.otros.filter((c) => c !== codigo) });

  return (
    <SeccionConfig titulo="Idioma de la página" ayuda={AYUDA}>
      <section className="config-tarjeta config-tarjeta--columna">
        <div className="config-idioma-fila">
          <div>
            <h2 className="config-bloque-titulo">Idioma preferido</h2>
            {editandoPreferido ? (
              <select
                className="perfil-form-input config-input config-select"
                value={idioma.preferido}
                onChange={(e) => cambiarPreferido(e.target.value)}
                aria-label="Idioma preferido"
              >
                {IDIOMAS_DISPONIBLES.map((i) => (
                  <option key={i.codigo} value={i.codigo}>{i.idioma} ({i.region})</option>
                ))}
              </select>
            ) : (
              <p className="config-idioma-nombre">
                {preferido?.idioma ?? idioma.preferido}
                <small className="config-ayuda-texto"> {preferido?.region}</small>
              </p>
            )}
          </div>
          <button
            type="button"
            className="btn-perfil btn-perfil--ghost config-btn-chico"
            onClick={() => setEditandoPreferido((v) => !v)}
            aria-label={editandoPreferido ? 'Cancelar edición del idioma preferido' : 'Editar idioma preferido'}
          >
            {editandoPreferido ? '✕' : '✎'}
          </button>
        </div>

        <hr className="config-separador" />

        <div>
          <h2 className="config-bloque-titulo">Otros idiomas</h2>
          {idioma.otros.length > 0 && (
            <ul className="config-chips">
              {idioma.otros.map((codigo) => {
                const i = buscarIdioma(codigo);
                return (
                  <li key={codigo} className="config-chip">
                    {i ? `${i.idioma} (${i.region})` : codigo}
                    <button type="button" className="config-chip-quitar" onClick={() => quitarOtro(codigo)} aria-label={`Quitar ${i?.idioma ?? codigo}`}>
                      ✕
                    </button>
                  </li>
                );
              })}
            </ul>
          )}

          {agregando ? (
            <div className="config-idioma-agregar">
              <select
                className="perfil-form-input config-input config-select"
                value={nuevo}
                onChange={(e) => setNuevo(e.target.value)}
                aria-label="Idioma a agregar"
              >
                <option value="">Elegí un idioma</option>
                {disponibles.map((i) => (
                  <option key={i.codigo} value={i.codigo}>{i.idioma} ({i.region})</option>
                ))}
              </select>
              <button type="button" className="btn-perfil" onClick={agregarOtro} disabled={!nuevo}>Agregar</button>
              <button type="button" className="btn-perfil btn-perfil--ghost" onClick={() => { setAgregando(false); setNuevo(''); }}>Cancelar</button>
            </div>
          ) : (
            <button
              type="button"
              className="btn-perfil btn-perfil--ghost config-btn-agregar"
              onClick={() => setAgregando(true)}
              disabled={disponibles.length === 0}
            >
              + Agregar otro idioma
            </button>
          )}
        </div>
      </section>

      <section className="config-tarjeta">
        <span>Agregar idioma automáticamente: {idioma.agregarAutomaticamente ? 'Activado' : 'Desactivado'}</span>
        <ToggleSwitch
          checked={idioma.agregarAutomaticamente}
          onChange={(v) => actualizarIdioma({ ...idioma, agregarAutomaticamente: v })}
          label="Agregar idioma automáticamente"
        />
      </section>
    </SeccionConfig>
  );
}

export default Idioma;
