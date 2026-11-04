import process from 'node:process'
import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// URL del backend de Spring Boot. Se puede cambiar con la variable de entorno BACKEND_URL.
// Se usa 127.0.0.1 y no "localhost": en Windows, Node puede resolver localhost a IPv6 (::1) y no encontrar el backend.
const BACKEND_URL = process.env.BACKEND_URL ?? 'http://127.0.0.1:8080'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    // En desarrollo, Vite reenvía al backend la API y las fotos subidas.
    // Así el navegador ve todo en el mismo origen (sin problemas de CORS).
    proxy: {
      '/api': BACKEND_URL,
      '/uploads': BACKEND_URL,
    },
  },
})
