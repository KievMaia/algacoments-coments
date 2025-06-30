package com.kiev.algacoments.api.config;

import com.kiev.algacoments.api.client.IModerationClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient moderationRestClient() {
        return RestClient.builder()
                .baseUrl("http://localhost:8081") // URL base do ModerationService
                .requestFactory(new SimpleClientHttpRequestFactory()) // Pode adicionar timeouts aqui
                .build();
    }

    @Bean
    public IModerationClient moderationClient(RestClient moderationRestClient) {
        RestClientAdapter adapter = RestClientAdapter.create(moderationRestClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(IModerationClient.class);
    }
}