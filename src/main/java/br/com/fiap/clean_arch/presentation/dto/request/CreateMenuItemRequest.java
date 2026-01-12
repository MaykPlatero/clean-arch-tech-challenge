package br.com.fiap.clean_arch.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record CreateMenuItemRequest(
    @NotNull(message = "Restaurant ID is required")
    Long restaurantId,

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    String name,

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    BigDecimal price,

    @NotNull(message = "Delivery item flag is required")
    Boolean deliveryItem,

    String photoUrl
) {}

