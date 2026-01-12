package br.com.fiap.clean_arch.presentation.dto.response;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record MenuItemResponse(
    Long id,
    Long restaurantId,
    String name,
    BigDecimal price,
    boolean deliveryItem,
    String photoUrl,
    ZonedDateTime lastUpdate
) {}
