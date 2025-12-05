package br.com.fiap.clean_arch.presentation.dto;

public record RestaurantResponse(
    Long id,
    String name,
    String address,
    String cuisineType
) {}
