import React from 'react';
import formatearPrecio from '../utils/formatearPrecio';

/**
 * Resumen de la compra: listado [Artículo | Precio] + total.
 * El botón Comprar está deshabilitado según el boceto.
 * @param {{ items: import('../types/wishlist').ResumenItem[], total: number }} props
 */
function ResumenCompra({ items, total }) {
  return (
    <aside className="resumen-compra">
      <h2 className="resumen-titulo">Resumen de la Compra</h2>

      <table className="resumen-tabla">
        <thead>
          <tr>
            <th>Artículo</th>
            <th className="col-precio">Precio</th>
          </tr>
        </thead>
        <tbody>
          {items.length === 0 ? (
            <tr>
              <td colSpan={2} className="resumen-vacio">Todavía no hay artículos.</td>
            </tr>
          ) : (
            items.map((it) => (
              <tr key={it.id}>
                <td>{it.titulo}</td>
                <td className="col-precio">{formatearPrecio(it.precio)}</td>
              </tr>
            ))
          )}
        </tbody>
        <tfoot>
          <tr>
            <td>Total</td>
            <td className="col-precio">{formatearPrecio(total)}</td>
          </tr>
        </tfoot>
      </table>

      <button type="button" className="btn-comprar" disabled aria-disabled="true" title="Próximamente">
        Comprar
      </button>
    </aside>
  );
}

export default ResumenCompra;
