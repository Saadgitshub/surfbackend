package com.example.Surf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Restrict to API endpoints
                .allowedOrigins(
                    "http://localhost:3000", // Web frontend
                    "http://192.168.1.2:3000", // Web frontend on backend machine
                    "http://192.168.1.2:19006", // Expo dev server (adjust IP if phone is on different IP)
                    "http://10.0.2.2:8080" // Android emulator
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(false);
    }
}