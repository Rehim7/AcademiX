package com.example.academix.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Map;

@Component
public class JwtUtil {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Map<String, Object> extractAllClaims(String token) {
        try {
            token = removeBearer(token);
            String[] parts = token.split("\\.");
            if (parts.length < 2) {
                throw new IllegalArgumentException("Yanlış JWT token formatı");
            }
            String payload = new String(Base64.getUrlDecoder().decode(parts[1]));
            return objectMapper.readValue(payload, new TypeReference<>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("JWT token parse edilə bilmədi: " + e.getMessage(), e);
        }
    }

    public String extractEmail(String token) {
        return (String) extractAllClaims(token).get("sub");
    }

    public Long extractId(String token) {
        Object id = extractAllClaims(token).get("id");
        if (id instanceof Number) {
            return ((Number) id).longValue();
        }
        return null;
    }

    public String extractName(String token) {
        return (String) extractAllClaims(token).get("name");
    }

    public String extractSurName(String token) {
        return (String) extractAllClaims(token).get("surName");
    }

    public String extractRole(String token) {
        return (String) extractAllClaims(token).get("role");
    }

    public boolean isTokenExpired(String token) {
        Object exp = extractAllClaims(token).get("exp");
        if (exp instanceof Number) {
            return ((Number) exp).longValue() * 1000 < System.currentTimeMillis();
        }
        return true;
    }

    private String removeBearer(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return token;
    }
}
