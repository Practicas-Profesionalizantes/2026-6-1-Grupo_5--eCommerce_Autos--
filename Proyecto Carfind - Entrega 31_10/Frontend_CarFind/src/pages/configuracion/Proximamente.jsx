import React from 'react';
import SeccionConfig from '../../components/configuracion/SeccionConfig';

/**
 * Vista provisoria para las secciones del menú que todavía no tienen boceto.
 * @param {{ titulo: string }} props
 */
function Proximamente({ titulo }) {
  return (
    <SeccionConfig
      titulo={titulo}
      ayuda={[{ titulo, texto: 'Esta sección todavía no está disponible. La vamos a habilitar próximamente.' }]}
    >
      <p className="perfil-status">Esta sección estará disponible próximamente.</p>
    </SeccionConfig>
  );
}

export default Proximamente;
