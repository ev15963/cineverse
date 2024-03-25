package com.example.cineverse.client.kobis;

import org.springframework.beans.factory.annotation.Value;

public class MovieDataFeignClientConfiguration {
    private final String secretKey;

    public MovieDataFeignClientConfiguration(
            @Value("${spring.data.openapi.key}") final String secretKey) {
        this.secretKey = secretKey;
    }
}
