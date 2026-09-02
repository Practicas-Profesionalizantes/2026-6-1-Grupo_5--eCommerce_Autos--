import React from "react";
import { Routes, Route } from 'react-router-dom';
import Home from "./pages/Home"; // Creamos este componente
import Login from "./pages/Login";
import Register from "./pages/Register"
import Publicacion from './pages/Publicacion';


function App() {
    return (
        <>
        <Routes>
            <Route path="/" element={<Home />} />
            
            <Route path="/login" element={<Login />} />

            <Route path="/register" element={<Register />} />

            <Route path="/publicacion/:id" element={<Publicacion />} />

        </Routes>
        </>
    );
}

export default App;