import React from 'react';
import { useNavigate } from 'react-router-dom';
import Icono from './Icono';
import { imagenAuto, usarImagenGenerica } from '../utils/imagenAuto';

const ProductCard = ({ publicacion }) => {
    const navigate = useNavigate();

    const {
        id,
        titulo = "",
        precio = 0,
        moneda = "$",
        imagen,
        vendedor = "Vendedor"
    } = publicacion || {};

    const irAlDetalle = () => navigate(`/publicacion/${id}`);

    return (
        <article
            className="product-card"
            onClick={irAlDetalle}
            onKeyDown={(e) => e.key === 'Enter' && irAlDetalle()}
            tabIndex={0}
            role="link"
            aria-label={`${titulo}, ${moneda} ${Number(precio).toLocaleString('es-AR')}`}
        >
            <div className="card-image-placeholder">
                <img src={imagenAuto(imagen)} alt={titulo} className="card-img" onError={usarImagenGenerica} loading="lazy" />
            </div>
            <div className="card-info">
                <span className="card-titulo">{titulo}</span>
                <strong className="card-precio">{moneda} {Number(precio).toLocaleString('es-AR')}</strong>
                <span className="card-seller">
                    <Icono nombre="usuario" tamano={14} /> {vendedor}
                </span>
            </div>
        </article>
    );
};

export default ProductCard;
