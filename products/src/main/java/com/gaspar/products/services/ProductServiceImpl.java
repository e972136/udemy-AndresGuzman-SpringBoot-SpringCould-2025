package com.gaspar.products.services;

import java.util.List;
import java.util.Optional;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.gaspar.products.entities.Product;
import com.gaspar.products.repositories.ProductoRepository;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductoRepository productoRepository;
    private final Environment environment;

    public ProductServiceImpl(ProductoRepository productoRepository, Environment environment) {
        this.productoRepository = productoRepository;
        this.environment = environment;
    }

    @Override
    public List<Product> findAll() {
        return productoRepository
            .findAll()
            .stream()
            .map(p->{
                p.setPort(environment.getProperty("local.server.port"));
                return p;
            }).toList();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productoRepository.findById(id).map(p->{
            p.setPort(environment.getProperty("local.server.port"));
            return p;
        });
    }

}
