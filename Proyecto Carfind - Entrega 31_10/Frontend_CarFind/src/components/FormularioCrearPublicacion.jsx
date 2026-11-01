import React from 'react';
import { MARCAS_MODELOS, COLORES_BASE } from '../utils/constantesAutos';

const TRANSMISIONES = ['Manual', 'Automático'];
const COMBUSTIBLES = ['Nafta', 'Gasoil', 'Eléctrico'];

/** Opciones fijas + el valor actual si no está entre ellas (al editar una publicación vieja, por ejemplo). */
const conActual = (opciones, actual) => (actual && !opciones.includes(actual) ? [...opciones, actual] : opciones);

function FormularioCrearPublicacion({ formData, onChange, onFilesAdded, onDrop, onSubmit, titulo = 'Artículo en venta' }) {
    const marcas = conActual(Object.keys(MARCAS_MODELOS), formData.marca);
    const modelos = conActual(Object.keys(MARCAS_MODELOS[formData.marca] ?? {}), formData.modelo);
    const versiones = conActual(MARCAS_MODELOS[formData.marca]?.[formData.modelo] ?? [], formData.version);

    return (
        <form onSubmit={onSubmit} className="info-column form-crear-columna">
            <h3 className="product-title">{titulo}</h3>

            {/* Dropzone */}
            <div 
                onDrop={onDrop} 
                onDragOver={(e) => e.preventDefault()}
                className="specs-card dropzone-card"
            >
                <input 
                    type="file" 
                    multiple 
                    accept="image/*" 
                    id="fileInput" 
                    style={{ display: 'none' }} 
                    onChange={(e) => onFilesAdded(e.target.files)} 
                />
                <label htmlFor="fileInput" className="dropzone-label">
                    <div className="dropzone-icon">+</div>
                    <strong>Agregar fotos</strong>
                    <p className="dropzone-text">Arrastrar / Soltar</p>
                    <small className="dropzone-subtext">{formData.fotos.length}/10 fotos (máx. 10)</small>
                </label>
            </div>

            {/* Precio */}
            <div className="specs-card">
                <label><strong>Precio ($):</strong></label>
                <input 
                    type="number" 
                    name="precio" 
                    value={formData.precio} 
                    onChange={onChange} 
                    className="search-bar full-width-input"
                    placeholder="Ej: 18500000" 
                    required 
                />
            </div>

            {/* Marca */}
            <div className="specs-card">
                <label><strong>Marca:</strong></label>
                <div className="radio-group-vertical">
                    {marcas.map((marca) => (
                        <label key={marca} className="radio-label">
                            <input 
                                type="radio" 
                                name="marca" 
                                value={marca} 
                                checked={formData.marca === marca} 
                                onChange={onChange} 
                            /> {marca}
                        </label>
                    ))}
                </div>
            </div>

            {/* Modelo y Versión */}
            {formData.marca && (
                <div className="specs-card">
                    <label><strong>Modelo:</strong></label>
                    <select name="modelo" value={formData.modelo} onChange={onChange} className="filter-select full-width-input" required>
                        <option value="">Seleccionar Modelo</option>
                        {modelos.map((mod) => (
                            <option key={mod} value={mod}>{mod}</option>
                        ))}
                    </select>

                    {formData.modelo && (
                        <div>
                            <label><strong>Versión:</strong></label>
                            <select name="version" value={formData.version} onChange={onChange} className="filter-select full-width-input" required>
                                <option value="">Seleccionar Versión</option>
                                {versiones.map((ver) => (
                                    <option key={ver} value={ver}>{ver}</option>
                                ))}
                            </select>
                        </div>
                    )}
                </div>
            )}

            {/* Estado */}
            <div className="specs-card">
                <label><strong>Estado:</strong></label>
                <div className="radio-group-vertical">
                    <label className="radio-label">
                        <input type="radio" name="estado" value="Sin detalles" checked={formData.estado === 'Sin detalles'} onChange={onChange} /> Sin detalles
                    </label>
                    <label className="radio-label">
                        <input type="radio" name="estado" value="Con detalles" checked={formData.estado === 'Con detalles'} onChange={onChange} /> Con detalles
                    </label>
                </div>
                {formData.estado === 'Con detalles' && (
                    <textarea 
                        name="descripcionDetalles" 
                        value={formData.descripcionDetalles} 
                        onChange={onChange} 
                        placeholder="Detalle obligatoriamente los defectos..." 
                        className="search-bar textarea-detalles"
                        required 
                    />
                )}
            </div>

            {/* Color */}
            <div className="specs-card">
                <label><strong>Color:</strong></label>
                <select name="color" value={formData.color} onChange={onChange} className="filter-select full-width-input" required>
                    <option value="">Desplegable de colores...</option>
                    {conActual(COLORES_BASE, formData.color).map(col => (
                        <option key={col} value={col}>{col}</option>
                    ))}
                </select>
            </div>

            {/* Papeles al día */}
            <div className="specs-card">
                <label><strong>Papeles al día:</strong></label>
                <div className="radio-group-horizontal">
                    <label className="radio-label">
                        <input type="radio" name="papelesAlDia" value="Si" checked={formData.papelesAlDia === 'Si'} onChange={onChange} /> Si
                    </label>
                    <label className="radio-label">
                        <input type="radio" name="papelesAlDia" value="No" checked={formData.papelesAlDia === 'No'} onChange={onChange} /> No
                    </label>
                </div>
                {formData.papelesAlDia === 'No' && (
                    <textarea 
                        name="papelesFaltantes" 
                        value={formData.papelesFaltantes} 
                        onChange={onChange} 
                        placeholder="Detalle documentación faltante..." 
                        className="search-bar textarea-detalles"
                        required 
                    />
                )}
            </div>

            {/* Año */}
            <div className="specs-card">
                <label><strong>Año:</strong></label>
                <input 
                    type="number" 
                    name="anio" 
                    value={formData.anio} 
                    onChange={onChange} 
                    className="search-bar full-width-input"
                    placeholder="Ej: 2019" 
                    min="1950"
                    max={new Date().getFullYear() + 1}
                />
            </div>

            {/* Kilometraje */}
            <div className="specs-card">
                <label><strong>Kilometraje:</strong></label>
                <input 
                    type="number" 
                    name="kilometraje" 
                    value={formData.kilometraje} 
                    onChange={onChange} 
                    className="search-bar full-width-input"
                    placeholder="Ej: 85000" 
                    required 
                />
            </div>

            {/* Transmisión y Combustible */}
            <div className="specs-card">
                <label><strong>Transmisión:</strong></label>
                <div className="radio-group-vertical">
                    {conActual(TRANSMISIONES, formData.transmision).map((tipo) => (
                        <label key={tipo} className="radio-label">
                            <input type="radio" name="transmision" value={tipo} checked={formData.transmision === tipo} onChange={onChange} /> {tipo}
                        </label>
                    ))}
                </div>

                <label><strong>Combustible:</strong></label>
                <div className="radio-group-vertical">
                    {conActual(COMBUSTIBLES, formData.combustible).map((tipo) => (
                        <label key={tipo} className="radio-label">
                            <input type="radio" name="combustible" value={tipo} checked={formData.combustible === tipo} onChange={onChange} /> {tipo}
                        </label>
                    ))}
                </div>
            </div>
        </form>
    );
}

export default FormularioCrearPublicacion;