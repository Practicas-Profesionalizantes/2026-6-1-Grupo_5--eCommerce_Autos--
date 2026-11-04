import React from 'react';
import { useOutletContext } from 'react-router-dom';
import SeccionConfig from '../../components/configuracion/SeccionConfig';
import { TEMAS, FUENTES, ESCALA_FUENTE } from '../../types/configuracion';

const AYUDA = [
  { titulo: 'Tema', texto: '"Sistema" usa el tema claro u oscuro de tu dispositivo y cambia solo con él. Claro y Oscuro lo fijan vos.' },
  { titulo: 'Tamaño de fuente', texto: 'Mové el control para agrandar o achicar el texto de toda la aplicación.' },
  { titulo: 'Estilo de fuente', texto: 'Cambia el tipo de letra de la aplicación. Si la fuente elegida no está instalada en tu dispositivo, se usa una parecida.' },
  { titulo: 'Guardado', texto: 'Los cambios se aplican y se guardan solos; no hace falta ningún botón.' },
];

function Apariencia() {
  const { config, actualizarApariencia } = useOutletContext();
  const { tema, escalaFuente, fuente } = config.apariencia;

  return (
    <SeccionConfig titulo="Apariencia" ayuda={AYUDA}>
      <section className="config-bloque">
        <h2 className="config-bloque-titulo">Tema</h2>
        <div role="radiogroup" aria-label="Tema" className="config-opciones">
          {TEMAS.map((t) => (
            <label key={t.id} className="config-opcion">
              <input
                type="radio"
                name="tema"
                value={t.id}
                checked={tema === t.id}
                onChange={() => actualizarApariencia({ tema: t.id })}
              />
              {t.nombre}
            </label>
          ))}
        </div>
      </section>

      <section className="config-bloque">
        <h2 className="config-bloque-titulo">Estilo de fuente</h2>

        <label className="config-campo" htmlFor="config-escala-fuente">
          <span className="config-ayuda-texto">Tamaño de fuente: ajustá el tamaño del texto para lectura.</span>
        </label>
        <div className="config-slider-fila">
          <span className="config-aa config-aa--chico" aria-hidden="true">Aa</span>
          <input
            id="config-escala-fuente"
            className="config-slider"
            type="range"
            min={ESCALA_FUENTE.min}
            max={ESCALA_FUENTE.max}
            step={ESCALA_FUENTE.paso}
            value={escalaFuente}
            onChange={(e) => actualizarApariencia({ escalaFuente: Number(e.target.value) })}
            aria-valuetext={`${escalaFuente}%`}
          />
          <span className="config-aa config-aa--grande" aria-hidden="true">Aa</span>
          <span className="config-ayuda-texto">{escalaFuente}%</span>
        </div>

        <label className="config-campo" htmlFor="config-fuente">
          <span className="config-ayuda-texto">Estilo de fuente: cambiá el tipo de letra de la aplicación.</span>
        </label>
        <select
          id="config-fuente"
          className="perfil-form-input config-input config-select"
          value={fuente}
          onChange={(e) => actualizarApariencia({ fuente: e.target.value })}
        >
          {FUENTES.map((f) => (
            <option key={f.id} value={f.id}>{f.nombre}</option>
          ))}
        </select>
      </section>
    </SeccionConfig>
  );
}

export default Apariencia;
