package com.gaspar.items;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${config.webclient.baseurl.products}")
    String baseUrl;

    @Bean
    @LoadBalanced
    WebClient.Builder webClient(){
        return WebClient
        .builder()
        .baseUrl(baseUrl);
    }
}
