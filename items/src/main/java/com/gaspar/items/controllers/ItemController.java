package com.gaspar.items.controllers;

import static java.util.Objects.isNull;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.gaspar.items.models.Item;
import com.gaspar.items.services.ItemService;

@RestController
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> findAll(){
        return itemService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> byId(
        @PathVariable Long id
    ){
        Optional<Item> byId = itemService.findById(id);
        if(isNull(byId)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(byId.get());
    }
}
