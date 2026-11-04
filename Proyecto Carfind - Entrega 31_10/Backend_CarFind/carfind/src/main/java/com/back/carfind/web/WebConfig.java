package com.back.carfind.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración de la API que consume el frontend (rutas /api/web/**).
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Normalmente el frontend llama a la API a través del proxy de Vite (mismo origen).
     * Esto permite además llamarla directo (VITE_API_URL=http://localhost:8080/api/web) desde un frontend local.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/web/**")
                .allowedOriginPatterns("http://localhost:*", "http://127.0.0.1:*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .exposedHeaders("Content-Disposition");
        registry.addMapping("/uploads/**").allowedOriginPatterns("http://localhost:*", "http://127.0.0.1:*");
    }
}
