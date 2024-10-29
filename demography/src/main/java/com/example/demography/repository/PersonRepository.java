package com.example.demography.repository;

import com.example.demography.execption.ResourceNotFoundException;
import org.springframework.http.HttpStatusCode;
import reactor.core.publisher.Flux;
import ru.lab2.library.Person;
import ru.lab2.library.Color;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PersonRepository {
    private WebClient webClient;

    @Autowired
    public PersonRepository(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://localhost:8598/person-service-1.0-SNAPSHOT").build();
    }

    public Mono<List<Person>> getByAll() {
        return webClient.get().uri("/persons/filter").retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    return Mono.error(new ResourceNotFoundException("Not found item"));
                } )
                .bodyToFlux(Person.class).collectList();
    }

    public Mono<List<Person>> getByEyeColor(Color eyeColor) {
        return webClient.get().uri(uriBuilder -> uriBuilder
                .path("/persons/filter")
                .queryParam("eye-color", eyeColor)
                .build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    return Mono.error(new ResourceNotFoundException("Not found item"));
                } )
                .bodyToFlux(Person.class).collectList();
    }
}
