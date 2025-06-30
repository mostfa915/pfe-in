package com.example.pfev2.services;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Chemin absolu vers le répertoire "uploads" dans votre projet
        String uploadsDir = "file:/home/mostfa/IdeaProjects/p-f-e-v2/uploads/"; // Remplacez par le chemin absolu vers votre répertoire "uploads"

        // Configurez le mapping pour accéder aux fichiers via /uploads/**
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(uploadsDir);
    }
}
