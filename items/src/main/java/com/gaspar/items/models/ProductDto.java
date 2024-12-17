package com.gaspar.items.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProductDto(
    Long id,
    String name,
    BigDecimal price,
    LocalDate createAt
) {

}
