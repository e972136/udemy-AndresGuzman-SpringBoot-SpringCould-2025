package com.gaspar.items.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.gaspar.items.models.Item;
import com.gaspar.items.models.ProductDto;

@Service
public class ItemsServiceWebClient implements ItemService{

    public ItemsServiceWebClient(Builder client) {
        this.client = client;
    }

    private final WebClient.Builder client;


    @Override
    public List<Item> findAll() {
        List<ProductDto> block = client.build()
        .get()
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToFlux(ProductDto.class)
        .collectList()
        .block();

        return block.stream()
        .map(p->new Item(p, 1))
        .toList();
    }

    @Override
    public Optional<Item> findById(Long id, Integer qty) {
        try {
            Map<String, Long> params = Map.of("id", id);
            ProductDto block = client.build()
            .get().uri("/{id}",params)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(ProductDto.class)
            .block();    
            return Optional.of(new Item(block,qty) );
        } catch (WebClientResponseException e) {
            return Optional.empty();
        }
      
    }

}
