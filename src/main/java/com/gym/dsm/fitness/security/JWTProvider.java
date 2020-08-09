package com.gym.dsm.fitness.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JWTProvider {
    public String generateAccessToken() {
        return "eya.b.c";
    }
}
