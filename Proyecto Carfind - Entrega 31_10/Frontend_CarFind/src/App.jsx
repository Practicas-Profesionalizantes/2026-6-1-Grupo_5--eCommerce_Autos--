import React from "react";
import { Routes, Route, Navigate } from 'react-router-dom';
import Layout from "./components/Layout";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Register from "./pages/Register"
import Publicacion from './pages/Publicacion';
import CrearPublicacion from "./pages/CrearPublicacion";
import Wishlist from "./pages/Wishlist";
import Profile from "./pages/Profile";
import Configuracion from "./pages/Configuracion";
import ReporteGeneral from "./pages/ReporteGeneral";
import ReportePublicacion from "./pages/ReportePublicacion";
import PerfilAjeno from "./pages/PerfilAjeno";
import Mensajeria from "./pages/Mensajeria";
import Terminos from "./pages/Terminos";
import AdminLayout from "./pages/admin/AdminLayout";
import EstadisticasAdmin from "./pages/admin/EstadisticasAdmin";
import ReportesAdmin from "./pages/admin/ReportesAdmin";
import DatosPersonales from "./pages/configuracion/DatosPersonales";
import Apariencia from "./pages/configuracion/Apariencia";
import Accesibilidad from "./pages/configuracion/Accesibilidad";
import Notificaciones from "./pages/configuracion/Notificaciones";
import Idioma from "./pages/configuracion/Idioma";
import Proximamente from "./pages/configuracion/Proximamente";


function App() {
    return (
        <Routes>
            <Route element={<Layout />}>
                <Route path="/" element={<Home />} />

                <Route path="/login" element={<Login />} />

                <Route path="/register" element={<Register />} />

                <Route path="/publicacion/:id" element={<Publicacion />} />

                <Route path="/crear-publicacion" element={<CrearPublicacion />} />

                <Route path="/publicacion/:id/editar" element={<CrearPublicacion />} />

                <Route path="/wishlist" element={<Wishlist />} />

                <Route path="/perfil" element={<Profile />} />

                <Route path="/usuario/:id" element={<PerfilAjeno />} />

                <Route path="/reportes" element={<ReporteGeneral />} />

                <Route path="/publicacion/:id/reportar" element={<ReportePublicacion />} />

                <Route path="/terminos" element={<Terminos />} />

                <Route path="/mensajes" element={<Mensajeria />} />
                <Route path="/mensajes/:conversacionId" element={<Mensajeria />} />

                {/* Panel de administración: el backend decide si el usuario es administrador */}
                <Route path="/admin" element={<AdminLayout />}>
                    <Route index element={<Navigate to="estadisticas" replace />} />
                    <Route path="estadisticas" element={<EstadisticasAdmin />} />
                    <Route path="reportes" element={<ReportesAdmin />} />
                </Route>

                <Route path="/configuracion" element={<Configuracion />}>
                    <Route index element={<Navigate to="datos-personales" replace />} />
                    <Route path="datos-personales" element={<DatosPersonales />} />
                    <Route path="apariencia" element={<Apariencia />} />
                    <Route path="accesibilidad" element={<Accesibilidad />} />
                    <Route path="notificaciones" element={<Notificaciones />} />
                    <Route path="idioma" element={<Idioma />} />
                    {/* Secciones del menú que todavía no tienen boceto */}
                    <Route path="chats" element={<Proximamente titulo="Chats" />} />
                    <Route path="ayuda" element={<Proximamente titulo="Ayuda y comentarios" />} />
                </Route>
            </Route>
        </Routes>
    );
}

export default App;