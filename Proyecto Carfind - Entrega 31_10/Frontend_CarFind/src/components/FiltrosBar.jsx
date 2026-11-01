import React from 'react';

/**
 * Barra de filtros del inicio.
 * @param {{
 *   campos: { nombre: string, etiqueta: string, opciones: { valor: string|number, texto: string }[] }[],
 *   filtrosSeleccionados: Record<string, string>,
 *   onFiltroChange: (e: import('react').ChangeEvent<HTMLSelectElement>) => void,
 *   onLimpiarFiltros: () => void,
 * }} props
 */
function FiltrosBar({ campos, filtrosSeleccionados, onFiltroChange, onLimpiarFiltros }) {
  return (
    <div className="filter-group">
      {campos.map((campo) => (
        <div key={campo.nombre} className="filter-item">
          <label htmlFor={`filtro-${campo.nombre}`}>{campo.etiqueta}</label>
          <select 
            id={`filtro-${campo.nombre}`}
            name={campo.nombre} 
            className="filter-select"
            onChange={onFiltroChange}
            value={filtrosSeleccionados[campo.nombre] || ''}
          >
            <option value="">Todos</option>
            {campo.opciones.map((opcion) => (
              <option key={opcion.valor} value={opcion.valor}>{opcion.texto}</option>
            ))}
          </select>
        </div>
      ))}
      <button className="clear-filters-btn" onClick={onLimpiarFiltros}>
        Limpiar filtros
      </button>
    </div>
  );
}

export default FiltrosBar;
