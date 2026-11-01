import React from 'react';

/**
 * Campo de texto largo con contador de caracteres (ej: 0/500).
 * @param {{
 *   id: string, etiqueta: string, valor: string, onChange: (valor: string) => void,
 *   max: number, filas?: number, requerido?: boolean,
 * }} props
 */
function CampoDescripcion({ id, etiqueta, valor, onChange, max, filas = 8, requerido = false }) {
  return (
    <div className="reporte-campo">
      <label htmlFor={id} className="reporte-etiqueta">{etiqueta}</label>
      <textarea
        id={id}
        className="reporte-textarea"
        rows={filas}
        value={valor}
        maxLength={max}
        required={requerido}
        onChange={(e) => onChange(e.target.value)}
      />
      <span className="reporte-contador" aria-live="off">{`${valor.length}/${max}`}</span>
    </div>
  );
}

export default CampoDescripcion;
