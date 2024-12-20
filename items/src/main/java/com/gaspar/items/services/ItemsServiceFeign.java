package com.gaspar.items.services;

import static java.util.Objects.isNull;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gaspar.items.clients.ProductFeignClient;
import com.gaspar.items.models.Item;
import com.gaspar.items.models.ProductDto;

import feign.FeignException;

//@Service
public class ItemsServiceFeign implements ItemService{

    private final ProductFeignClient feignClient;

    public ItemsServiceFeign(ProductFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    @Override
    public List<Item> findAll() {
        return feignClient.findAll()
            .stream()
            .map(p->new Item(p, 1))
            .toList();
    }

    @Override
    public Optional<Item> findById(Long id, Integer qty) {

        try {
             ProductDto byId = feignClient.getById(id); 
             return Optional.of(
            new Item(byId,qty) 
            ) ;
        } catch (FeignException e) {
            return Optional.empty();
        }       
    }

}
