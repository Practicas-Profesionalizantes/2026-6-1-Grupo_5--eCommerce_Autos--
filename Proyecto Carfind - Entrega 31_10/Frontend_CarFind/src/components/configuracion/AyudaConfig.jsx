import React, { useEffect, useId, useRef, useState } from 'react';

/**
 * Botón "Obtener ayuda ?" que abre un cuadro flotante explicando cada ítem de la vista.
 * Se cierra con la ✕, con Escape o haciendo clic fuera del cuadro.
 *
 * @param {{ titulo: string, items: { titulo: string, texto: string }[] }} props
 */
function AyudaConfig({ titulo, items }) {
  const [abierto, setAbierto] = useState(false);
  const contenedorRef = useRef(null);
  const idPanel = useId();

  useEffect(() => {
    if (!abierto) return undefined;

    const onKeyDown = (e) => {
      if (e.key === 'Escape') setAbierto(false);
    };
    const onMouseDown = (e) => {
      if (contenedorRef.current && !contenedorRef.current.contains(e.target)) setAbierto(false);
    };
    document.addEventListener('keydown', onKeyDown);
    document.addEventListener('mousedown', onMouseDown);
    return () => {
      document.removeEventListener('keydown', onKeyDown);
      document.removeEventListener('mousedown', onMouseDown);
    };
  }, [abierto]);

  return (
    <div className="config-ayuda" ref={contenedorRef}>
      {abierto && (
        <div id={idPanel} className="config-ayuda-popup" role="dialog" aria-label={`Ayuda: ${titulo}`}>
          <div className="config-ayuda-cabecera">
            <strong>Ayuda · {titulo}</strong>
            <button type="button" className="config-ayuda-cerrar" aria-label="Cerrar ayuda" onClick={() => setAbierto(false)}>
              ✕
            </button>
          </div>
          <dl className="config-ayuda-lista">
            {items.map((item) => (
              <div key={item.titulo} className="config-ayuda-item">
                <dt>{item.titulo}</dt>
                <dd>{item.texto}</dd>
              </div>
            ))}
          </dl>
        </div>
      )}
      <button
        type="button"
        className="config-ayuda-btn"
        aria-expanded={abierto}
        aria-controls={abierto ? idPanel : undefined}
        onClick={() => setAbierto((v) => !v)}
      >
        Obtener ayuda <span className="config-ayuda-icono" aria-hidden="true">?</span>
      </button>
    </div>
  );
}

export default AyudaConfig;
