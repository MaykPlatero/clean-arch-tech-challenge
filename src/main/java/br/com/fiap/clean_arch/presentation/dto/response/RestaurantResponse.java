package br.com.fiap.clean_arch.presentation.dto.response;

import br.com.fiap.clean_arch.presentation.dto.OpeningHoursDTO;
import br.com.fiap.clean_arch.presentation.dto.UserDTO;

import java.util.Set;

public record RestaurantResponse(
    Long id,
    String name,
    String address,
    String cuisineType,
    Set<OpeningHoursDTO> openingHours,
    Set<UserDTO> owners
) {}
