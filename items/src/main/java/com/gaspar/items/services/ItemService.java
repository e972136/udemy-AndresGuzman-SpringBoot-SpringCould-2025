package com.gaspar.items.services;

import java.util.List;
import java.util.Optional;

import com.gaspar.items.models.Item;

public interface ItemService {
    List<Item> findAll();
    Optional<Item> findById(Long id, Integer qty);
}
