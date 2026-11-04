import React, { useEffect, useState } from 'react';
import { useNavigate, useParams, Link } from 'react-router-dom';
import FormularioCrearPublicacion from '../components/FormularioCrearPublicacion';
import GaleriaPrevisualizacion from '../components/GaleriaPrevisualizacion';
import formatearPrecio from '../utils/formatearPrecio';
import useAuth from '../hooks/useAuth';
import { crearPublicacion, getPublicacionParaEditar, actualizarPublicacion } from '../services/publicacionService';
import '../assets/styles/App.css';
import '../assets/styles/variables.css';

/**
 * Crear publicación (/crear-publicacion) y Editar publicación (/publicacion/:id/editar):
 * es el mismo formulario; al editar se cargan los datos y las fotos que ya tenía.
 */
function CrearPublicacion() {
    const navigate = useNavigate();
    const { id: idEdicion } = useParams();
    const editando = Boolean(idEdicion);
    const { usuario, cargando } = useAuth();

    // Publicar y editar requieren sesión: el backend asocia la publicación al usuario logueado.
    useEffect(() => {
        const desde = editando ? `/publicacion/${idEdicion}/editar` : '/crear-publicacion';
        if (!cargando && !usuario) navigate('/login', { replace: true, state: { desde } });
    }, [cargando, usuario, navigate, editando, idEdicion]);

    const [formData, setFormData] = useState({
        fotos: [],
        precio: '',
        marca: '',
        modelo: '',
        version: '',
        anio: '',
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

    // Archivos originales de las fotos (formData.fotos guarda solo las URLs de vista previa).
    // Al editar, las fotos que ya estaban guardadas tienen null: no hay que volver a subirlas.
    const [archivos, setArchivos] = useState([]);
    const [publicando, setPublicando] = useState(false);
    const [error, setError] = useState(null);
    const [cargandoEdicion, setCargandoEdicion] = useState(editando);
    const [errorCarga, setErrorCarga] = useState(null);

    // Al editar: trae los datos actuales de la publicación (el backend responde 403 si no es del usuario).
    useEffect(() => {
        if (!editando || !usuario) return;
        let vigente = true;
        getPublicacionParaEditar(idEdicion)
            .then(({ fotos, ...campos }) => {
                if (!vigente) return;
                delete campos.id;
                delete campos.estadoPublicacion;
                setFormData((prev) => ({ ...prev, ...campos, fotos }));
                setArchivos(fotos.map(() => null));
            })
            .catch((e) => vigente && setErrorCarga(e.message || 'No pudimos cargar la publicación.'))
            .finally(() => vigente && setCargandoEdicion(false));
        return () => {
            vigente = false;
        };
    }, [editando, idEdicion, usuario]);

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
        setArchivos(prev => [...prev, ...selectedFiles]);
    };

    const handleDrop = (e) => {
        e.preventDefault();
        if (e.dataTransfer.files && e.dataTransfer.files.length > 0) {
            handleFiles(e.dataTransfer.files);
        }
    };

    const handleRemoveFoto = (e, indexToRemove) => {
        e.stopPropagation();
        if (archivos[indexToRemove]) URL.revokeObjectURL(formData.fotos[indexToRemove]);
        setArchivos(prev => prev.filter((_, idx) => idx !== indexToRemove));
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

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!formData.precio || !formData.marca || !formData.modelo || !formData.kilometraje || !formData.color) {
            setError('Completá precio, marca, modelo, color y kilometraje.');
            return;
        }
        if (formData.estado === 'Con detalles' && !formData.descripcionDetalles.trim()) {
            alert('Por favor especifique los detalles del vehículo.');
            return;
        }
        if (formData.papelesAlDia === 'No' && !formData.papelesFaltantes.trim()) {
            alert('Por favor especifique qué papeles faltan.');
            return;
        }

        setPublicando(true);
        setError(null);
        try {
            const datos = { ...formData };
            delete datos.fotos; // las fotos viajan como archivos, no como URLs de vista previa
            const nuevas = archivos.filter(Boolean);
            let id = idEdicion;
            if (editando) {
                const conservadas = formData.fotos.filter((_, i) => !archivos[i]);
                await actualizarPublicacion(idEdicion, datos, conservadas, nuevas);
            } else {
                ({ id } = await crearPublicacion(datos, nuevas));
            }
            formData.fotos.forEach((url, i) => archivos[i] && URL.revokeObjectURL(url));
            navigate(`/publicacion/${id}`);
        } catch (err) {
            setError(err.message || (editando ? 'No pudimos guardar los cambios.' : 'No pudimos crear la publicación.') + ' Intentá de nuevo.');
            setPublicando(false);
        }
    };

    if (editando && (cargandoEdicion || errorCarga)) {
        return (
            <main className="publicacion-container">
                {errorCarga ? (
                    <div className="wishlist-error" role="alert">
                        {errorCarga} <Link to="/perfil">Volver a mis publicaciones</Link>
                    </div>
                ) : (
                    <p className="wishlist-status">Cargando publicación...</p>
                )}
            </main>
        );
    }

    return (
        <>
            <div className="publicacion-container">
                <div className="crear-publicacion-grid">
                    
                    {/* FORMULARIO */}
                    <FormularioCrearPublicacion 
                        titulo={editando ? 'Editar publicación' : 'Artículo en venta'}
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
                            {error && <p className="perfil-error" role="alert">{error}</p>}
                            <button type="button" onClick={handleSubmit} className="navButton btn-publicar" disabled={publicando}>
                                {publicando ? 'Guardando...' : editando ? 'Guardar cambios' : 'Publicar'}
                            </button>

                            <p className="soporte-texto">
                                ¿Tuviste un problema con la publicación?{' '}
                                <Link to="/reportes" className="soporte-link">
                                    Avísanos.
                                </Link>
                            </p>
                            <button type="button" className="btn-support" onClick={() => navigate('/reportes')}>
                                SOPORTE/REPORTAR
                            </button>
                        </div>
                    </div>

                </div>
            </div>
        </>
    );
}

export default CrearPublicacion;