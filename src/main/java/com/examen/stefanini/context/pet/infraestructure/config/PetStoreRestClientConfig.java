package com.examen.stefanini.context.pet.infraestructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class PetStoreRestClientConfig {

    @Bean
    public RestClient petStoreRestClient() {
        return RestClient.create("https://petstore.swagger.io/v2");
    }
}
