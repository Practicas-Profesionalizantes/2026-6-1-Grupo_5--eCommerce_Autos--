import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';

function Header() {
    const navigate = useNavigate();
    const [isDarkMode, setIsDarkMode] = useState(false);

    // Cambiar tema global
    const toggleTheme = () => {
        setIsDarkMode(prevMode => {
            const newMode = !prevMode;
            if (newMode) {
                document.documentElement.setAttribute('data-theme', 'dark');
            } else {
                document.documentElement.removeAttribute('data-theme');
            }
            return newMode;
        });
    };

    return (
        <header className="header">
            <div className="logo">
                <Link to="/" style={{ textDecoration: "none", color: "inherit", fontWeight: "bold" }}>
                    CarFind
                </Link>
            </div>
            
            <input className="search-bar" type="text" placeholder="Buscar auto..." />
            
            <div className="actions">
                <button onClick={toggleTheme} className="icon-button" title="Cambiar Tema">
                    {isDarkMode ? "🌙" : "☀️"}
                </button>
                <button className="icon-button" title="Carrito">🛒</button>
                <button className="icon-button" title="Notificaciones">🔔</button>
                <button className="icon-button" title="Mensajes">💬</button>
                <button className="icon-button" title="Perfil">👤</button>
            </div>

            <div className="login">
                <button className="navButton" onClick={() => navigate("/login")}>Iniciar Sesión</button>
                <button className="navButton" onClick={() => navigate("/register")}>Registrarse</button>
            </div>
        </header>
    );
}

export default Header;