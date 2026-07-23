package com.seyran.scda.ai.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;


@Getter
@Configuration
public class AIConfig {


    @Value("${groq.api.key}")
    private String apiKey;


    @Value("${groq.api.url}")
    private String apiUrl;


    @Value("${groq.model}")
    private String model;



    @Bean
    public RestTemplate restTemplate(){

        return new RestTemplate();

    }

    @Bean
    public RestClient restClient() {

        return RestClient.builder()
                .build();
    }

}