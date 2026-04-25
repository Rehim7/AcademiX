package com.example.academix.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Bütün endpoint-lərə icazə ver
                .allowedOrigins("http://localhost:5173") // Sənin frontend portun
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // İcazə verilən metodlar
                .allowedHeaders("*") // Bütün header-lərə icazə ver (Authorization və s.)
                .exposedHeaders("Authorization", "Content-Type") // Frontendin Authorization header-ini oxuya bilməsi üçün
                .allowCredentials(true); // Tokenlərin ötürülməsinə icazə ver
    }
}