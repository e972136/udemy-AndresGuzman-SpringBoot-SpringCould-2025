package com.gaspar.products.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gaspar.products.entities.Product;
import com.gaspar.products.repositories.ProductoRepository;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductoRepository productoRepository;

    public ProductServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Product> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productoRepository.findById(id);
    }

}
