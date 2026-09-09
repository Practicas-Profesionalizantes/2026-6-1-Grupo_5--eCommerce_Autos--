import React from 'react';
import { MARCAS_MODELOS, COLORES_BASE } from '../utils/constantesAutos';

function FormularioCrearPublicacion({ formData, onChange, onFilesAdded, onDrop, onSubmit }) {
    return (
        <form onSubmit={onSubmit} className="info-column form-crear-columna">
            <h3 className="product-title">Artículo en venta</h3>

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
                    {Object.keys(MARCAS_MODELOS).map((marca) => (
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
                        {Object.keys(MARCAS_MODELOS[formData.marca]).map((mod) => (
                            <option key={mod} value={mod}>{mod}</option>
                        ))}
                    </select>

                    {formData.modelo && (
                        <div>
                            <label><strong>Versión:</strong></label>
                            <select name="version" value={formData.version} onChange={onChange} className="filter-select full-width-input" required>
                                <option value="">Seleccionar Versión</option>
                                {MARCAS_MODELOS[formData.marca][formData.modelo].map((ver) => (
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
                    {COLORES_BASE.map(col => (
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
                    <label className="radio-label">
                        <input type="radio" name="transmision" value="Manual" checked={formData.transmision === 'Manual'} onChange={onChange} /> Manual
                    </label>
                    <label className="radio-label">
                        <input type="radio" name="transmision" value="Automático" checked={formData.transmision === 'Automático'} onChange={onChange} /> Automático
                    </label>
                </div>

                <label><strong>Combustible:</strong></label>
                <div className="radio-group-vertical">
                    {['Nafta', 'Gasoil', 'Eléctrico'].map((tipo) => (
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