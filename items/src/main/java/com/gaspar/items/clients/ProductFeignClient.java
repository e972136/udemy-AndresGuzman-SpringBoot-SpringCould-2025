package com.gaspar.items.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gaspar.items.models.ProductDto;

@FeignClient(url = "localhost:9090/api/products")
public interface ProductFeignClient {
    @GetMapping
    List<ProductDto> findAll();

    @GetMapping("/{id}")
    ProductDto getById(@PathVariable Long id);
}
