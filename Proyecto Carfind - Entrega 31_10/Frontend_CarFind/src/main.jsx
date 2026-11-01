import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './assets/styles/variables.css'
import './assets/styles/preferencias.css'
// App.css se importa acá (y no solo desde las páginas) para fijar el orden en el build:
// tema-carfind.css tiene que quedar después para pisar sus reglas.
import './assets/styles/App.css'
import './assets/styles/tema-carfind.css'
import { BrowserRouter } from 'react-router-dom';
import App from './App.jsx'
import { AuthProvider } from './context/AuthContext'
import { aplicarTemaGuardado } from './utils/tema'
import { aplicarPreferenciasGuardadas } from './utils/preferenciasUI'

aplicarTemaGuardado()
aplicarPreferenciasGuardadas()

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <BrowserRouter>
      <AuthProvider>
        <App />
      </AuthProvider>
    </BrowserRouter>
  </StrictMode>
)