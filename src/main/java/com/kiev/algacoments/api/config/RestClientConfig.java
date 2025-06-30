package com.kiev.algacoments.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import java.time.Duration;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient moderationRestClient(@Value("${moderation.service.base-url}") String baseUrl) {
        return RestClient.builder()
                .baseUrl(baseUrl)
                .requestFactory(new org.springframework.http.client.SimpleClientHttpRequestFactory())
                .requestInterceptors(interceptors -> {
                    // Configura o timeout aqui. O SimpleClientHttpRequestFactory suporta isso.
                    interceptors.add((request, body, execution) -> {
                        // O SimpleClientHttpRequestFactory não tem um timeout por request fácil de configurar aqui
                        // A melhor abordagem é configurar a factory diretamente.
                        return execution.execute(request, body);
                    });
                })
                .build();
    }
    
    // Abordagem Alternativa e Melhor para Timeout
    @Bean("moderationRestClientWithTimeout")
    public RestClient restClientWithTimeout(@Value("${moderation.service.base-url}") String baseUrl) {
        org.springframework.http.client.SimpleClientHttpRequestFactory requestFactory = new org.springframework.http.client.SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(Duration.ofSeconds(5));
        requestFactory.setReadTimeout(Duration.ofSeconds(5)); // Timeout para ler a resposta

        return RestClient.builder()
                .baseUrl(baseUrl)
                .requestFactory(requestFactory)
                .build();
    }
}