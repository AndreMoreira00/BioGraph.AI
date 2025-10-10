package com.henrique.biograph.Configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {
    
    @Value("${python.api.url}")
    private String pythonApiUrl;

    @Bean
    public WebClient webClient() {
        System.out.println("pythonApiUrl = " + pythonApiUrl);
        return WebClient.builder()
                .baseUrl(pythonApiUrl)
                .build();

    }
}
