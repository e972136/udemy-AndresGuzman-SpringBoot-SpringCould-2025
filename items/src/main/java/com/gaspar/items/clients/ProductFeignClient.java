package com.gaspar.items.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gaspar.items.models.ProductDto;

@FeignClient(name = "products")
public interface ProductFeignClient {
    @GetMapping("/api/products")
    List<ProductDto> findAll();

    @GetMapping("/api/products/{id}")
    ProductDto getById(@PathVariable Long id);
}
