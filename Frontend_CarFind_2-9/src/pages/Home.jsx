import React, { useState } from "react";
import { useNavigate, Link } from 'react-router-dom';
import ProductCard from '../components/ProductCard';
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

    const publicacionesEjemplo = [1, 2, 3, 4, 5, 6, 7, 8].map(num => ({
        id: num,
        titulo: `Corolla 2017`,
        precio: 19235000,
        vendedor: `Concesionaria ${num}`
    }));

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
            
            {/* CONTENEDOR DE FILTROS EN LÍNEA */}
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
                <button 
                    className="clear-filters-btn" 
                    onClick={() => setFiltrosSeleccionados({marca: '', modelo: '', anio: '', kilometraje: '', precio: ''})}
                >
                    Limpiar Filtros
                </button>
            </div>
            
            {/* GRILLA DE AUTOS COMPLETA */}
            <main className="content-grid">
                {publicacionesEjemplo.map((pub) => (
                    <ProductCard key={pub.id} publicacion={pub} />
                ))}
            </main>
        </div>
    );
}

export default Home;