package com.gaspar.items.models;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private ProductDto product;
    private int quantity;

    public BigDecimal getTotal(){
        return product.price().multiply(BigDecimal.valueOf(quantity));
    }
}
