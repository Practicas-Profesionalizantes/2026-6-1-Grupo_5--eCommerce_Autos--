import React from 'react';
import { Link } from 'react-router-dom';
import { TERMINOS } from '../utils/terminosYCondiciones';
import '../assets/styles/App.css';

/** Términos y Condiciones. El contenido se edita en utils/terminosYCondiciones.js. */
function Terminos() {
  const { titulo, articulos } = TERMINOS;

  return (
    <main className="terminos-container">
      <h1 className="terminos-titulo">{titulo}</h1>

      {articulos.length > 1 && (
        <nav className="terminos-indice" aria-label="Índice de artículos">
          <ul>
            {articulos.map((a) => (
              <li key={a.numero}><a href={`#articulo-${a.numero}`}>Artículo {a.numero}. {a.titulo}</a></li>
            ))}
          </ul>
        </nav>
      )}

      {articulos.map((articulo) => (
        <section key={articulo.numero} id={`articulo-${articulo.numero}`} className="terminos-articulo">
          <h2 className="terminos-articulo-titulo">{`ARTÍCULO ${articulo.numero}. ${articulo.titulo.toUpperCase()}`}</h2>
          <ol className="terminos-clausulas">
            {articulo.clausulas.map((texto, i) => (
              <li key={i} className="terminos-clausula">
                <span className="terminos-numero">{`${articulo.numero}.${i + 1}.`}</span> {texto}
              </li>
            ))}
          </ol>
        </section>
      ))}

      <Link to="/" className="btn-perfil btn-perfil--ghost terminos-volver">Volver al inicio</Link>
    </main>
  );
}

export default Terminos;
