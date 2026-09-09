import React from 'react';
import { useNavigate } from 'react-router-dom';
import corollaImg from '../assets/images/CorollaEjemplo.jpg';

const ProductCard = ({ publicacion }) => {
    const navigate = useNavigate();

    // Valores por defecto si aún no vienen desde la API / DB
    const {
        id = 1,
        titulo = "Corolla 2017",
        precio = 19235000,
        moneda = "$",
        imagen = corollaImg,
        vendedor = "Vendedor"
    } = publicacion || {};

    const handleCardClick = () => {
        // Redirige a la vista de detalle
        navigate(`/publicacion/${id}`);
    };

    return (
        <div className="product-card" onClick={handleCardClick} style={{ cursor: 'pointer' }}>
            <div className="card-seller">{vendedor}</div>
            <div className="card-image-placeholder">
                <img src={imagen} alt={titulo} className="card-img" />
            </div>
            <div className="card-info">
                <span>{titulo}</span>
                <strong>{moneda} {precio.toLocaleString('es-AR')}</strong>
            </div>
        </div>
    );
};

export default ProductCard;