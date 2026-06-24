import React from "react";
import { Routes, Route } from 'react-router-dom';
import Home from "./pages/Home"; // Creamos este componente
import Login from "./pages/Login";
import Register from "./pages/Register"

function App() {
    return (
        <>
        <Routes>
            {/* Al entrar a la web (ruta raíz), muestra el Home */}
            <Route path="/" element={<Home />} />
            
            {/* Al hacer clic en Login, muestra el Login */}
            <Route path="/login" element={<Login />} />

            <Route path="/register" element={<Register />} />
        </Routes>
        </>
    );
}

export default App;