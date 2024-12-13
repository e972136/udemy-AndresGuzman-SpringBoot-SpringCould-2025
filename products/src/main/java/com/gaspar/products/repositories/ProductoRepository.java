package com.gaspar.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gaspar.products.entities.Product;

public interface ProductoRepository extends JpaRepository<Product,Long>{

}
