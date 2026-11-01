import React, { useEffect } from 'react';

/**
 * Cuadro de diálogo genérico: se cierra con Escape o haciendo clic fuera.
 * @param {{ titulo: string, onCerrar: () => void, ancho?: boolean, children: React.ReactNode }} props
 *   ancho: versión más grande, para contenido extenso (por ejemplo, el detalle de un reporte)
 */
function Modal({ titulo, onCerrar, ancho = false, children }) {
  useEffect(() => {
    const onKeyDown = (e) => {
      if (e.key === 'Escape') onCerrar();
    };
    document.addEventListener('keydown', onKeyDown);
    return () => document.removeEventListener('keydown', onKeyDown);
  }, [onCerrar]);

  return (
    <div className="modal-backdrop" onClick={onCerrar}>
      <div
        className={`modal-box${ancho ? ' modal-box--ancho' : ''}`}
        role="dialog"
        aria-modal="true"
        aria-label={titulo}
        onClick={(e) => e.stopPropagation()}
      >
        <h2 className="modal-title">{titulo}</h2>
        {children}
      </div>
    </div>
  );
}

export default Modal;
