import React, { useState } from "react";
import { useNavigate } from 'react-router-dom';
import { Link } from 'react-router-dom';
import corollaImg from '../assets/images/CorollaEjemplo.jpg'; // Ajustá la ruta si es necesario
import '../assets/styles/App.css';
import '../assets/styles/variables.css';

function Home() {
    const [isDarkMode, setIsDarkMode] = useState(false);
    const navigate = useNavigate();

    const [filtrosSeleccionados, setFiltrosSeleccionados] = useState({
        marca: '',
        modelo: '',
        anio: '',
        kilometraje: '',
        precio: ''
    });

    const handleFiltroChange = (e) => {
        const { name, value } = e.target;
        setFiltrosSeleccionados({
            ...filtrosSeleccionados, [name]: value
        });
    };

    const toggleTheme = () => {
        setIsDarkMode(!isDarkMode);
    };

    return (
        <div className="app-wrapper" data-theme={isDarkMode ? 'dark' : 'light'}>
            <header className="header">
                <div className="logo">
                    <Link title="Volver al inicio" to="/" style={{ textDecoration: 'none', color: 'inherit', fontWeight: 'bold' }}> 
                    CarFind
                    </Link>
                </div>
                <input className="search-bar" type="text" placeholder="Buscar auto..." />
                <div className="actions">
                    <button onClick={toggleTheme} className="icon-button">
                        {isDarkMode ? '🌙' : '☀️'}
                    </button>  
                    <button className="icon-button">🛒</button>
                    <button className="icon-button">🔔</button>
                    <button className="icon-button">💬</button>
                    <button className="icon-button">👤</button>
                </div>
                <div className="login">
                    <button className="navButton" onClick={() => navigate("/login")}>Iniciar Sesión</button>
                    <button className="navButton" onClick={() => navigate("/register")}>Registrarse</button>
                </div>
                
            </header>
            
            <div className="main-conteiner">
                <aside className="sidebar">
                    <div className="filter-group">
                        {['marca', 'modelo', 'año', 'kilometraje', 'precio'].map((filtro) => (
                            <div key={filtro} className="filter-item">
                                <label>{filtro.charAt(0).toUpperCase() + filtro.slice(1)}</label>
                                <select 
                                    name={filtro} 
                                    onChange={handleFiltroChange}
                                    value={filtrosSeleccionados[filtro] || ''}
                                >
                                    <option value="">Todos</option>
                                    <option value="ejemplo1">Ejemplo 1</option>
                                    <option value="ejemplo2">Ejemplo 2</option>
                                </select>
                            </div>
                        ))}
                        <button className="clear-filters-btn" onClick={() => setFiltrosSeleccionados({marca: '', modelo: '', anio: '', kilometraje: '', precio: ''})}>
                            Limpiar Filtros
                        </button>
                    </div>
                </aside>
                
                <main className="content-grid">
                    {[1, 2, 3, 4, 5, 6, 7, 8].map((n) => (
                        <div key={n} className="product-card">
                            <div className="card-seller"></div>
                            <div className="card-image-placeholder"><img src={corollaImg} alt="" /></div>
                            <div className="card-info">
                                <span>Corolla 2017</span>
                                <strong>$19.235.000</strong>
                            </div>
                        </div>
                    ))}
                </main>
            </div>
        </div>
    );
}

export default Home;