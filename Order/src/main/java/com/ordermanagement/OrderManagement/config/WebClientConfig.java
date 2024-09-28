package com.ordermanagement.OrderManagement.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
//    public WebClient webClient() {
//        return WebClient.builder().build();
//    }

    @Bean
    public WebClient inventoryWebClient() {
        return webClientBuilder().baseUrl("http://apigateway/api/v1").build();
    }
//    public WebClient inventoryWebClient() {
//        return WebClient.builder().baseUrl("http://localhost:8080/api/v1").build();
//    }

    @Bean
    public WebClient productWebClient() {
        return webClientBuilder().baseUrl("http://apigateway/api/v1").build();
    }
//    public WebClient productWebClient() {
//        return WebClient.builder().baseUrl("http://localhost:8082/api/v1").build();
//    }
}
