import React from 'react';

const FILTROS_CAMPOS = ['marca', 'modelo', 'año', 'kilometraje', 'precio'];

function FiltrosBar({ filtrosSeleccionados, onFiltroChange, onLimpiarFiltros }) {
  return (
    <div className="filter-group">
      {FILTROS_CAMPOS.map((filtro) => (
        <div key={filtro} className="filter-item">
          <label>{filtro.charAt(0).toUpperCase() + filtro.slice(1)}</label>
          <select 
            name={filtro} 
            onChange={onFiltroChange}
            value={filtrosSeleccionados[filtro] || ''}
          >
            <option value="">Todos</option>
            <option value="ejemplo1">Ejemplo 1</option>
            <option value="ejemplo2">Ejemplo 2</option>
          </select>
        </div>
      ))}
      <button className="clear-filters-btn" onClick={onLimpiarFiltros}>
        Limpiar Filtros
      </button>
    </div>
  );
}

export default FiltrosBar;