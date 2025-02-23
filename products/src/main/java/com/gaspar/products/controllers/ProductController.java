package com.gaspar.products.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.gaspar.products.entities.Product;
import com.gaspar.products.services.ProductService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> list() {
        return productService.findAll();
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<Product> detail(@PathVariable Long id){
        Optional<Product> byId = productService.findById(id);
        if(byId.isPresent()){
            return ResponseEntity.ok(byId.get()) ;
        }
        return ResponseEntity.notFound().build(); 
    }
}
