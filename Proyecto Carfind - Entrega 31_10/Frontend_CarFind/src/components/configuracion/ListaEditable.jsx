import React from 'react';

/**
 * Lista de campos de texto que se pueden agregar y quitar (teléfonos, otras direcciones).
 *
 * @param {{
 *   etiqueta: string,
 *   valores: string[],
 *   onChange: (valores: string[]) => void,
 *   tipo?: string,
 *   textoAgregar: string,
 *   textoVacio?: string,
 *   maximo?: number,
 * }} props
 */
function ListaEditable({ etiqueta, valores, onChange, tipo = 'text', textoAgregar, textoVacio, maximo = 5 }) {
  const cambiar = (indice, valor) => onChange(valores.map((v, i) => (i === indice ? valor : v)));
  const quitar = (indice) => onChange(valores.filter((_, i) => i !== indice));

  return (
    <fieldset className="config-lista">
      <legend className="config-campo-etiqueta">{etiqueta}</legend>

      {valores.length === 0 && textoVacio && <p className="config-ayuda-texto">{textoVacio}</p>}

      {valores.map((valor, indice) => (
        // El índice sirve de key: los campos son controlados y la lista no se reordena.
        <div key={indice} className="config-lista-fila">
          <input
            className="perfil-form-input config-input"
            type={tipo}
            value={valor}
            onChange={(e) => cambiar(indice, e.target.value)}
            aria-label={`${etiqueta} ${indice + 1}`}
          />
          <button
            type="button"
            className="btn-perfil btn-perfil--ghost config-btn-chico"
            onClick={() => quitar(indice)}
            aria-label={`Quitar ${etiqueta.toLowerCase()} ${indice + 1}`}
          >
            ✕
          </button>
        </div>
      ))}

      {valores.length < maximo && (
        <button type="button" className="btn-perfil btn-perfil--ghost config-btn-agregar" onClick={() => onChange([...valores, ''])}>
          + {textoAgregar}
        </button>
      )}
    </fieldset>
  );
}

export default ListaEditable;
