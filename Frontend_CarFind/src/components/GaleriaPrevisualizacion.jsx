import React from 'react';
import formatearPrecio from '../utils/formatearPrecio';

function GaleriaPrevisualizacion({ fotos, fotoPrincipalIndex, onSelectFoto, onRemoveFoto, precio }) {
    return (
        <div className="gallery-container">
            <div className="main-image-box preview-main-box">
                {fotos.length > 0 ? (
                    <img src={fotos[fotoPrincipalIndex]} alt="Vista previa" />
                ) : (
                    <div className="preview-placeholder">
                        <p>Vista previa de tu publicación</p>
                        <span className="preview-placeholder-icon"></span>
                    </div>
                )}
            </div>

            <div className="thumbnails-column">
                {fotos.map((foto, idx) => (
                    <div key={idx} className="thumb-wrapper">
                        <button 
                            type="button" 
                            className={`thumb-btn thumb-preview-btn ${fotoPrincipalIndex === idx ? 'active' : ''}`}
                            onClick={() => onSelectFoto(idx)}
                        >
                            <img src={foto} alt={`Preview ${idx}`} />
                        </button>
                        <button 
                            type="button" 
                            className="btn-remove-thumb"
                            onClick={(e) => onRemoveFoto(e, idx)}
                            title="Eliminar imagen"
                        >
                            ✕
                        </button>
                    </div>
                ))}
            </div>

            <div className="price-tag">
                {formatearPrecio(precio)}
            </div>
        </div>
    );
}

export default GaleriaPrevisualizacion;