package com.gaspar.products.services;

import java.util.List;
import java.util.Optional;

import com.gaspar.products.entities.Product;

public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(Long id);
} 
