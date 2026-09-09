import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import Header from '../components/Header';
import FormularioCrearPublicacion from '../components/FormularioCrearPublicacion';
import GaleriaPrevisualizacion from '../components/GaleriaPrevisualizacion';
import formatearPrecio from '../utils/formatearPrecio';
import '../assets/styles/App.css';
import '../assets/styles/variables.css';

function CrearPublicacion() {
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        fotos: [],
        precio: '',
        marca: '',
        modelo: '',
        version: '',
        estado: 'Sin detalles',
        descripcionDetalles: '',
        color: '',
        papelesAlDia: 'Si',
        papelesFaltantes: '',
        kilometraje: '',
        transmision: 'Manual',
        combustible: 'Nafta',
        caracteristicasAdicionales: '',
        descripcionLibre: ''
    });

    const [fotoPrincipalIndex, setFotoPrincipalIndex] = useState(0);

    const handleChange = (e) => {
        const { name, value } = e.target;
        if (name === 'marca') {
            setFormData(prev => ({ ...prev, marca: value, modelo: '', version: '' }));
        } else if (name === 'modelo') {
            setFormData(prev => ({ ...prev, modelo: value, version: '' }));
        } else {
            setFormData(prev => ({ ...prev, [name]: value }));
        }
    };

    const handleFiles = (files) => {
        const selectedFiles = Array.from(files).slice(0, 10 - formData.fotos.length);
        const newPhotos = selectedFiles.map(file => URL.createObjectURL(file));
        setFormData(prev => ({ ...prev, fotos: [...prev.fotos, ...newPhotos] }));
    };

    const handleDrop = (e) => {
        e.preventDefault();
        if (e.dataTransfer.files && e.dataTransfer.files.length > 0) {
            handleFiles(e.dataTransfer.files);
        }
    };

    const handleRemoveFoto = (e, indexToRemove) => {
        e.stopPropagation();
        setFormData(prev => ({
            ...prev,
            fotos: prev.fotos.filter((_, idx) => idx !== indexToRemove)
        }));

        if (fotoPrincipalIndex >= indexToRemove && fotoPrincipalIndex > 0) {
            setFotoPrincipalIndex(prev => prev - 1);
        }
    };

    const tituloAutogenerado = [
        formData.marca,
        formData.modelo,
        formData.version,
        formData.estado,
        formData.color,
        formData.kilometraje ? `${formData.kilometraje} km` : ''
    ].filter(Boolean).join(' - ') || 'Nombre del producto';

    const usuarioTelefono = "5491112345678";
    const mensajeWhatsApp = encodeURIComponent(`Hola, estoy interesado en tu publicación: ${tituloAutogenerado}`);
    const linkWhatsApp = `https://wa.me/${usuarioTelefono}?text=${mensajeWhatsApp}`;

    const handleSubmit = (e) => {
        e.preventDefault();
        if (formData.estado === 'Con detalles' && !formData.descripcionDetalles.trim()) {
            alert('Por favor especifique los detalles del vehículo.');
            return;
        }
        if (formData.papelesAlDia === 'No' && !formData.papelesFaltantes.trim()) {
            alert('Por favor especifique qué papeles faltan.');
            return;
        }

        console.log('Publicación procesada:', formData);
        alert('Publicación creada con éxito.');
        navigate('/');
    };

    return (
        <div className="app-wrapper">
            <Header />

            <div className="publicacion-container">
                <div className="crear-publicacion-grid">
                    
                    {/* FORMULARIO */}
                    <FormularioCrearPublicacion 
                        formData={formData}
                        onChange={handleChange}
                        onFilesAdded={handleFiles}
                        onDrop={handleDrop}
                        onSubmit={handleSubmit}
                    />

                    {/* VISTA PREVIA */}
                    <div className="info-column">
                        <div className="publicacion-main-grid">
                            
                            <GaleriaPrevisualizacion 
                                fotos={formData.fotos}
                                fotoPrincipalIndex={fotoPrincipalIndex}
                                onSelectFoto={setFotoPrincipalIndex}
                                onRemoveFoto={handleRemoveFoto}
                                precio={formData.precio}
                            />

                            {/* Detalle y Botones de contacto */}
                            <div className="info-column">
                                <h3 className="product-title">{tituloAutogenerado}</h3>
                                <div className="price-tag">{formatearPrecio(formData.precio)}</div>

                                <div className="specs-card">
                                    <label><strong>Características del producto:</strong></label>
                                    <textarea 
                                        name="caracteristicasAdicionales" 
                                        value={formData.caracteristicasAdicionales} 
                                        onChange={handleChange} 
                                        className="search-bar full-width-input"
                                        placeholder="Detalles y características adicionales..."
                                        rows={5}
                                    />
                                </div>

                                <div className="contact-actions">
                                    <button type="button" className="btn-ask" onClick={() => alert('Conversación privada en desarrollo.')}>Preguntar</button>
                                    <a href={linkWhatsApp} target="_blank" rel="noopener noreferrer">
                                        <button type="button" className="btn-ws">WhatsApp</button>
                                    </a>
                                </div>
                            </div>
                        </div>

                        {/* Descripción Libre */}
                        <div className="specs-card">
                            <label><strong>Descripción:</strong></label>
                            <textarea 
                                name="descripcionLibre" 
                                value={formData.descripcionLibre} 
                                onChange={handleChange} 
                                className="search-bar full-width-input"
                                placeholder="Descripción libre del vehículo (motivo de venta, detalles extras, etc.)..." 
                                rows={6}
                            />
                        </div>

                        {/* Publicar / Soporte */}
                        <div className="support-box">
                            <button type="button" onClick={handleSubmit} className="navButton btn-publicar">
                                Publicar
                            </button>

                            <p className="soporte-texto">
                                ¿Tuviste un problema con la publicación?{' '}
                                <Link to="/soporte" className="soporte-link">
                                    Avísanos.
                                </Link>
                            </p>
                            <button type="button" className="btn-support" onClick={() => navigate('/soporte')}>
                                SOPORTE/REPORTAR
                            </button>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    );
}

export default CrearPublicacion;