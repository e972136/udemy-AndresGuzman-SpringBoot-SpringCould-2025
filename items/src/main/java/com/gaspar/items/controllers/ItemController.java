package com.gaspar.items.controllers;

import static java.util.Objects.isNull;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaspar.items.models.Item;
import com.gaspar.items.services.ItemService;

@RestController
public class ItemController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final ItemService itemService;

    public ItemController(@Qualifier("itemsServiceWebClient") ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> findAll(
        @RequestParam(required = false) String desde_gateway,
        @RequestHeader(required = false) String patito_request
    ){
        log.info("desde_gateway:"+desde_gateway);
        log.info("patito_request:"+patito_request);
        return itemService.findAll();
    }

    @GetMapping("/{id}/{qty}")
    public ResponseEntity<?> byId(
        @PathVariable Long id,
        @PathVariable Integer qty
    ){        
        Optional<Item> byId = itemService.findById(id,qty);
        if(byId.isEmpty()){
            //return ResponseEntity.notFound().build();
            return ResponseEntity.status(404).body(Map.of("message","Not Found"));
        }
        return ResponseEntity.ok(byId.get());
    }
}
