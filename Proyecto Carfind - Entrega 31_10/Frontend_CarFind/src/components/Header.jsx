import React, { useState, useSyncExternalStore } from 'react';
import { useNavigate, useSearchParams } from 'react-router-dom';
import Logo from './Logo';
import Icono from './Icono';
import useAuth from '../hooks/useAuth';
import { esTemaOscuro, aplicarTema, suscribirTema } from '../utils/tema';

function Header() {
    const navigate = useNavigate();
    const [searchParams] = useSearchParams();
    const { usuario, cargando, logout } = useAuth();
    const [busqueda, setBusqueda] = useState(searchParams.get('q') ?? '');
    // El tema ya viene aplicado desde main.jsx. Se lee con suscripción para que el ícono
    // también se actualice cuando el tema cambia desde Configuración > Apariencia o desde el sistema.
    const isDarkMode = useSyncExternalStore(suscribirTema, esTemaOscuro);

    const toggleTheme = () => aplicarTema(!isDarkMode);

    // La búsqueda se resuelve en el inicio, que lee el parámetro ?q=
    const buscar = (e) => {
        e.preventDefault();
        const texto = busqueda.trim();
        navigate(texto ? `/?q=${encodeURIComponent(texto)}` : '/');
    };

    const cerrarSesion = async () => {
        await logout();
        navigate('/');
    };

    const accesos = [
        { titulo: 'Publicar un auto', icono: 'mas', ruta: '/crear-publicacion' },
        { titulo: 'Lista de deseos', icono: 'corazon', ruta: '/wishlist' },
        { titulo: 'Notificaciones', icono: 'campana' },
        { titulo: 'Mensajes', icono: 'mensaje', ruta: '/mensajes' },
        { titulo: 'Perfil', icono: 'usuario', ruta: '/perfil' },
    ];

    return (
        <header className="header">
            <Logo />
            
            <form className="header-busqueda" role="search" onSubmit={buscar}>
                <Icono nombre="buscar" tamano={18} className="header-busqueda-icono" />
                <input
                    className="search-bar"
                    type="search"
                    placeholder="Buscar marca, modelo o año..."
                    aria-label="Buscar auto"
                    value={busqueda}
                    onChange={(e) => setBusqueda(e.target.value)}
                />
            </form>
            
            <nav className="actions" aria-label="Accesos rápidos">
                <button onClick={toggleTheme} className="icon-button" title={isDarkMode ? 'Usar tema claro' : 'Usar tema oscuro'} aria-label="Cambiar tema">
                    <Icono nombre={isDarkMode ? 'sol' : 'luna'} />
                </button>
                {accesos.map(({ titulo, icono, ruta }) => (
                    <button
                        key={titulo}
                        className="icon-button"
                        title={titulo}
                        aria-label={titulo}
                        onClick={ruta ? () => navigate(ruta) : undefined}
                    >
                        <Icono nombre={icono} />
                    </button>
                ))}
                <button className="icon-button" title="Configuración" aria-label="Configuración" onClick={() => navigate("/configuracion")}>
                    <Icono nombre="config" />
                </button>
            </nav>

            <div className="login">
                {usuario ? (
                    <>
                        <span className="header-saludo">
                            <span className="header-avatar" aria-hidden="true">{usuario.nombre.charAt(0).toUpperCase()}</span>
                            <span className="header-saludo-texto">Hola, {usuario.nombre}</span>
                        </span>
                        {usuario.esAdmin && (
                            <button className="navButton navButton-secundario" onClick={() => navigate("/admin")}>
                                <Icono nombre="escudo" tamano={16} /> Administración
                            </button>
                        )}
                        <button className="navButton navButton-fantasma" onClick={cerrarSesion} title="Cerrar sesión">
                            <Icono nombre="salir" tamano={16} /> <span className="header-texto-opcional">Cerrar sesión</span>
                        </button>
                    </>
                ) : !cargando && (
                    <>
                        <button className="navButton navButton-secundario" onClick={() => navigate("/login")}>Iniciar sesión</button>
                        <button className="navButton" onClick={() => navigate("/register")}>Registrarse</button>
                    </>
                )}
            </div>
        </header>
    );
}

export default Header;
