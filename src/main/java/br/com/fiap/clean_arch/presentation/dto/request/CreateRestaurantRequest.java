package br.com.fiap.clean_arch.presentation.dto.request;

import java.util.Set;

import br.com.fiap.clean_arch.presentation.dto.OpeningHoursDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateRestaurantRequest(
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    String name,
    
    @NotBlank(message = "Address is required")
    String address,
    
    @NotBlank(message = "Cuisine type is required")
    String cuisineType,

    @NotNull(message = "User IDs are required")
    Set<Long> userIds,

    @NotNull(message = "Opening hours are required")
    Set<OpeningHoursDTO> openingHours
) {}
