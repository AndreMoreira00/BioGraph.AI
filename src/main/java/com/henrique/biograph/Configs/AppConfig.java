package com.henrique.biograph.Configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {
    
    @Value("${python.api.url}")
    private String pythonApiUrl;

    @Bean
    public WebClient webClient() {
        final int bufferSize = 16 * 1024 * 1024;

        final ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs
                        .defaultCodecs()
                        .maxInMemorySize(bufferSize))
                .build();

        System.out.println("pythonApiUrl = " + pythonApiUrl);
        return WebClient.builder()
                .baseUrl(pythonApiUrl)
                .exchangeStrategies(strategies)
                .build();

    }
}
